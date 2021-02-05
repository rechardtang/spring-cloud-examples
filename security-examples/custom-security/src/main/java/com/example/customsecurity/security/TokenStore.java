package com.example.customsecurity.security;

public interface TokenStore {

    AccessToken getAccessToken(String token);

    void refreshToken(String token);
}
