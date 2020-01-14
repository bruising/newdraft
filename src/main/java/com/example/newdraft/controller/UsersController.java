package com.example.newdraft.controller;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.model.pojo.Users;
import com.example.newdraft.service.UsersService;
import com.example.newdraft.util.UserAgentUtil;
import cz.mallat.uasparser.UserAgentInfo;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
}
