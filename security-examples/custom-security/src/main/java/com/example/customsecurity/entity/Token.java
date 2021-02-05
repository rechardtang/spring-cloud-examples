package com.example.customsecurity.entity;

import com.example.customsecurity.security.AccessToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements AccessToken {

    @Id
    private String token;

    private Date expire;

    @Override
    public String getValue() {
        return token;
    }

    @Override
    public Date getTimestamp() {
        return expire;
    }
}
