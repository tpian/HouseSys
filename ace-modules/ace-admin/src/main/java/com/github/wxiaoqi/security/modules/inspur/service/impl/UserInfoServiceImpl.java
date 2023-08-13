package com.github.wxiaoqi.security.modules.inspur.service.impl;


import com.github.wxiaoqi.security.api.vo.user.UserInfo;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.mapper.UserinfoMapper;
import com.github.wxiaoqi.security.modules.inspur.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public List<Userinfo> getfarmlist(String phone, String keyword) {

        return userinfoMapper.getfarmlist(phone, keyword);
    }

    @Override
    public List<Userinfo> getvilist(String county, String town, String village, String keyword) {
        return userinfoMapper.getvilist(county, town, village, keyword);
    }

    @Override
    public List<Userinfo> getTownlist(String county, String town, String keyword) {
        return userinfoMapper.getTownlist(county, town, keyword);
    }

    @Override
    public List<Userinfo> getUserlist(String county, String keyword) {

        return userinfoMapper.getUserlist(county, keyword);
    }

    @Override
    public List<Userinfo> getweblist(Userinfo userinfo) {
        return userinfoMapper.getweblist(userinfo);
    }


    @Override
    public List<Userinfo> getwebvilist(String county, String town, String village, Userinfo userinfo) {
        return userinfoMapper.getwebvilist(county, town, village, userinfo);
    }

    @Override
    public List<Userinfo> getwebTownlist(String county, String town, Userinfo userinfo) {
        return userinfoMapper.getwebTownlist(county, town, userinfo);
    }

    @Override
    public List<Userinfo> getwebUserlist(String county, Userinfo userinfo) {
        return userinfoMapper.getwebUserlist(county, userinfo);
    }

    @Override
    public Map stat() {
        return userinfoMapper.stat();
    }


}
