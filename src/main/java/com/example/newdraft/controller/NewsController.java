package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.News;
import com.example.newdraft.model.vo.Message;
import com.example.newdraft.service.NewsService;
import com.example.newdraft.util.QNYUtils;
import com.example.newdraft.util.RedisUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "/news")
@Api(tags = "吴昊天写的类")
public class NewsController {
    @Resource
    private NewsService newsService;
    @Resource
    private QNYUtils qnyUtils;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 查询显示新闻信息按点击量排序
     * @param index
     * @param limit
     * @return
     */
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

    /**
     * 根据id查询新闻详细信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询新闻详细信息",notes = "正确返回信息信息,错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "新闻id",dataType = "String",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @PostMapping("/QueryByID")
    public Message QueryByID(@RequestParam("id")int id){
        News news = newsService.inquiryByNewsId(id);
        Message message = new Message();
        if(null == news){
            message.setCode("4");
            message.setMsg("failed");
        }else{
            message.setCode("0");
            message.setMsg("success");
            message.setData(JSON.toJSONString(news));
        }
        return message;
    }

    /**
     * 文本编译器图片上传
     * @param file
     * @return
     */
    @ApiOperation(value = "新闻上传图片",notes = "上传成功返回状态码0及url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "edit",value = "文件",dataType = "MultipartFile")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @RequestMapping(value = "/doUpload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("edit") MultipartFile file) {
            String name = file.getOriginalFilename();
            DefaultPutRet defaultPutRet=null;
            Map<String,Object>map=new HashMap<>();
            try {
                Response response = qnyUtils.upload(file.getInputStream(), name);
                defaultPutRet = qnyUtils.defaultPutRet(response);
            } catch (Exception e) {
                map.put("status","failed");
                map.put("code",4);
                e.printStackTrace();
            }
            map.put("url",qnyUtils.getPath()+defaultPutRet.key);
            if(defaultPutRet.key!=null&&defaultPutRet.key!=""){
                map.put("status","success");
                map.put("code",0);
            }
            return JSON.toJSONString(map);

    }

    /**
     * 后台新闻列表渲染
     * @param news_title
     * @param userName
     * @param news_status
     * @param limit
     * @param page
     * @return
     */
    @ApiOperation(value = "渲染新闻列表",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "news_title",value = "新闻标题",dataType = "String"),
            @ApiImplicitParam(name = "userName",value = "发布人",dataType = "String"),
            @ApiImplicitParam(name = "news_status",value = "新闻状态",dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "每页条数",dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "Integer")
    })
    @RequestMapping(value = "/showAllNews")
    @ResponseBody
    public String showAllNews(@RequestParam(value = "news_title",required = false,defaultValue = "") String news_title,
                              @RequestParam(value = "userName",required = false,defaultValue = "") String userName,
                              @RequestParam(value = "news_status",required = false,defaultValue = "0") Integer news_status,
                              @RequestParam(value = "limit",required = false,defaultValue = "5") Integer limit,
                              @RequestParam(value = "page",required = false,defaultValue = "1") Integer page){
        Map<String, Object> map = new HashMap<>();
        map.put("news_title",news_title);
        map.put("userName",userName);
        map.put("news_status",news_status);
        map.put("limit",limit);
        map.put("page",page);
        Map<String, Object> map1 = newsService.queryNewsList(map);
        return JSON.toJSONString(map1);
    }

    /**
     * 新闻权重修改
     * @param news
     * @param token
     * @return
     */
    @ApiOperation(value = "修改新闻的权重",notes = "修改成功返回状态码0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "news_id",value = "新闻id",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "news_level",value = "新闻权重",dataType = "Integer",example = "2"),
            @ApiImplicitParam(name = "token",value = "token验证",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success"),
            @ApiResponse(code = 5,message = "noToken")
    })
    @RequestMapping(value = "/updateNewsLevel",method = RequestMethod.POST)
    @ResponseBody
    public String updateNewsLevel(News news,String token){
        Message message = new Message();
        if(redisUtils.judgeToken(token)){
            if(newsService.updateNewsLevel(news)){
                message.setCode("0");
                message.setMsg("success");
            }else{
                message.setCode("4");
                message.setMsg("failed");
            }
        }else{
            message.setCode("5");
            message.setMsg("noToken");
        }
        return JSON.toJSONString(message);
    }
}


