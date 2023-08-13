package com.github.wxiaoqi.security.modules.inspur.service;


import com.github.wxiaoqi.security.modules.inspur.entity.Print;

import java.util.List;

public interface PermService {

    public List<Print> list(String type, String coverId);

}
