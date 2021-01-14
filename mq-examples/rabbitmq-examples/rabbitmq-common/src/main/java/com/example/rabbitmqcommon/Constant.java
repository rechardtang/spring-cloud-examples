package com.example.rabbitmqcommon;

public interface Constant {
    String FANOUT_QUEUE_1_NAME = "com.example.spring-amqp-simple.fanout.queue1";
    String FANOUT_QUEUE_2_NAME = "com.example.spring-amqp-simple.fanout.queue2";
    String FANOUT_EXCHANGE_NAME = "com.example.spring-amqp-simple.fanout.exchange";

    String TOPIC_QUEUE_1_NAME = "com.example.spring-amqp-simple.topic.queue1";
    String TOPIC_QUEUE_2_NAME = "com.example.spring-amqp-simple.topic.queue2";
    String TOPIC_EXCHANGE_NAME = "com.example.spring-amqp-simple.topic.exchange";
    String BINDING_PATTERN_IMPORTANT = "*.important.*";
    String BINDING_PATTERN_ERROR = "#.error";
    String QUEUE_001 = "queue-001";
}
