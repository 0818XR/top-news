package com.qiubai.headline.service;

import com.qiubai.headline.pojo.NewsHeadline;
import com.qiubai.headline.pojo.vo.HeadlineDetailVo;
import com.qiubai.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadLineService {

    /**
     * 按条件返回页面信息
     * @param headlineQueryVo
     * @return
     */
    Map<String, Object> findPage(HeadlineQueryVo headlineQueryVo);

    /**
     *
     * @param hid
     * @return
     */
    Map<String, HeadlineDetailVo> findHeadlineDetail(Integer hid);

    /**
     *
     * @param newsHeadline
     * @return
     */
    Integer addNewsHeadline(NewsHeadline newsHeadline);

    Integer updateNewsHeadline(NewsHeadline newsHeadline);

    Integer remove(Integer hid);
}
