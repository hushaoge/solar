package com.solar.services.user;

import com.solar.entities.mybatis.UserInfo;

import java.util.List;

/**
 *
 * @author hushaoge
 * @date 2017/11/7
 */
public interface UserInfoService {

    List<UserInfo> selectAll();

    boolean insert(UserInfo userInfo);
}
