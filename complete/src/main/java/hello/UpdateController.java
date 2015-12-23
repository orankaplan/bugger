package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gabi on 12/23/2015.
 */
@Controller
public class UpdateController {

    @Autowired
    BugRepository bugsRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void updateBugs(List<Bug> bugs){
        bugsRepo.setBugs(bugs);
    }

}
