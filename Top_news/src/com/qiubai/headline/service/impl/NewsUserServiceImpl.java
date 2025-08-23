package com.qiubai.headline.service.impl;

import com.qiubai.headline.dao.NewsUserDao;
import com.qiubai.headline.dao.impl.NewsUserDaoImpl;
import com.qiubai.headline.pojo.NewsUser;
import com.qiubai.headline.service.NewsUserService;
import com.qiubai.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao = new NewsUserDaoImpl();

    @Override
    public NewsUser findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return userDao.findByUid(userId);
    }

    @Override
    public Integer regist(NewsUser registUser) {
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
        return userDao.insertUser(registUser);
    }
}
