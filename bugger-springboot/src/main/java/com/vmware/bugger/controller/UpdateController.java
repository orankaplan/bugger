package com.vmware.bugger.controller;

import com.vmware.bugger.model.Bug;
import com.vmware.bugger.service.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by gabi on 12/23/2015.
 */
@Controller
public class UpdateController {

    @Autowired
    BugRepository bugsRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void updateBugs(List<Bug> bugs){
        bugsRepo.setBugs(bugs);
    }

}
