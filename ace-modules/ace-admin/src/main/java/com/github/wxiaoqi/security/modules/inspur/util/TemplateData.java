package com.github.wxiaoqi.security.modules.inspur.util;

import lombok.Data;

@Data
public class TemplateData {
    private String value;

    public TemplateData(String value) {
        this.value = value;
    }
}
