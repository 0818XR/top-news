package com.qiubai.headline.service;

import com.qiubai.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 通过用户名查找用户
     * @param username 用户名信息以String格式传参
     * @return 一个NewsUser的实例化对象
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
    Integer regist(NewsUser registUser);
}
