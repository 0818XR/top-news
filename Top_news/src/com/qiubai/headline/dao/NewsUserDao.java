package com.qiubai.headline.dao;

import com.qiubai.headline.pojo.NewsUser;


public interface NewsUserDao {
    /**
     * 通过用户名查找用户信息
     * @param username
     * @return NewsUser实例化对象
     */
    NewsUser findByUserName(String username);

    /**
     * 通过用户id查找用户
     * @param userId
     * @return NewsUser的实例化对象
     */
    NewsUser findByUid(Integer userId);

    /**
     * 向数据库增加用户
     * @param registUser
     * @return
     */
    Integer insertUser(NewsUser registUser);
}
