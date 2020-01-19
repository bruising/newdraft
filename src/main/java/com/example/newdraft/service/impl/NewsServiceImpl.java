package com.example.newdraft.service.impl;

import com.example.newdraft.mapper.NewsMapper;
import com.example.newdraft.model.pojo.News;
import com.example.newdraft.model.vo.NewsList;
import com.example.newdraft.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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

    @Override
    public News inquiryByNewsId(int id) {
        News news = newsMapper.selectByNewsId(id);
        if(null == news ){
            return null;
        }
        return news;
    }

    @Override
    public Map<String, Object> queryNewsList(Map<String, Object> map) {
        Map<String,Object>statusMap=new HashMap<String, Object>();
        statusMap.put("code",0);
        statusMap.put("msg","");
        statusMap.put("count",0);
        int page=Integer.parseInt(map.get("page").toString());
        int limit=Integer.parseInt(map.get("limit").toString());
        int index=(page-1)*limit;
        map.put("index",index);
        List<NewsList> newsLists = newsMapper.selectAllNewsList(map);
        long num = newsMapper.selectAllNewsListCont(map);
        if(num>0){
            statusMap.put("data",newsLists);
            statusMap.put("count",num);
        }
        return statusMap;
    }
    @Override
    public boolean updateNewsLevel(News news){
        int i = newsMapper.updateNewsLevel(news);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean del(News news) {
        int i = newsMapper.delNews(news);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean shang(News news) {
        int i = newsMapper.shang(news);
        if(i>0){
            return true;
        }
        return false;
    }
}
