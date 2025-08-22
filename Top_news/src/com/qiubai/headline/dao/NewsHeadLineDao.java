package com.qiubai.headline.dao;

import com.qiubai.headline.pojo.vo.HeadlineDetailVo;
import com.qiubai.headline.pojo.vo.HeadlinePageVo;
import com.qiubai.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadLineDao {

    /**
     * 按条件查找页面信息
     * @param queryVo
     * @return 返回格式为链表
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo queryVo);

    /**
     * 按条件查找页面总数
     * @param queryVo
     * @return
     */
    Integer findPageCount(HeadlineQueryVo queryVo);

    /**
     *
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(Integer hid);

    Integer addPageViews(Integer hid);
}
