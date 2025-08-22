package com.qiubai.headline.service;

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
}
