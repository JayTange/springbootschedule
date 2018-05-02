package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StartApplication {
    public static void main(String args[]){
        SpringApplication application = new SpringApplication(StartApplication.class);
        application.run(args);
    }
}
