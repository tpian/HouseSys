package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.modules.inspur.service.PermUserInfoService;

import com.github.wxiaoqi.security.modules.inspur.vo.PicUploadVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("home")
public class StatController {

    @Autowired
    private PermUserInfoService permUserInfoService;

    @ApiOperation(value = "危房改造--数据统计", notes = "get", tags = "危房改造--数据统计")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ObjectRestResponse<Map> stat() {
        Map stat = permUserInfoService.getstat();
        return new ObjectRestResponse<Map>().data(stat);
    }

}
