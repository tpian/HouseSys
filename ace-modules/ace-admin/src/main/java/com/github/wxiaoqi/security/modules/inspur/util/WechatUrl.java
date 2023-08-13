package com.github.wxiaoqi.security.modules.inspur.util;

public class WechatUrl {
    //auth.code2Session  登录
    public static final String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
    // auth.getAccessToken 接口调用凭证
    public static final String Meurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={appSecret}";
    //customerServiceMessage.send 发送通知
    public static final String push = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";


}
