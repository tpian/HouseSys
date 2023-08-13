package com.github.wxiaoqi.security.modules.inspur.entity.body;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 审批单
 *
 * @author ggq
 * @version 2022-07-23 22:32:13
 * @email 1002375151@qq.com
 */
@Data
public class AppBody implements Serializable {
    private Integer appid;
    private Integer schedule;

}
