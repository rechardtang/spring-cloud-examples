package com.example.txmessageorder.config;

import com.example.txmessageorder.listener.MQTransactionListener;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQTemplateConfig {

    @Bean
    public RocketMQTemplate txRocketMQTemplate(RocketMQMessageConverter messageConverter, TransactionListener mqTransactionListener, RocketMQProperties properties) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        TransactionMQProducer txMQProducer = new TransactionMQProducer(properties.getProducer().getGroup());
        txMQProducer.setTransactionListener(mqTransactionListener);
        txMQProducer.setInstanceName("TransactionMQProducer");
        rocketMQTemplate.setProducer(txMQProducer);
        rocketMQTemplate.setMessageConverter(messageConverter.getMessageConverter());
        return rocketMQTemplate;
    }

    @Bean
    public TransactionListener mqTransactionListener() {
        return new MQTransactionListener();
    }
}
