package com.bjqx.baseData.entity;


import com.bjqx.base.common.IdEntity;

/**
 * 角色资源中间表
 */
public class RolePermission extends IdEntity {

    /**
     * 角色ID
     **/
    private Integer idRole;
    /**
     * 权限ID
     **/
    private Integer idPermission;

    /**
     * 无参的构造函数
     **/
    public RolePermission() {
        super();
    }

    /**
     * 有参构造函数
     **/
    public RolePermission(Integer idRole, Integer idPermission) {
        super();
        this.idRole = idRole;
        this.idPermission = idPermission;
    }


    /**
     * @return 角色ID
     */
    public Integer getIdRole() {
        return idRole;
    }

    /**
     * @param idRole 角色ID
     */
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    /**
     * @return 权限ID
     */
    public Integer getIdPermission() {
        return idPermission;
    }

    /**
     * @param idPermission 权限ID
     */
    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

}
