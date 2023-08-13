package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 审批进度
 *
 * @author 111
 * @version 2022-08-16 22:38:35
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_review")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    @Id
    private Integer id;

    //村审批日期
    @Column(name = "au_time")
    private Date auTime;

    //村审批进度 0:审核中, 1:村通过, -1:驳回
    @Column(name = "schedule")
    private Integer schedule;

    //村审批驳回,需备注原因
    @Column(name = "au_notes")
    private String auNotes;

    //村审核人
    @Column(name = "au_appr")
    private String auAppr;

    //评议记录
    @Column(name = "record")
    private String record;

    //村级公示照片
    @Column(name = "autell_image")
    private String autellImage;

    //村级公示影像资料
    @Column(name = "scene")
    private String scene;

    //村级公示回执
    @Column(name = "receipt")
    private String receipt;

    //村公告时间
    @Column(name = "autell_time")
    private Date autellTime;

    //镇审批日期
    @Column(name = "wn_time")
    private Date wnTime;

    //镇审批进度 0:审核中, 1:镇通过 -1:驳回
    @Column(name = "wn_schedule")
    private Integer wnSchedule;

    //镇审核原因
    @Column(name = "wn_notes")
    private String wnNotes;

    //镇审核人
    @Column(name = "wn_appr")
    private String wnAppr;

    //镇街实地考察
    @Column(name = "fieldwn")
    private String fieldwn;

    //镇公告照片
    @Column(name = "wntell_image")
    private String wntellImage;

    //镇级公示影像资料
    @Column(name = "scenewn")
    private String scenewn;

    //镇级公示回执
    @Column(name = "receiptwn")
    private String receiptwn;

    //农村危房改造对象认定表
    @Column(name = "hold")
    private String hold;

    //危房改造工程协议书
    @Column(name = "protocol")
    private String protocol;

    //镇公告时间
    @Column(name = "wntell_time")
    private Date wntellTime;

    //县审批日期
    @Column(name = "ty_time")
    private Date tyTime;

    //县审批0:审核中,1:县通过 -1:驳回
    @Column(name = "ty_schedule")
    private Integer tySchedule;

    //县审核备注
    @Column(name = "ty_notes")
    private String tyNotes;

    //县审核人
    @Column(name = "ty_appr")
    private String tyAppr;

    //民政审批日期
    @Column(name = "mz_time")
    private Date mzTime;

    //民政审批0:审核中,1:,民政通过 -1:驳回
    @Column(name = "mz_schedule")
    private Integer mzSchedule;

    //民政审核备注
    @Column(name = "mz_notes")
    private String mzNotes;

    //民政审核人
    @Column(name = "mz_appr")
    private String mzAppr;


    //住建局审批日期
    @Column(name = "zj_time")
    private Date zjTime;

    //住建局审批0:审核中,1:,住建局通过 -1:驳回
    @Column(name = "zj_schedule")
    private Integer zjSchedule;

    //住建局审核备注
    @Column(name = "zj_notes")
    private String zjNotes;

    //住建局审核人
    @Column(name = "zj_appr")
    private String zjAppr;

    //住建局公示
    @Column(name = "zjtell_image")
    private String zjtellImage;

    //住建局公告时间
    @Column(name = "zjtell_time")
    private Date zjtellTime;

    //创建时间
    @Column(name = "crea_time")
    private Date creaTime;

    //审批表 ID
    @Column(name = "app_id")
    private Integer appId;

    //软删 0:正常 1:删除
    @Column(name = "is_delete")
    private Integer isDelete;


}
