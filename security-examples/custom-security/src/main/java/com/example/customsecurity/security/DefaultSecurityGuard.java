package com.example.customsecurity.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;
import java.util.Map;

public class DefaultSecurityGuard implements SecurityGuard {

    private String key;

    private TokenStore tokenStore;

    public DefaultSecurityGuard(String key, TokenStore tokenStore) {
        this.key = key;
        this.tokenStore = tokenStore;
    }

    @Override
    public boolean check(SignDefinition signDefinition) {
        Map<String, ?> params = signDefinition.getParams();
        AccessToken token = signDefinition.getToken();
        String nonce = signDefinition.getNonce();
        String sign = signDefinition.getSign();
        try {
            String jsonParams = new ObjectMapper().writeValueAsString(params);
            String rawSign = jsonParams + token.getValue() + token.getTimestamp() + nonce + key;
            String encryptSign = encrypt(rawSign);
            return encryptSign.equals(sign);
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    private String encrypt(String rawSign) {
        return Base64Utils.encodeToString(DigestUtils.md5DigestAsHex(rawSign.getBytes(Charset.defaultCharset())).getBytes(Charset.defaultCharset()));
    }

    @Override
    public boolean check(AccessToken token) {
        if (token == null || token.getValue() == null) {
            return false;
        }

        AccessToken tokenStored = tokenStore.getAccessToken(token.getValue());
        if (tokenStored == null) {
            return false;
        }

        if (token.getTimestamp().getTime() > tokenStored.getTimestamp().getTime()) {
            return false;
        }
        return token.getValue().equals(tokenStored.getValue());
    }
}
