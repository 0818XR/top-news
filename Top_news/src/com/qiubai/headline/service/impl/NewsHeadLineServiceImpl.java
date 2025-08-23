package com.qiubai.headline.service.impl;

import com.qiubai.headline.dao.NewsHeadLineDao;
import com.qiubai.headline.dao.impl.NewsHeadLineDaoImpl;
import com.qiubai.headline.pojo.NewsHeadline;
import com.qiubai.headline.pojo.vo.HeadlineDetailVo;
import com.qiubai.headline.pojo.vo.HeadlinePageVo;
import com.qiubai.headline.pojo.vo.HeadlineQueryVo;
import com.qiubai.headline.service.NewsHeadLineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadLineServiceImpl implements NewsHeadLineService {

    NewsHeadLineDao headLineDao = new NewsHeadLineDaoImpl();

    @Override
    public Map<String, Object> findPage(HeadlineQueryVo headlineQueryVo) {
        /*
        要返回的信息有：
        pageData 使用更底层的dao获取
        pageNum vo中有
        pageSize vo中有
        totalPage 总数除以页面大小向上取整
        totalSize 使用底层dao获取
         */
        Map<String, Object> pageInfo = new HashMap<>();
        Integer pageSize = headlineQueryVo.getPageSize();
        Integer totalSize = headLineDao.findPageCount(headlineQueryVo);
        List<HeadlinePageVo> pageData = headLineDao.findPageList(headlineQueryVo);
        Integer totalPage = (int)Math.ceil((double) totalSize / pageSize);
        pageInfo.put("pageNum", headlineQueryVo.getPageNum());
        pageInfo.put("pageSize", pageSize);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("totalSize", totalSize);
        pageInfo.put("pageData", pageData);

        return pageInfo;
    }

    @Override
    public Map<String, HeadlineDetailVo> findHeadlineDetail(Integer hid) {
        headLineDao.addPageViews(hid);
        HeadlineDetailVo detailVo = headLineDao.findHeadlineDetail(hid);
        Map<String, HeadlineDetailVo> data = new HashMap<>();
        data.put("headline",detailVo);
        return data;
    }


    public Integer addNewsHeadline(NewsHeadline newsHeadline) {
        return headLineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public Integer updateNewsHeadline(NewsHeadline newsHeadline) {
        return headLineDao.updateNewsHeadline(newsHeadline);
    }

    @Override
    public Integer remove(Integer hid) {
        return headLineDao.remove(hid);
    }
}
