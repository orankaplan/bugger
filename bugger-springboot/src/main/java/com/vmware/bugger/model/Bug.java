package com.vmware.bugger.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabi on 12/23/2015.
 */
public class Bug {
    String description;
    List<String> blamers = new ArrayList<>();

    public List<String> getBlamers() {
        return blamers;
    }

    public void addBlamers(String blamers) {
        this.blamers.add(blamers);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
