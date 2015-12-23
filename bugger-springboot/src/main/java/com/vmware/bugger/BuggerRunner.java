package com.vmware.bugger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@ComponentScan("com.vmware.bugger")
public class BuggerRunner extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(BuggerRunner.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BuggerRunner.class, args);
        logger.info("Complete loading context for {}.", ctx.getApplicationName());
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application;
    }
}