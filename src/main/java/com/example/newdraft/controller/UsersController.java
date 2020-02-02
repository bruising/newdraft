package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.Users;
import com.example.newdraft.model.vo.Message;
import com.example.newdraft.service.UsersService;
import com.example.newdraft.util.DecentUtil;
import com.example.newdraft.util.QNYUtils;
import com.example.newdraft.util.RedisUtils;
import com.example.newdraft.util.UserAgentUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import cz.mallat.uasparser.UserAgentInfo;
import io.swagger.annotations.*;
import org.apache.catalina.User;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        HttpResponse response = decentUtil.response(phone, code);
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

    @RequestMapping("/queryUser")
    @ResponseBody
    public String queryUser( @RequestParam(value = "limit",required = false,defaultValue = "5") Integer limit,
                             @RequestParam(value = "pa  ge",required = false,defaultValue = "1") Integer page,
                             Users users){
        Map<String, Object> map = new HashMap<>();
        map.put("limit",limit);
        map.put("page",page);
        map.put("users",users);
        Map<String, Object> map1 = usersService.queryUsersList(map);
        System.out.println(JSON.toJSONString(map1));
        return JSON.toJSONString(map1);
    }


    /**
     * 停用   用户
     * @param
     * @param token
     * @return
     */
    @ApiOperation(value = "停用用户",notes = "成功返回状态码0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "token",value = "token验证",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success"),
            @ApiResponse(code = 5,message = "noToken")
    })
    @RequestMapping(value = "/delUsers",method = RequestMethod.POST)
    @ResponseBody
    public String delUsers(Integer id, String token){
        Message message = new Message();
        if(redisUtils.judgeToken(token)){
            if(usersService.del(id)){
                message.setCode("0");
                message.setMsg("success");
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
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
     * 修改用户状态为正常
     * @param
     * @param token
     * @return
     */
    @ApiOperation(value = "修改用户状态为正常",notes = "成功返回状态码0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "token",value = "token验证",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 4,message = "failed"),
            @ApiResponse(code = 0,message = "success"),
            @ApiResponse(code = 5,message = "noToken")
    })
    @RequestMapping(value = "/qiyong",method = RequestMethod.POST)
    @ResponseBody
    public String qiyong(Integer id, String token){
        Message message = new Message();
        if(redisUtils.judgeToken(token)){
            if(usersService.qiyong(id)){
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


    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    @ResponseBody
    public String updateUserById(Users users){
        Message message = new Message();
        if(usersService.updateUserById(users)){
            message.setCode("0");
            message.setMsg("success");
        }else{
            message.setCode("4");
            message.setMsg("failed");
        }
        return JSON.toJSONString(message);
    }

    @ApiOperation(value = "超级管理员查询所有子管理员")
    @ApiResponses({
            @ApiResponse(code = 1,message = "failed"),
            @ApiResponse(code = 0,message = "success")
    })
    @PostMapping(value = "/queryAdministrator")
    @ResponseBody
    public   String  queryAdministrator(){

        Map<String,Object>map=new HashMap<>();
        List<Users>administrator=usersService.queryAdministrator();
        if(administrator!=null){
            map.put("code",0);
            map.put("data",administrator);
        }else {
            map.put("code",1);
            map.put("data","");
        }
        System.out.println(administrator);
        return   JSON.toJSONString(map);
    }


}
