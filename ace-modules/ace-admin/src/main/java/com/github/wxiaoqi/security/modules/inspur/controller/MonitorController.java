package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.MonitorBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Monitor;
import com.github.wxiaoqi.security.modules.inspur.entity.body.Monitorbody;
import com.github.wxiaoqi.security.modules.inspur.service.PermMonitorService;
import com.github.wxiaoqi.security.modules.inspur.vo.MonitorVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("monitor")
public class MonitorController extends BaseController<MonitorBiz, Monitor> {


    @Autowired
    private PermMonitorService permMonitorService;


    @ApiOperation(value = "是否允许已动工", notes = "get", tags = "是否允许已动工")
    @RequestMapping(value = "/v2/start", method = RequestMethod.GET)
    public ObjectRestResponse<Map> getStart(@RequestHeader("Authorization") String token) throws Exception {
        Map<String, String> start = permMonitorService.istsrt(token);
        return new ObjectRestResponse<Map>().data(start);
    }

    @ApiOperation(value = "开始动工", notes = "post", tags = "开始动工")
    @RequestMapping(value = "/v2/add", method = RequestMethod.POST)
    public ObjectRestResponse<MonitorVO> start(@RequestHeader("Authorization") String token,
                                               @RequestBody Monitor monitor) throws Exception {
        MonitorVO start = permMonitorService.start(token, monitor);
        return new ObjectRestResponse<MonitorVO>().data(start);
    }

    @ApiOperation(value = "农户动工-动态监管进度", notes = "get", tags = "农户动工-动态监管进度")
    @RequestMapping(value = "/v2/token", method = RequestMethod.GET)
    public ObjectRestResponse<MonitorVO> gitinfo(@RequestHeader("Authorization") String token) throws Exception {
        MonitorVO monitorVO = permMonitorService.getToken(token);
        return new ObjectRestResponse<MonitorVO>().data(monitorVO);
    }

    @ApiOperation(value = "干部查看动工列表", notes = "get", tags = "干部查看动工列表")
    @RequestMapping(value = "/v1/list", method = RequestMethod.GET)
    public TableResultResponse<MonitorVO> list(@RequestHeader("Authorization") String token,
                                               String keyword) throws Exception {
        List<MonitorVO> list = permMonitorService.list(token, keyword);
        return new TableResultResponse<MonitorVO>(list.size(), list);
    }


    @ApiOperation(value = "动态监管详情", notes = "get", tags = "动态监管详情")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<MonitorVO> review(@PathVariable int id) {
        MonitorVO monitorVO = permMonitorService.getId(id);
        return new ObjectRestResponse<MonitorVO>().data(monitorVO);
    }


    @ApiOperation(value = "动态监管-现场审查", notes = "post", tags = "动态监管-现场审查")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.POST)
    public ObjectRestResponse<MonitorVO> review(@RequestHeader("Authorization") String token,
                                                @RequestBody Monitorbody monitor) throws Exception {
        MonitorVO monit = permMonitorService.monit(token, monitor);
        return new ObjectRestResponse<MonitorVO>().data(monit);
    }

    @ApiOperation(value = "动态监管-暂停恢复施工", notes = "post", tags = "动态监管-暂停恢复施工")
    @RequestMapping(value = "/v3/{id}", method = RequestMethod.POST)
    public ObjectRestResponse<MonitorVO> stop(@RequestHeader("Authorization") String token,
                                              @RequestBody Monitorbody monitor) throws Exception {
        MonitorVO monit = permMonitorService.renew(token, monitor);
        return new ObjectRestResponse<MonitorVO>().data(monit);
    }

    /*** ******************************web端**********************************/

    @ApiOperation(value = "web端--动态监管列表", notes = "get", tags = "web端--动态监管列表")
    @RequestMapping(value = "/web/list", method = RequestMethod.GET)
    public TableResultResponse<MonitorVO> weblist(@RequestParam(defaultValue = "10") int limit,
                                                  @RequestParam(defaultValue = "1") int page, MonitorVO monitorVO) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<MonitorVO> list = permMonitorService.weblist(monitorVO);
        return new TableResultResponse<MonitorVO>(startPage.getTotal(), list);
    }

    @ApiOperation(value = "web端---动态监管详情", notes = "get", tags = "web端---动态监管详情")
    @RequestMapping(value = "/web/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<MonitorVO> getwebid(@PathVariable int id) {
        MonitorVO monitorVO = permMonitorService.getId(id);
        return new ObjectRestResponse<MonitorVO>().data(monitorVO);
    }

}
