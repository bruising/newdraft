package com.example.newdraft;

import com.example.newdraft.mapper.UsersMapper;
import com.example.newdraft.model.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewdraftApplicationTests {
    @Resource
    private UsersMapper usersMapper;

    @Test
    public void contextLoads() {
    }
    @Test
    public void login(){
        Users users = new Users();
        users.setUserPhone("19919990911");
        users.setUserPassword("123456");
        Users users1 = usersMapper.selectUsersByPhoneAndPassword(users);
        System.out.println(users1);
    }


}
