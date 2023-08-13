package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.TownBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Town;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("town")
public class TownController extends BaseController<TownBiz, Town> {

}
