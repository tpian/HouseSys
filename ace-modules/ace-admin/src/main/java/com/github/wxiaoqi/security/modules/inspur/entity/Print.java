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
@Table(name = "base_print")
public class Print implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //套装 id
    @Column(name = "cover_id")
    private String coverId;

    //图纸
    @Column(name = "images")
    private String images;

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
