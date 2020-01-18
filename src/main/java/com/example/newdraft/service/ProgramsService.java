package com.example.newdraft.service;

import com.example.newdraft.model.pojo.Programs;

public interface ProgramsService {

    int addPrograms( Programs programs);//添加新的项目

    int updatePrograms( Programs programs);//修改项目

    Programs getProgramsById( String id);//通过编号获取项目信息

}
