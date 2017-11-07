package com.solar.controllers.index;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hushaoge on 2016/11/10.
 */
@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private Mapper dozerMapper;

    @RequestMapping("/index")
    public String listUser(Model model){
        return "index";
    }

    @RequestMapping("/metadata")
    public String metadata(Model model){
        return "metadata";
    }


}
