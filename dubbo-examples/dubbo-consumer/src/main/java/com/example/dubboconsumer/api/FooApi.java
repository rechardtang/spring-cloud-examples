package com.example.dubboconsumer.api;

import com.example.dubboapi.BarApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooApi {

    @DubboReference(
            version = "1.0.0",
            url = "dubbo://127.0.0.1:12345",
            timeout = 100,
            methods = {
                    @Method(name = "hello", timeout = 300)
            }
    )
    private BarApi barService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return barService.hello(name);
    }
}
