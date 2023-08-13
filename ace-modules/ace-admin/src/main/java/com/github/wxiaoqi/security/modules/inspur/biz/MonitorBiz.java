package com.github.wxiaoqi.security.modules.inspur.biz;

import org.springframework.stereotype.Service;

import com.github.wxiaoqi.security.modules.inspur.entity.Monitor;
import com.github.wxiaoqi.security.modules.inspur.mapper.MonitorMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;

/**
 * 动态监管
 *
 * @author ggq
 * @email 1002375151@qq.com
 * @version 2022-07-31 23:45:58
 */
@Service
public class MonitorBiz extends BaseBiz<MonitorMapper,Monitor> {


    public Monitor gerUserId(int id){
        Monitor monitor = new Monitor();
        monitor.setUserId(id);
        monitor.setIsDelete(0);
        return mapper.selectOne(monitor);
    }


}
