package com.github.wxiaoqi.security.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * @author ggq
 * @version 2022-07-23 22:32:13
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //用户名
    @Column(name = "username")
    private String username;

    //密码
    @Column(name = "password")
    private String password;

    //姓名
    @Column(name = "name")
    private String name;

    //生日
    @Column(name = "birthday")
    private String birthday;

    //县地址
    @Column(name = "county")
    private String county;

    //镇地址
    @Column(name = "town")
    private String town;

    //村地址
    @Column(name = "village")
    private String village;

    //地址
    @Column(name = "address")
    private String address;

    //类型 1:村干部 2:镇街干部,3:局县干部 4;民政局 5 住建局
    @Column(name = "type")
    private Integer type;

    //手机号
    @Column(name = "mobile_phone")
    private String mobilePhone;

    //电话
    @Column(name = "tel_phone")
    private String telPhone;

    //邮箱
    @Column(name = "email")
    private String email;

    //单位
    @Column(name = "unit")
    private String unit;

    //性别
    @Column(name = "sex")
    private String sex;

    //描述
    @Column(name = "description")
    private String description;

    //创建时间
    @Column(name = "crt_time")
    private Date crtTime;

    //创建
    @Column(name = "crt_user")
    private String crtUser;

    //创建用户名
    @Column(name = "crt_name")
    private String crtName;

    //
    @Column(name = "crt_host")
    private String crtHost;

    //更新时间
    @Column(name = "upd_time")
    private Date updTime;

    //更新
    @Column(name = "upd_user")
    private String updUser;

    //更新用户名
    @Column(name = "upd_name")
    private String updName;

    //
    @Column(name = "upd_host")
    private String updHost;

}
