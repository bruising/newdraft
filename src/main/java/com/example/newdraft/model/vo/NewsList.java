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
}
