package com.qiubai.headline.dao.impl;

import com.qiubai.headline.dao.BaseDao;
import com.qiubai.headline.dao.NewsTypeDao;
import com.qiubai.headline.dao.NewsUserDao;
import com.qiubai.headline.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> findAll() {
        String sql = "SELECT tid, tname FROM news_type";
        return baseQuery(NewsType.class, sql);
    }
}
