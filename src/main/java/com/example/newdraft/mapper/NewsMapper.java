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
    List<NewsList> selectAllNews(Map<String,Object> map);

    Long selectAllNewsCount();

    /**
     * 根据id查询新闻详细信息
     * @param num
     * @return
     */
    NewsList selectByNewsId(@Param("num")int num);

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

    /**
     * 下架新闻
     * @param news
     * @return
     */
    int delNews(News news);

    /**
     * 上架新闻
     * @param news
     * @return
     */
    int shang(News news);

    /**
     * 管理员发布新闻
     * @param news
     * @return
     */
    int add(News news);

    /**
     * 修改新闻的查看数量
     * @param news_id
     * @return
     */
    int updateNewsIndex(@Param("news_id") Integer news_id);

    int queryNewsCount();
}
