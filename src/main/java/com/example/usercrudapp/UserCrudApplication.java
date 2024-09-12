package com.example.usercrudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan({"com.example.usercrudapp.service","com.example.usercrudapp.controller"})
@EntityScan("com.example.usercrudapp.model")
@EnableJpaRepositories("com.example.usercrudapp.repo")
public class UserCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCrudApplication.class, args);
    }
}
