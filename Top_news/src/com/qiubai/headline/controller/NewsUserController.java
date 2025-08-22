package com.qiubai.headline.controller;

import com.qiubai.headline.common.Result;
import com.qiubai.headline.common.ResultCodeEnum;
import com.qiubai.headline.pojo.NewsUser;
import com.qiubai.headline.service.NewsUserService;
import com.qiubai.headline.service.impl.NewsUserServiceImpl;
import com.qiubai.headline.util.JwtHelper;
import com.qiubai.headline.util.MD5Util;
import com.qiubai.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{

    NewsUserService userService = new NewsUserServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        if(token != null) {
            if(!JwtHelper.isExpiration(token)) {
                result = Result.ok(null);
            }
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 用户注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser registUser = WebUtil.readJson(req, NewsUser.class);
        Integer rows =  userService.regist(registUser);

        Result result = Result.ok(null);
        if(rows == 0) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);

    }

    /**
     * 检验用户名是否被占用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过用户名拿到用户信息
        // 检查用户信息是否为空返回即可
        String username = req.getParameter("username");
        NewsUser newsUser = userService.findByUserName(username);

        Result result = Result.ok(null);
        if(newsUser != null) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        WebUtil.writeJson(resp, result);
    }

    /**
     * 根据token口令获取用户信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
            接收用户信息，检验token，是否为空，是否为null，是否过期
            通过token取出用户名，查找用户，返回的用户是否为空
            最后通过result对象将结果返回
         */
        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        if(token != null && !"".equals(token)) {
            if(!JwtHelper.isExpiration(token)) {
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser =  userService.findByUid(userId);
                if(newsUser != null) {
                    Map map = new HashMap();
                    newsUser.setUserPwd("");
                    map.put("loginUser", newsUser);
                    result = Result.ok(map);
                }
            }
        }
        WebUtil.writeJson(resp,result);

    }

    /**
     * 处理登录表单提交的业务接口的视线
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        NewsUser loginUser = userService.findByUserName(paramUser.getUsername());

        Result result = null;
        if(loginUser != null) {
            if(MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())) {
                Integer uid = loginUser.getUid();
                String token = JwtHelper.createToken(uid.longValue());
                Map data =  new HashMap();
                data.put("token",token);
                result = Result.ok(data);
            } else {
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        } else {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        WebUtil.writeJson(resp, result);
    }
}
