package com.github.wxiaoqi.security.modules.inspur.util;

import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PushService {

    @Autowired
    private WxloginUtil wxloginUtil;

    /**
     * 审核通过
     * @param approvalVO
     */
    public void push(ApprovalVO approvalVO) {
        String openId = approvalVO.getOpenId();
        String auAppr = approvalVO.getAuAppr();
        Date auTime = approvalVO.getAuTime();
        String name = approvalVO.getName();
        String auNotes = approvalVO.getAuNotes();
        wxloginUtil.push(openId, auAppr, auTime, name, auNotes);
    }

    /**
     * 审核不通过
     * @param approvalVO
     */
    public void turn(ApprovalVO approvalVO) {
        String openId = approvalVO.getOpenId();
        String auAppr = approvalVO.getZjAppr();
        Date auTime = approvalVO.getZjTime();
        String name = approvalVO.getName();
        String auNotes = approvalVO.getZjNotes();
        wxloginUtil.turn(openId, auAppr, auTime, name, auNotes);

    }


}
