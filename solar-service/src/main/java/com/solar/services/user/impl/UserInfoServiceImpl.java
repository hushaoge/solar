package com.solar.services.user.impl;

import com.solar.dao.UserInfoDAO;
import com.solar.entities.mybatis.UserInfo;
import com.solar.services.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hushaoge
 * @date 2017/11/7
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public List<UserInfo> selectAll() {
        return userInfoDAO.selectAll();
    }

    @Override
    public boolean insert(UserInfo userInfo) {
        return userInfoDAO.insert(userInfo) > 0;
    }
}
