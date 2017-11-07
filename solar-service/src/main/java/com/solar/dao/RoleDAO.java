package com.solar.dao;

import com.solar.entities.mybatis.Role;

import java.util.List;

public interface RoleDAO {
    /**
     * database table tb_role
     * @mbggenerated 2016-11-10
     */
    int deleteByPrimaryKey(String id);

    /**
     * database table tb_role
     * @mbggenerated 2016-11-10
     */
    int insert(Role record);

    /**
     * database table tb_role
     * @mbggenerated 2016-11-10
     */
    Role selectByPrimaryKey(String id);

    /**
     * database table tb_role
     * @mbggenerated 2016-11-10
     */
    List<Role> selectAll();

    /**
     * database table tb_role
     * @mbggenerated 2016-11-10
     */
    int updateByPrimaryKey(Role record);
}