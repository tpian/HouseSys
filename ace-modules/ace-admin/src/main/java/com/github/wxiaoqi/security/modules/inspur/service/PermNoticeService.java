package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.modules.admin.biz.UserBiz;
import com.github.wxiaoqi.security.modules.admin.entity.User;
import com.github.wxiaoqi.security.modules.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.modules.inspur.biz.NoticeBiz;
import com.github.wxiaoqi.security.modules.inspur.entity.Notice;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermNoticeService {

    @Autowired
    private NoticeBiz noticeBiz;

    @Autowired
    private NoticeService noticeService;


    public void save(Notice notice) {

        noticeBiz.insertSelective(notice);
    }

    public void delete(int id) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setIsDelete(1);
        noticeBiz.updateSelectiveById(notice);
    }

    public void update(Notice notice) {

        noticeBiz.updateSelectiveById(notice);
    }

    public Notice getById(int id) {
        return noticeBiz.selectById(id);

    }

    public List<Notice> getlist(Notice notice) {
        return noticeService.getlist(notice);

    }

}
