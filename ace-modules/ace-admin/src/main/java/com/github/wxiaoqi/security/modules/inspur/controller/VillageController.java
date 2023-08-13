package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.VillageBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Village;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("village")
public class VillageController extends BaseController<VillageBiz, Village> {


}
