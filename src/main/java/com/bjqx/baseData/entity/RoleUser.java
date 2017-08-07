package com.bjqx.baseData.entity;

import com.bjqx.base.common.IdEntity;

/**
 * 角色用户中间表
 */
public class RoleUser extends IdEntity {

    /**
     * 角色ID
     **/
    private Integer roleId;
    /**
     * 用户ID
     **/
    private Integer userId;

    /**
     * 无参的构造函数
     **/
    public RoleUser() {
        super();
    }

    /**
     * 有参构造函数
     **/
    public RoleUser(Integer roleId, Integer userId) {
        super();
        this.roleId = roleId;
        this.userId = userId;
    }


    /**
     * @return 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
