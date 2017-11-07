package com.solar.dubbo.service.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.solar.dubbo.service.provider.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author hushaoge
 * @date 2016/11/4
 */

@Component
@Service
public class DemoServiceImpl implements DemoService {
    static private Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
    @Override
    public String build(String name) throws Exception {
        logger.info("传入的名称是：{}",name);
        return "你好 名称是 ------------- >>>> " + name;
    }
}
