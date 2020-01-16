package com.example.newdraft.mapper;

import com.example.newdraft.model.pojo.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsMapper {
    /**
     * 查询新闻 根据点击量排序 index 页数 limit 每页显示多少
     * @return
     */
    List<News> selectAllNews(Map<String,Object> map);

    /**
     * 根据id查询新闻详细信息
     * @param id
     * @return
     */
    News selectByNewsId(int id);
}
