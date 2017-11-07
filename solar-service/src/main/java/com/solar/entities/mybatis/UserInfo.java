package com.solar.entities.mybatis;

import java.util.Date;

public class UserInfo {
    /**
     * id
     * @mbggenerated 2016-11-10
     */
    private String id;

    /**
     * user_name
     * @mbggenerated 2016-11-10
     */
    private String userName;

    /**
     * passwd
     * @mbggenerated 2016-11-10
     */
    private String passwd;

    /**
     * nick_name
     * @mbggenerated 2016-11-10
     */
    private String nickName;

    /**
     * age
     * @mbggenerated 2016-11-10
     */
    private Integer age;

    /**
     * sex
     * @mbggenerated 2016-11-10
     */
    private String sex;

    /**
     * role_id
     * @mbggenerated 2016-11-10
     */
    private String roleId;

    /**
     * create_time
     * @mbggenerated 2016-11-10
     */
    private Date createTime;

    /**
     * last_time
     * @mbggenerated 2016-11-10
     */
    private Date lastTime;

    /**
     * @return the value of id
     * @mbggenerated 2016-11-10
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the value for id
     * @mbggenerated 2016-11-10
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return the value of user_name
     * @mbggenerated 2016-11-10
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the value for user_name
     * @mbggenerated 2016-11-10
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return the value of passwd
     * @mbggenerated 2016-11-10
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the value for passwd
     * @mbggenerated 2016-11-10
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /**
     * @return the value of nick_name
     * @mbggenerated 2016-11-10
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the value for nick_name
     * @mbggenerated 2016-11-10
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @return the value of age
     * @mbggenerated 2016-11-10
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the value for age
     * @mbggenerated 2016-11-10
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the value of sex
     * @mbggenerated 2016-11-10
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the value for sex
     * @mbggenerated 2016-11-10
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * @return the value of role_id
     * @mbggenerated 2016-11-10
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the value for role_id
     * @mbggenerated 2016-11-10
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * @return the value of create_time
     * @mbggenerated 2016-11-10
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the value for create_time
     * @mbggenerated 2016-11-10
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the value of last_time
     * @mbggenerated 2016-11-10
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * @param lastTime the value for last_time
     * @mbggenerated 2016-11-10
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}