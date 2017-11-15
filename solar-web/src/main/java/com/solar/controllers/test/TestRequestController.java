package com.solar.controllers.test;

import com.solar.entities.pojo.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hushaoge on 2017/11/14.
 */
@Controller()
@RequestMapping("/hello")
public class TestRequestController {
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    public void test(@RequestBody TestVO vo){
        if(vo == null){

        }
        vo.getCode();
    }
}
