//$Source: /petSys/petSys/src/java/com/drategy/pets/bom/Person.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/01/06 02:33:45 $


package com.drategy.pets.bom;


/**
* 系统的person
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.3 $
*/
public class Person {
	/**
     * 名字
     */
    private String name;
    /**
     * 性别�
     */
    private String sex;
    /**
     * 年龄��
     */
    private String age;
    
    /**
     * 身份证
     */
    private String residentID; 
    /**
     * �邮政编码����
     */   
    private String postCode;
    
    /**
     * ַ地址����
     */
    private java.util.Set addrSet;
    
    /**
     *  电话
     */
    private Phone phone ;
    
	/**
	 * @return 返回 addrSet。
	 */
	public java.util.Set getAddrSet() {
		return addrSet;
	}
	/**
	 * @param addrSet 要设置的 addrSet。
	 */
	public void setAddrSet(java.util.Set addrSet) {
		this.addrSet = addrSet;
	}
	/**
	 * @return 返回 age。
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age 要设置的 age。
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return 返回 name。
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 返回 phone。
	 */
	public Phone getPhone() {
		return phone;
	}
	/**
	 * @param phone 要设置的 phone。
	 */
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	/**
	 * @return 返回 postCode。
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode 要设置的 postCode。
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * @return 返回 residentID。
	 */
	public String getResidentID() {
		return residentID;
	}
	/**
	 * @param residentID 要设置的 residentID。
	 */
	public void setResidentID(String residentID) {
		this.residentID = residentID;
	}
	/**
	 * @return 返回 sex。
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex 要设置的 sex。
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}      
    
}
