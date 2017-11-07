package com.solar.controllers.user;

import com.solar.entities.dtos.UserInfoDTO;
import com.solar.entities.mybatis.UserInfo;
import com.solar.services.user.UserInfoService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * Created by hushaoge on 2016/11/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private Mapper dozerMapper;

    @RequestMapping("/list")
    public String listUser(Model model){
        List<UserInfo> list =userInfoService.selectAll();
        model.addAttribute("list",list);
        return "/user/list";
    }

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public String addUser(UserInfoDTO userInfoDTO, Model model){
        userInfoDTO.setId(UUID.randomUUID().toString());
        userInfoService.insert(dozerMapper.map(userInfoDTO,UserInfo.class));
        List<UserInfo> list =userInfoService.selectAll();
        model.addAttribute("list",list);
        return "/user/list";
    }
}
