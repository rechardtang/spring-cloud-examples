package com.example.customsecurity.security;

public interface SecurityGuard {

    boolean check(SignDefinition sign);

    boolean check(AccessToken token);
}
