package com.example.rabbitmqcommon;

public class Constant {
    public final static String FANOUT_QUEUE_1_NAME = "com.baeldung.spring-amqp-simple.fanout.queue1";
    public final static String FANOUT_QUEUE_2_NAME = "com.baeldung.spring-amqp-simple.fanout.queue2";
    public final static String FANOUT_EXCHANGE_NAME = "com.baeldung.spring-amqp-simple.fanout.exchange";

    public final static String TOPIC_QUEUE_1_NAME = "com.baeldung.spring-amqp-simple.topic.queue1";
    public final static String TOPIC_QUEUE_2_NAME = "com.baeldung.spring-amqp-simple.topic.queue2";
    public final static String TOPIC_EXCHANGE_NAME = "com.baeldung.spring-amqp-simple.topic.exchange";
    public static final String BINDING_PATTERN_IMPORTANT = "*.important.*";
    public static final String BINDING_PATTERN_ERROR = "#.error";
}
