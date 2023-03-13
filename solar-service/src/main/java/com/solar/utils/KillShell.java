package com.solar.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hushaoge
 * @date 2023/3/13 9:59
 * 秒杀脚本
 */
public class KillShell {

    public static void main(String[] args) {
        try {
            baidu();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     *
     * 内核下载地址  http://chromedriver.storage.googleapis.com/index.html
     * @throws Exception
     */
    public static void baidu() throws Exception {

        //浏览器驱动路径
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");

        //设置秒杀时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSSSSS");
        Date date = sdf.parse("2023-03-13 10:10:00 000000000");

        //1、打开浏览器
        ChromeDriver browser = new ChromeDriver();
        Actions actions = new Actions(browser);
        //2、输入网址
        browser.get("https://www.baidu.com");
        Thread.sleep(3000);

        //3、点击登录
        browser.findElement(By.linkText("登录")).click();

        Thread.sleep(2000);

        //4、账号登录
        browser.findElement(By.id("TANGRAM__PSP_11__userName")).click();
        Thread.sleep(20000);

        //5、进入贴吧页面
        browser.get("https://tieba.baidu.com/index.html");
        Thread.sleep(3000);

        //6、点击选择第一个按钮
        browser.findElement(By.xpath("//*[@id=\"J_Order_s_2207407355826_1\"]/div[1]/div/div/label")).click();

        Thread.sleep(2000);
        while (true){
            //当前时间
            Date now = new Date();
            System.out.println(now);
            if(now.after(date)){
                if(browser.findElement(By.linkText("结 算")).isEnabled()){
                    browser.findElement(By.linkText("结 算")).click();
                    System.out.println("结算成功");
                    break;
                }

            }
        }

        Thread.sleep(5000);
    }


    public void taoBao() throws Exception {

        //浏览器驱动路径
        System.setProperty("webdriver.chrome.driver","D:\\JDK\\chromedriver.exe");

        //设置秒杀时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSSSSS");
        Date date = sdf.parse("2022-04-14 14:07:00 000000000");

        //1、打开浏览器
        ChromeDriver browser = new ChromeDriver();
        Actions actions = new Actions(browser);
        //2、输入网址
        browser.get("https://www.taobao.com");
        Thread.sleep(3000);

        //3、点击登录
        browser.findElement(By.linkText("亲，请登录")).click();

        Thread.sleep(2000);

        //4、扫码登录
        browser.findElement(By.className("icon-qrcode")).click();
        Thread.sleep(4000);

        //5、进入购物车页面
        browser.get("https://cart.taobao.com/cart.htm");
        Thread.sleep(3000);

        //6、点击选择第一个按钮
        browser.findElement(By.xpath("//*[@id=\"J_Order_s_2207407355826_1\"]/div[1]/div/div/label")).click();

        Thread.sleep(2000);
        while (true){
            //当前时间
            Date now = new Date();
            System.out.println(now);
            if(now.after(date)){
                if(browser.findElement(By.linkText("结 算")).isEnabled()){
                    browser.findElement(By.linkText("结 算")).click();
                    System.out.println("结算成功");
                    break;
                }

            }
        }

        Thread.sleep(5000);
    }
}
