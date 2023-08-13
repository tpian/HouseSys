package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wxiaoqi.security.common.exception.auth.AppException;
import com.github.wxiaoqi.security.common.exception.auth.UserException;
import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.modules.admin.biz.UserBiz;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.modules.inspur.biz.AppBiz;
import com.github.wxiaoqi.security.modules.inspur.biz.ReviewBiz;
import com.github.wxiaoqi.security.modules.inspur.biz.UserinfoBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.App;
import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.entity.body.AppBody;
import com.github.wxiaoqi.security.modules.inspur.entity.body.Appro;
import com.github.wxiaoqi.security.modules.inspur.entity.Review;
import com.github.wxiaoqi.security.modules.inspur.entity.body.Apps;
import com.github.wxiaoqi.security.modules.inspur.util.DateUtil;
import com.github.wxiaoqi.security.modules.inspur.util.HsConstants;
import com.github.wxiaoqi.security.modules.inspur.util.PushService;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class PermAppService {


    @Autowired
    private JwtTokenUtil userAuthUtil;
    @Autowired
    private AppBiz appBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private UserinfoBiz userinfoBiz;
    @Autowired
    private ReviewBiz reviewBiz;
    @Autowired
    private Appservice appservice;
    @Autowired
    private PushService pushService;


    public Map isubmit(String token) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        App user = appBiz.getUserById(Integer.parseInt(tokenModel.getUserId()));
        if (user != null) {
            //建房已提交申请
            map.put("state", "1");
            return map;
        }
        //建房未提交申请
        map.put("state", "0");
        return map;

    }


    public ApprovalVO submit(String token, Appro app) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        App user = appBiz.getUserById(Integer.parseInt(tokenModel.getUserId()));
        if (user != null) {
            throw new AppException("建房资料已提交");
        }
        app.getApp().setOpenid(tokenModel.getOpenId());
        app.getApp().setUserId(Integer.valueOf(tokenModel.getUserId()));
        app.getApp().setCreaTime(new Date());
        appBiz.insertSelective(app.getApp());
        App userById = appBiz.getUserById(Integer.parseInt(tokenModel.getUserId()));
        Review review = new Review();
        review.setAppId(userById.getId());
        review.setCreaTime(new Date());
        reviewBiz.insertSelective(review);
        return appservice.getUserId(Integer.parseInt(tokenModel.getUserId()));
    }


    public Map subm(int id) {
        HashMap<String, String> map = new HashMap<>();
        App user = appBiz.getUserById(id);
        if (user != null) {
            //建房已提交申请
            map.put("state", "1");
            return map;
        }
        Userinfo userinfo = userinfoBiz.selectById(id);
        if (!userinfo.getType().equals(0)) {
            //该身份不是农户,谨慎提交
            map.put("state", "2");
            return map;
        }
        //建房未提交申请
        map.put("state", "0");
        return map;

    }


    public ApprovalVO websubm(int id, App app) {
        app.setUserId(id);
        app.setCreaTime(new Date());
        appBiz.insertSelective(app);
        App userById = appBiz.getUserById(id);
        Review review = new Review();
        review.setAppId(userById.getId());
        review.setCreaTime(new Date());
        reviewBiz.insertSelective(review);
        return appservice.getUserId(id);
    }


    public ApprovalVO selectById(int id) {
        return appservice.selectById(id);

    }


    public List<ApprovalVO> list(String token, String keyword) throws Exception {
        List<ApprovalVO> list = new ArrayList<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        switch (userinfo.getType()) {
            case HsConstants.VI_TYPE:
                list = appservice.getvilist(userinfo.getCounty(), userinfo.getTown(), userinfo.getVillage(), keyword);
                break;
            case HsConstants.WN_TYPE:
                list = appservice.getTownlist(userinfo.getCounty(), userinfo.getTown(), keyword);
                break;
            case HsConstants.TY_TYPE:
                list = appservice.getUserlist(userinfo.getCounty(), keyword);
                break;
            case HsConstants.MZ_TYPE:
                list = appservice.getmzlist(userinfo.getCounty(), keyword);
                break;
            case HsConstants.ZJ_TYPE:
                list = appservice.getzjlist(userinfo.getCounty(), keyword);
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return list;
    }


    public ApprovalVO schedule(String token) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        if (tokenModel.getUserId() == null) {
            throw new UserInvalidException("token格式有误");
        }
        ApprovalVO approvalVO = appservice.getUserId(Integer.parseInt(tokenModel.getUserId()));
        if (approvalVO != null) {
            return approvalVO;
        } else {
            return appservice.getinfoId(Integer.parseInt(tokenModel.getUserId()));
        }

    }


    public ApprovalVO approval(String token, AppBody appBody) throws Exception {
        ApprovalVO app = new ApprovalVO();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        Review review = reviewBiz.selectAppById(appBody.getAppid());
        switch (userinfo.getType()) {
            case HsConstants.VI_TYPE:
                if (review.getSchedule().equals(2)) {
                    throw new UserInvalidException("村干部已同意! 但是未上传公示照片");
                }
                if (appBody.getSchedule().equals(-1)) {
                    review.setId(review.getId());
                    review.setAuTime(new Date());
                    review.setSchedule(-1);
                    review.setAuAppr(tokenModel.getName());
                    review.setIsDelete(1);
                    reviewBiz.updateSelectiveById(review);
                    App ap = new App();
                    ap.setId(appBody.getAppid());
                    ap.setIsDelete(1);
                    appBiz.updateSelectiveById(ap);
                    app = appservice.getAppId(appBody.getAppid());
                    pushService.turn(app);
                }
                review.setId(review.getId());
                review.setAuTime(new Date());
                review.setSchedule(2);
                review.setAuAppr(tokenModel.getName());
                reviewBiz.updateSelectiveById(review);
                app = appservice.getAppId(appBody.getAppid());
                pushService.push(app);
                break;
            case HsConstants.WN_TYPE:
                if (review.getWnSchedule().equals(2)) {
                    throw new UserInvalidException("镇干部已同意! 但是未上传公示照片");
                }
                if (appBody.getSchedule().equals(-1)) {
                    review.setId(review.getId());
                    review.setWnTime(new Date());
                    review.setWnSchedule(-1);
                    review.setWnAppr(tokenModel.getName());
                    review.setIsDelete(1);
                    reviewBiz.updateSelectiveById(review);
                    App ap = new App();
                    ap.setId(appBody.getAppid());
                    ap.setIsDelete(1);
                    appBiz.updateSelectiveById(ap);
                    app = appservice.getAppId(appBody.getAppid());
                    pushService.turn(app);
                }
                review.setId(review.getId());
                review.setWnTime(new Date());
                review.setWnSchedule(2);
                review.setWnAppr(tokenModel.getName());
                reviewBiz.updateSelectiveById(review);
                app = appservice.getAppId(appBody.getAppid());
                pushService.push(app);
                break;
            case HsConstants.TY_TYPE:
                if (appBody.getSchedule().equals(-1)) {
                    review.setId(review.getId());
                    review.setTyTime(new Date());
                    review.setTySchedule(-1);
                    review.setTyAppr(tokenModel.getName());
                    review.setIsDelete(1);
                    reviewBiz.updateSelectiveById(review);
                    App ap = new App();
                    ap.setId(appBody.getAppid());
                    ap.setIsDelete(1);
                    appBiz.updateSelectiveById(ap);
                    app = appservice.getAppId(appBody.getAppid());
                    pushService.turn(app);
                }
                review.setId(review.getId());
                review.setTyTime(new Date());
                review.setTySchedule(1);
                review.setTyAppr(tokenModel.getName());
                reviewBiz.updateSelectiveById(review);
                app = appservice.getAppId(appBody.getAppid());
                pushService.push(app);
                break;
            case HsConstants.MZ_TYPE:
                if (appBody.getSchedule().equals(-1)) {
                    review.setId(review.getId());
                    review.setMzTime(new Date());
                    review.setMzSchedule(-1);
                    review.setMzAppr(tokenModel.getName());
                    review.setIsDelete(1);
                    reviewBiz.updateSelectiveById(review);
                    App ap = new App();
                    ap.setId(appBody.getAppid());
                    ap.setIsDelete(1);
                    appBiz.updateSelectiveById(ap);
                    app = appservice.getAppId(appBody.getAppid());
                    pushService.turn(app);
                }
                review.setId(review.getId());
                review.setMzTime(new Date());
                review.setMzSchedule(1);
                review.setMzAppr(tokenModel.getName());
                reviewBiz.updateSelectiveById(review);
                app = appservice.getAppId(appBody.getAppid());
                pushService.push(app);
                break;
            case HsConstants.ZJ_TYPE:
                if (review.getZjSchedule().equals(2)) {
                    throw new UserInvalidException("住建干部已同意! 但是未上传公示照片");
                }
                if (appBody.getSchedule().equals(-1)) {
                    review.setId(review.getId());
                    review.setZjTime(new Date());
                    review.setZjSchedule(-1);
                    review.setZjAppr(tokenModel.getName());
                    review.setIsDelete(1);
                    reviewBiz.updateSelectiveById(review);
                    App ap = new App();
                    ap.setId(appBody.getAppid());
                    ap.setIsDelete(1);
                    appBiz.updateSelectiveById(ap);
                    app = appservice.getAppId(appBody.getAppid());
                    pushService.turn(app);
                }
                review.setId(review.getId());
                review.setZjTime(new Date());
                review.setZjSchedule(2);
                review.setZjAppr(tokenModel.getName());
                reviewBiz.updateSelectiveById(review);
                app = appservice.getAppId(appBody.getAppid());
                pushService.push(app);
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return app;
    }


    public void publicity(String token, Apps apps) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        Review review = reviewBiz.selectAppById(apps.getKey().getAppid());
        switch (userinfo.getType()) {
            case HsConstants.VI_TYPE:
                if (apps.getKey() != null) {
                    review.setRecord(apps.getKey().getRecord());
                    review.setAutellImage(apps.getKey().getTellImage());
                    review.setScene(apps.getKey().getScene());
                    review.setReceipt(apps.getKey().getReceipt());
                    review.setAutellTime(new Date());
                    review.setSchedule(1);
                    reviewBiz.updateSelectiveById(review);
                }
                break;
            case HsConstants.WN_TYPE:
                if (apps.getKey() != null) {
                    review.setFieldwn(apps.getKey().getFieldwn());
                    review.setWntellImage(apps.getKey().getTellImage());
                    review.setScenewn(apps.getKey().getScene());
                    review.setReceiptwn(apps.getKey().getReceipt());
                    review.setHold(apps.getKey().getHold());
                    review.setProtocol(apps.getKey().getProtocol());
                    review.setWntellTime(new Date());
                    review.setWnSchedule(1);
                    reviewBiz.updateSelectiveById(review);
                }
                break;
            case HsConstants.ZJ_TYPE:
                if (apps.getKey() != null) {
                    review.setZjtellImage(apps.getKey().getTellImage());
                    review.setZjtellTime(new Date());
                    review.setZjSchedule(1);
                    reviewBiz.updateSelectiveById(review);
                }
                break;
            default:
                throw new UserInvalidException("干部类型不存在!");
        }
    }


    public Map getcount(String token) throws Exception {
        Map map;
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        switch (userinfo.getType()) {
            case HsConstants.VI_TYPE:
                map = appservice.getvicount(userinfo.getCounty(), userinfo.getTown(), userinfo.getVillage());
                break;
            case HsConstants.WN_TYPE:
                map = appservice.getTowncount(userinfo.getCounty(), userinfo.getTown());
                break;
            case HsConstants.TY_TYPE:
                map = appservice.getUsercount(userinfo.getCounty());
                break;
            case HsConstants.MZ_TYPE:
                map = appservice.getmzcount(userinfo.getCounty());
                break;
            case HsConstants.ZJ_TYPE:
                map = appservice.getzjcount(userinfo.getCounty());
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return map;
    }

    public PageInfo<ApprovalVO> weblist(String token, ApprovalVO approvalVO, int page, int limit) throws Exception {
        List<ApprovalVO> list = new ArrayList<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        User user = userBiz.getUserByUsername(tokenModel.getUsername());
        switch (user.getType()) {
            case HsConstants.VI_TYPE:
                PageHelper.startPage(page, limit);
                list = appservice.getwebvilist(user.getCounty(), user.getTown(), user.getVillage(), approvalVO);
                break;
            case HsConstants.WN_TYPE:
                PageHelper.startPage(page, limit);
                list = appservice.getwebTownlist(user.getCounty(), user.getTown(), approvalVO);
                break;
            case HsConstants.TY_TYPE:
                PageHelper.startPage(page, limit);
                list = appservice.getwebUserlist(user.getCounty(), approvalVO);
                break;
            case HsConstants.MZ_TYPE:
                PageHelper.startPage(page, limit);
                list = appservice.getwebmzlist(user.getCounty(), approvalVO);
                break;
            case HsConstants.ZJ_TYPE:
                PageHelper.startPage(page, limit);
                list = appservice.getwebzjlist(user.getCounty(), approvalVO);
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return new PageInfo<>(list);
    }


    public Map getviolation(String token, int appId) throws Exception {
        Map<String, String> map = new HashMap<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        ApprovalVO vo = appservice.getAppId(appId);
        switch (userinfo.getType()) {
            case HsConstants.VI_TYPE:
                String s = DateUtil.date(vo.getAuTime());
                String format = DateUtil.date(new Date());
                long date = DateUtil.paseDate(s, format);
                if (date < 3) {
                    map.put("warn", "0");
                } else {
                    map.put("warn", "1");
                }
                break;
            case HsConstants.WN_TYPE:
                String date1 = DateUtil.date(vo.getWnTime());
                String date2 = DateUtil.date(new Date());
                long date3 = DateUtil.paseDate(date1, date2);
                if (date3 < 3) {
                    map.put("warn", "0");
                } else {
                    map.put("warn", "1");
                }
                break;
            case HsConstants.ZJ_TYPE:
                String date04 = DateUtil.date(vo.getZjTime());
                String date05 = DateUtil.date(new Date());
                long date06 = DateUtil.paseDate(date04, date05);
                if (date06 < 3) {
                    map.put("warn", "0");
                } else {
                    map.put("warn", "1");
                }
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return map;
    }
}
