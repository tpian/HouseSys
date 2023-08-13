package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.modules.admin.biz.UserBiz;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.modules.inspur.biz.ReviewBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Review;
import com.github.wxiaoqi.security.modules.inspur.entity.body.Reviewby;
import com.github.wxiaoqi.security.modules.inspur.util.HsConstants;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermRevService {

    @Autowired
    private JwtTokenUtil userAuthUtil;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private ReviewBiz reviewBiz;

    public void check(String token, Reviewby review) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        User user = userBiz.getUserByUsername(tokenModel.getUsername());
        Review appById = reviewBiz.selectAppById(review.getAppId());
        switch (user.getType()) {
            case HsConstants.VI_TYPE:
                appById.setAuTime(new Date());
                appById.setSchedule(review.getSchedule());
                appById.setRecord(review.getRecord());
                appById.setAuNotes(review.getAuNotes());
                appById.setScene(review.getScene());
                appById.setAuAppr(user.getUsername());
                appById.setReceipt(review.getReceipt());
                appById.setAutellImage(review.getAutellImage());
                appById.setAutellTime(new Date());
                reviewBiz.updateSelectiveById(appById);
                break;
            case HsConstants.WN_TYPE:
                appById.setWnTime(new Date());
                appById.setWnSchedule(review.getWnschedule());
                appById.setWnNotes(review.getWnNotes());
                appById.setWnAppr(user.getUsername());
                appById.setWntellImage(review.getWntellImage());
                appById.setFieldwn(review.getFieldwn());
                appById.setReceiptwn(review.getReceiptwn());
                appById.setScenewn(review.getScenewn());
                appById.setHold(review.getHold());
                appById.setProtocol(review.getProtocol());
                appById.setWntellTime(new Date());
                reviewBiz.updateSelectiveById(appById);
                break;
            case HsConstants.TY_TYPE:
                appById.setTyTime(new Date());
                appById.setTyAppr(user.getUsername());
                appById.setTySchedule(review.getTyschedule());
                appById.setTyNotes(review.getTyNotes());
                reviewBiz.updateSelectiveById(appById);
                break;
            case HsConstants.MZ_TYPE:
                appById.setMzTime(new Date());
                appById.setMzSchedule(review.getMzschedule());
                appById.setMzAppr(user.getUsername());
                appById.setMzNotes(review.getMzNotes());
                reviewBiz.updateSelectiveById(appById);
                break;
            case HsConstants.ZJ_TYPE:
                appById.setZjTime(new Date());
                appById.setZjSchedule(review.getZjschedule());
                appById.setZjAppr(user.getUsername());
                appById.setZjtellImage(review.getZjtellImage());
                appById.setZjNotes(review.getZjNotes());
                appById.setZjtellTime(new Date());
                reviewBiz.updateSelectiveById(appById);
                break;
            default:
                throw new UserInvalidException("类型不存在!,不能审核");
        }
    }


    public void turn(String token, int id) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        User user = userBiz.getUserByUsername(tokenModel.getUsername());
        Review review = reviewBiz.selectAppById(id);
        switch (user.getType()) {
            case HsConstants.VI_TYPE:
                review.setSchedule(-1);
                review.setAuAppr(user.getUsername());
                review.setAuTime(new Date());
                reviewBiz.updateSelectiveById(review);
                break;
            case HsConstants.WN_TYPE:
                review.setWnSchedule(-1);
                review.setWnAppr(user.getUsername());
                review.setWnTime(new Date());
                reviewBiz.updateSelectiveById(review);
                break;
            case HsConstants.TY_TYPE:
                review.setTySchedule(-1);
                review.setTyAppr(user.getUsername());
                review.setTyTime(new Date());
                reviewBiz.updateSelectiveById(review);
                break;
            case HsConstants.MZ_TYPE:
                review.setMzSchedule(-1);
                review.setMzAppr(user.getUsername());
                review.setMzTime(new Date());
                reviewBiz.updateSelectiveById(review);
                break;
            case HsConstants.ZJ_TYPE:
                review.setZjSchedule(-1);
                review.setZjAppr(user.getUsername());
                review.setZjTime(new Date());
                reviewBiz.updateSelectiveById(review);
                break;
            default:
                throw new UserInvalidException("角色不存在!,不能驳回");
        }
    }

}
