package com.example.newdraft.mapper;

import com.example.newdraft.model.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.mapper
 * @Description:
 * @date 2020/1/14 星期二 18:57
 */
@Mapper
public interface UsersMapper {
    /**
     * 登录
     * @param users
     * @return
     */
    Users selectUsersByPhoneAndPassword(Users users);

    /**
     * 查询用户  通过昵称  手机号   邮箱模糊查询
     * @return
     */
    List<Users>queryUserByNameandPhoneandEmail(@Param("users1") Users  users1,@Param("start") int  currentPage,@Param("rows") int rows);

    /**
     * 查询符合条件的总记录数
     * @return
     */
    int   queryUserByNameandPhoneandEmailCount(Users  users);
}
