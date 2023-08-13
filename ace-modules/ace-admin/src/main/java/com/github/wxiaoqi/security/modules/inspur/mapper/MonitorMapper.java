package com.github.wxiaoqi.security.modules.inspur.mapper;

import com.github.wxiaoqi.security.modules.inspur.entity.Monitor;
import com.github.wxiaoqi.security.modules.inspur.vo.ArchivesVO;
import com.github.wxiaoqi.security.modules.inspur.vo.MonitorVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 动态监管
 *
 * @author ggq
 * @version 2022-07-31 23:45:58
 * @email 1002375151@qq.com
 */
public interface MonitorMapper extends Mapper<Monitor> {

    public MonitorVO getUserId(int id);

    public MonitorVO getId(int id);

    public ArchivesVO getarchivesId(int userId);


    public List<MonitorVO> getvilist(@Param("county") String county,
                                     @Param("town") String town,
                                     @Param("village") String village,
                                     @Param("keyword") String keyword);

    public List<MonitorVO> getTownlist(@Param("county") String county,
                                       @Param("town") String town,
                                       @Param("keyword") String keyword);

    public List<MonitorVO> getUserlist(@Param("county") String county,
                                       @Param("keyword") String keyword);

    public List<MonitorVO> weblist( MonitorVO monitorVO);


    /*档案*/
    public List<ArchivesVO> getvifile(@Param("county") String county,
                                      @Param("town") String town,
                                      @Param("village") String village,
                                      @Param("keyword") String keyword);

    public List<ArchivesVO> getTownfile(@Param("county") String county,
                                        @Param("town") String town,
                                        @Param("keyword") String keyword);

    public List<ArchivesVO> getUserfile(@Param("county") String county,
                                        @Param("keyword") String keyword);

    public List<ArchivesVO> getInfo(ArchivesVO archivesVO);


}
