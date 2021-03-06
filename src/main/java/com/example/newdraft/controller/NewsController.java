package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.News;
import com.example.newdraft.model.vo.Message;
import com.example.newdraft.model.vo.NewsList;
import com.example.newdraft.service.NewsService;
import com.example.newdraft.service.ProgramsService;
import com.example.newdraft.service.UsersService;
import com.example.newdraft.util.QNYUtils;
import com.example.newdraft.util.RedisUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestController(value = "/news")
@Api(tags = "吴昊天写的类")
public class NewsController {
    @Resource
    private NewsService newsService;
    @Resource
    private ProgramsService  programsService;
    @Resource
    private UsersService  usersService;
    @Resource
    private QNYUtils qnyUtils;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 查询显示新闻信息按点击量排序
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "查询显示新闻信息按点击量排序",notes = "正确返回信息信息,错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "String",example = "1"),
            @ApiImplicitParam(name = "limit",value = "每页显示的新闻数量",dataType = "String",example = "5")
    })
    @RequestMapping(value = "/showNews",method = RequestMethod.POST)
    @ResponseBody
    public String showNews(@RequestParam(value = "limit",required = false,defaultValue = "5") Integer limit,
                            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page){
        Map<String ,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        Map<String, Object> map1 = newsService.inquiryAllNews(map);
        return JSON.toJSONString(map1);
    }

    /**
     * 根据id查询新闻详细信息
     * @param num
     * @return
     */
    @ApiOperation(value = "根据id查询新闻详细信息",notes = "正确返回信息信息,错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "新闻序号",dataType = "String",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @PostMapping("/QueryByID")
    public Message QueryByID(@RequestParam("num")int num){
        NewsList newsList = newsService.inquiryByNewsId(num);
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

    /**
     * 新闻下架
     * @param news
     * @param token
     * @return
     */
    @ApiOperation(value = "新闻下架",notes = "成功返回状态码0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "news_id",value = "新闻id",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "token",value = "token验证",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success"),
            @ApiResponse(code = 5,message = "noToken")
    })
    @RequestMapping(value = "/delNews",method = RequestMethod.POST)
    @ResponseBody
    public String delNews(News news,String token){
        Message message = new Message();
        if(redisUtils.judgeToken(token)){
            if(newsService.del(news)){
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


    /**
     * 新闻上架
     * @param news
     * @param token
     * @return
     */
    @ApiOperation(value = "新闻上架",notes = "成功返回状态码0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "news_id",value = "新闻id",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "token",value = "token验证",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success"),
            @ApiResponse(code = 5,message = "noToken")
    })
    @RequestMapping(value = "/shang",method = RequestMethod.POST)
    @ResponseBody
    public String shang(News news,String token){
        Message message = new Message();
        if(redisUtils.judgeToken(token)){
            if(newsService.shang(news)){
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

    /**
     * 管理员发布新闻
     * @param news
     * @param token
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(News news,String token){
        System.out.println(news);
        Message message = new Message();
        if(redisUtils.judgeToken(token)){
            if(newsService.add(news)){
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


    /**
     * 查看某个新闻
     * @param news
     * @param token
     * @return
     */
    @RequestMapping(value = "/queryNewById",method = RequestMethod.POST)
    @ResponseBody
    public String queryNewById(News news,String token){
        System.out.println(news);
        Message message = new Message();
        if(redisUtils.judgeToken(token)){
            NewsList newsList = newsService.queryNewsById(news);
            if(newsList!=null){
                System.out.println(newsList);
                message.setCode("0");
                message.setMsg("success");
                message.setData(newsList.getNews_text());
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

    /**
     * 新闻查看数量+1
     * @param news_id
     * @return
     */
    @RequestMapping(value = "/addNewsIndex",method = RequestMethod.POST)
    @ResponseBody
    public String addNewsIndex(Integer news_id){
        Message message = new Message();
            if(newsService.addNewsIndex(news_id)){
                message.setCode("0");
                message.setMsg("success");
            }else{
                message.setCode("4");
                message.setMsg("failed");
            }
        return JSON.toJSONString(message);
    }

    /**
     * 欢迎页需要展示得内容
     * @return
     */
        @RequestMapping(value = "/welcome")
    @ResponseBody
    public   String welcome (){
            System.out.println("welcome访问了");
        Map<String,Object>map=new HashMap<>();
        int  newsCounts= newsService.queryNewsCount();//查询新闻得总数
        int  BusinessCount=usersService.queryBusinessCount();//查询商家得总数
        int  userCount= usersService.queryUserCount();//查询用户数
        int   programsCount=programsService.queryProgramsCount(); //查询项目数
        map.put("newsCounts",newsCounts);
        map.put("BusinessCount",BusinessCount);
        map.put("userCount",userCount);
        map.put("programsCount",programsCount);
        return JSON.toJSONString(map);
    }
}


