package com.github.wxiaoqi.security.modules.inspur.biz;

import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.admin.util.Sha256PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import com.github.wxiaoqi.security.modules.inspur.mapper.UserinfoMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;

/**
 *
 *
 * @author ggq
 * @email 1002375151@qq.com
 * @version 2022-07-31 23:45:59
 */
@Service
public class UserinfoBiz extends BaseBiz<UserinfoMapper,Userinfo> {


    private Sha256PasswordEncoder encoder = new Sha256PasswordEncoder();

    @Override
    public void insertSelective(Userinfo entity) {
        String password = encoder.encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    @Override
    public void updateSelectiveById(Userinfo entity) {
        super.updateSelectiveById(entity);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public Userinfo getUserByUsername(String username){
        Userinfo user = new Userinfo();
        user.setUsername(username);
        user.setIsDelete(0);
        return mapper.selectOne(user);
    }

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    public Userinfo UserByPhone(String phone){
        Userinfo user = new Userinfo();
        user.setMobilePhone(phone);
        user.setIsDelete(0);
        return mapper.selectOne(user);
    }

}
