package com.vmware.bugger.controller;

import com.vmware.bugger.model.*;
import com.vmware.bugger.service.GitBlamerService;
import com.vmware.bugger.service.MailUtil;
import com.vmware.bugger.model.Bug;
import com.vmware.bugger.service.BugRepository;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
public class BuggerController {

    @Autowired
    BugRepository bugRepository;

    private static final Logger logger = LoggerFactory.getLogger(BuggerController.class);
    @Autowired
    private GitBlamerService gitBlamerService;
    private HashSet<String> exceptionCache = new HashSet<>();

    @RequestMapping(method = RequestMethod.GET, value = "/reset")
    String reset() {
        exceptionCache  = new HashSet<>();
        return "Reset";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/webhook", consumes = { "application/json" })
    String post(@RequestBody LogRequest logRequest) throws GitAPIException {
        String pattern = "at com\\.vmware\\.itfm\\.cloud.*\\((.*\\.java):(\\d*)\\).*";

        Pattern r = Pattern.compile(pattern);
        for (Message message : logRequest.getMessages()) {
            String stackTrace = message.getText();
            String hostName = "localhost";
            for (Field field : message.getFields()) {
                if(field.getName().equals("hostname")){
                    hostName=field.getContent();
                    break;
                }
            }

            if (exceptionCache.contains(stackTraceSubString(stackTrace))) {
                continue;
            }
            exceptionCache.add(stackTraceSubString(stackTrace));
            Set<String> clzzs = new HashSet<>();
            for (String line : stackTrace.split("\n")) {
                Matcher m = r.matcher(line);
                if (m.find()) {
                    clzzs.add(m.group(1));
                }
            }
            Bug bug = new Bug();
            List<Culprit> culprits = gitBlamerService.blame(new ErrorStack(clzzs));
            for (Culprit culprit : culprits) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bugger found the following events on host matching the criteria for alert ").append(logRequest.getAlertName())
                        .append("Host:").append(hostName)
                        .append("\n\n")
                        .append("Commit:").append(culprit.getFullMessage())
                        .append("\n")
                        .append(culprit.getCommit())
                        .append("\n\n")
                        .append(stackTrace);

                sendEmail(culprit, sb.toString());
                bug.addBlamers(culprit.getName());
                bug.setCommitMessage(bug.getCommitMessage() + culprit.getFullMessage() + "\n");
            }
            bug.setDescription(stackTrace.split("\n")[0]);
            bugRepository.addBugs(bug);
        }
        return "Processed events";
    }

    private String stackTraceSubString(String stackTrace) {
        return stackTrace.substring(stackTrace.indexOf("]"), stackTrace.indexOf("]") + 50);
    }

    public boolean sendEmail(Culprit culprit, String message) {
        MailMessage mailMessage = new MailMessage("gabi@vmware.com", message);
        List<MailMessage> mailMessages = new ArrayList<>();
        mailMessages.add(mailMessage);
        MailUtil mailUtil = new MailUtil();
        mailUtil.SendMail(mailMessages);
        return true;
    }
}

