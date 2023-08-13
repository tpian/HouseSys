package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * @author ggq
 * @version 2023-03-01 00:25:28
 * @email 2014314038@qq.com
 */
@Data
@Table(name = "base_cover")
public class Cover implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //名字
    @Column(name = "name")
    private String name;

    //楼层
    @Column(name = "type")
    private String type;

    //效果图
    @Column(name = "effect")
    private String effect;

    //创建时间
    @Column(name = "crt_time")
    private Date crtTime;

    //创建人
    @Column(name = "crt_name")
    private String crtName;

    //更新时间
    @Column(name = "upd_time")
    private Date updTime;

    //更新人
    @Column(name = "upd_name")
    private String updName;

}
