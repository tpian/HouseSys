package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.modules.inspur.util.PicUploadUtil;
import com.github.wxiaoqi.security.modules.inspur.vo.PicUploadVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("oss")
public class OssController {

    @Autowired
    private PicUploadUtil picUploadUtil;

    @ApiOperation(value = "OSS上传图片", notes = "post", tags = "OSS上传图片")
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse upload(@RequestParam("file") MultipartFile multipartFile) {
        PicUploadVO upload = picUploadUtil.upload(multipartFile);
        if (upload.getStatus().equals("error")) {
            throw new UserInvalidException("网络不稳定 ,请重试");
        }
        return new ObjectRestResponse<>().data(upload);
    }
}
