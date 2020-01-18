package com.example.newdraft.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.util
 * @Description: 短信服务配置类
 * @date 2020/1/15 星期三 10:08
 */
@Configuration
@ConfigurationProperties(prefix = "decent")
@Data
public class DecentConfig {
   private String host = "http://dingxin.market.alicloudapi.com";
   private String path = "/dx/sendSms";
   private String method = "POST";
   private String appcode;

   public String getHost() {
      return host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public String getMethod() {
      return method;
   }

   public void setMethod(String method) {
      this.method = method;
   }

   public String getAppcode() {
      return appcode;
   }

   public void setAppcode(String appcode) {
      this.appcode = appcode;
   }
}
