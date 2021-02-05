package com.example.customsecurity.security;

import java.util.Date;

public interface AccessToken {
    /**
     * token
     */
    String getValue();

    /**
     * expire time
     */
    Date getTimestamp();
}
