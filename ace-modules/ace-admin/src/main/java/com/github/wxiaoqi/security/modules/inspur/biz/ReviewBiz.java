package com.github.wxiaoqi.security.modules.inspur.biz;

import org.springframework.stereotype.Service;

import com.github.wxiaoqi.security.modules.inspur.entity.Review;
import com.github.wxiaoqi.security.modules.inspur.mapper.ReviewMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;

/**
 * 审批进度
 *
 * @author ggq
 * @version 2022-07-23 22:32:13
 * @email 1002375151@qq.com
 */
@Service
public class ReviewBiz extends BaseBiz<ReviewMapper, Review> {


    public Review selectAppById(int id) {
        Review review = new Review();
        review.setAppId(id);
        return mapper.selectOne(review);
    }
}
