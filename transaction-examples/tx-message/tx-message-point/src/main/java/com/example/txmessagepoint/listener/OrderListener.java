package com.example.txmessagepoint.listener;

import com.example.txmessagecommon.dto.OrderDTO;
import com.example.txmessagepoint.service.PointService;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "cart-item-add-topic",
        consumerGroup = "cart-consumer_cart-item-add-topic",
        consumeMode = ConsumeMode.CONCURRENTLY
)
public class OrderListener implements RocketMQListener<OrderDTO> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PointService pointService;

    @Override
    public void onMessage(OrderDTO orderDTO) {
        logger.info("prepare increase point.");
        pointService.increasePoint(orderDTO);
    }
}
