package com.github.wxiaoqi.security.modules.inspur.mapper;

import com.github.wxiaoqi.security.modules.inspur.entity.Userinfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author ggq
 * @version 2022-07-31 23:45:59
 * @email 1002375151@qq.com
 */
public interface UserinfoMapper extends Mapper<Userinfo> {

    public List<Userinfo> getfarmlist(@Param("phone") String phone,
                                      @Param("keyword") String keyword);

    public List<Userinfo> getvilist(@Param("county") String county,
                                    @Param("town") String town,
                                    @Param("village") String village,
                                    @Param("keyword") String keyword);

    public List<Userinfo> getTownlist(@Param("county") String county,
                                      @Param("town") String town,
                                      @Param("keyword") String keyword);

    public List<Userinfo> getUserlist(@Param("county") String county,
                                      @Param("keyword") String keyword);


    public List<Userinfo> getweblist(Userinfo userinfo);


    public List<Userinfo> getwebvilist(@Param("county") String county,
                                       @Param("town") String town,
                                       @Param("village") String village,
                                       @Param("userinfo") Userinfo userinfo);

    public List<Userinfo> getwebTownlist(@Param("county") String county,
                                         @Param("town") String town,
                                         @Param("userinfo") Userinfo userinfo);

    public List<Userinfo> getwebUserlist(@Param("county") String county,
                                         @Param("userinfo") Userinfo userinfo);

    public Map stat();

}
