package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.ReviewBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Review;

import com.github.wxiaoqi.security.modules.inspur.entity.body.Reviewby;
import com.github.wxiaoqi.security.modules.inspur.service.PermRevService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("revi")
public class ReviewController extends BaseController<ReviewBiz, Review> {

    @Autowired
    private PermRevService permRevService;

    @ApiOperation(value = "驳回", notes = "post", tags = "驳回")
    @RequestMapping(value = "/v1/{id}", method = RequestMethod.POST)
    public ObjectRestResponse<Review> turn(@RequestHeader("Authorization") String token,
                                           @PathVariable int id) throws Exception {
        permRevService.turn(token, id);
        return new ObjectRestResponse<Review>();
    }

    @ApiOperation(value = "审批", notes = "post", tags = "审批")
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ObjectRestResponse<Review> check(@RequestHeader("Authorization") String token,
                                            @RequestBody Reviewby review) throws Exception {
        permRevService.check(token, review);
        return new ObjectRestResponse<Review>();
    }

}
