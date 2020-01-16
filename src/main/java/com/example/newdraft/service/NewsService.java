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
}
