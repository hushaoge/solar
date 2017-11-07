package com.solar.dubbo.service.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hushaoge on 2016/11/4.
 */
public class Provider {
    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
        context.start();

        System.out.println("服务提供者启动.");

        System.in.read(); // 按任意键退出
    }
}
