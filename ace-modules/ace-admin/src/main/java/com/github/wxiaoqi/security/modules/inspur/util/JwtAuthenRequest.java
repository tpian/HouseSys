package com.github.wxiaoqi.security.modules.inspur.util;

import lombok.Data;
import java.io.Serializable;

@Data
public class JwtAuthenRequest implements Serializable {
    private static final long serialVersionUID = -8445943548965154778L;
    private String phone;
    private String password;
    private String code;

    public JwtAuthenRequest(String phone, String password, String code) {
        this.phone = phone;
        this.password = password;
        this.code = code;

    }
    public JwtAuthenRequest(){

    }
}
