package com.github.wxiaoqi.security.modules.admin.entity;

import com.github.wxiaoqi.security.modules.admin.constant.AdminCommonConstant;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "base_menu")
public class Menu {
    @Id
    private Integer id;

    private String code;

    private String title;

    @Column(name = "parent_id")
    private Integer parentId = AdminCommonConstant.ROOT;

    private String href;

    private String icon;

    private String type;

    @Column(name = "order_num")
    private Integer orderNum;

    private String description;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;

    @Column(name = "upd_host")
    private String updHost;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

    private String path;


}
