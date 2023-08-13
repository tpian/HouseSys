package com.github.wxiaoqi.security.modules.inspur.service.impl;

import com.github.wxiaoqi.security.modules.inspur.entity.Pubty;
import com.github.wxiaoqi.security.modules.inspur.mapper.PubtyMapper;
import com.github.wxiaoqi.security.modules.inspur.service.PubtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PubtyServiceImpl implements PubtyService {
    @Autowired
    private PubtyMapper pubtyMapper;

    @Override
    public List<Pubty> getlist(Pubty pubty) {
        return pubtyMapper.getlist(pubty);
    }
}
