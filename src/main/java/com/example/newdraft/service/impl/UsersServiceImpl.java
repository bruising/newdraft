package com.example.newdraft.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.newdraft.mapper.UsersMapper;
import com.example.newdraft.model.pojo.Users;
import com.example.newdraft.service.UsersService;
import com.example.newdraft.util.MD5;
import com.example.newdraft.util.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.service.impl
 * @Description:
 * @date 2020/1/14 星期二 19:26
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private RedisUtils redisUtils;
    @Override
    public Map<String, Object> queryUsersByPhoneAndPassword(Users users,String type) {
        Map<String,Object>map=new HashMap<>();
        map.put("msg","failed");
        map.put("code",4);
        Users user1 = usersMapper.selectUsersByPhoneAndPassword(users);
        if(user1!=null){
            map.put("code",0);
            map.put("msg","success");
            map.put("data",user1);
            String token = this.createToken(user1, type);
            map.put("token",token);
            this.saveToken(user1,token);
        }
        return map;
    }
    //存放token
    private void saveToken(Users user1, String token) {
        String tokenKey="User"+user1.getUserId();
        String tokenValue=null;
        if((tokenValue=(String) redisUtils.get(tokenKey))!=null){
            redisUtils.delete(tokenKey);
            redisUtils.delete(tokenValue);
        }
        redisUtils.set(tokenKey,token,300);
        redisUtils.set(token, JSON.toJSONString(user1));
    }
    //创建token
    private String createToken(Users user, String type) {
        StringBuilder sb=new StringBuilder();
        sb.append("token-");
        sb.append(type);
        sb.append(MD5.getMD5(user.getUserId().toString(),32));
        sb.append(LocalDateTime.now(ZoneOffset.of("+8")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        sb.append(UUID.randomUUID().toString().substring(0,6));
        return sb.toString();
    }



}
