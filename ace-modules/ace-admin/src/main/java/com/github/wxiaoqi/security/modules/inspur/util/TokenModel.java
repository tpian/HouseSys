package com.github.wxiaoqi.security.modules.inspur.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenModel implements Serializable {

    private String username;
    private String userId;
    private String name;
    private String phone;
    private String openId;

    public TokenModel(String username, String userId, String name, String phone, String openId) {
        this.username = username;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.openId = openId;
    }

    public TokenModel(String userId, String name, String phone, String openId) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.openId = openId;
    }
}
