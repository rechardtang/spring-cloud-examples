package com.example.kafkaproducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerTests {

    private static final String TOPIC_NAME = "topic1";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void sendMessage() {
        kafkaTemplate.send(TOPIC_NAME, "hello, topic 1");
    }

    @Test
    public void sendMessageAsync() {
        String message = "hello, topic 1. async!";
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(@NonNull Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }

}
