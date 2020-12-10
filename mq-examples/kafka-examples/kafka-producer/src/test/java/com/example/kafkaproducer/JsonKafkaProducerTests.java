package com.example.kafkaproducer;

import com.example.kafkaproducer.pojo.Greeting;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@SpringBootTest
public class JsonKafkaProducerTests {

    private static final String TOPIC_NAME = "topic1";

    @Autowired
    private KafkaTemplate<String, Greeting> kafkaTemplate;

    @Test
    public void sendMessage() {
        kafkaTemplate.send(TOPIC_NAME, new Greeting("hello", "world"));
    }

    @Test
    public void sendMessageAsync() {
        Greeting message = new Greeting("hello", "async");
        ListenableFuture<SendResult<String, Greeting>> future = kafkaTemplate.send(TOPIC_NAME, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Greeting>>() {
            @Override
            public void onFailure(@NonNull Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Greeting> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }

}
