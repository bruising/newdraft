package com.example.newdraft.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: newdraft
 * @Package com.example.newdraft.model.pojo
 * @Description: ${todo}
 * @author 吴成卓
 * @date 2020/1/16 星期四 13:42
 * @version V1.0 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programs {
    /**
    * 项目主键
    */
    private Integer programId;

    /**
    * 项目标题
    */
    private String programTitle;

    /**
    * 项目所属国家
    */
    private String country;

    /**
    * 项目简介
    */
    private String programInfo;

    /**
    * 项目的申请要求
    */
    private String programRequire;

    /**
    * 联系方式
    */
    private String programPhone;

    /**
    * 1：正常，2：已下架
    */
    private Integer programStatus;
}