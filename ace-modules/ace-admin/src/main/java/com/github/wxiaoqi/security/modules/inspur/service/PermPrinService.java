package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.modules.inspur.entity.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermPrinService {

    @Autowired
    private PermService permService;

    public List<Print> list(String type, String coverId) {
        return permService.list(type, coverId);
    }

}
