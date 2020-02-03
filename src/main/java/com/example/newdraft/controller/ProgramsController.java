package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.Programs;
import com.example.newdraft.model.vo.Message;
import com.example.newdraft.service.ProgramsService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/program")
@Api(tags = "这是刘小博写的类")
public class ProgramsController {

    @Resource
    private ProgramsService programsService;

    @RequestMapping(value = "/doAddProgram",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加新的项目",notes = "正确返回正确码,错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId",value = "编号（自动增长，不可以填写！！）",dataType = "int",example = ""),
            @ApiImplicitParam(name = "programTitle",value = "标题",dataType = "String",example = "这是一个项目"),
            @ApiImplicitParam(name = "country",value = "国家",dataType = "String",example = "英国"),
            @ApiImplicitParam(name = "programInfo",value = "项目简介",dataType = "String",example = "这是关于英国移民的项目"),
            @ApiImplicitParam(name = "programRequire",value = "项目需求",dataType = "String",example = "1.成绩好。2.乐于助人。3.爱运动。"),
            @ApiImplicitParam(name = "programPhone",value = "联系方式",dataType = "String",example = "18900001111"),
            @ApiImplicitParam(name = "programLevel",value = "项目重要等级数字越大越靠前展示（可以不写，默认为1）",dataType = "String",example = "1"),
            @ApiImplicitParam(name = "programStatus",value = "项目上架情况1上架，2下架（可以不写，默认为1）",dataType = "int",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    public String doAddPrograms(Programs programs){
        Map<String,Object>map=new HashMap<>();
        int num = programsService.addPrograms(programs);
        if(num>1){
            map.put("msg","failed");
            map.put("code",4);
        }else{
            map.put("msg","success");
            map.put("code",0);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/doUpdateProgram",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改项目",notes = "正确返回正确码,错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId",value = "编号（通过点击修改获得 不可以修改！！）",dataType = "int",example = "1"),
            @ApiImplicitParam(name = "programTitle",value = "标题",dataType = "String",example = "这是一个项目"),
            @ApiImplicitParam(name = "country",value = "国家",dataType = "String",example = "英国"),
            @ApiImplicitParam(name = "programInfo",value = "项目简介",dataType = "String",example = "这是关于英国移民的项目"),
            @ApiImplicitParam(name = "programRequire",value = "项目需求",dataType = "String",example = "1.成绩好。2.乐于助人。3.爱运动。"),
            @ApiImplicitParam(name = "programPhone",value = "联系方式",dataType = "String",example = "18900001111"),
            @ApiImplicitParam(name = "programLevel",value = "项目重要等级数字越大越靠前展示（可以不写，默认为1）",dataType = "String",example = "1"),
            @ApiImplicitParam(name = "programStatus",value = "项目上架情况1上架，2下架（可以不写，默认为1）",dataType = "int",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    public String doUpdatePrograms(Programs programs){
        Map<String,Object>map=new HashMap<>();
        System.out.println(programs);
        int num = programsService.updatePrograms(programs);
        if(num>1){
            map.put("msg","failed");
            map.put("code",4);
        }else{
            map.put("msg","success");
            map.put("code",0);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getProgramsById",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取项目信息",notes = "正确返回项目信息,错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "项目的编号",dataType = "String",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    public Message getProgramsById(String id){
        Map<String ,Object> map = new HashMap<>();
        Message message = new Message();
        Programs programs = programsService.getProgramsById(id);
        if(null==programs){
            message.setCode("4");
            message.setMsg("failed");
        }else{
            message.setCode("0");
            message.setMsg("success");
            message.setData(JSON.toJSONString(programs));
        }
        return message;
    }


}
