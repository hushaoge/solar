package com.solar.controllers.analytics;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hushaoge
 * @date 2016/11/10
 */
@Deprecated
@Controller
@RequestMapping("/analytics")
public class AnalyticsController {
    private static Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

    @Autowired
    private Mapper dozerMapper;

    @RequestMapping("/hello")
    public String helloAnalytics(Model model){
        return "reporting/helloAnalytics";
    }

    @RequestMapping("/metadata")
    public String metadata(Model model){
        return "metadata";
    }

    @RequestMapping("/callback")
    public String callback(@RequestParam("code")String code, Model model){
        System.out.println(code);
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://www.googleapis.com/oauth2/v3/token");
            //httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("charset", "UTF-8");

            List<NameValuePair> params=new ArrayList<NameValuePair>();
            //建立一个NameValuePair数组，用于存储欲传送的参数
            params.add(new BasicNameValuePair("code", code));



            logger.debug("start to execute");
            client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "metadata";
    }

}
