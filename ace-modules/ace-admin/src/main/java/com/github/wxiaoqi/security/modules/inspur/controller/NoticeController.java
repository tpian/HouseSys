package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.NoticeBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Notice;
import com.github.wxiaoqi.security.modules.inspur.service.PermNoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notice")
public class NoticeController extends BaseController<NoticeBiz, Notice> {


    @Autowired
    private PermNoticeService permNoticeService;


    @ApiOperation(value = "事件通报列表", notes = "get", tags = "事件通报列表")
    @RequestMapping(value = "/v2/list", method = RequestMethod.GET)
    public TableResultResponse<Notice> getById(@RequestParam(defaultValue = "10") int limit,
                                               @RequestParam(defaultValue = "1") int page, Notice notice) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<Notice> list = permNoticeService.getlist(notice);
        return new TableResultResponse<Notice>(startPage.getTotal(), list);
    }

    @ApiOperation(value = "事件通报添加", notes = "post", tags = "事件通报添加")
    @RequestMapping(value = "/v2/add", method = RequestMethod.POST)
    public ObjectRestResponse<Notice> save(@RequestBody Notice notice) {
        permNoticeService.save(notice);
        return new ObjectRestResponse<Notice>();
    }


    @ApiOperation(value = "事件通报软删", notes = "delete", tags = "事件通报软删")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<Notice> delete(@PathVariable int id) {
        permNoticeService.delete(id);
        return new ObjectRestResponse<Notice>();
    }

    @ApiOperation(value = "事件通报更新", notes = "post", tags = "事件通报更新")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.POST)
    public ObjectRestResponse<Notice> upnotice(@RequestBody Notice notice) {
        permNoticeService.update(notice);
        return new ObjectRestResponse<Notice>();
    }

    @ApiOperation(value = "事件通报详情", notes = "get", tags = "事件通报详情")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<Notice> getById(@PathVariable int id) {
        Notice notice = permNoticeService.getById(id);
        return new ObjectRestResponse<Notice>().data(notice);
    }


}
