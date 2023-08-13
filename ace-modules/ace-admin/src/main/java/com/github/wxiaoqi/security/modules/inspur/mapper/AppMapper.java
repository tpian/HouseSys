package com.github.wxiaoqi.security.modules.inspur.mapper;

import com.github.wxiaoqi.security.modules.inspur.entity.App;
import com.github.wxiaoqi.security.modules.inspur.vo.ApprovalVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 审批单
 *
 * @author ggq
 * @version 2022-07-23 22:32:13
 * @email 1002375151@qq.com
 */
public interface AppMapper extends Mapper<App> {

    public ApprovalVO getUserId(int id);

    public ApprovalVO getinfoId(int id);

    public ApprovalVO getAppId(int id);

    public ApprovalVO selectById(int id);

    public List<ApprovalVO> getvilist(@Param("county") String county,
                                      @Param("town") String town,
                                      @Param("village") String village,
                                      @Param("keyword") String keyword);

    public List<ApprovalVO> getTownlist(@Param("county") String county,
                                        @Param("town") String town,
                                        @Param("keyword") String keyword);

    public List<ApprovalVO> getUserlist(@Param("county") String county,
                                        @Param("keyword") String keyword);

    public List<ApprovalVO> getmzlist(@Param("county") String county,
                                      @Param("keyword") String keyword);

    public List<ApprovalVO> getzjlist(@Param("county") String county,
                                      @Param("keyword") String keyword);


    public Map getvicount(@Param("county") String county,
                          @Param("town") String town,
                          @Param("village") String village);

    public Map getTowncount(@Param("county") String county,
                            @Param("town") String town);

    public Map getUsercount(@Param("county") String county);

    public Map getmzcount(@Param("county") String county);

    public Map getzjcount(@Param("county") String county);

    public List<ApprovalVO> weblist(ApprovalVO approvalVO);

    public List<ApprovalVO> getwebvilist(@Param("county") String county,
                                         @Param("town") String town,
                                         @Param("village") String village,
                                         @Param("approvalVO") ApprovalVO approvalVO);

    public List<ApprovalVO> getwebTownlist(@Param("county") String county,
                                           @Param("town") String town,
                                           @Param("approvalVO") ApprovalVO approvalVO);

    public List<ApprovalVO> getwebUserlist(@Param("county") String county,
                                           @Param("approvalVO") ApprovalVO approvalVO);

    public List<ApprovalVO> getwebmzlist(@Param("county") String county,
                                         @Param("approvalVO") ApprovalVO approvalVO);

    public List<ApprovalVO> getwebzjlist(@Param("county") String county,
                                         @Param("approvalVO") ApprovalVO approvalVO);


}
