package com.vmware.bugger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example {

    private static final Logger logger = LoggerFactory.getLogger(Example.class);

    @RequestMapping(method = RequestMethod.GET)
    String get() {

        return "Hello World! get";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/webhook")
    String post(@RequestBody String requset) {
        logger.info(requset);
        return "Hello World! post";
    }
}


