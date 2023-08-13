package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 政策宣传
 *
 * @author ggq
 * @version 2022-07-27 22:34:15
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_pubty")
public class Pubty implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //标题
    @Column(name = "title")
    private String title;

    //网址
    @Column(name = "url")
    private String url;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;

    //发布人
    @Column(name = "relea")
    private String relea;

    //删除标志（0代表存在 1代表软删
    @Column(name = "is_delete")
    private Integer isDelete;


}
