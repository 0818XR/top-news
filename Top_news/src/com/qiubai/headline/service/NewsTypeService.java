package com.qiubai.headline.service;

import com.qiubai.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {
    /**
     * 查询所有类型的信息
     * @return 类型的链表
     */
    List<NewsType> findAll();
}
