package com.example.newdraft.util;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: demo_redis_token
 * @Package com.example.demo_redis_token.util
 * @Description:
 * @date 2020/1/5 星期日 14:11
 */
@Component
public class UserAgentUtil {
    public static UASparser uaSparser;
    static {
        try {
            uaSparser=new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
