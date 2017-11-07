package com.solar.utils;

import java.util.UUID;

/**
 * 生成uuid工具类
 * @author hushaoge
 * 
 */
public class UUIDUtils {

    /**
     * 获取32位UUID,无'-'
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.toUpperCase().replace("-", "");
    }

}