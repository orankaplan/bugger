package hello;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gabi on 12/23/2015.
 */
@Component
public class BugRepository {

    public List<Bug> getBugs() {
        List<Bug> bugs = new ArrayList<>();
        Bug bug = new Bug();
        bug.setDescription("NullPointerException at RefDbUploadManager.java:81");
        bug.setBlamers(Arrays.asList("gabi@vmware.com", "okaplan@vmware.com"));
        bugs.add(bug);
        Bug bug2 = new Bug();
        bug2.setDescription("NullPointerException at RefDbUploadManager.java:81");
        bug2.setBlamers(Arrays.asList("gabi@vmware.com", "okaplan@vmware.com"));
        bugs.add(bug2);
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    private List<Bug> bugs = new ArrayList<>();
}
