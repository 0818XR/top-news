package com.qiubai.headline.controller;

import com.qiubai.headline.common.Result;
import com.qiubai.headline.pojo.NewsHeadline;
import com.qiubai.headline.pojo.vo.HeadlineDetailVo;
import com.qiubai.headline.service.NewsHeadLineService;
import com.qiubai.headline.service.impl.NewsHeadLineServiceImpl;
import com.qiubai.headline.util.JwtHelper;
import com.qiubai.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadLineController extends BaseController{

    NewsHeadLineService headLineService = new NewsHeadLineServiceImpl();

    /**
     * 发布新头条
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Long userId = JwtHelper.getUserId(token);

        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        newsHeadline.setPublisher(userId.intValue());
        headLineService.addNewsHeadline(newsHeadline);
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 通过hid查找相应文章
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        Map<String, HeadlineDetailVo> data = headLineService.findHeadlineDetail(hid);
        Result result = Result.ok(data);
        WebUtil.writeJson(resp, result);
    }

    /**
     * 将修改后的文章内容更新到数据库中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        headLineService.updateNewsHeadline(newsHeadline);
        WebUtil.writeJson(resp,Result.ok(null));
    }

    /**
     * 删除当前hid新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        headLineService.remove(hid);
        WebUtil.writeJson(resp, Result.ok(null));
    }
}
