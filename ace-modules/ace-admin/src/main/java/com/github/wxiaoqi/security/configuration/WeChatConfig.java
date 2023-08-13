package com.github.wxiaoqi.security.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {
    private String appid;
    private String secret;
    private String temp;
    private String template;


}
