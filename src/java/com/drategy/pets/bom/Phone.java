//$Source: /petSys/petSys/src/java/com/drategy/pets/bom/Phone.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/12 09:03:34 $


package com.drategy.pets.bom;


/**
* 系统的phone
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/
public class Phone {
    
	 /**
     * id ���
     */
    private String id;
   
   /**
    *  家庭电话
    */
    public  String homeTelephone;
   /**
    * 公司电话
    */
    public String companyTelephone;
   /**
    * 移动电话
    */
    public String mobileTelephone;
	
	/**
	 * @return 返回 companyTelephone。
	 */
	public String getCompanyTelephone() {
		return companyTelephone;
	}
	/**
	 * @param companyTelephone 要设置的 companyTelephone。
	 */
	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}
	/**
	 * @return 返回 homeTelephone。
	 */
	public String getHomeTelephone() {
		return homeTelephone;
	}
	/**
	 * @param homeTelephone 要设置的 homeTelephone。
	 */
	public void setHomeTelephone(String homeTelephone) {
		this.homeTelephone = homeTelephone;
	}
	/**
	 * @return 返回 id。
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return 返回 mobileTelephone。
	 */
	public String getMobileTelephone() {
		return mobileTelephone;
	}
	/**
	 * @param mobileTelephone 要设置的 mobileTelephone。
	 */
	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}
    
}
