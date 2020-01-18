package com.example.newdraft.service.impl;

import com.example.newdraft.mapper.ProgramsMapper;
import com.example.newdraft.model.pojo.Programs;
import com.example.newdraft.service.ProgramsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProgramsServiceImpl implements ProgramsService {

    @Resource
    private ProgramsMapper programsMapper;

    @Override
    public int addPrograms(Programs programs) {
        return programsMapper.addPrograms(programs);
    }

    @Override
    public int updatePrograms(Programs programs) {
        return programsMapper.updatePrograms(programs);
    }

    @Override
    public Programs getProgramsById(String id) {
        return programsMapper.getProgramsById(id);
    }
}
