package com.solar.controllers.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hushaoge on 2017/11/8.
 */
@Controller
@RequestMapping
public class LoginController {

    @RequestMapping(value = "/tologin",method = RequestMethod.GET)
    public String toLogin(){
        return "/login";
    }

}
