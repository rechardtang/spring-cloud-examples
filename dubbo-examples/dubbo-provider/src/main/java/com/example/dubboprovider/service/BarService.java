package com.example.dubboprovider.service;

import com.example.dubboapi.BarApi;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class BarService implements BarApi {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
