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
}
