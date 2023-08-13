package com.github.wxiaoqi.security.modules.inspur.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApprovalVO implements Serializable {
    private Integer userId;
    private String name;
    private String town;
    private String village;
    private String address;
    private String mobilePhone;
    private Integer staff;
    private Date crtTime;
    private Integer appId;
    private String cardId;
    private String front;
    private String Back;
    private String member;
    private String poor;
    private String report;
    private String house;
    private String cardBank;
    private String housingSt;
    private String houseType;
    private String houseImage;
    private String housePlan;
    private Date creaTime;
    private Integer rwId;
    private Date auTime;
    private Integer schedule;
    private String auNotes;
    private String auAppr;
    private String record;
    private String autellImage;
    private String scene;
    private String receipt;
    private Date autellTime;
    private Date wnTime;
    private Integer wnschedule;
    private String wnNotes;
    private String wnAppr;
    private String fieldwn;
    private String wntellImage;
    private String scenewn;
    private String receiptwn;
    private String hold;
    private String protocol;
    private Date wntellTime;
    private Date tyTime;
    private Integer tyschedule;
    private String tyNotes;
    private String tyAppr;
    private String tytellImage;
    private Date tytellTime;
    private Date mzTime;
    private Integer mzschedule;
    private String mzNotes;
    private String mzAppr;
    private String mztellImage;
    private Date zjTime;
    private Integer zjschedule;
    private String zjNotes;
    private String zjAppr;
    private String zjtellImage;
    private Date zjtellTime;
    private String openId;

}
