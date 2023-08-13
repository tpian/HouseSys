package com.github.wxiaoqi.security.modules.inspur.entity.body;

import lombok.Data;

import java.util.Date;
@Data
public class Reviewby {
    private Integer appId;
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
}
