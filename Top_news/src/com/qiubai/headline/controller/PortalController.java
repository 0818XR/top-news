package com.qiubai.headline.controller;

import com.qiubai.headline.common.Result;
import com.qiubai.headline.pojo.NewsType;
import com.qiubai.headline.pojo.vo.HeadlineDetailVo;
import com.qiubai.headline.pojo.vo.HeadlineQueryVo;
import com.qiubai.headline.service.NewsHeadLineService;
import com.qiubai.headline.service.NewsTypeService;
import com.qiubai.headline.service.impl.NewsHeadLineServiceImpl;
import com.qiubai.headline.service.impl.NewsTypeServiceImpl;
import com.qiubai.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门户控制器
 * 不需要登录，不需要做增删改的门户操作页面的请求放在这里
 * 若当前服务的请求量过大，可以将当前控制器单独部署在一个服务器上实现集群部署分担服务器压力
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
    NewsTypeService typeService = new NewsTypeServiceImpl();
    NewsHeadLineService headLineService = new NewsHeadLineServiceImpl();

    /**
     * 查找所有新闻种类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList = typeService.findAll();

        Result<List<NewsType>> result = Result.ok(newsTypeList);
        WebUtil.writeJson(resp, result);
    }

    /**
     * 依据前端提供的数据查询当前页新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
         Map<String, Object> pageInfo =  headLineService.findPage(headlineQueryVo);
         Map<String, Object> data = new HashMap<>();
         data.put("pageInfo", pageInfo);

         WebUtil.writeJson(resp, Result.ok(data));
    }

    /**
     * 根据传入的参数查询某个文章的信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        Map<String, HeadlineDetailVo> data = headLineService.findHeadlineDetail(hid);
        WebUtil.writeJson(resp, Result.ok(data));

    }
}
