package com.example.newdraft.model.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    /**
     * 新闻主键
     */
    private Integer news_id;
    /**
     *  新闻标题
     */
    private String news_title;
    /**
     * 新闻内容
     */
    private String news_text;
    /**
     * 商户发的：1，管理员发的：2
     */
    private Integer news_type;
    /**
     * 关联users表id  外键
     */
    private Integer news_owner;
    /**
     * 创建时间
     */
    private Date news_createTime;
    /**
     * 审核时间
     */
    private Date news_verifiedTime;
    /**
     * 最后修改时间
     */
    private Date news_updatedTime;
    /**
     * 优先级，默认为1，越高的越靠前
     */
    private Integer news_level;
    /**
     * 点击量越高越靠前
     */
    private Integer news_index;
    /**
     * 1：通过，2：未审批，3：已删除
     */
    private Integer news_status;

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_text() {
        return news_text;
    }

    public void setNews_text(String news_text) {
        this.news_text = news_text;
    }

    public Integer getNews_type() {
        return news_type;
    }

    public void setNews_type(Integer news_type) {
        this.news_type = news_type;
    }

    public Integer getNews_owner() {
        return news_owner;
    }

    public void setNews_owner(Integer news_owner) {
        this.news_owner = news_owner;
    }

    public Date getNews_createTime() {
        return news_createTime;
    }

    public void setNews_createTime(Date news_createTime) {
        this.news_createTime = news_createTime;
    }

    public Date getNews_verifiedTime() {
        return news_verifiedTime;
    }

    public void setNews_verifiedTime(Date news_verifiedTime) {
        this.news_verifiedTime = news_verifiedTime;
    }

    public Date getNews_updatedTime() {
        return news_updatedTime;
    }

    public void setNews_updatedTime(Date news_updatedTime) {
        this.news_updatedTime = news_updatedTime;
    }

    public Integer getNews_level() {
        return news_level;
    }

    public void setNews_level(Integer news_level) {
        this.news_level = news_level;
    }

    public Integer getNews_index() {
        return news_index;
    }

    public void setNews_index(Integer news_index) {
        this.news_index = news_index;
    }

    public Integer getNews_status() {
        return news_status;
    }

    public void setNews_status(Integer news_status) {
        this.news_status = news_status;
    }
}
