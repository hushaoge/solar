package com.solar.dao;

import com.solar.core.mybatis.Pager;
import com.solar.entities.mybatis.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hushaoge
 * @date 2017/11/07
 */
public interface UserInfoDAO {
    /**
     * database table tb_user
     * @mbggenerated 2016-11-10
     */
    int deleteByPrimaryKey(String id);

    /**
     * database table tb_user
     * @mbggenerated 2016-11-10
     */
    int insert(UserInfo record);

    /**
     * database table tb_user
     * @mbggenerated 2016-11-10
     */
    UserInfo selectByPrimaryKey(String id);

    /**
     * database table tb_user
     * @mbggenerated 2016-11-10
     */
    List<UserInfo> selectAll();

    /**
     * database table tb_user
     * @mbggenerated 2016-11-10
     */
    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> queryAll(@Param("pager") Pager<UserInfo> pager);
}