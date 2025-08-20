package com.qiubai.headline.dao.impl;

import com.qiubai.headline.dao.BaseDao;
import com.qiubai.headline.dao.NewsUserDao;
import com.qiubai.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {

    @Override
    public NewsUser findByUserName(String username) {
        String sql = "SELECT uid,username,user_pwd userPwd,nick_name nickName FROM news_user WHERE username = ?";
        List<NewsUser> userList = baseQuery(NewsUser.class, sql, username);
        if(userList != null && userList.size()> 0) return userList.get(0);
        return null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = "SELECT uid,username,user_pwd userPwd,nick_name nickName FROM news_user WHERE uid = ?";
        List<NewsUser> userList = baseQuery(NewsUser.class, sql, userId);
        if(userList != null && userList.size()> 0) return userList.get(0);
        return null;
    }

    @Override
    public Integer insertUser(NewsUser registUser) {
        String sql = "INSERT INTO news_user values (DEFAULT,?,?,?)";
        return baseUpdate(sql,
                registUser.getUid(),
                registUser.getUsername(),
                registUser.getUserPwd(), 
                registUser.getNickName()
                );
    }
}
