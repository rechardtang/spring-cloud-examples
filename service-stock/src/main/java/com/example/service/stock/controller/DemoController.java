package com.example.service.stock.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/dec1")
    public Object decreaseStock1() {
        try {
            if (lock()) {
                String value = redisTemplate.opsForValue().get("foo");
                if (Integer.parseInt(value) > 0) {
                    Long foo = redisTemplate.opsForValue().decrement("foo");
                    System.out.println("foo = " + foo);
                }
            }
        } finally {
            unlock();
        }
        return 0;
    }

    @GetMapping("/dec2")
    public Object decreaseStock() {
        RLock redLock = redissonClient.getLock("redLock");
        redLock.lock();
        try {
            String foo1 = redisTemplate.opsForValue().get("foo");
            if (Integer.parseInt(foo1) > 0) {
                Long foo = redisTemplate.opsForValue().decrement("foo");
                System.out.println("foo = " + foo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }
        return 0;
    }

    private Boolean lock() {
        return redisTemplate.opsForValue().setIfAbsent("lock", "1");
    }

    private Boolean unlock() {
        return redisTemplate.delete("lock");
    }
}
