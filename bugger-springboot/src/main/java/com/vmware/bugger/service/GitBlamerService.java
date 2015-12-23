package com.vmware.bugger.service;

import com.vmware.bugger.model.Culprit;
import com.vmware.bugger.model.ErrorStack;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.revwalk.RevCommit;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by abadyan on 23/12/15.
 */
@Service
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class GitBlamerService {

    private Git git;

    @Value("${bugger.localrepo}")
    private String localPath;


    @PostConstruct
    private void init() throws IOException {
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
        File root = git.getRepository().getWorkTree();
        return classNames.stream().map(name ->
                FileUtils.listFiles(root, new NameFileFilter(name), TrueFileFilter.INSTANCE).iterator().next())
                .map(File::getPath)
                .map(Paths::get)
                .map(path -> Paths.get(root.getPath()).relativize(path))
                .map(Path::toString)
                .collect(Collectors.toList());
    }
}
