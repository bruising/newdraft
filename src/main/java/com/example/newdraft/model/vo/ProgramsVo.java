package com.example.newdraft.model.vo;

import java.util.List;

/**
 * @author 胡婷婷
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.model.vo
 * @date 2020/1/28 17:59 星期二
 * @Description
 */
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

    public ProgramsVo(Integer programId, String programTitle, String programInfo, String country, String programRequire, List<String> resource, String programPhone, List<String> firmNames, List<String> links) {
        this.programId = programId;
        this.programTitle = programTitle;
        this.programInfo = programInfo;
        this.country = country;
        this.programRequire = programRequire;
        this.resource = resource;
        this.programPhone = programPhone;
        this.firmNames = firmNames;
        this.links = links;
    }

    public ProgramsVo() {
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getProgramInfo() {
        return programInfo;
    }

    public void setProgramInfo(String programInfo) {
        this.programInfo = programInfo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProgramRequire() {
        return programRequire;
    }

    public void setProgramRequire(String programRequire) {
        this.programRequire = programRequire;
    }

    public List<String> getResource() {
        return resource;
    }

    public void setResource(List<String> resource) {
        this.resource = resource;
    }

    public String getProgramPhone() {
        return programPhone;
    }

    public void setProgramPhone(String programPhone) {
        this.programPhone = programPhone;
    }

    public List<String> getFirmNames() {
        return firmNames;
    }

    public void setFirmNames(List<String> firmNames) {
        this.firmNames = firmNames;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
