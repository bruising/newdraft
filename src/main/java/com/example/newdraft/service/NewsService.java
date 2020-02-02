package com.example.newdraft.service;

import com.example.newdraft.model.pojo.News;
import com.example.newdraft.model.vo.NewsList;

import java.util.Map;

public interface NewsService {
    /**
     * 查询新闻 根据点击量排序
     * @param map
     * @return
     */
    Map<String,Object> inquiryAllNews(Map<String,Object> map);
    /**
     * 根据新闻序号 查询新闻详细信息
     * @param num
     * @return
     */
    NewsList inquiryByNewsId(int num);

    /**
     * 后台新闻列表
     * @param map
     * @return
     */
    Map<String,Object>queryNewsList(Map<String,Object>map);

    /**
     * 修改权重
     * @param news
     * @return
     */
    boolean updateNewsLevel(News news);

    /**
     * 下架新闻
     * @param news
     * @return
     */
    boolean del(News news);

    /**
     * 上架新闻
     * @param news
     * @return
     */
    boolean shang(News news);

    /**
     * 管理员发布新闻
     * @param news
     * @return
     */
    boolean add(News news);

    /**
     * 查询新闻详细信息
     * @param news
     * @return
     */
    NewsList queryNewsById(News news);

    /**
     * 新闻查看数量+1
     * @param news_id
     * @return
     */
    boolean addNewsIndex(Integer news_id);
    int  queryNewsCount();
}
