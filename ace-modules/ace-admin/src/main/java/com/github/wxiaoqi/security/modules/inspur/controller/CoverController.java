package com.github.wxiaoqi.security.modules.inspur.controller;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.modules.inspur.biz.CoverBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Cover;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("cover")
public class CoverController extends BaseController<CoverBiz, Cover> {

}
