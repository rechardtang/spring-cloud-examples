package com.example.txmessageorder.service.impl;

import com.example.txmessagecommon.dto.OrderDTO;
import com.example.txmessageorder.entity.Order;
import com.example.txmessageorder.entity.TransactionLog;
import com.example.txmessageorder.repository.OrderRepository;
import com.example.txmessageorder.repository.TransactionLogRepository;
import com.example.txmessageorder.service.OrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TransactionLogRepository transactionRepository;

//    The dependencies of some of the beans in the application context form a cycle:
//    txRocketMQTemplate -> mqTransactionListener -> orderServiceImpl
    @Autowired
    private RocketMQTemplate txRocketMQTemplate;

    @Transactional
    @Override
    public void createOrder(OrderDTO orderDTO, String transactionId) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        orderRepository.save(order);

        TransactionLog transactionLog = new TransactionLog(transactionId, "order", String.valueOf(order.getId()));
        transactionRepository.save(transactionLog);
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {
//        orderDTO.setId();
//        orderDTO.setOrderNo();
//        txRocketMQTemplate.sendMessageInTransaction();
    }
}
