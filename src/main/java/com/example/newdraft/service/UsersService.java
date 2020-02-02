package com.example.newdraft.service;

import com.example.newdraft.model.pojo.Users;

import java.util.List;
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
     * 前台登录
     * @param users
     * @return
     */
    Map<String,Object> frontQueryUsersByPhoneAndPassword(Users users,String type);

    /**
     * 后台用户和商家列表
     * @param map
     * @return
     */
    Map<String,Object>queryUsersList(Map<String,Object>map);

    boolean del(Integer id);

    boolean qiyong(Integer id);

    boolean updateUserById(Users users);

    /**
     * 查询子管理员
     * @return
     */
    List<Users> queryAdministrator();

    /**
     * 前台短信登录
     * @param phone
     * @return
     */
    Map<String,Object> frontQueryUsersByPhone(String phone,String type);
}
