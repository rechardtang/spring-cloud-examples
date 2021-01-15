package com.example.rabbitmqconsumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static com.example.rabbitmqcommon.Constant.*;

@SpringBootApplication
public class RabbitmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqConsumerApplication.class, args);
    }

    @RabbitListener(queues = QUEUE_001)
    public void receiveQueue001(Message message, Channel channel) throws IOException {
        System.out.println("method1 " + new String(message.getBody()) + ", tag = " + message.getMessageProperties());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

//    @RabbitListener(queues = {FANOUT_QUEUE_1_NAME})
//    public void receiveMessageFromFanout1(String message) {
//        System.out.println("Received fanout 1 message: " + message);
//    }
//
//    @RabbitListener(queues = {FANOUT_QUEUE_2_NAME})
//    public void receiveMessageFromFanout2(String message) {
//        System.out.println("Received fanout 2 message: " + message);
//    }
//
//    @RabbitListener(queues = {TOPIC_QUEUE_1_NAME})
//    public void receiveMessageFromTopic1(String message) {
//        System.out.println("Received topic 1 (" + BINDING_PATTERN_IMPORTANT + ") message: " + message);
//    }
//
//    @RabbitListener(queues = {TOPIC_QUEUE_2_NAME})
//    public void receiveMessageFromTopic2(String message) {
//        System.out.println("Received topic 2 (" + BINDING_PATTERN_ERROR + ") message: " + message);
//    }

}
