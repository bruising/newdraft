package com.example.newdraft.service;

import com.example.newdraft.model.pojo.News;

import java.util.List;
import java.util.Map;

public interface NewsService {
    /**
     * 查询新闻 根据点击量排序
     * @param map
     * @return
     */
    List<News> inquiryAllNews(Map<String,Object> map);
    /**
     * 根据新闻id 查询新闻详细信息
     * @param id
     * @return
     */
    News inquiryByNewsId(int id);

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
}
