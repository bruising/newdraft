package com.example.newdraft.mapper;

import com.example.newdraft.model.pojo.Programs;
import com.example.newdraft.model.vo.ProgramsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProgramsMapper {
    int addPrograms(@Param("programs") Programs programs);//添加新的项目

    int updatePrograms(@Param("programs") Programs programs);//修改项目

    Programs getProgramsById(@Param("id") String id);//通过编号获取项目信息

    /**
     *项目列表
     * @param map 查询条件（项目名称模糊查询、国家名）
     * @return
     */
    List<Programs> searchPrograms(Map<String, Object> map);

    Long selectAllProgramsCount();

    /**
     * 项目详情页
     * @param programId 项目ID
     * @return 项目详情
     */
    ProgramsVo searchProgramInfo(@Param("programId") String programId);

    /**
     * 下架项目
     * @param programId 项目ID
     * @return 执行结果
     */
    int deleteProgramById(@Param("programId") String programId);

    /***
     * 查询项目数
     * @return
     */
    int  queryProgramsCount();
}
