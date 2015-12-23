package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
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
