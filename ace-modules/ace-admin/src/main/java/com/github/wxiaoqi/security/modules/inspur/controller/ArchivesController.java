package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.MonitorBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Monitor;
import com.github.wxiaoqi.security.modules.inspur.service.PermMonitorService;
import com.github.wxiaoqi.security.modules.inspur.service.WordService;
import com.github.wxiaoqi.security.modules.inspur.vo.ArchivesVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("record")
public class ArchivesController extends BaseController<MonitorBiz, Monitor> {

    @Autowired
    private PermMonitorService permMonitorService;

    @Autowired
    private WordService wordService;

    @ApiOperation(value = "农户-是否存在档案", notes = "get", tags = "农户-是否存在档案")
    @RequestMapping(value = "/v2/file", method = RequestMethod.GET)
    public ObjectRestResponse<Map> file(@RequestHeader("Authorization") String token) throws Exception {
        Map<String, String> file = permMonitorService.isfile(token);
        return new ObjectRestResponse<Map>().data(file);
    }

    @ApiOperation(value = "农户档案", notes = "get", tags = "农户档案")
    @RequestMapping(value = "/v2/token", method = RequestMethod.GET)
    public ObjectRestResponse<ArchivesVO> archives(@RequestHeader("Authorization") String token) throws Exception {
        ArchivesVO archives = permMonitorService.archives(token);
        return new ObjectRestResponse<ArchivesVO>().data(archives);
    }

    @ApiOperation(value = "档案列表", notes = "get", tags = "档案列表")
    @RequestMapping(value = "/v2/list", method = RequestMethod.GET)
    public TableResultResponse<ArchivesVO> list(@RequestHeader("Authorization") String token,
                                                String keyword) throws Exception {
        List<ArchivesVO> files = permMonitorService.getfiles(token, keyword);
        return new TableResultResponse<ArchivesVO>(files.size(), files);
    }

    @ApiOperation(value = "档案详情", notes = "get", tags = "档案详情")
    @RequestMapping(value = "/v2/{userId}", method = RequestMethod.GET)
    public ObjectRestResponse<ArchivesVO> getById(@PathVariable int userId) throws Exception {
        ArchivesVO id = permMonitorService.getById(userId);
        return new ObjectRestResponse<ArchivesVO>().data(id);
    }

    /*** ******************************web端**********************************/

    @ApiOperation(value = "web---档案列表", notes = "get", tags = "web---档案列表")
    @RequestMapping(value = "/web/list", method = RequestMethod.GET)
    public TableResultResponse<ArchivesVO> list(@RequestParam(defaultValue = "10") int limit,
                                                @RequestParam(defaultValue = "1") int page, ArchivesVO archivesVO) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<ArchivesVO> files = permMonitorService.getInfo(archivesVO);
        return new TableResultResponse<ArchivesVO>(startPage.getTotal(), files);
    }

    @ApiOperation(value = "web端-档案详情", notes = "get", tags = "web端-档案详情")
    @RequestMapping(value = "/web/{userId}", method = RequestMethod.GET)
    public ObjectRestResponse<ArchivesVO> getwebById(@PathVariable int userId) {
        ArchivesVO id = permMonitorService.getById(userId);
        return new ObjectRestResponse<ArchivesVO>().data(id);
    }

    @ApiOperation(value = "web端-导出 word文档", notes = "get", tags = "web端-导出 word文档")
    @RequestMapping(value = "/word/{userId}", method = RequestMethod.GET)
    public ObjectRestResponse word(@PathVariable int[] userId) {
        List<String> files = new ArrayList<>();
        for (int i = 0; i < userId.length; i++) {
            ArchivesVO archivesVO = permMonitorService.getById(userId[i]);
            if (archivesVO.getFiling() == null) {
                throw new UserInvalidException("档案资料未完善,请完善之后导出");
            }
            String fileName = wordService.word(archivesVO, archivesVO.getName() + "档案");
            log.info("fileName:{}", fileName);
            files.add(fileName);
        }
        // String writeZip = wordUtil.writeZip(files, "档案记录");
        return new ObjectRestResponse().data(files);
    }

}

