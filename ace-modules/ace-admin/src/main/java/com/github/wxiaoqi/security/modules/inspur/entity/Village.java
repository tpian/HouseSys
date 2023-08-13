package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 村庄名字
 *
 * @author ggq
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_village")
public class Village implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //村庄名字
    @Column(name = "name")
    private String name;

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
