package com.example.newdraft.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 胡婷婷
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.model.vo
 * @date 2020/1/28 17:59 星期二
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramsVo {
    /**
     * 项目主键
     */
    private Integer programId;
    /**
     * 项目标题
     */
    private String programTitle;

    /**
     * 项目简介
     */
    private String programInfo;
    /**
     * 所属国家
     */
    private String country;
    /**
     * 项目的申请要求
     */
    private String programRequire;
    /**
     * 图片
     */
    private List<String> resource;

    /**
     * 联系方式
     */
    private String programPhone;

    /**
     * 商家名称
     */
    private List<String> firmNames;
    /**
     * 商家链接
     */
    private List<String> links;
}
