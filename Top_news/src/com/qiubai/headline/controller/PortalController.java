package com.qiubai.headline.controller;

import com.qiubai.headline.common.Result;
import com.qiubai.headline.pojo.NewsType;
import com.qiubai.headline.service.NewsTypeService;
import com.qiubai.headline.service.impl.NewsTypeServiceImpl;
import com.qiubai.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 门户控制器
 * 不需要登录，不需要做增删改的门户操作页面的请求放在这里
 * 若当前服务的请求量过大，可以将当前控制器单独部署在一个服务器上实现集群部署分担服务器压力
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
    NewsTypeService typeService = new NewsTypeServiceImpl();

    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList = typeService.findAll();

        Result<List<NewsType>> result = Result.ok(newsTypeList);
        WebUtil.writeJson(resp, result);
    }
}
