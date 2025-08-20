package com.qiubai.headline.dao;

import com.qiubai.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     * 查找所有类型信息
     * @return 类型的链表
     */
    List<NewsType> findAll();
}
