package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.modules.admin.biz.UserBiz;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.modules.inspur.biz.PubtyBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Pubty;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermPutyService {

    @Autowired
    private PubtyBiz pubtyBiz;

    @Autowired
    private PubtyService pubtyService;

    public void save(Pubty pubty) {
        pubtyBiz.insertSelective(pubty);
    }

    public void delete(int id) {
        Pubty pubty = new Pubty();
        pubty.setId(id);
        pubty.setIsDelete(1);
        pubtyBiz.updateSelectiveById(pubty);
    }

    public void update(Pubty pubty) {

        pubtyBiz.updateSelectiveById(pubty);
    }

    public Pubty getById(int id) {

        return pubtyBiz.selectById(id);
    }

    public List<Pubty> getlist(Pubty pubty) {

        return pubtyService.getlist(pubty);
    }
}
