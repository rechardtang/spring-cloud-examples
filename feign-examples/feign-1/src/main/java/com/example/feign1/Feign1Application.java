package com.example.feign1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Feign1Application {

    public static void main(String[] args) {
        SpringApplication.run(Feign1Application.class, args);
    }

}
