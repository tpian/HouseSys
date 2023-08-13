package com.github.wxiaoqi.security.modules.inspur.entity.body;

import lombok.Data;

import java.io.Serializable;

@Data
public class Reviewbody implements Serializable {
    private Integer appid;
    /**
     * 村,镇级公示照片
     */
    private String tellImage;

    /**
     * 村评议记录
     */
    private String record;

    /**
     * 村,镇级公示影像资料
     */
    private String scene;

    /**
     * 村,镇级公示回执
     */
    private String receipt;

    /**
     * 镇街实地考察
     */
    private String fieldwn;
    /**
     * 镇农村危房改造对象认定表
     */
    private String hold;

    /**
     * 镇危房改造工程协议书
     */
    private String protocol;
}
