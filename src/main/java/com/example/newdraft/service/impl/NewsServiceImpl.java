package com.example.newdraft.service.impl;

import com.example.newdraft.mapper.NewsMapper;
import com.example.newdraft.model.pojo.News;
import com.example.newdraft.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;
    @Override
    public List<News> inquiryAllNews(Map<String, Object> map) {
        List<News> newsList = newsMapper.selectAllNews(map);
        if(null == newsList){
            return null;
        }else {
            return newsList;
        }
    }
}
