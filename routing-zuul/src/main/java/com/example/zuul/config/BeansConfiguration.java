package com.example.zuul.config;

import com.example.zuul.filter.SimpleFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }
}
