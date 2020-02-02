package com.example.newdraft.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.model.vo
 * @Description:
 * @date 2020/1/19 星期日 11:41
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsList {
    private Integer news_id;
    private String news_title;
    private String news_text;
    private Integer news_type;
    private String userName;
    private Date news_createTime;
    private Date news_verifiedTime;
    private Date news_updatedTime;
    private Integer news_level;
    private Integer news_index;
    private Integer news_status;
    private Integer num;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
