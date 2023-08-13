package com.github.wxiaoqi.security.modules.inspur.biz;

import org.springframework.stereotype.Service;

import com.github.wxiaoqi.security.modules.inspur.entity.App;
import com.github.wxiaoqi.security.modules.inspur.mapper.AppMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;

/**
 * 审批单
 *
 * @author ggq
 * @version 2022-07-23 22:32:13
 * @email 1002375151@qq.com
 */
@Service
public class AppBiz extends BaseBiz<AppMapper, App> {

    public App getUserById(int userId) {
        App app = new App();
        app.setUserId(userId);
        app.setIsDelete(0);
        return mapper.selectOne(app);
    }
}
