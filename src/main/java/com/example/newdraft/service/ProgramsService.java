package com.example.newdraft.service;

import com.example.newdraft.model.pojo.Programs;

import java.util.Map;

public interface ProgramsService {

    int addPrograms( Programs programs);//添加新的项目

    int updatePrograms( Programs programs);//修改项目

    Programs getProgramsById( String id);//通过编号获取项目信息

    /**
     * 项目列表
     * @param map 查询条件（项目名称模糊查询、国家名）
     * @return
     */
    Map<String, Object> searchPrograms(Map<String, Object> map);
    /**
     * 项目详情页
     * @param programId 项目ID
     * @return 项目详情
     */
    Map<String, Object> searchProgramInfo(String programId);
    /**
     * 下架项目
     * @param programId 项目ID
     * @return 执行结果
     */
    Map<String, Object> deleteProgramById(String programId);
}
