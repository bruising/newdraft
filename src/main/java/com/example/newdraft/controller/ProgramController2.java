package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.vo.Message;
import com.example.newdraft.service.ProgramsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 胡婷婷
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.controller
 * @date 2020/1/28 14:13 星期二
 * @Description
 */
@Controller
@RequestMapping(value = "/program")
@Api(tags = "这是胡婷婷写的类")
public class ProgramController2 {

    @Autowired
    private ProgramsService programsService;

    @PostMapping(value = "/searchPrograms")
    @ResponseBody
    @ApiOperation(value = "展示项目列表", notes = "查询成功则返回符合条件的所有项目，失败则无结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programTitle", value = "项目标题", dataType = "String", example = "xx"),
            @ApiImplicitParam(name = "country", value = "项目所属国家", dataType = "String", example = "xx(国家全称， 例：法国)")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 123, message = "未查询到符合条件的项目")
    })
    public Message searchPrograms(
            @RequestParam(value = "programTitle", required = false, defaultValue = "") String programTitle,
            @RequestParam(value = "country", required = false, defaultValue = "") String country){
        Map<String, Object> map = new HashMap<>();
        if (programTitle!=""){
            map.put("programTitle", programTitle);
        }
        if (country!=""){
            map.put("country", country);
        }
        return programsService.searchPrograms(map);
    }

    @RequestMapping(value = "/searchProgramInfo")
    @ResponseBody
    @ApiOperation(value = "项目详情", notes = "查询成功则展示到详情页，失败则不显示结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId", value = "项目ID", dataType = "String", example = "1"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 123, message = "未查询到项目详情")
    })
    public String searchProgramInfo(
            @RequestParam(value = "programId", required = false, defaultValue = "") String programId){
        System.out.println(programId);
//        Message message = programsService.searchProgramInfo(programId);
//        Map<String, Object> map = new HashMap<>();
//        map.put("data", message);
        return JSON.toJSONString(programsService.searchProgramInfo(programId));
    }

    @PostMapping(value = "/deleteProgramById")
    @ResponseBody
    @ApiOperation(value = "删除项目", notes = "根据项目ID下架该项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId", value = "项目ID", dataType = "String", example = "1"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "下架项目成功"),
            @ApiResponse(code = 123, message = "下架项目失败")
    })
    public Message deleteProgramById(
            @RequestParam(value = "programId", required = false, defaultValue = "") String programId){
        return programsService.deleteProgramById(programId);
    }
}
