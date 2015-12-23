package com.vmware.bugger.model;

import java.util.Set;

/**
 * Created by abadyan on 23/12/15.
 */
public class ErrorStack {
    public ErrorStack(Set<String> classNames) {
        this.classNames = classNames;
    }

    private Set<String> classNames;

    public ErrorStack() {

    }


    public Set<String> getClassNames() {
        return classNames;
    }

    public void setClassNames(Set<String> classNames) {
        this.classNames = classNames;
    }
}
