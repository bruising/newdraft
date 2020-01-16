package com.example.newdraft.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: newdraft
 * @Package com.example.newdraft.model.pojo
 * @Description: ${todo}
 * @author 吴成卓
 * @date 2020/1/16 星期四 13:44
 * @version V1.0 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidResourcesOwner {
    /**
    * 新闻或项目图片的中间表   主键
    */
    private Integer mroId;

    /**
    * 关联资源表的主键
    */
    private Integer resId;

    /**
    * 关联项目ID或新闻ID，与下面类型对应
    */
    private Integer resOwner;

    /**
    * 类型：1 项目  2 新闻
    */
    private Integer resType;
}