package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.modules.admin.biz.UserBiz;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.inspur.biz.UserinfoBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.util.WxloginUtil;
import com.github.wxiaoqi.security.modules.inspur.util.JwtAuthenRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxloginUtil wxloginUtil;
    @Autowired
    private UserinfoBiz userinfoBiz;

    @ApiOperation(value = "小程序账号登录", notes = "post", tags = "小程序账号登录")
    @PostMapping("/login")
    public ObjectRestResponse<String> applogin(@RequestBody JwtAuthenRequest jwtAuthenRequest) throws Exception {
        log.info(jwtAuthenRequest.getPhone() + " require logging...");
        Map applogin = wxloginUtil.login(jwtAuthenRequest);
        return new ObjectRestResponse<>().data(applogin);
    }

    @ApiOperation(value = "小程序注册新用户", notes = "post", tags = "小程序注册新用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<Userinfo> saveUser(@RequestBody Userinfo userinfo)  {
        log.info(userinfo.getMobilePhone() + "register ing.......");
        Userinfo userinf = userinfoBiz.UserByPhone(userinfo.getMobilePhone());
        if (userinf != null) {
            throw new UserInvalidException("手机号已注册,请登录");
        }
        userinfo.setUsername(userinfo.getName());
        userinfo.setPassword("123456");
        userinfo.setAddress(userinfo.getCounty() + userinfo.getTown() + userinfo.getVillage() + userinfo.getDplate());
        userinfoBiz.insertSelective(userinfo);
        return new ObjectRestResponse<Userinfo>();
    }

}
