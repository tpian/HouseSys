package com.github.wxiaoqi.security.modules.inspur.controller;

import com.alibaba.nacos.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.inspur.biz.UserinfoBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.service.PermUserInfoService;
import io.jsonwebtoken.lang.Assert;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userinfo")
public class UserinfoController extends BaseController<UserinfoBiz, Userinfo> {

    @Autowired
    private PermUserInfoService permUserInfoService;


    @ApiOperation(value = "基础信息查询", notes = "get", tags = "基础信息模块")
    @RequestMapping(value = "/v2/list", method = RequestMethod.GET)
    public TableResultResponse<Userinfo> userInfo(@RequestHeader("Authorization") String token,
                                                  String keyword) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(token), "必须提供token");
        List<Userinfo> userToken = permUserInfoService.getUserToken(token, keyword);
        return new TableResultResponse<Userinfo>(userToken.size(), userToken);
    }

    @ApiOperation(value = "基础信息修改", notes = "put", tags = "基础信息模块")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<Userinfo> updateuser(@RequestHeader("Authorization") String token,
                                                   @RequestBody Userinfo userinfo) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(token), "必须提供token");
        permUserInfoService.updateUser(token, userinfo);
        return new ObjectRestResponse<Userinfo>();
    }


    /*** ******************************web端**********************************/

    @ApiOperation(value = "web----基础信息列表", notes = "get", tags = "web----基础信息列表")
    @RequestMapping(value = "/web/list", method = RequestMethod.GET)
    public TableResultResponse<Userinfo> list(@RequestHeader("Authorization") String token,
                                              @RequestParam(defaultValue = "10") int limit,
                                              @RequestParam(defaultValue = "1") int page, Userinfo userinfo) throws Exception {

        PageInfo<Userinfo> list = permUserInfoService.list(token, userinfo, page, limit);
        return new TableResultResponse<Userinfo>(list.getTotal(), list.getList());
    }

    @ApiOperation(value = "web端--添加用户", notes = "post", tags = "web端--添加用户")
    @RequestMapping(value = "/web/add", method = RequestMethod.POST)
    public ObjectRestResponse<Userinfo> saveUser(@RequestBody Userinfo userinfo) {
        permUserInfoService.add(userinfo);
        return new ObjectRestResponse<Userinfo>();
    }

    @ApiOperation(value = "web端--基础信息软删", notes = "DELETE", tags = "web端--基础信息软删")
    @RequestMapping(value = "/web/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<Userinfo> dal(@PathVariable int id) {
        permUserInfoService.del(id);
        return new ObjectRestResponse<Userinfo>();
    }

    @ApiOperation(value = "web----基础信息修改", notes = "put", tags = "web----基础信息修改")
    @RequestMapping(value = "/web/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<Userinfo> update(@RequestBody Userinfo userinfo) {
        permUserInfoService.update(userinfo);
        return new ObjectRestResponse<Userinfo>();
    }
}
