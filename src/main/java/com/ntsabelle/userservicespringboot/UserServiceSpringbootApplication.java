package com.ntsabelle.userservicespringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceSpringbootApplication {
    static Logger logger = LoggerFactory.getLogger(UserServiceSpringbootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserServiceSpringbootApplication.class, args);
        logger.info("User Service Spring Boot application started successfully.");
    }

}
