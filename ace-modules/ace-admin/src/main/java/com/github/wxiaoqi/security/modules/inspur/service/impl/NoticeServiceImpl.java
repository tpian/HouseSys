package com.github.wxiaoqi.security.modules.inspur.service.impl;

import com.github.wxiaoqi.security.modules.inspur.entity.Notice;
import com.github.wxiaoqi.security.modules.inspur.mapper.NoticeMapper;
import com.github.wxiaoqi.security.modules.inspur.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getlist(Notice notice) {
        return noticeMapper.getlist(notice);
    }
}
