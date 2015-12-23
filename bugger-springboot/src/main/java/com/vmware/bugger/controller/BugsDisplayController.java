package com.vmware.bugger.controller;

import com.vmware.bugger.model.Bug;
import com.vmware.bugger.service.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BugsDisplayController {
    @Autowired
    BugRepository bugsRepo;

    @MessageMapping("/hello")
    @SendTo("/topic/bugs")
    public List<Bug> bugs() throws Exception {
        return bugsRepo.getBugs();
    }

}
