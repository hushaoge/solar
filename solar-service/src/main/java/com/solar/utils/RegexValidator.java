package com.solar.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验工具类
 * @author hushaoge
 * @date 2017/10/24
 */
public class RegexValidator {

    /**手机号正则表达式*/
    public static final String MOBILE_NUMBER = "^1[0-9]{10}$";

    /**中文+字母+常用符号+数字*/
    public static final String EFFECTUAL_CHARACTER = "[a-zA-Z0-9~!@#$￥%^&*()-_+\\u0391-\\uFFE5]+$";

    /**
     * 判断字符串是否符合正则表达式
     * @param str
     * @param regex
     * @return
     */
    public static boolean find(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
