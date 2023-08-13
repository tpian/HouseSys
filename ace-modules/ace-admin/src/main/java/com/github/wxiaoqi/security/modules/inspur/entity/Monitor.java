package com.github.wxiaoqi.security.modules.inspur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 动态监管
 *
 * @author ggq
 * @version 2022-08-19 22:28:23
 * @email 1002375151@qq.com
 */
@Data
@Table(name = "base_monitor")
public class Monitor implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //改造施工合同
    @Column(name = "contract")
    private String contract;

    //状态
    @Column(name = "contract_state")
    private Integer contractState;

    //时间
    @Column(name = "contract_time")
    private Date contractTime;

    //建房承诺书
    @Column(name = "promise")
    private String promise;

    //状态
    @Column(name = "promise_state")
    private Integer promiseState;

    //时间
    @Column(name = "promise_time")
    private Date promiseTime;

    //安全责任书
    @Column(name = "liability")
    private String liability;

    //状态
    @Column(name = "liability_state")
    private Integer liabilityState;

    //时间
    @Column(name = "liability_time")
    private Date liabilityTime;

    //告知书
    @Column(name = "inform")
    private String inform;

    //状态
    @Column(name = "inform_state")
    private Integer informState;

    //时间
    @Column(name = "inform_time")
    private Date informTime;

    //地基照片
    @Column(name = "found")
    private String found;

    //建房状态 0 正常 ,1 暂停
    @Column(name = "found_state")
    private Integer foundState;

    //上传时间
    @Column(name = "found_time")
    private Date foundTime;

    //主体照片
    @Column(name = "part")
    private String part;

    //建房状态 0 正常 ,1 暂停
    @Column(name = "part_state")
    private Integer partState;

    //上传时间
    @Column(name = "part_time")
    private Date partTime;

    //封顶照片
    @Column(name = "capped")
    private String capped;

    //建房状态 0 正常 ,1 暂停
    @Column(name = "capped_state")
    private Integer cappedState;

    //上传时间
    @Column(name = "capped_time")
    private Date cappedTime;

    //验收照片
    @Column(name = "acceptance")
    private String acceptance;

    //建房状态 0 正常 ,1 暂停
    @Column(name = "ace_state")
    private Integer aceState;

    //上传时间
    @Column(name = "ace_time")
    private Date aceTime;

    //改造质量安全巡查记录
    @Column(name = "checking")
    private String checking;

    //状态
    @Column(name = "checking_state")
    private Integer checkingState;

    //时间
    @Column(name = "checking_time")
    private Date checkingTime;

    //竣工验收监督检查表
    @Column(name = "urge")
    private String urge;

    //状态
    @Column(name = "urge_state")
    private Integer urgeState;

    //时间
    @Column(name = "urge_time")
    private Date urgeTime;

    //建房备案书
    @Column(name = "filing")
    private String filing;

    //状态
    @Column(name = "filing_state")
    private Integer filingState;

    //时间
    @Column(name = "filing_time")
    private Date filingTime;

    //转账记录
    @Column(name = "transfer")
    private String transfer;

    //状态
    @Column(name = "transfer_state")
    private Integer transferState;

    //时间
    @Column(name = "transfer_time")
    private Date transferTime;

    //违规情况
    @Column(name = "Violation")
    private String violation;

    //违规时间
    @Column(name = "Violation_time")
    private Date violationTime;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;

    //农户ID
    @Column(name = "user_id")
    private Integer userId;

    //删除标志（0代表存在 1代表软删
    @Column(name = "is_delete")
    private Integer isDelete;


}
