package com.example.newdraft.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.mapper.ProgramsMapper;
import com.example.newdraft.model.pojo.Programs;
import com.example.newdraft.model.vo.Message;
import com.example.newdraft.model.vo.ProgramsVo;
import com.example.newdraft.service.ProgramsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public Message searchPrograms(Map<String, Object> map) {
        List<Programs> programs = programsMapper.searchPrograms(map);
        Message message = new Message();
        if (programs!=null && programs.size()>0){
            message.setCode("200");
            message.setMsg("查询成功");
            message.setData(JSON.toJSONString(programs));
        }else {
            message.setCode("123");
            message.setMsg("未查询到符合条件的项目");
        }
        return message;
    }

    @Override
    public Message searchProgramInfo(String programId) {
        ProgramsVo program = programsMapper.searchProgramInfo(programId);
        Message message = new Message();
        if (program!=null){
            message.setCode("200");
            message.setMsg("查询成功");
            message.setData(JSON.toJSONString(program));
        }else {
            message.setCode("123");
            message.setMsg("未查询到项目详情");
        }
        return message;
    }

    @Override
    public Message deleteProgramById(String programId) {
        Message message = new Message();
        if(programsMapper.deleteProgramById(programId)>0){
            message.setCode("200");
            message.setMsg("下架项目成功");
        }else {
            message.setCode("123");
            message.setMsg("下架项目失败");
        }
        return message;
    }

}
