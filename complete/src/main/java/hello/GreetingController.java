package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GreetingController {



    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/hello")
    @SendTo("/topic/bugs")
    public List<Bug> bugs(HelloMessage message) throws Exception {
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

}
