package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import com.github.wxiaoqi.security.modules.inspur.vo.ArchivesVO;
import com.github.wxiaoqi.security.modules.inspur.vo.MonitorVO;

import java.util.List;

public interface MonitorService {

    public MonitorVO getUserId(int id);

    public MonitorVO getId(int id);

    public ArchivesVO getarchivesId(int userId);


    /**
     * 村干部权限
     *
     * @param county
     * @param town
     * @param village
     * @param keyword
     * @return
     */
    public List<MonitorVO> getvilist(String county, String town, String village, String keyword);

    /**
     * 镇领导权限
     *
     * @param county
     * @param town
     * @param keyword
     * @return
     */
    public List<MonitorVO> getTownlist(String county, String town, String keyword);


    /**
     * 县干部及以上权限
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<MonitorVO> getUserlist(String county, String keyword);


    public List<MonitorVO> weblist(MonitorVO monitorVO);

    /*档案*/

    /**
     * 村干部权限
     *
     * @param county
     * @param town
     * @param village
     * @param keyword
     * @return
     */
    public List<ArchivesVO> getvifile(String county, String town, String village, String keyword);

    /**
     * 镇领导权限
     *
     * @param county
     * @param town
     * @param keyword
     * @return
     */
    public List<ArchivesVO> getTownfile(String county, String town, String keyword);


    /**
     * 县干部及以上权限
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<ArchivesVO> getUserfile(String county, String keyword);


    public List<ArchivesVO> getInfo(ArchivesVO archivesVO);


}
