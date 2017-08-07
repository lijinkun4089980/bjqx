package com.bjqx.baseData.entity;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 部门表
 */
public class Department {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6987986279287377988L;
	/** 机构名称 **/ 
	private String depName;
	/** 机构类型 **/ 
	private String depType;
	/** 值班电话 **/ 
	private String dutyPhone;
	/** 部门地址 **/ 
	private String depAddress;
	/** 状态 1-启用，0-停用 **/ 
	private String status;
	/** 备注 **/ 
	private String remark;
	/** 父ID **/ 
	private String pid;
	/** 排序号 **/
	private String order;
	/** 创建人 **/ 
	private String creater;
	/** 创建时间 **/ 
	private Date createTime;
	/** 修改人 **/ 
	private String updater;
	/** 修改时间 **/ 
	private Date updateTime;
	/** 修改人IP **/ 
	private String updateIp;
	/** 机构类型 **/ 
	private Integer departmentFlag;
	private Integer isMain;
	
	/**人员统计用**/
	/**有效日报数**/
	private Integer validNum;
	/**无效日报数**/
	private Integer notValidNum;
	/**有效日报比例**/
	private Double validRate;
	private Double averageValid;
	/** 外出出差数**/
	private Integer outApplyNum;
	/**外出出差天数平均值**/
	private Double averageOutApply;
	/** 无参的构造函数 **/
	public Department(){
		super();
	}
	
	/** 有参构造函数 **/
	public Department(String depName,String depType,String dutyPhone,String depAddress,String status,String remark,String pid,String order,String creater,Date createTime,String updater,Date updateTime,String updateIp,Integer departmentFlag){
		super();
		this.depName = depName;
		this.depType = depType;
		this.dutyPhone = dutyPhone;
		this.depAddress = depAddress;
		this.status = status;
		this.remark = remark;
		this.pid = pid;
		this.order = order;
		this.creater = creater;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.updateIp = updateIp;
		this.departmentFlag = departmentFlag;
	}
 	
		
	/**
	 * @return 机构名称
	 */
	@Column(name="c_dep_name")
	public String getDepName() {
		return depName;
	}
	/**
	 * @param depName 机构名称
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}
		
	/**
	 * @return 机构类型
	 */
	@Column(name="c_dep_type")
	public String getDepType() {
		return depType;
	}
	/**
	 * @param depType 机构类型
	 */
	public void setDepType(String depType) {
		this.depType = depType;
	}
		
	/**
	 * @return 值班电话
	 */
	@Column(name="c_duty_phone")
	public String getDutyPhone() {
		return dutyPhone;
	}
	/**
	 * @param dutyPhone 值班电话
	 */
	public void setDutyPhone(String dutyPhone) {
		this.dutyPhone = dutyPhone;
	}
		
	/**
	 * @return 部门地址
	 */
	@Column(name="c_dep_address")
	public String getDepAddress() {
		return depAddress;
	}
	/**
	 * @param depAddress 部门地址
	 */
	public void setDepAddress(String depAddress) {
		this.depAddress = depAddress;
	}
		
	/**
	 * @return 状态 1-启用，0-停用
	 */
	@Column(name="c_status")
	public String getStatus() {
		return status;
	}
	/**
	 * @param status 状态 1-启用，0-停用
	 */
	public void setStatus(String status) {
		this.status = status;
	}
		
	/**
	 * @return 备注
	 */
	@Column(name="c_remark")
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
		
	/**
	 * @return 父ID
	 */
	@Column(name="c_pid")
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid 父ID
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
		
	/**
	 * @return 排序号
	 */
	@Column(name="c_order")
	public String getOrder() {
		return order;
	}
	/**
	 * @param order 排序号
	 */
	public void setOrder(String order) {
		this.order = order;
	}
		
	/**
	 * @return 创建人
	 */
	@Column(name="c_creater")
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
	@Column(name="c_create_time")
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
	 * @return 修改人
	 */
	@Column(name="c_updater")
	public String getUpdater() {
		return updater;
	}
	/**
	 * @param updater 修改人
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}
		
	/**
	 * @return 修改时间
	 */
	@Column(name="c_update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
		
	/**
	 * @return 修改人IP
	 */
	@Column(name="c_update_ip")
	public String getUpdateIp() {
		return updateIp;
	}
	/**
	 * @param updateIp 修改人IP
	 */
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
		
	
	/**
	 * @return 机构类型
	 */
	@Column(name="c_department_flag")
	public Integer getDepartmentFlag() {
		return departmentFlag;
	}
	/**
	 * @param departmentFlag 机构类型
	 */
	public void setDepartmentFlag(Integer departmentFlag) {
		this.departmentFlag = departmentFlag;
	}

	@Transient
	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

	@Transient
	public Integer getValidNum() {
		return validNum;
	}

	public void setValidNum(Integer validNum) {
		this.validNum = validNum;
	}

	@Transient
	public Integer getNotValidNum() {
		return notValidNum;
	}

	public void setNotValidNum(Integer notValidNum) {
		this.notValidNum = notValidNum;
	}

	public void setValidRate(Double validRate) {
		this.validRate = validRate;
	}

	@Transient
	public Double getAverageValid() {
		return averageValid;
	}

	public void setAverageValid(Double averageValid) {
		this.averageValid = averageValid;
	}

	@Transient
	public Integer getOutApplyNum() {
		return outApplyNum;
	}

	public void setOutApplyNum(Integer outApplyNum) {
		this.outApplyNum = outApplyNum;
	}

	
	@Transient
	public Double getAverageOutApply() {
		return averageOutApply;
	}

	public void setAverageOutApply(Double averageOutApply) {
		this.averageOutApply = averageOutApply;
	}
 	
}
