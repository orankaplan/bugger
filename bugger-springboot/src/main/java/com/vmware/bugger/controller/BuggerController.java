package com.vmware.bugger.controller;

import com.vmware.bugger.model.Culprit;
import com.vmware.bugger.model.ErrorStack;
import com.vmware.bugger.model.LogRequest;
import com.vmware.bugger.model.Message;
import com.vmware.bugger.modle.MailMessage;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@EnableAutoConfiguration
public class BuggerController {

    private static final Logger logger = LoggerFactory.getLogger(BuggerController.class);
    @Autowired
    private GitBlamerService gitBlamerService;

    @RequestMapping(method = RequestMethod.GET)
    List<Culprit>  get() {
        //if(sendEmail()) System.out.println("Finish sending mails");
        List<Culprit> culprits = null;
        try {
            final ErrorStack errorStack = new ErrorStack();
            errorStack.setClassNames(Arrays.asList("ErrorDTO.java", "hq-plugin.xml"));
            culprits = gitBlamerService.blame(errorStack);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return culprits;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/webhook", consumes = {"application/json"})
    String post(@RequestBody LogRequest logRequest) throws GitAPIException {
        String pattern = "\\.*com.vmware.*\\((.*?\\.java\\:\\d*)\\)";

        Pattern r = Pattern.compile(pattern);
        for (Message message : logRequest.getMessages()) {
            String stackTrace = message.getText();
            List<String> clzzs = new ArrayList<String>();
            for (String line : stackTrace.split("\n")) {
                Matcher m = r.matcher(line);
                if(m.find()){
                    clzzs.add(m.group());
                }
            }
            List<Culprit> blame = gitBlamerService.blame(new ErrorStack(clzzs));

        }

        return "Hello World! post";
    }

    public boolean sendEmail(){
        String messageBody = "Bug Description: blablablablablablablablablablablablablablablablablablablablablablablablabla";
        MailMessage mailMessage = new MailMessage("shyotam1@gmail.com",messageBody);
        List<MailMessage> mailMessages = new ArrayList<>();
        mailMessages.add(mailMessage);
        MailUtil mailUtil= new MailUtil();
        mailUtil.SendMail(mailMessages);
        return true;
    }
}


