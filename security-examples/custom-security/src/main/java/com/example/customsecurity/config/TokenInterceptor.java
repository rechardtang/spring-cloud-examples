package com.example.customsecurity.config;

import com.example.customsecurity.entity.Token;
import com.example.customsecurity.security.AccessToken;
import com.example.customsecurity.security.DefaultSignDefinition;
import com.example.customsecurity.security.SecurityGuard;
import com.example.customsecurity.security.SignDefinition;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenInterceptor implements HandlerInterceptor {

    private SecurityGuard securityGuard;

    public TokenInterceptor(SecurityGuard securityGuard) {
        this.securityGuard = securityGuard;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            long timestamp = Long.parseLong(request.getHeader("timestamp"));
            String nonce = request.getHeader("nonce");
            String sign = request.getHeader("sign");

            AccessToken accessToken = new Token(token, new Date(timestamp));
            SignDefinition signDefinition = new DefaultSignDefinition(request.getParameterMap(), accessToken, nonce, sign);

            return securityGuard.check(signDefinition) && securityGuard.check(accessToken);
        } catch (Exception e) {
            return false;
        }
    }
}
