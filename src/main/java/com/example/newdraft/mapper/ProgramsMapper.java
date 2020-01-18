package com.example.newdraft.mapper;

import com.example.newdraft.model.pojo.Programs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProgramsMapper {
    int addPrograms(@Param("programs") Programs programs);//添加新的项目

    int updatePrograms(@Param("programs") Programs programs);//修改项目

    Programs getProgramsById(@Param("id") String id);//通过编号获取项目信息
}
