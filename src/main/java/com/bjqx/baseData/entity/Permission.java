package com.bjqx.baseData.entity;


import com.bjqx.base.common.IdEntity;

/**
 * 资源表
 */
public class Permission extends IdEntity {

    /**
     * 权限名
     **/
    private String permissionName;
    /**
     * 访问地址
     **/
    private String uri;
    /**
     * 父ID
     **/
    private Integer pId;
    /**
     * 备注
     **/
    private String remark;

    /**
     * 无参的构造函数
     **/
    public Permission() {
        super();
    }

    /**
     * 有参构造函数
     **/
    public Permission(String permissionName, String uri, Integer pId, String remark) {
        super();
        this.permissionName = permissionName;
        this.uri = uri;
        this.pId = pId;
        this.remark = remark;
    }


    /**
     * @return 权限名
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * @param permissionName 权限名
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * @return 访问地址
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri 访问地址
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return 父ID
     */
    public Integer getPId() {
        return pId;
    }

    /**
     * @param pId 父ID
     */
    public void setPId(Integer pId) {
        this.pId = pId;
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
