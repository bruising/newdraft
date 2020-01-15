package com.example.newdraft.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.util
 * @Description: 短信工具类
 * @date 2020/1/15 星期三 10:17
 */
@Component
public class DecentUtil {
    @Resource
    private DecentConfig config;

    public Map<String, String> getHeaders(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization","APPCODE " +config.getAppcode());
        return map;
    }

    public  Map<String, String> getQuerys(String phone,String code){
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",phone);
        map.put("param","code:"+code);
        map.put("tpl_id", "TP1711063");
        return map;
    }

    /**
     * 发送短信
     * @param phone
     * @param code
     * @return
     * @throws Exception
     */
    public HttpResponse response(String phone,String code)throws Exception{
        return HttpUtils.doPost(config.getHost(),config.getPath(),config.getMethod(),getHeaders(),getQuerys(phone,code),new HashMap<>());
    }

    /**
     * 判断发送的状态
     * @param response
     * @return
     */
    public boolean status(HttpResponse response)throws Exception{
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();//获取响应的状态码
        String reasonPhrase = statusLine.getReasonPhrase();
        if("OK".equalsIgnoreCase(reasonPhrase)&&200==statusCode) {
            String string = EntityUtils.toString(response.getEntity());
            Map<String,String>map=(Map<String,String>)JSON.parse(string);
            String return_code = map.get("return_code");
            if(return_code.equals("00000")){
                return true;
            }
        }
        return false;
    }
}
