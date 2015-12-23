package com.vmware.bugger.model;

import org.eclipse.jgit.revwalk.RevCommit;

/**
 * Created by abadyan on 23/12/15.
 */
public class Culprit implements Comparable<Culprit> {
    private final int modifiedDate;
    private final String name;
    private final String commit;
    private final String fullMessage;
    private String email;

    public int getModifiedDate() {
        return modifiedDate;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Culprit(RevCommit revCommit) {
        name = revCommit.getAuthorIdent().getName();
        email = revCommit.getAuthorIdent().getEmailAddress();
        modifiedDate = revCommit.getCommitTime();
        fullMessage = revCommit.getFullMessage();
        commit = revCommit.toString();
    }

    @Override
    public int compareTo(Culprit o) {
        return o.modifiedDate - modifiedDate;
    }

    public String getCommit() {
        return commit;
    }

    public String getFullMessage() {
        return fullMessage;
    }
}
