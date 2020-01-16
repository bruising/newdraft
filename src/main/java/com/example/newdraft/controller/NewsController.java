package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.News;
import com.example.newdraft.model.vo.Message;
import com.example.newdraft.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "/news")
@Api(tags = "吴昊天写的类")
public class NewsController {
    @Resource
    private NewsService newsService;
    @ApiOperation(value = "查询显示新闻信息按点击量排序",notes = "正确返回信息信息,错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index",value = "页数",dataType = "String",example = "1"),
            @ApiImplicitParam(name = "limit",value = "每页显示的新闻数量",dataType = "String",example = "10")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @PostMapping("/showNews")
    public Message showNews(@RequestParam(value = "index")int index,@RequestParam("limit")int limit){
        Map<String ,Object> map = new HashMap<>();
        map.put("index",index);
        map.put("limit",limit);
        List<News> newsList = newsService.inquiryAllNews(map);
        Message message = new Message();
        if(null == newsList){
            message.setCode("4");
            message.setMsg("failed");
        }else{
            message.setCode("0");
            message.setMsg("success");
            message.setData(JSON.toJSONString(newsList));
        }
        return message;
    }
}
