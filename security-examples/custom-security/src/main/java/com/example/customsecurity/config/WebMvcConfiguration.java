package com.example.customsecurity.config;

import com.example.health.security.DefaultSecurityGuard;
import com.example.health.security.SecurityGuard;
import com.example.health.security.TokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private HandlerInterceptor tokenInterceptor;

    public WebMvcConfiguration(HandlerInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/token/");
    }

    @Bean
    HandlerInterceptor tokenInterceptor(TokenStore tokenStore) {
        SecurityGuard securityGuard = new DefaultSecurityGuard("1", tokenStore);
        return new TokenInterceptor(securityGuard);
    }

}
