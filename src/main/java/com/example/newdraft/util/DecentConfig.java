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
}
