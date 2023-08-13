package com.github.wxiaoqi.security.modules.inspur.util;

import com.alibaba.fastjson.JSONObject;
import com.github.wxiaoqi.security.api.vo.user.UserInfo;
import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.configuration.WeChatConfig;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.modules.inspur.service.PermUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WxloginUtil {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeChatConfig weChatConfig;
    @Autowired
    private PermUserInfoService permUserInfoService;
    @Autowired
    private JwtTokenUtil userAuthUtil;

    /**
     * 登录接口
     *
     * @param jwtAuthenRequest
     * @return
     * @throws Exception
     */
    public Map login(JwtAuthenRequest jwtAuthenRequest) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(jwtAuthenRequest.getCode()), "必须提供code");
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appid", weChatConfig.getAppid());
        requestMap.put("secret", weChatConfig.getSecret());
        requestMap.put("code", jwtAuthenRequest.getCode());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(WechatUrl.url, String.class, requestMap);
        log.info("responseEntity:{}", responseEntity);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        log.info("jsonObject:{}", jsonObject);
        if (jsonObject.containsKey("errcode")) {
            throw new UserInvalidException("长时间未操作,请重新登录");
        }
        String openId = jsonObject.getString("openid");
        log.info("openId:{}", openId);
        UserInfo info = permUserInfoService.valiphone(jwtAuthenRequest.getPhone(), jwtAuthenRequest.getPassword());
        if (!StringUtils.isEmpty(info.getId())) {
            TokenModel tokenModel = new TokenModel(info.getUsername(), info.getId() + "", info.getName(), info.getPhone(), openId);
            String token = userAuthUtil.genToken(tokenModel);
            Map<String, String> result = new HashMap<>();
            result.put("accessToken", token);
            result.put("id", info.getId());
            result.put("type", info.getType().toString());
            return result;
        }
        log.info("Id:{}", info.getId());
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    /**
     * 生成access_token
     *
     * @return
     */
    public String getAccessToken() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appId", weChatConfig.getAppid());
        requestMap.put("appSecret", weChatConfig.getSecret());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(WechatUrl.Meurl, String.class, requestMap);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        String token = jsonObject.getString("access_token");
        log.info("有效时长" + jsonObject.getString("expires_in"));
        return token;
    }

    /**
     * 审核不通过
     *
     * @param openid
     * @return
     */
    public String turn(String openid, String approver, Date auTime, String name, String auNotes) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String pushUrl = WechatUrl.push + getAccessToken();
        WechatSend wechatSend = new WechatSend();
        wechatSend.setTouser(openid);
        wechatSend.setTemplate_id(weChatConfig.getTemp());
        wechatSend.setPage("pages/index/index");
        Map<String, TemplateData> hashMap = new HashMap<>();
        hashMap.put("name1", new TemplateData(approver));
        hashMap.put("date2", new TemplateData(simpleDateFormat.format(auTime)));
        hashMap.put("name3", new TemplateData(name));
        hashMap.put("phrase7", new TemplateData("未通过审核"));
        hashMap.put("thing8", new TemplateData(auNotes));
        wechatSend.setData(hashMap);
        ResponseEntity<String> ResponseEntity = restTemplate.postForEntity(pushUrl, wechatSend, String.class);
        return ResponseEntity.getBody();

    }


    /**
     * 审核通过
     *
     * @param openid
     * @return
     */
    public String push(String openid, String approver, Date auTime, String name, String auNotes) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String pushUrl = WechatUrl.push + getAccessToken();
        WechatSend wechatSend = new WechatSend();
        wechatSend.setTouser(openid);
        wechatSend.setTemplate_id(weChatConfig.getTemplate());
        wechatSend.setPage("pages/index/index");
        Map<String, TemplateData> hashMap = new HashMap<>();
        hashMap.put("name1", new TemplateData(approver));
        hashMap.put("date2", new TemplateData(simpleDateFormat.format(auTime)));
        hashMap.put("name3", new TemplateData(name));
        hashMap.put("phrase6", new TemplateData("审核通过"));
        hashMap.put("thing7", new TemplateData(auNotes));
        wechatSend.setData(hashMap);
        ResponseEntity<String> ResponseEntity = restTemplate.postForEntity(pushUrl, wechatSend, String.class);
        return ResponseEntity.getBody();

    }

}
