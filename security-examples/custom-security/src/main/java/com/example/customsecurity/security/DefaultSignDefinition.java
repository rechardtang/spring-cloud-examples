package com.example.customsecurity.security;

import java.util.Map;

public class DefaultSignDefinition implements SignDefinition {
    private Map<String, ?> params;
    private AccessToken accessToken;
    private String nonce;
    private String sign;

    public DefaultSignDefinition(Map<String, ?> params, AccessToken accessToken, String nonce, String sign) {
        this.params = params;
        this.accessToken = accessToken;
        this.nonce = nonce;
        this.sign = sign;
    }

    @Override
    public Map<String, ?> getParams() {
        return params;
    }

    @Override
    public AccessToken getToken() {
        return accessToken;
    }

    @Override
    public String getNonce() {
        return nonce;
    }

    @Override
    public String getSign() {
        return sign;
    }
}
