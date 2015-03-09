//$Source: /petSys/petSys/src/java/com/drategy/pets/form/EmployeeForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/28 08:48:58 $

package com.drategy.pets.form;

/* 
 *Employee Form
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.7 $
 */

public class EmployeeForm extends BaseForm {

	private String id = null;

	private String name = null;

	private String sex = null;

	private String age = null;

	private String postCode = null;

	private String residentID = null;
	
	private String method = null;
	

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getResidentID() {
		return residentID;
	}

	public void setResidentID(String residentID) {
		this.residentID = residentID;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
