package com.example.rabbitmqproducer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.rabbitmqcommon.Constant.*;

@SpringBootApplication
public class RabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    private static String ROUTING_KEY_USER_IMPORTANT_WARN = "user.important.warn";
    private static String ROUTING_KEY_USER_IMPORTANT_ERROR = "user.important.error";

    @Bean
    public ApplicationRunner runner(RabbitTemplate rabbitTemplate) {
        String message = " payload is broadcast";
        return args -> {
            rabbitTemplate.convertAndSend(QUEUE_001, "hello, world");

//            rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", "fanout" + message);
//            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_USER_IMPORTANT_WARN, "topic important warn" + message);
//            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_USER_IMPORTANT_ERROR, "topic important error" + message);
        };
    }

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_001, true);
    }

}
