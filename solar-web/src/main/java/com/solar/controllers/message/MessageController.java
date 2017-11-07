package com.solar.controllers.message;

import com.solar.entities.dtos.message.KFMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hushaoge on 2016/11/14.
 */
@Controller
@RequestMapping("/msg")
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping("kfsend")
    public String  kfsend(KFMessageDTO kfMessageDTO){
        logger.info(kfMessageDTO.toString());
        return "SUCCESS";
    }
}
