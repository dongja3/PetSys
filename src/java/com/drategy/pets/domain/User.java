//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/User.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $

package com.drategy.pets.domain;

import com.drategy.pets.domain.Employee;

/**
 * 系统的user
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.3 $
 */
public class User {
	/**
	 * id 登陆帐号
	 */
	private String id;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 对应的员工
	 */
	private Employee employee;

	/**
	 * 对应的权限Set
	 * 
	 */
	private java.util.Set authorizationSet;

	/**
	 * 系统角色
	 */
	private String sysRole;

	/** User 所属区域 */
	private Area area;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public void setSysRole(String sysRole) {
		this.sysRole = sysRole;
	}

	public String getSysRole() {
		return this.sysRole;
	}

	public java.util.Set getAuthorizationSet() {
		return authorizationSet;
	}

	public void setAuthorizationSet(java.util.Set authorizationSet) {
		this.authorizationSet = authorizationSet;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getId() {
		return this.id;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}