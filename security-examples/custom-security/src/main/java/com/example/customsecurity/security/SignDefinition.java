package com.example.customsecurity.security;

import java.util.Map;

public interface SignDefinition {

    Map<String, ?> getParams();

    AccessToken getToken();

    String getNonce();

    String getSign();
}
