package com.example.newdraft.mapper;

import com.example.newdraft.model.pojo.News;
import com.example.newdraft.model.vo.NewsList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 后台显示新闻列表
     * @param map
     * @return
     */
    List<NewsList> selectAllNewsList(@Param("map") Map<String,Object>map);

    /**
     *条数
     * @param map
     * @return
     */
    Long selectAllNewsListCont(@Param("map") Map<String,Object>map);

    /**
     * 修改权重
     * @param news
     * @return
     */
    int updateNewsLevel(News news);
}
