package com.vmware.bugger.controller;

import com.vmware.bugger.model.Culprit;
import com.vmware.bugger.model.ErrorStack;
import com.vmware.bugger.model.LogRequest;
import com.vmware.bugger.service.GitBlamerService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class BuggerController {

    private static final Logger logger = LoggerFactory.getLogger(BuggerController.class);
    @Autowired
    private GitBlamerService gitBlamerService;

    @RequestMapping(method = RequestMethod.GET)
    List<Culprit> get() {
        List<Culprit> culprits = null;
        try {
            culprits = gitBlamerService.blame(new ErrorStack());
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return culprits;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/webhook", consumes = {"application/json"})
    String post(@RequestBody LogRequest body) {
        logger.info(body.getAlertName());

        return "Hello World! post";
    }
}


