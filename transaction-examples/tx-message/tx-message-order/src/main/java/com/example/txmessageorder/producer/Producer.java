package com.example.txmessageorder.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RocketMQTemplate txRocketMQTemplate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
}
