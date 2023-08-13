package com.github.wxiaoqi.security.modules.inspur.service.impl;

import com.github.wxiaoqi.security.modules.inspur.mapper.AppMapper;
import com.github.wxiaoqi.security.modules.inspur.service.Appservice;
import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppserviceImpl implements Appservice {
    @Autowired
    private AppMapper appMapper;

    @Override
    public ApprovalVO getUserId(int id) {
        return appMapper.getUserId(id);
    }

    @Override
    public ApprovalVO getinfoId(int id) {
        return appMapper.getinfoId(id);
    }

    @Override
    public ApprovalVO getAppId(int id) {

        return appMapper.getAppId(id);
    }

    @Override
    public ApprovalVO selectById(int id) {
        return appMapper.selectById(id);
    }

    @Override
    public List<ApprovalVO> getvilist(String county, String town, String village, String keyword) {
        return appMapper.getvilist(county, town, village, keyword);
    }

    @Override
    public List<ApprovalVO> getTownlist(String county, String town, String keyword) {
        return appMapper.getTownlist(county, town, keyword);
    }

    @Override
    public List<ApprovalVO> getUserlist(String county, String keyword) {
        return appMapper.getUserlist(county, keyword);
    }

    @Override
    public List<ApprovalVO> getmzlist(String county, String keyword) {
        return appMapper.getmzlist(county, keyword);
    }

    @Override
    public List<ApprovalVO> getzjlist(String county, String keyword) {
        return appMapper.getzjlist(county, keyword);
    }


    @Override
    public Map getvicount(String county, String town, String village) {
        return appMapper.getvicount(county, town, village);
    }

    @Override
    public Map getTowncount(String county, String town) {
        return appMapper.getTowncount(county, town);
    }

    @Override
    public Map getUsercount(String county) {
        return appMapper.getUsercount(county);
    }

    @Override
    public Map getmzcount(String county) {
        return appMapper.getmzcount(county);
    }

    @Override
    public Map getzjcount(String county) {
        return appMapper.getzjcount(county);
    }


    @Override
    public List<ApprovalVO> weblist(ApprovalVO approvalVO) {
        return appMapper.weblist(approvalVO);
    }

    @Override
    public List<ApprovalVO> getwebvilist(String county, String town, String village, ApprovalVO approvalVO) {
        return appMapper.getwebvilist(county, town, village, approvalVO);
    }

    @Override
    public List<ApprovalVO> getwebTownlist(String county, String town, ApprovalVO approvalVO) {
        return appMapper.getwebTownlist(county, town, approvalVO);
    }

    @Override
    public List<ApprovalVO> getwebUserlist(String county, ApprovalVO approvalVO) {
        return appMapper.getwebUserlist(county, approvalVO);
    }

    @Override
    public List<ApprovalVO> getwebmzlist(String county, ApprovalVO approvalVO) {
        return appMapper.getwebmzlist(county, approvalVO);
    }

    @Override
    public List<ApprovalVO> getwebzjlist(String county, ApprovalVO approvalVO) {
        return appMapper.getwebzjlist(county, approvalVO);
    }




}
