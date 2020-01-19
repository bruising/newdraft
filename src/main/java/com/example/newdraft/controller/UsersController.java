package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.Users;
import com.example.newdraft.model.vo.UserMessage;
import com.example.newdraft.service.UsersService;
import com.example.newdraft.util.*;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import cz.mallat.uasparser.UserAgentInfo;
import io.swagger.annotations.*;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.controller
 * @Description:
 * @date 2020/1/14 星期二 19:33
 */
@RestController
@Api(tags="这是吴成卓写的类")
public class UsersController {
    @Resource
    private UsersService usersService;
    @Resource
    private UserAgentUtil userAgentUtil;
    @Resource
    private DecentUtil decentUtil;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private QNYUtils qnyUtils;
    @ApiOperation(value = "输入电话密码验证登录信息",notes = "正确返回用户信息，错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone",value = "手机号",dataType = "String",example = "19919990911"),
            @ApiImplicitParam(name="userPassword",value = "密码",type = "String",example = "123456")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(Users users, HttpServletRequest request)throws Exception{
        String header = request.getHeader("User-Agent");
        System.err.println(users);
        UserAgentInfo parse = userAgentUtil.uaSparser.parse(header);
        String type = parse.getDeviceType();
        Map<String, Object> map = usersService.queryUsersByPhoneAndPassword(users, type);
        return JSON.toJSONString(map);
    }

    /**
     * 给手机发送短信的方法    同时redis保存60秒
     * @param phone
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "输入电话发送验证码",notes = "收到验证码返回成功状态，错误返回错误码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号",dataType = "String",example = "19919990911"),
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    @ResponseBody
    public String message(String phone)throws Exception{
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        HttpResponse response = decentUtil.response( phone, code);
        Map<Object, Object> map = new HashMap<>();
        map.put("code",4);
        map.put("msg","failed");
        if(decentUtil.status(response)){
           redisUtils.set(phone,code,60);
            map.put("code",0);
            map.put("msg","success");
           return  JSON.toJSONString(map);
       }
        return  JSON.toJSONString(map);
    }

    @ApiOperation(value = "头像上传",notes = "token验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "头像",dataType = "MultipartFile"),
            @ApiImplicitParam(name = "token",value = "请求所携带token",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @RequestMapping(value = "/usersUpload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,String token) {
       if(redisUtils.judgeToken(token)){
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
        return  JSON.toJSONString(new HashMap<String,Object>());
    }


    @ApiOperation(value = "根据昵称、手机号、邮箱模糊查找",notes = "查询成功返回用户数据，失败返回字符串")
    @ApiImplicitParams({

           @ApiImplicitParam(name ="username",value = "昵称",dataType ="Users",example = "用户"),
            @ApiImplicitParam(name ="userPhone",value = "手机号",dataType ="Users",example = "177"),
            @ApiImplicitParam(name ="userEmail",value = "邮箱",dataType ="Users",example = "741")

   })
    @ApiResponses({
            @ApiResponse(code = 101,message = "没有找到符合条件的信息"),
            @ApiResponse(code = 102,message = "找到符合条件的信息")
    })
    @RequestMapping(value = "/queryUserByNameAndPhoneAndEmail",method = RequestMethod.POST)
    public   UserMessage  queryUserByNameAndPhoneAndEmail(
            @Valid Users  users,
            @RequestParam(value = "currentPage",required = false,defaultValue = "1")int currentPage,
            @RequestParam(value = "rows",required = false,defaultValue = "1")int rows, Model  model){
        System.out.println(users);
           PageBean<Users>pb=usersService.queryUserByNameandPhoneandEmailandPage(users,currentPage,rows);
        System.out.println(pb);
           model.addAttribute("pb",pb);
           UserMessage  um=new UserMessage();
           if(pb.getList().size()<0){//没有找到符合条件的数据
               um.setCode("101");
               um.setMsg("没有找到符合条件的用户");
           }else {
               um.setCode("102");
               um.setMsg("为您找到"+pb.getList().size()+"条数据");
               um.setData(JSON.toJSONString(pb.getList()));
           }
        return um;
    }
}
