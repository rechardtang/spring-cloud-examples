package com.example.zookeeperclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperClientApplication.class, args);
    }

}
