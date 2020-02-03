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
import java.util.List;
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

    @Override
    public Map<String, Object> frontQueryUsersByPhoneAndPassword(Users users, String type) {
        Map<String,Object>map=new HashMap<>();
        map.put("msg","failed");
        map.put("code",4);
        Users user1 = usersMapper.frontSelectUsersByPhoneAndPassword(users);
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

    @Override
    public Map<String, Object> queryUsersList(Map<String, Object> map) {
        Map<String,Object>statusMap=new HashMap<String, Object>();
        statusMap.put("code",0);
        statusMap.put("msg","");
        statusMap.put("count",0);
        int page=Integer.parseInt(map.get("page").toString());
        int limit=Integer.parseInt(map.get("limit").toString());
        int index=(page-1)*limit;
        map.put("index",index);
        List<Users> UserLists = usersMapper.selectUsers(map);
        long num = usersMapper.selectUsersCount(map);
        if(num>0){
            statusMap.put("data",UserLists);
            statusMap.put("count",num);
        }
        return statusMap;
    }

    @Override
    public boolean del(Integer id) {
        if(usersMapper.delUsers(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean qiyong(Integer id) {
        if(usersMapper.qiyong(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserById(Users users) {
        if(usersMapper.updateUserById(users)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Users> queryAdministrator() {


        return usersMapper.queryAdministrator();
    }

    @Override
    public Map<String, Object> frontQueryUsersByPhone(String phone,String type) {
        Map<String,Object>map=new HashMap<>();
        map.put("msg","failed");
        map.put("code",4);
        Users user1 = usersMapper.selectUsersByPhone(phone);
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

    @Override
    public int queryUserCount() {
        return usersMapper.queryUserCount();
    }

    @Override
    public int queryBusinessCount() {
        return usersMapper.queryBusinessCount();
    }

    //存放token
    private void saveToken(Users user1, String token) {
        String tokenKey="User"+user1.getId();
        String tokenValue=null;
        if((tokenValue=(String) redisUtils.get(tokenKey))!=null){
            redisUtils.delete(tokenKey);
            redisUtils.delete(tokenValue);
        }
        redisUtils.set(tokenKey,token);
        redisUtils.set(token, JSON.toJSONString(user1));
    }
    //创建token
    private String createToken(Users user, String type) {
        StringBuilder sb=new StringBuilder();
        sb.append("token-");
        sb.append(type);
        sb.append(MD5.getMD5(user.getId().toString(),32));
        sb.append(LocalDateTime.now(ZoneOffset.of("+8")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        sb.append(UUID.randomUUID().toString().substring(0,6));
        return sb.toString();
    }



}
