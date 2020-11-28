package com.example.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryZookeeperApplication.class, args);
    }

}
