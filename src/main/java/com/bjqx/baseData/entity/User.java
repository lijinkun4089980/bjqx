package com.bjqx.baseData.entity;

import com.bjqx.base.common.IdEntity;

import java.util.Date;

/**
 * 用户表
 */
public class User extends IdEntity {

    /**
     * 登录帐号
     **/
    private String loginName;
    /**
     * 登录密码
     **/
    private String password;
    /**
     * 姓名
     **/
    private String userName;
    /**
     * 性别（1男2女）
     **/
    private Integer sex;
    /**
     * 电话
     **/
    private String phone;
    /**
     * 手机
     **/
    private String mobile;
    /**
     * 电子邮箱
     **/
    private String email;
    /**
     * 联系地址
     **/
    private String address;
    /**
     * 是否删除（0否1是）
     **/
    private Integer isDelete;
    /**
     * 创建人
     **/
    private String creater;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 创建人ID
     **/
    private Integer createrId;
    /**
     * 更新人
     **/
    private String updater;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 更新人ID
     **/
    private Integer updaterId;

    /**
     * 无参的构造函数
     **/
    public User() {
        super();
    }

    /**
     * 有参构造函数
     **/
    public User(String loginName, String password, String userName, Integer sex, String phone, String mobile, String email, String address, Integer isDelete, String creater, Date createTime, Integer createrId, String updater, Date updateTime, Integer updaterId) {
        super();
        this.loginName = loginName;
        this.password = password;
        this.userName = userName;
        this.sex = sex;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.isDelete = isDelete;
        this.creater = creater;
        this.createTime = createTime;
        this.createrId = createrId;
        this.updater = updater;
        this.updateTime = updateTime;
        this.updaterId = updaterId;
    }


    /**
     * @return 登录帐号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName 登录帐号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return 性别（1男2女）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex 性别（1男2女）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return 联系地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 联系地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 是否删除（0否1是）
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete 是否删除（0否1是）
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return 创建人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * @param creater 创建人
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 创建人ID
     */
    public Integer getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId 创建人ID
     */
    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    /**
     * @return 更新人
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * @param updater 更新人
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 更新人ID
     */
    public Integer getUpdaterId() {
        return updaterId;
    }

    /**
     * @param updaterId 更新人ID
     */
    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

}
