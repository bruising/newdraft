package com.example.newdraft;

import com.example.newdraft.mapper.NewsMapper;
import com.example.newdraft.mapper.UsersMapper;
import com.example.newdraft.model.pojo.Users;
import com.example.newdraft.model.vo.NewsList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewdraftApplicationTests {
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private NewsMapper newsMapper;

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

    @Test
    public void test01(){
        Map<String,Object>map=new HashMap<>();
        map.put("index",0);
        map.put("limit",5);
        map.put("news_title","z");
        map.put("userName","商家1");
        map.put("news_status",1);
        List<NewsList> newsLists = newsMapper.selectAllNewsList(map);
        System.out.println(newsLists.size());
    }
    @Test
    public void test02(){
        Map<String,Object>map=new HashMap<>();
        map.put("index",0);
        map.put("limit",5);
        Users users = new Users();
        users.setUserName("商家1");
        users.setUserPhone("18600730811");
        map.put("users",users);
        List<Users> users1 = usersMapper.selectUsers(map);
        System.out.println(users1.size());
    }
}
