package com.github.wxiaoqi.security.modules.admin.rest;

import com.alibaba.nacos.common.utils.StringUtils;
import com.github.wxiaoqi.security.api.vo.user.UserInfo;
import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.admin.biz.MenuBiz;
import com.github.wxiaoqi.security.modules.admin.biz.UserBiz;
import com.github.wxiaoqi.security.modules.admin.entity.Menu;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.admin.rpc.service.PermissionService;
import com.github.wxiaoqi.security.modules.admin.vo.FrontUser;
import com.github.wxiaoqi.security.modules.admin.vo.FrontUserV2;
import com.github.wxiaoqi.security.modules.admin.vo.MenuTree;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;

import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import io.jsonwebtoken.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author the sun
 * @create 2017-06-08 11:51
 */
@Slf4j
@Api("基础信息模块")
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz, User> {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuBiz menuBiz;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<User> saveUserInfo(@RequestBody User user) {
        log.info(user.getUsername() + "register ing.......");
        permissionService.save(user);
        return new ObjectRestResponse<User>();
    }

    @RequestMapping(value = "v1/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<User> upuser(@RequestBody User user) {
        permissionService.update(user);
        return new ObjectRestResponse<User>();
    }


    @RequestMapping(value = "v1/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<User> dluser(@PathVariable int id) {
        permissionService.dluser(id);
        return new ObjectRestResponse<User>();
    }


    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if (userInfo == null) {
            return ResponseEntity.status(401).body(false);
        }
        return ResponseEntity.ok(userInfo);
    }



    @RequestMapping(value = "/v2/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse getUserInfoV2() throws Exception {
        FrontUserV2 userInfo = permissionService.getUserInfoV2();
        return new ObjectRestResponse<FrontUserV2>().data(userInfo);
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return menuBiz.selectListAll();
    }
}
