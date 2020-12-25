package com.example.txmessageorder.service;


import com.example.txmessagecommon.dto.OrderDTO;

public interface OrderService {

    void createOrder(OrderDTO orderDTO, String transactionId);

    void createOrder(OrderDTO orderDTO);
}
