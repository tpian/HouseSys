package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.AppBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.App;
import com.github.wxiaoqi.security.modules.inspur.entity.body.AppBody;
import com.github.wxiaoqi.security.modules.inspur.entity.body.Appro;
import com.github.wxiaoqi.security.modules.inspur.entity.body.Apps;
import com.github.wxiaoqi.security.modules.inspur.service.PermAppService;
import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("app")
public class AppController extends BaseController<AppBiz, App> {

    @Autowired
    private PermAppService permAppService;

    @ApiOperation(value = "待审核数量", notes = "get", tags = "待审核数量")
    @RequestMapping(value = "/v1/count", method = RequestMethod.GET)
    public ObjectRestResponse<Map> count(@RequestHeader("Authorization") String token) throws Exception {
        Map getcount = permAppService.getcount(token);
        log.info("getcount:{}", getcount);
        return new ObjectRestResponse<Map>().data(getcount);
    }

    @ApiOperation(value = "是否提交建房申请", notes = "get", tags = "是否提交建房申请")
    @RequestMapping(value = "/v2/submit", method = RequestMethod.GET)
    public ObjectRestResponse<Map> isubmit(@RequestHeader("Authorization") String token) throws Exception {
        Map map = permAppService.isubmit(token);
        return new ObjectRestResponse<Map>().data(map);
    }

    @ApiOperation(value = "建房申报", notes = "post", tags = "建房申报")
    @RequestMapping(value = "/v2/add", method = RequestMethod.POST)
    public ObjectRestResponse<ApprovalVO> submit(@RequestHeader("Authorization") String token,
                                                 @RequestBody Appro appro) throws Exception {
        ApprovalVO submit = permAppService.submit(token, appro);
        return new ObjectRestResponse<ApprovalVO>().data(submit);
    }

    @ApiOperation(value = "农户查看进度", notes = "get", tags = "农户审批进度")
    @RequestMapping(value = "/v2/list", method = RequestMethod.GET)
    public ObjectRestResponse<ApprovalVO> schedule(@RequestHeader("Authorization") String token) throws Exception {
        ApprovalVO approval = permAppService.schedule(token);
        return new ObjectRestResponse<ApprovalVO>().data(approval);
    }

    @ApiOperation(value = "审批列表", notes = "get", tags = "审批列表")
    @RequestMapping(value = "/v1/list", method = RequestMethod.GET)
    public ObjectRestResponse<ApprovalVO> list(@RequestHeader("Authorization") String token,
                                               String keyword) throws Exception {
        List<ApprovalVO> list = permAppService.list(token, keyword);
        return new ObjectRestResponse<List<ApprovalVO>>().data(list);
    }

    @ApiOperation(value = "审批详情", notes = "get", tags = "审批详情")
    @RequestMapping(value = "/v1/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ApprovalVO> getInfo(@RequestHeader("Authorization") String token,
                                                  @PathVariable int id) throws Exception {
        ApprovalVO approval = permAppService.selectById(id);
        return new ObjectRestResponse<ApprovalVO>().data(approval);
    }


    @ApiOperation(value = "干部审批", notes = "post", tags = "干部审批")
    @RequestMapping(value = "/v2/{id}", method = RequestMethod.POST)
    public ObjectRestResponse<ApprovalVO> approval(@RequestHeader("Authorization") String token,
                                                   @RequestBody AppBody appBody) throws Exception {
        ApprovalVO approval = permAppService.approval(token, appBody);
        return new ObjectRestResponse<ApprovalVO>().data(approval);
    }

    @ApiOperation(value = "是否符合三天规范", notes = "get", tags = "是否符合三天规范")
    @RequestMapping(value = "/v1/viola/{appid}", method = RequestMethod.GET)
    public ObjectRestResponse<Map> violation(@RequestHeader("Authorization") String token,
                                             @PathVariable("appid") int appId) throws Exception {
        Map warn = permAppService.getviolation(token, appId);
        return new ObjectRestResponse<Map>().data(warn);
    }

    @ApiOperation(value = "上传公示资料图片", notes = "post", tags = "上传公示资料图片")
    @RequestMapping(value = "/v1/{appid}", method = RequestMethod.POST)
    public ObjectRestResponse<ApprovalVO> publicity(@RequestHeader("Authorization") String token,
                                                    @RequestBody Apps apps) throws Exception {
        permAppService.publicity(token, apps);
        return new ObjectRestResponse<ApprovalVO>();
    }


    /*********************************web端**********************************/

    @ApiOperation(value = "web端--申报审批列表", notes = "get", tags = "web端--申报审批列表")
    @RequestMapping(value = "/web/list", method = RequestMethod.GET)
    public TableResultResponse<ApprovalVO> weblist(@RequestHeader("Authorization") String token,
                                                   @RequestParam(defaultValue = "10") int limit,
                                                   @RequestParam(defaultValue = "1") int page, ApprovalVO approvalVO) throws Exception {

        PageInfo<ApprovalVO> list = permAppService.weblist(token, approvalVO, page, limit);
        return new TableResultResponse<ApprovalVO>(list.getTotal(), list.getList());
    }

    @ApiOperation(value = "web端----是否提交建房申请", notes = "post", tags = "web端---是否提交建房申请")
    @RequestMapping(value = "/web/submit/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<Map> webisubmit(@PathVariable int id) throws Exception {
        Map map = permAppService.subm(id);
        return new ObjectRestResponse<Map>().data(map);
    }

    @ApiOperation(value = "web端----建房申请", notes = "post", tags = "web端---建房申请")
    @RequestMapping(value = "/web/{id}", method = RequestMethod.POST)
    public ObjectRestResponse<ApprovalVO> websubmit(@PathVariable int id,
                                                    @RequestBody App app) {
        ApprovalVO submit = permAppService.websubm(id, app);
        return new ObjectRestResponse<ApprovalVO>().data(submit);
    }

    @ApiOperation(value = "web端--申报审批详情", notes = "get", tags = "web端--申报审批详情")
    @RequestMapping(value = "/web/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ApprovalVO> getapp(@PathVariable int id) {
        ApprovalVO approval = permAppService.selectById(id);
        return new ObjectRestResponse<ApprovalVO>().data(approval);
    }

}
