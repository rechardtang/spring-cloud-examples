package com.example.rocketmqconsumer;

import com.example.rocketmqcommon.CartItemEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class RocketmqConsumerApplication {

    private static final Logger log = LoggerFactory.getLogger(RocketmqConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RocketmqConsumerApplication.class, args);
    }

    @Service
    @RocketMQMessageListener(
            topic = "cart-item-add-topic",
            consumerGroup = "cart-consumer_cart-item-add-topic"
    )
    public static class CardItemAddConsumer implements RocketMQListener<CartItemEvent> {
        public void onMessage(CartItemEvent addItemEvent) {
            log.info("Adding item: {}", addItemEvent);
            // additional logic
        }
    }

    @Service
    @RocketMQMessageListener(
            topic = "cart-item-removed-topic",
            consumerGroup = "cart-consumer_cart-item-removed-topic"
    )
    public static class CardItemRemoveConsumer implements RocketMQListener<CartItemEvent> {
        public void onMessage(CartItemEvent removeItemEvent) {
            log.info("Removing item: {}", removeItemEvent);
            // additional logic
        }
    }

}
