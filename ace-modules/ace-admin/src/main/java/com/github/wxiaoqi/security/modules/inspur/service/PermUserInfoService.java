package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wxiaoqi.security.api.vo.user.UserInfo;
import com.github.wxiaoqi.security.common.exception.auth.UserException;
import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.modules.admin.biz.UserBiz;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.admin.util.Sha256PasswordEncoder;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.modules.inspur.biz.UserinfoBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.util.HsConstants;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PermUserInfoService {
    @Autowired
    private JwtTokenUtil userAuthUtil;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserinfoBiz userinfoBiz;

    @Autowired
    private UserBiz userBiz;

    private Sha256PasswordEncoder encoder = new Sha256PasswordEncoder();


    public List<Userinfo> getUserToken(String token, String keyword) throws Exception {
        List<Userinfo> userinfos = new ArrayList<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        Userinfo user = userinfoBiz.UserByPhone(tokenModel.getPhone());
        switch (user.getType()) {
            case HsConstants.FARMERS:
                userinfos = userInfoService.getfarmlist(user.getMobilePhone(), keyword);
                break;
            case HsConstants.VI_TYPE:
                userinfos = userInfoService.getvilist(user.getCounty(), user.getTown(), user.getVillage(), keyword);
                break;
            case HsConstants.WN_TYPE:
                userinfos = userInfoService.getTownlist(user.getCounty(), user.getTown(), keyword);
                break;
            case HsConstants.TY_TYPE:
            case HsConstants.MZ_TYPE:
            case HsConstants.ZJ_TYPE:
                userinfos = userInfoService.getUserlist(user.getCounty(), keyword);
                break;
            default:
                throw new UserInvalidException("数据不存在!");
        }
        return userinfos;
    }

    public void updateUser(String token, Userinfo userinfo) throws Exception {
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        if (!tokenModel.getUserId().equals(userinfo.getId().toString())) {
            throw new UserInvalidException("非本人资料,不能修改");
        }
        userinfoBiz.updateSelectiveById(userinfo);
    }


    public UserInfo valiphone(String phone, String password) {
        UserInfo info = new UserInfo();
        Userinfo userinfo = userinfoBiz.UserByPhone(phone);
        if (userinfo == null) {
            throw new UserInvalidException("用户不存在!");
        }
        if (!encoder.matches(password, userinfo.getPassword())) {
            throw new UserException("账户密码错误!");
        }
        if (encoder.matches(password, userinfo.getPassword())) {
            BeanUtils.copyProperties(userinfo, info);
            info.setId(userinfo.getId().toString());
            info.setPhone(userinfo.getMobilePhone());
        }
        return info;
    }

    public void add(Userinfo userinfo) {
        Userinfo user = userinfoBiz.UserByPhone(userinfo.getMobilePhone());
        if (user != null) {
            throw new UserInvalidException("手机号已注册");
        }
        userinfo.setUsername(userinfo.getName());
        userinfo.setPassword("123456");
        userinfo.setAddress(userinfo.getCounty() + userinfo.getTown() + userinfo.getVillage() + userinfo.getDplate());
        userinfoBiz.insertSelective(userinfo);

    }

    public void del(int id) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        userinfo.setIsDelete(1);
        userinfoBiz.updateSelectiveById(userinfo);
    }


    public void update(Userinfo userinfo) {
        userinfo.setAddress(userinfo.getCounty() + userinfo.getTown() + userinfo.getVillage() + userinfo.getDplate());
        userinfoBiz.updateSelectiveById(userinfo);
    }

    public PageInfo<Userinfo> list(String token, Userinfo userinfo,int page,int limit) throws Exception {
        List<Userinfo> userinfos = new ArrayList<>();
        TokenModel tokenModel = userAuthUtil.getfromToken(token);
        User user = userBiz.getUserByUsername(tokenModel.getUsername());
        switch (user.getType()) {
            case HsConstants.VI_TYPE:
                PageHelper.startPage(page, limit);
                userinfos = userInfoService.getwebvilist(user.getCounty(), user.getTown(), user.getVillage(), userinfo);
                break;
            case HsConstants.WN_TYPE:
                PageHelper.startPage(page, limit);
                userinfos = userInfoService.getwebTownlist(user.getCounty(), user.getTown(), userinfo);
                break;
            case HsConstants.TY_TYPE:
            case HsConstants.MZ_TYPE:
            case HsConstants.ZJ_TYPE:
                PageHelper.startPage(page, limit);
                userinfos = userInfoService.getwebUserlist(user.getCounty(), userinfo);
                break;
            default:
                throw new UserInvalidException("数据不存在!");
        }
        return new PageInfo<Userinfo>(userinfos);
    }


    public Map getstat() {
        return userInfoService.stat();
    }

}
