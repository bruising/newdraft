package com.example.newdraft.service;

import com.example.newdraft.model.pojo.Users;

import java.util.Map;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.service.impl
 * @Description:
 * @date 2020/1/14 星期二 19:24
 */
public interface UsersService {
    /**
     * 登录
     * @param users
     * @return
     */
    Map<String,Object> queryUsersByPhoneAndPassword(Users users,String type);


    /**
     * 后台用户和商家列表
     * @param map
     * @return
     */
    Map<String,Object>queryUsersList(Map<String,Object>map);

    boolean del(Integer id);

    boolean qiyong(Integer id);

    boolean updateUserById(Users users);
}
