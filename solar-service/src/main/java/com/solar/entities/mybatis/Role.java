package com.solar.entities.mybatis;

import java.util.Date;

public class Role {
    /**
     * id
     * @mbggenerated 2016-11-10
     */
    private String id;

    /**
     * role_name
     * @mbggenerated 2016-11-10
     */
    private String roleName;

    /**
     * auth_id
     * @mbggenerated 2016-11-10
     */
    private String authId;

    /**
     * order
     * @mbggenerated 2016-11-10
     */
    private Integer order;

    /**
     * create_id
     * @mbggenerated 2016-11-10
     */
    private String createId;

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
     * @return the value of role_name
     * @mbggenerated 2016-11-10
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the value for role_name
     * @mbggenerated 2016-11-10
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * @return the value of auth_id
     * @mbggenerated 2016-11-10
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * @param authId the value for auth_id
     * @mbggenerated 2016-11-10
     */
    public void setAuthId(String authId) {
        this.authId = authId == null ? null : authId.trim();
    }

    /**
     * @return the value of order
     * @mbggenerated 2016-11-10
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order the value for order
     * @mbggenerated 2016-11-10
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return the value of create_id
     * @mbggenerated 2016-11-10
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * @param createId the value for create_id
     * @mbggenerated 2016-11-10
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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