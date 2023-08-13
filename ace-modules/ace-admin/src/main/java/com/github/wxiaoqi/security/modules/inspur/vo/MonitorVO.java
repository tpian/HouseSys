package com.github.wxiaoqi.security.modules.inspur.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class MonitorVO implements Serializable {
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
    private Integer moId;
    private String contract;
    private Integer contractState;
    private Date contractTime;
    private String promise;
    private Integer promiseState;
    private Date promiseTime;
    private String liability;
    private Integer liabilityState;
    private Date liabilityTime;
    private String inform;
    private Integer informState;
    private Date informTime;
    private String found;
    private Integer foundState;
    private Date foundTime;
    private String part;
    private Integer partState;
    private Date partTime;
    private String capped;
    private Integer cappedState;
    private Date cappedTime;
    private String acceptance;
    private Integer aceState;
    private Date aceTime;
    private String checking;
    private Integer checkingState;
    private Date checkingTime;
    private String urge;
    private Integer urgeState;
    private Date urgeTime;
    private String filing;
    private Integer filingState;
    private Date filingTime;
    private String transfer;
    private Integer transferState;
    private Date transferTime;

}
