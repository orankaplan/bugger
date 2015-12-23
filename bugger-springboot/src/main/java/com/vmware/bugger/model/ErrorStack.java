package com.vmware.bugger.model;

import java.util.List;

/**
 * Created by abadyan on 23/12/15.
 */
public class ErrorStack {
    public ErrorStack(List<String> classNames) {
        this.classNames = classNames;
    }

    private List<String> classNames;

    public ErrorStack() {

    }


    public List<String> getClassNames() {
        return classNames;
    }
}
