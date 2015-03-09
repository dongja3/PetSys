package com.drategy.pets.form;

import com.drategy.pets.context.Constant;
import com.drategy.pets.domain.Area;

public class UserForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String method = null;

	private String id = null;

	private String userName = null;

	private String password = null;

	private String sysRole = null;

	private String areaId = null;

	private String note = null;
	
	private String employeeId = null;
	
	private String[] areaNameList ;
	private String[] areaIdList;
	
	
	public UserForm(){
	    areaNameList = new String[Constant.getAllArea().size()];
		areaIdList= new String[Constant.getAllArea().size()];
		
		for(int i=0;i<Constant.getAllArea().size();i++){
			Area tArea = (Area)Constant.getAllArea().get(i);
			areaNameList[i]= tArea.getName();
			areaIdList[i]= tArea.getId();
		}
		
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String[] getAreaIdList() {
		return areaIdList;
	}

	public void setAreaIdList(String[] areaIdList) {
		this.areaIdList = areaIdList;
	}

	public String[] getAreaNameList() {
		return areaNameList;
	}

	public void setAreaNameList(String[] areaNameList) {
		this.areaNameList = areaNameList;
	}


	public String getAreaId() {
		return areaId;
	}


	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}


	public String getSysRole() {
		return sysRole;
	}


	public void setSysRole(String sysRole) {
		this.sysRole = sysRole;
	}

	

}
