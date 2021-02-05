package com.example.customsecurity.repository;

import com.example.health.entity.Token;
import com.example.health.security.AccessToken;
import com.example.health.security.TokenStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface TokenRepository extends JpaRepository<Token, String>, TokenStore {

    @Override
    default AccessToken getAccessToken(String token) {
        return findById(token).orElse(null);
    }

    @Override
    default void refreshToken(String token) {
        save(new Token(token, Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant())));
    }
}
