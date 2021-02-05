package com.example.jwtsecurity.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooApi {

    @GetMapping("/foo")
    public String foo() {
        return "hello foo!";
    }

    @GetMapping("/bar")
    public String bar() {
        return "hello bar!";
    }
}
