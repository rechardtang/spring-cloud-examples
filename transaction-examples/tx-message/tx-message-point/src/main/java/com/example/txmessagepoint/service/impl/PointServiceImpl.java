package com.example.txmessagepoint.service.impl;

import com.example.txmessagecommon.dto.OrderDTO;
import com.example.txmessagepoint.entity.Point;
import com.example.txmessagepoint.repository.PointRepository;
import com.example.txmessagepoint.service.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PointRepository pointRepository;


    @Override
    public void increasePoint(OrderDTO orderDTO) {
        // query before
        Optional<Point> pointFound = pointRepository.findByOrderNo(orderDTO.getOrderNo());
        if (pointFound.isPresent()) {
            logger.info("point of order {} proceed.", orderDTO.getOrderNo());
        } else {
            Point point = new Point();
            pointRepository.save(point);
            logger.info("increase {} point for order {}.", 10, orderDTO.getOrderNo());
        }
    }
}
