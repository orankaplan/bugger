package com.vmware.bugger.service;

import com.vmware.bugger.model.Bug;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabi on 12/23/2015.
 */
@Component
public class BugRepository {

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    public void addBugs(Bug bug) {
        this.bugs.add(bug);
    }

    private List<Bug> bugs = new ArrayList<>();
}
