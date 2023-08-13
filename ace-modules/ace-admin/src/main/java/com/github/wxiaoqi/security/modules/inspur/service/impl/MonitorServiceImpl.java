package com.github.wxiaoqi.security.modules.inspur.service.impl;

import com.github.wxiaoqi.security.modules.inspur.mapper.MonitorMapper;
import com.github.wxiaoqi.security.modules.inspur.service.MonitorService;
import com.github.wxiaoqi.security.modules.inspur.vo.ArchivesVO;
import com.github.wxiaoqi.security.modules.inspur.vo.MonitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {
    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    public MonitorVO getUserId(int id) {
        return monitorMapper.getUserId(id);
    }

    @Override
    public MonitorVO getId(int id) {
        return monitorMapper.getId(id);
    }

    @Override
    public ArchivesVO getarchivesId(int userId) {
        return monitorMapper.getarchivesId(userId);
    }

    @Override
    public List<MonitorVO> getvilist(String county, String town, String village, String keyword) {
        return monitorMapper.getvilist(county, town, village, keyword);
    }

    @Override
    public List<MonitorVO> getTownlist(String county, String town, String keyword) {
        return monitorMapper.getTownlist(county, town, keyword);
    }

    @Override
    public List<MonitorVO> getUserlist(String county, String keyword) {
        return monitorMapper.getUserlist(county, keyword);
    }

    @Override
    public List<MonitorVO> weblist(MonitorVO monitorVO) {
        return monitorMapper.weblist(monitorVO);
    }

    @Override
    public List<ArchivesVO> getvifile(String county, String town, String village, String keyword) {
        return monitorMapper.getvifile(county, town, village, keyword);
    }

    @Override
    public List<ArchivesVO> getTownfile(String county, String town, String keyword) {
        return monitorMapper.getTownfile(county, town, keyword);
    }

    @Override
    public List<ArchivesVO> getUserfile(String county, String keyword) {
        return monitorMapper.getUserfile(county, keyword);
    }

    @Override
    public List<ArchivesVO> getInfo(ArchivesVO archivesVO) {
        return monitorMapper.getInfo(archivesVO);
    }


}
