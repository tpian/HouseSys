package com.github.wxiaoqi.security.modules.inspur.service.impl;

import com.github.wxiaoqi.security.modules.inspur.entity.Print;
import com.github.wxiaoqi.security.modules.inspur.mapper.PrintMapper;
import com.github.wxiaoqi.security.modules.inspur.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PrintMapper printMapper;

    @Override
    public List<Print> list(String type, String coverId) {
        return printMapper.list(type, coverId);
    }
}
