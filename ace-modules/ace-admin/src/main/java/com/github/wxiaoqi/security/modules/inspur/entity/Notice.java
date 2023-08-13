package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 通告表
 *
 * @author ggq
 * @version 2022-07-27 22:34:14
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_notice")
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //通告类型  1.违规申报（虚假信息）2.违规审批（资料不规范）3.违规建设（建设中的问题）
    @Column(name = "type")
    private String type;

    //通告人
    @Column(name = "notifier")
    private String notifier;

    //通告内容
    @Column(name = "content")
    private String content;

    //创建人
    @Column(name = "founder")
    private String founder;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;

    //删除标志（0代表存在 1代表软删
    @Column(name = "is_delete")
    private Integer isDelete;


}
