package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.PubtyBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Pubty;
import com.github.wxiaoqi.security.modules.inspur.service.PermAppService;
import com.github.wxiaoqi.security.modules.inspur.service.PermPutyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pubty")
public class PubtyController extends BaseController<PubtyBiz, Pubty> {

    @Autowired
    private PermPutyService permPutyService;

    @ApiOperation(value = "宣传列表", notes = "get", tags = "宣传列表")
    @RequestMapping(value = "/v2/list", method = RequestMethod.GET)
    public TableResultResponse<Pubty> getPubty(@RequestParam(defaultValue = "10") int limit,
                                               @RequestParam(defaultValue = "1") int page, Pubty pubty) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<Pubty> list = permPutyService.getlist(pubty);
        return new TableResultResponse<Pubty>(startPage.getTotal(), list);
    }

    @ApiOperation(value = "宣传提交", notes = "post", tags = "政策宣传添加")
    @RequestMapping(value = "/v2/add", method = RequestMethod.POST)
    public ObjectRestResponse<Pubty> save(@RequestBody Pubty pubty) {
        permPutyService.save(pubty);
        return new ObjectRestResponse<Pubty>();
    }

    @ApiOperation(value = "宣传软删", notes = "DELETE", tags = "宣传软删")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<Pubty> delete(@PathVariable int id) {
        permPutyService.delete(id);
        return new ObjectRestResponse<Pubty>();
    }

    @ApiOperation(value = "宣传修改", notes = "post", tags = "宣传修改")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.POST)
    public ObjectRestResponse<Pubty> upubty(@RequestBody Pubty pubty) {
        permPutyService.update(pubty);
        return new ObjectRestResponse<Pubty>();
    }

    @ApiOperation(value = "宣传详情", notes = "get", tags = "宣传详情")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<Pubty> getPubty(@PathVariable int id) {
        Pubty pubty = permPutyService.getById(id);
        return new ObjectRestResponse<Pubty>().data(pubty);
    }


}
