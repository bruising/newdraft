package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.Users;
import com.example.newdraft.service.UsersService;
import com.example.newdraft.util.DecentUtil;
import com.example.newdraft.util.RedisUtils;
import com.example.newdraft.util.UserAgentUtil;
import cz.mallat.uasparser.UserAgentInfo;
import io.swagger.annotations.*;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
@Controller
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

}
