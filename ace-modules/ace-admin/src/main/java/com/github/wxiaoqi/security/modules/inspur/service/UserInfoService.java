package com.github.wxiaoqi.security.modules.inspur.service;


import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    /**
     * 农户信息
     *
     * @param phone
     * @param keyword
     * @return
     */
    public List<Userinfo> getfarmlist(String phone, String keyword);

    /**
     * 村干部列表
     *
     * @param county
     * @param town
     * @param village
     * @param keyword
     * @return
     */
    public List<Userinfo> getvilist(String county, String town, String village, String keyword);

    /**
     * 镇领导基础数据
     *
     * @param county
     * @param town
     * @param keyword
     * @return
     */
    public List<Userinfo> getTownlist(String county, String town, String keyword);


    /**
     * 县干部及以上
     *
     * @param county
     * @param keyword
     * @return
     */
    public List<Userinfo> getUserlist(String county, String keyword);



    /**
     * 数据展示
     *
     * @return
     */
    public Map stat();



    /*********************************************web***********************/


    /**
     * 村干部列表
     *
     * @param county
     * @param town
     * @param village
     * @param userinfo
     * @return
     */
    public List<Userinfo> getwebvilist(String county, String town, String village, Userinfo userinfo);

    /**
     * 镇领导基础数据
     *
     * @param county
     * @param town
     * @param userinfo
     * @return
     */
    public List<Userinfo> getwebTownlist(String county, String town, Userinfo userinfo);


    /**
     * 县干部及以上
     *
     * @param county
     * @param userinfo
     * @return
     */
    public List<Userinfo> getwebUserlist(String county, Userinfo userinfo);


    /**
     * web端--list
     *
     * @param userinfo
     * @return
     */
    public List<Userinfo> getweblist(Userinfo userinfo);



}
