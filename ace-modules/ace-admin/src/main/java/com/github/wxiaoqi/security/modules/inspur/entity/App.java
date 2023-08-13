package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 审批单
 *
 * @author ggq
 * @version 2022-07-23 22:32:13
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_app")
public class App implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    @Id
    private Integer id;

    //身份证
    @Column(name = "card_id")
    private String cardId;

    //身份证正面
    @Column(name = "front")
    private String front;

    //身份证反面
    @Column(name = "back")
    private String back;

    //户口索引和家庭成员信息复印件
    @Column(name = "member")
    private String member;

    //贫困证明材料复印件
    @Column(name = "poor")
    private String poor;

    //申请报告图片
    @Column(name = "report")
    private String report;

    //建（修）房申请表
    @Column(name = "house")
    private String house;

     //社保卡或其他银行卡
    @Column(name = "card_bank")
    private String cardBank;

    //房屋情况 :哪年建，几间，属于平房还是屋房
    @Column(name = "Housing_st")
    private String housingSt;

    //危房类型 : 0:修缮，1:重建
    @Column(name = "house_type")
    private String houseType;

    //房屋图片 一张全景，两张重要问题特写
    @Column(name = "house_image")
    private String houseImage;

    //建房计划 图纸，层数，间数，地基深，框架结构
    @Column(name = "house_plan")
    private String housePlan;

    //创建时间
    @Column(name = "crea_time")
    private Date creaTime;

    //最后更新时间
    @Column(name = "updata_time")
    private Date updataTime;

    //微信识别标识
    @Column(name = "openid")
    private String openid;

    //农户ID
    @Column(name = "user_id")
    private Integer userId;

    //是否软删除 0:正常 1:删除
    @Column(name = "is_delete")
    private Integer isDelete;


}
