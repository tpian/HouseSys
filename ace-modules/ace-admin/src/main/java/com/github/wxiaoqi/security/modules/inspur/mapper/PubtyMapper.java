package com.github.wxiaoqi.security.modules.inspur.mapper;

import com.github.wxiaoqi.security.modules.inspur.entity.Pubty;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 政策宣传
 *
 * @author ggq
 * @email 1002375151@qq.com
 * @version 2022-07-27 22:34:15
 */
public interface PubtyMapper extends Mapper<Pubty> {

    public List<Pubty>getlist(Pubty pubty);

}
