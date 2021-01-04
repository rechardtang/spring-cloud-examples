package com.example.redispubsubpublisher;

import com.example.redispubsubpublisher.queue.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RedisPubsubPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisPubsubPublisherApplication.class, args);
    }


    @Autowired
    private MessagePublisher messagePublisher;

    @GetMapping("/publish/{i}")
    public void publish(@PathVariable int i) {
        messagePublisher.publish("send " + i);
    }

}
