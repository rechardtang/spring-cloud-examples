package com.example.txmessageorder.listener;

import com.alibaba.fastjson.JSONObject;
import com.example.txmessagecommon.dto.OrderDTO;
import com.example.txmessageorder.service.OrderService;
import com.example.txmessageorder.service.TransactionLogService;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;


public class MQTransactionListener implements TransactionListener, ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(MQTransactionListener.class);

    private ApplicationContext context;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        logger.info("start local transaction.");
        LocalTransactionState state;
        try {
            String body = new String(message.getBody());
            OrderDTO order = JSONObject.parseObject(body, OrderDTO.class);
            context.getBean(OrderService.class).createOrder(order, message.getTransactionId());
            state = LocalTransactionState.COMMIT_MESSAGE;
            logger.info("transaction {} was committed.", message.getTransactionId());
        } catch (Exception e) {
            logger.info("roll back");
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        logger.info("checkLocalTransaction start {}", messageExt.getTransactionId());
        LocalTransactionState state;
        String transactionId = messageExt.getTransactionId();
        if (context.getBean(TransactionLogService.class).get(transactionId) > 0) {
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            state = LocalTransactionState.UNKNOW;
        }
        logger.info("checkLocalTransaction end {}", state);
        return state;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
