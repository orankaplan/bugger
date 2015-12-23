package com.vmware.bugger.controller;

import com.vmware.bugger.model.*;
import com.vmware.bugger.service.GitBlamerService;
import com.vmware.bugger.service.MailUtil;
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

    private static final Logger logger = LoggerFactory.getLogger(BuggerController.class);
    @Autowired
    private GitBlamerService gitBlamerService;
    private HashSet<String> exceptionCache = new HashSet<>();

    @RequestMapping(method = RequestMethod.GET)
    List<Culprit> get() {
        List<Culprit> culprits = null;
        try {
            final ErrorStack errorStack = new ErrorStack();
            errorStack.setClassNames(new HashSet<>(Arrays.asList("IisDetector.java", "hq-plugin.xml")));
            culprits = gitBlamerService.blame(errorStack);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return culprits;
    }

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
            List<Culprit> culprits = gitBlamerService.blame(new ErrorStack(clzzs));
            for (Culprit culprit : culprits.stream().filter(c -> c.getEmail().equals("gabi@vmware.com")).collect(Collectors.toList())) {
                StringBuilder sb = new StringBuilder();
                sb.append(culprit.getFullMessage()).append("\n\n")
                        .append(culprit.getCommit()).append("\n\n").append(stackTrace);

                sendEmail(culprit, sb.toString());
            }
        }

        return "Hello World! post";
    }

    private String stackTraceSubString(String stackTrace) {
        return stackTrace.substring(stackTrace.indexOf("]"), stackTrace.indexOf("]") + 50);
    }

    public boolean sendEmail(Culprit culprit, String message) {
        MailMessage mailMessage = new MailMessage("okaplan@vmware.com", message);
        List<MailMessage> mailMessages = new ArrayList<>();
        mailMessages.add(mailMessage);
        MailUtil mailUtil = new MailUtil();
        mailUtil.SendMail(mailMessages);
        return true;
    }
}

