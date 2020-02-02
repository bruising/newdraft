package com.example.newdraft.util;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.util
 * @Description: 拦截器
 * @date 2020/2/2 星期日 10:18
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Resource
    private RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            // ajax跨域
            response.setHeader("Access-Control-Allow-Credentials", "true");
            //自定义header头部是否允许
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            return true;
        } else{
            // ajax跨域
            response.setHeader("Access-Control-Allow-Credentials", "true");
            //自定义header头部是否允许
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            Map<String,Object>map=new HashMap<>();
            String token = request.getHeader("token");
            if(token==null||token.equals("")||token.equals("null")){
                map.put("isToken",4);
                returnJson(response, JSON.toJSONString(map));
                System.out.println("不放行,没有token");
                return false;

            }
            System.out.println("放行,有token");
            return true;
        }





    }


    public void returnJson(HttpServletResponse response,String json){
        PrintWriter writer = null;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
