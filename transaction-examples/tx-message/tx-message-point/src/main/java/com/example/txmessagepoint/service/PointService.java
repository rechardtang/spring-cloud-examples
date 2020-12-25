package com.example.txmessagepoint.service;

import com.example.txmessagecommon.dto.OrderDTO;

public interface PointService {

    void increasePoint(OrderDTO orderDTO);
}
