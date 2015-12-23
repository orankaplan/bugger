package com.vmware.bugger.service;

import com.vmware.bugger.model.Culprit;
import com.vmware.bugger.model.ErrorStack;
import org.springframework.stereotype.Service;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by abadyan on 23/12/15.
 */
@Service
public class GitBlamerService {

    private final Git git;

    public GitBlamerService() throws IOException {
        String localPath = "/Users/abadyan/work/git/dcs-iis-plugin";
        FileRepository localRepo = new FileRepository(localPath + "/.git");
        git = new Git(localRepo);
    }

    public List<Culprit> blame(ErrorStack errorStack) throws GitAPIException {
        List<String> paths = extractPaths(errorStack.getClassNames());
        List<Culprit> culprits = new ArrayList<>();
        for (String path : paths) {
            final LogCommand logCommand = git.log();
            logCommand.setMaxCount(3);
            logCommand.addPath(path);
            for (RevCommit revCommit : logCommand.call()) {
                culprits.add(new Culprit(revCommit));
            }

        }
        Collections.sort(culprits);
        return culprits;
    }

    private List<String> extractPaths(List<String> classNames) {
        return Collections.singletonList("oss/src/main/java/org/hyperic/hq/plugin/iis/IisDetector.java");
    }
}
