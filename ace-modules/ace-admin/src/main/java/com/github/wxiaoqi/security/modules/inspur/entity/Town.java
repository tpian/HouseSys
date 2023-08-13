package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 乡镇名字
 *
 * @author ggq
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_town")
public class Town implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //镇名字
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
