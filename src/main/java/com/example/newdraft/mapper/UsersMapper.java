package com.example.newdraft.mapper;

import com.example.newdraft.model.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<Users> selectUsers(@Param("map") Map<String,Object>map);

    Long selectUsersCount(@Param("map") Map<String,Object>map);

    int delUsers(@Param("id") Integer id);

    int qiyong(Integer id);

    int updateUserById(Users users);
}
