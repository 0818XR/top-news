package com.qiubai.headline.dao.impl;

import com.qiubai.headline.dao.BaseDao;
import com.qiubai.headline.dao.NewsHeadLineDao;
import com.qiubai.headline.dao.NewsUserDao;
import com.qiubai.headline.pojo.vo.HeadlineDetailVo;
import com.qiubai.headline.pojo.vo.HeadlinePageVo;
import com.qiubai.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NewsHeadLineDaoImpl extends BaseDao implements NewsHeadLineDao {
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo queryVo) {
        List<Object> args = new LinkedList<>();
        /*
        pageData中的数据为：
        hid
        title
        type
        pageViews
        pastHours
        publisher */
        String sql = """
                select 
                    hid,
                    title,
                    type,
                    page_views pageViews,
                    TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,
                    publisher 
                from
                    news_headline 
                where
                    is_deleted = 0 
                """;
        if(queryVo.getType() != null && queryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            args.add(queryVo.getType());
        }
        if (queryVo.getKeyWords() != null && !queryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ?");
            args.add("%" + queryVo.getKeyWords() +"%");
        }
        sql = sql.concat(" order by pastHours , page_views DESC ");
        sql = sql.concat(" limit ? , ? ");
        Integer frontPageNumber = (queryVo.getPageNum()-1 )* queryVo.getPageSize();
        args.add(frontPageNumber);
        args.add(queryVo.getPageSize());

        return baseQuery(HeadlinePageVo.class, sql, args.toArray());
    }

    @Override
    public Integer findPageCount(HeadlineQueryVo queryVo) {
        List<Object> args = new LinkedList<>();
        String sql = """
                select 
                    count(1)
                from
                    news_headline 
                where
                    is_deleted = 0 
                """;
        if(queryVo.getType() != null && queryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            args.add(queryVo.getType());
        }
        if (queryVo.getKeyWords() != null && !queryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ?");
            args.add("%" + queryVo.getKeyWords() +"%");
        }
        Long count = baseQueryObject(Long.class, sql, args.toArray());
        return count.intValue();
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        String sql = """
                select
                    h.hid hid,
                    h.title title,
                    h.article article,
                    h.type type,
                    t.tname typeName,
                    h.page_views pageViews,
                    TIMESTAMPDIFF(HOUR,h.create_time,NOW()) pastHours,
                    h.publisher publisher,
                    u.nick_name author
                from
                    news_headline h
                    left join news_type t on h.type = t.tid
                    left join news_user u on h.publisher = u.uid
                where
                    is_deleted = 0 and hid = ? 
                """;
        List<HeadlineDetailVo> list = baseQuery(HeadlineDetailVo.class, sql, hid);
        return list != null && list.size()>0 ? list.get(0) : null;
    }

    public Integer addPageViews(Integer hid) {
        String sql = "update news_headline set page_views = page_views + 1 where hid = ? ";
        return baseUpdate(sql, hid);

    }
}
