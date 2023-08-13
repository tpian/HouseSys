package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.modules.inspur.biz.MonitorBiz;

import com.github.wxiaoqi.security.modules.inspur.biz.UserinfoBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Monitor;

import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.entity.body.Monitorbody;
import com.github.wxiaoqi.security.modules.inspur.util.HsConstants;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import com.github.wxiaoqi.security.modules.inspur.vo.ArchivesVO;
import com.github.wxiaoqi.security.modules.inspur.vo.MonitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PermMonitorService {


    @Autowired
    private JwtTokenUtil userAuthUtil;
    @Autowired
    private UserinfoBiz userinfoBiz;
    @Autowired
    private MonitorBiz monitorBiz;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private Appservice appservice;


    public Map<String, String> istsrt(String token) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        ApprovalVO userId = appservice.getUserId(Integer.parseInt(tokenModel.getUserId()));
        if (userId == null) {
            //新注册用户
            map.put("start", "0");
            map.put("id", tokenModel.getUserId());
            return map;
        }
        if (userId.getZjschedule() != 1) {
            //住建局未审批完成
            map.put("start", "-1");
            map.put("id", tokenModel.getUserId());
            return map;
        }
        Monitor monitor = monitorBiz.gerUserId(Integer.parseInt(tokenModel.getUserId()));
        if (monitor != null) {
            //已动工
            map.put("start", "1");
            map.put("id", tokenModel.getUserId());
            return map;
        }
        //未动工
        map.put("start", "2");
        map.put("id", tokenModel.getUserId());
        return map;
    }


    public MonitorVO start(String token, Monitor monitor) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        ApprovalVO userId = appservice.getUserId(Integer.parseInt(tokenModel.getUserId()));
        if (userId.getZjschedule() != 1) {
            throw new UserInvalidException("住建局未审批完成!");
        }
        Monitor monito = monitorBiz.gerUserId(Integer.parseInt(tokenModel.getUserId()));
        if (monito != null) {
            throw new Exception("已动工!");
        }
        monitor.setCreateTime(new Date());
        monitor.setUserId(Integer.parseInt(tokenModel.getUserId()));
        monitorBiz.insertSelective(monitor);
        return monitorService.getUserId(Integer.parseInt(tokenModel.getUserId()));
    }


    public List<MonitorVO> list(String token, String keyword) throws Exception {
        List<MonitorVO> list;
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        switch (userinfo.getType()) {
            case HsConstants.VI_TYPE:
                list = monitorService.getvilist(userinfo.getCounty(), userinfo.getTown(), userinfo.getVillage(), keyword);
                break;
            case HsConstants.WN_TYPE:
                list = monitorService.getTownlist(userinfo.getCounty(), userinfo.getTown(), keyword);
                break;
            case HsConstants.TY_TYPE:
            case HsConstants.MZ_TYPE:
            case HsConstants.ZJ_TYPE:
                list = monitorService.getUserlist(userinfo.getCounty(), keyword);
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return list;
    }


    public MonitorVO getToken(String token) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        return monitorService.getUserId(Integer.parseInt(tokenModel.getUserId()));

    }


    public MonitorVO monit(String token, Monitorbody monitorbody) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        Monitor monitor = new Monitor();
        switch (monitorbody.getType()) {
            case HsConstants.FOUT:
                monitor.setId(monitorbody.getId());
                monitor.setFound(monitorbody.getImages());
                monitor.setFoundState(monitorbody.getStart());
                monitor.setFoundTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.PART:
                monitor.setId(monitorbody.getId());
                monitor.setPart(monitorbody.getImages());
                monitor.setPartState(monitorbody.getStart());
                monitor.setPartTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.CAPPED:
                monitor.setId(monitorbody.getId());
                monitor.setCapped(monitorbody.getImages());
                monitor.setCappedState(monitorbody.getStart());
                monitor.setCappedTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.ANCE:
                monitor.setId(monitorbody.getId());
                monitor.setAcceptance(monitorbody.getImages());
                monitor.setAceState(monitorbody.getStart());
                monitor.setAceTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.lia:
                monitor.setId(monitorbody.getId());
                monitor.setLiability(monitorbody.getImages());
                monitor.setLiabilityState(monitorbody.getStart());
                monitor.setLiabilityTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.INF:
                monitor.setId(monitorbody.getId());
                monitor.setInform(monitorbody.getImages());
                monitor.setInformState(monitorbody.getStart());
                monitor.setInformTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.che:
                monitor.setId(monitorbody.getId());
                monitor.setChecking(monitorbody.getImages());
                monitor.setCheckingState(monitorbody.getStart());
                monitor.setCheckingTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.urge:
                monitor.setId(monitorbody.getId());
                monitor.setUrge(monitorbody.getImages());
                monitor.setUrgeState(monitorbody.getStart());
                monitor.setUrgeTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.filing:
                monitor.setId(monitorbody.getId());
                monitor.setFiling(monitorbody.getImages());
                monitor.setFilingState(monitorbody.getStart());
                monitor.setFilingTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.TF:
                monitor.setId(monitorbody.getId());
                monitor.setTransfer(monitorbody.getImages());
                monitor.setTransferState(monitorbody.getStart());
                monitor.setTransferTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return monitorService.getId(monitorbody.getId());
    }


    public MonitorVO getId(int id) {
        return monitorService.getId(id);
    }


    public ArchivesVO archives(String token) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Monitor monitor = monitorBiz.gerUserId(Integer.parseInt(tokenModel.getUserId()));
        if (monitor.getFiling() != null) {
            throw new UserInvalidException("档案资料未完善!");
        }
        return monitorService.getarchivesId(Integer.parseInt(tokenModel.getUserId()));


    }

    public ArchivesVO getById(int userId) {

        return monitorService.getarchivesId(userId);

    }


    public Map<String, String> isfile(String token) throws Exception {
        Map<String, String> map = new HashMap<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Monitor monitor = monitorBiz.gerUserId(Integer.parseInt(tokenModel.getUserId()));
        if (monitor == null) {
            // 新用户
            map.put("isfile", "-1");
            map.put("id", tokenModel.getUserId());
            return map;
        }
        if (monitor.getFiling() != null) {
            // 有档案
            map.put("isfile", "1");
            map.put("id", tokenModel.getUserId());
            return map;
        }
        // 无档案
        map.put("isfile", "0");
        map.put("id", tokenModel.getUserId());
        return map;
    }


    public List<ArchivesVO> getfiles(String token, String keyword) throws Exception {
        List<ArchivesVO> list;
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        switch (userinfo.getType()) {
            case HsConstants.VI_TYPE:
                list = monitorService.getvifile(userinfo.getCounty(), userinfo.getTown(), userinfo.getVillage(), keyword);
                break;
            case HsConstants.WN_TYPE:
                list = monitorService.getTownfile(userinfo.getCounty(), userinfo.getTown(), keyword);
                break;
            case HsConstants.TY_TYPE:
            case HsConstants.MZ_TYPE:
            case HsConstants.ZJ_TYPE:
                list = monitorService.getUserfile(userinfo.getCounty(), keyword);
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return list;

    }


    public MonitorVO renew(String token, Monitorbody monitorbody) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo userinfo = userinfoBiz.UserByPhone(tokenModel.getPhone());
        if (!userinfo.getType().equals(2)) {
            throw new UserInvalidException("无权操作!");
        }
        Monitor monitor = new Monitor();
        switch (monitorbody.getType()) {
            case HsConstants.FOUT:
                monitor.setId(monitorbody.getId());
                monitor.setFoundState(monitorbody.getStart());
                monitor.setFoundTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.PART:
                monitor.setId(monitorbody.getId());
                monitor.setPartState(monitorbody.getStart());
                monitor.setPartTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;

            case HsConstants.CAPPED:
                monitor.setId(monitorbody.getId());
                monitor.setCappedState(monitorbody.getStart());
                monitor.setCappedTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            case HsConstants.ANCE:
                monitor.setId(monitorbody.getId());
                monitor.setAceState(monitorbody.getStart());
                monitor.setAceTime(new Date());
                monitorBiz.updateSelectiveById(monitor);
                break;
            default:
                throw new UserInvalidException("类型不存在!");
        }
        return monitorService.getId(monitorbody.getId());
    }

    public List<MonitorVO> weblist(MonitorVO monitorVO) {
        return monitorService.weblist(monitorVO);
    }

    public List<ArchivesVO> getInfo(ArchivesVO archivesVO) {
        return monitorService.getInfo(archivesVO);
    }

}
