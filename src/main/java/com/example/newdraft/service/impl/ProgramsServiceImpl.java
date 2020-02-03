package com.example.newdraft.service.impl;

import com.example.newdraft.mapper.ProgramsMapper;
import com.example.newdraft.model.pojo.Programs;
import com.example.newdraft.model.vo.ProgramsVo;
import com.example.newdraft.service.ProgramsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public Map<String, Object> searchPrograms(Map<String, Object> map) {
        //默认值
        map.put("count",0);
        map.put("msg","");
        map.put("code", 0);
        int page=Integer.parseInt(map.get("page").toString());
        int limit=Integer.parseInt(map.get("limit").toString());
        int index=(page-1)*limit;
        map.put("index",index);
        List<Programs> programs = programsMapper.searchPrograms(map);
        long num = programsMapper.selectAllProgramsCount(map);
        if(num>0){
            map.put("data",programs);
            map.put("count",num);
        }
        return map;
    }

    @Override
    public Map<String, Object> searchProgramInfo(String programId) {
        ProgramsVo program = programsMapper.searchProgramInfo(programId);
        Map<String, Object> map = new HashMap<>();
        if (program!=null){
            map.put("code", 200);
            map.put("data", program);
        }else {
            map.put("code", 123);
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteProgramById(String programId) {
        Map<String, Object> map = new HashMap<>();
        map.put("code",4);
        if(programsMapper.deleteProgramById(programId)>0){
            map.put("code",0);
        }
        return map;
    }

    @Override
    public int queryProgramsCount() {
        return programsMapper.queryProgramsCount();
    }

}
