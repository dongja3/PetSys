//$Source: /petSys/petSys/src/java/com/drategy/pets/form/LoginForm.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/20 03:01:34 $

package com.drategy.pets.form;

/**
 * 系统用户登陆 Form
 * 
 * @author Jackie Dong
 * @author $Author: jason.jiang $
 * @$Revision: 1.3 $
 */

public class LoginForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private String action=null;
	private String userName=null;
	private String password=null;
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
