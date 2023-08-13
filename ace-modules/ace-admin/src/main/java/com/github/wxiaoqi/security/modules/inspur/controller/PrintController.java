package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.PrintBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Print;
import com.github.wxiaoqi.security.modules.inspur.service.PermPrinService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("print")
public class PrintController extends BaseController<PrintBiz, Print> {

    @Autowired
    private PermPrinService permPrinService;

    @ApiOperation(value = "图纸列表", notes = "get", tags = "图纸列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ObjectRestResponse<Print> list(@RequestParam("type") String type,
                                          @RequestParam("coverId") String coverId) {
        List<Print> list = permPrinService.list(type, coverId);
        return new ObjectRestResponse<List<Print>>().data(list);
    }

}
