package com.qiubai.headline.service.impl;

import com.qiubai.headline.dao.NewsTypeDao;
import com.qiubai.headline.dao.impl.NewsTypeDaoImpl;
import com.qiubai.headline.pojo.NewsType;
import com.qiubai.headline.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    NewsTypeDao typeDao = new NewsTypeDaoImpl();

    @Override
    public List<NewsType> findAll() {

        return typeDao.findAll();
    }
}
