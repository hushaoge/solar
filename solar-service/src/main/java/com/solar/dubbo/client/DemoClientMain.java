package com.solar.dubbo.client;

import com.solar.dubbo.service.provider.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hushaoge on 2016/11/4.
 */
public class DemoClientMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "dubbo/dubbo-client.xml" });
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // get
        // services
        // invocation
        // proxy
        String hello = "";
        try {
            hello = demoService.build("Client发起的调用");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // do invoke!
        System.out.println(Thread.currentThread().getName() + " " + hello);
    }
}
