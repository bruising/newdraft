package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.service.ProgramsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/searchPrograms")
    @ResponseBody
    @ApiOperation(value = "展示项目列表", notes = "查询成功则返回符合条件的所有项目，失败则无结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programTitle", value = "项目标题", dataType = "String", example = "xx"),
            @ApiImplicitParam(name = "country", value = "项目所属国家", dataType = "String", example = "xx(国家全称， 例：法国)"),
            @ApiImplicitParam(name = "programStatus", value = "项目当前状态", dataType = "String", example = "1"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "String",example = "1"),
            @ApiImplicitParam(name = "limit",value = "每页显示的新闻数量",dataType = "String",example = "5")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 123, message = "未查询到符合条件的项目")
    })
    public String searchPrograms(
            @RequestParam(value = "programTitle", required = false, defaultValue = "") String programTitle,
            @RequestParam(value = "country", required = false, defaultValue = "") String country,
            @RequestParam(value = "programStatus", required = false, defaultValue = "1") Integer programStatus,
            @RequestParam(value = "limit",required = false,defaultValue = "0") Integer limit,
            @RequestParam(value = "page",required = false,defaultValue = "0") Integer page){
        Map<String, Object> map = new HashMap<>();
        if (programTitle!=""){
            map.put("programTitle", programTitle);
        }
        if (country!=""){
            map.put("country", country);
        }
        if (programStatus>0){
            map.put("programStatus", programStatus);
        }
        map.put("limit", limit);
        map.put("page", page);
        return JSON.toJSONString(programsService.searchPrograms(map));
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
        return JSON.toJSONString(programsService.searchProgramInfo(programId));
    }

    @RequestMapping(value = "/deleteProgramById")
    @ResponseBody
    @ApiOperation(value = "删除项目", notes = "根据项目ID下架该项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId", value = "项目ID", dataType = "String", example = "1"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "下架项目成功"),
            @ApiResponse(code = 123, message = "下架项目失败")
    })
    public String deleteProgramById(
            @RequestParam(value = "programId", required = false, defaultValue = "") String programId){
        return JSON.toJSONString(programsService.deleteProgramById(programId));
    }
}
