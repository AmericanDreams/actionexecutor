package com.project.actionexecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class ActionExecutorApplication {
    @PostConstruct
    public void init(){
        // Setting time zone
        TimeZone.setDefault(TimeZone.getTimeZone("Africa/Lagos"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ActionExecutorApplication.class, args);
    }
}
