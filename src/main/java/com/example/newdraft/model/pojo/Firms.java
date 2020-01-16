package com.example.newdraft.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: newdraft
 * @Package com.example.newdraft.model.pojo
 * @Description: ${todo}
 * @author 吴成卓
 * @date 2020/1/16 星期四 13:41
 * @version V1.0 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Firms {
    /**
    * 商家主键
    */
    private Integer firmId;

    /**
    * 关联 users表id 需满足users表user_role为2
    */
    private Integer userId;

    /**
    * 商家简介
    */
    private String firmInfo;

    /**
    * 商家的官网链接
    */
    private String link;
}