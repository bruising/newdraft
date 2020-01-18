package com.example.newdraft.service;

import com.example.newdraft.model.pojo.Users;
import com.example.newdraft.util.PageBean;

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
     * 查询用户  通过昵称  手机号   邮箱模糊查询
     * @param users
     * @return
     */

    /**
     * 查询用户  通过昵称  手机号   邮箱模糊查询  并分页
     * @param currentPage  当前第几页
     * @param   rows  每页显示的条数
     * @return
     */
    PageBean<Users>queryUserByNameandPhoneandEmailandPage(Users  users,int currentPage,int  rows);
}
