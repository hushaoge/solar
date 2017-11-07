package com.solar.controllers.hello;

import com.solar.dubbo.service.provider.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hushaoge
 * @date 2016/11/8
 */
@Controller()
@RequestMapping("/hello")
public class HelloWorldController1 {
    static private Logger logger = LoggerFactory.getLogger(HelloWorldController1.class);
    @Autowired
    private DemoService  demoService;

    @RequestMapping("/dubbo")
    public String helloDubbo(Model model){
        String name = null;
        try {
            name = demoService.build("MVC AND DUBBO");
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("helloDubbo",name);
        model.addAttribute("title",name);
        return "/dubbo/hello";
    }
}
