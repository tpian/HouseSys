package com.github.wxiaoqi.security.modules.inspur.mapper;

import com.github.wxiaoqi.security.modules.inspur.entity.Notice;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 通告表
 *
 * @author ggq
 * @email 1002375151@qq.com
 * @version 2022-07-27 22:34:14
 */
public interface NoticeMapper extends Mapper<Notice> {


    public List<Notice> getlist(Notice notice);

}
