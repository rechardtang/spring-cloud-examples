package com.example.txmessageorder.listener;

import com.alibaba.fastjson.JSONObject;
import com.example.txmessageorder.service.OrderService;
import com.example.txmessageorder.service.TransactionService;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MQTransactionListener implements TransactionListener {

    private final Logger logger = LoggerFactory.getLogger(MQTransactionListener.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        logger.info("start local transaction.");
        try {

        } catch (Exception e) {

        }
//        String body = new String(message.getBody());
//        OrderDTO order = JSONObject.parseObject(body, OrderDTO.class);
//        orderService.createOrder(order, message.getTransactionId());
//        LocalTransactionState state = LocalTransactionState.COMMIT_MESSAGE;
        logger.info("transaction {} was committed.", message.getTransactionId());
        return null;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return null;
    }
}
