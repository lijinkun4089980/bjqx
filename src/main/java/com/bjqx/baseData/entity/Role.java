package com.bjqx.baseData.entity;

import com.bjqx.base.common.IdEntity;

/**
 * 角色表
 */
public class Role extends IdEntity {

    /**
     * 角色名称
     **/
    private String roleName;
    /**
     * 状态（0否1是）
     **/
    private Integer status;
    /**
     * 备注
     **/
    private String remark;

    /**
     * 无参的构造函数
     **/
    public Role() {
        super();
    }

    /**
     * 有参构造函数
     **/
    public Role(String roleName, Integer status, String remark) {
        super();
        this.roleName = roleName;
        this.status = status;
        this.remark = remark;
    }


    /**
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return 状态（0否1是）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 状态（0否1是）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
