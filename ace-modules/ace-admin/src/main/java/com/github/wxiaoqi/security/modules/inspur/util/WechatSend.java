package com.github.wxiaoqi.security.modules.inspur.util;

import lombok.Data;

import java.util.Map;

@Data
public class WechatSend {

    private String touser;//用户openid

    private String template_id;//订阅消息模版id

    private String page = "pages/index/index";

    private Map<String, TemplateData> data;//推送文字


}
