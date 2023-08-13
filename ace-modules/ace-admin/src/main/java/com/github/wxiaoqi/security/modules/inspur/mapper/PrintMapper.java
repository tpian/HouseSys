package com.github.wxiaoqi.security.modules.inspur.mapper;

import com.github.wxiaoqi.security.modules.inspur.entity.Print;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ggq
 * @version 2023-03-01 00:25:28
 * @email 2014314038@qq.com
 */
public interface PrintMapper extends Mapper<Print> {

    public List<Print> list(@Param("type") String type,
                            @Param("coverId") String coverId);
}
