package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;

import java.util.List;
import java.util.Map;

public interface Appservice {

    public ApprovalVO getUserId(int id);

    public ApprovalVO getinfoId(int id);

    public ApprovalVO getAppId(int id);

    public ApprovalVO selectById(int id);

    /**
     * 村干部列表
     *
     * @param county
     * @param town
     * @param village
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getvilist(String county, String town, String village, String keyword);

    /**
     * 镇领导基础数据
     *
     * @param county
     * @param town
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getTownlist(String county, String town, String keyword);


    /**
     * 县干部
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getUserlist(String county, String keyword);

    /**
     * 民政局
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getmzlist(String county, String keyword);


    /**
     * 住建局
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getzjlist(String county, String keyword);


    /**
     * 村干部待审核数量
     *
     * @param county
     * @param town
     * @param village
     * @return
     */
    public Map getvicount(String county, String town, String village);

    /**
     * 镇领导待审核数量
     *
     * @param county
     * @param town
     * @return
     */
    public Map getTowncount(String county, String town);


    /**
     * 县干部待审核数量
     *
     * @param county
     * @return
     */
    public Map getUsercount(String county);

    /**
     * 民政局待审核数量
     *
     * @param county
     * @return
     */
    public Map getmzcount(String county);


    /**
     * 住建局待审核数量
     *
     * @param county
     * @return
     */
    public Map getzjcount(String county);


    /*** ******************************web端**********************************/
    public List<ApprovalVO> weblist(ApprovalVO approvalVO);


    /**
     * 村干部列表
     *
     * @param county
     * @param town
     * @param village
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getwebvilist(String county, String town, String village, ApprovalVO approvalVO);

    /**
     * 镇领导基础数据
     *
     * @param county
     * @param town
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getwebTownlist(String county, String town, ApprovalVO approvalVO);


    /**
     * 县干部
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getwebUserlist(String county, ApprovalVO approvalVO);

    /**
     * 民政局
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getwebmzlist(String county, ApprovalVO approvalVO);


    /**
     * 住建局
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<ApprovalVO> getwebzjlist(String county, ApprovalVO approvalVO);




}
