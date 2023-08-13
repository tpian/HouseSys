package com.github.wxiaoqi.security.api.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author the sun
 * @create 2017-06-21 8:12
 */
@Data
public class UserInfo implements Serializable{
    public String id;
    public String username;
    public String phone;
    public String password;
    public String name;
    public  Integer type;
}
