package com.example.service.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApi {

    @GetMapping("/order/{id}")
    public Object findById(@PathVariable long id) {
        return id;
    }
}
