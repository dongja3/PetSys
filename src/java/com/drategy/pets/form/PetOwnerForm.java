//$Source: /petSys/petSys/src/java/com/drategy/pets/form/PetOwnerForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 04:18:35 $

package com.drategy.pets.form;

import java.util.List;

import com.drategy.pets.biz.GetAreaListByUser;
import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;


/**
 * 宠物主人注册 Form
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.8 $
 */

public class PetOwnerForm extends BaseForm {
    
	private static final long serialVersionUID = 1L;
    
	private String id = null;

	private String name = null;

	private String age = null;
    
	private String postCode = null;
	
	private String sex = null;

	private String familyphone = null;

	private String officephone = null;

	private String mobile = null;
   
	private String residentID = null;
   
	private String addr = null;	
	
	private String areaCode=null;
	
	/**是否开通短信*/
    private String smsService; 
    
	private String startTime=null;
	
	private String endTime=null;
	
	private String areaId ;
	
	private String[] areaIdList ;
	
	private String[] areaNameList;
	
	public PetOwnerForm(){  
        
        /**得到当前用户所在区域和子区域*/
        GetAreaListByUser getAreaListByUser = (GetAreaListByUser)Global.getInstance().getService("getAreaListByUser");
        
        List areaList = getAreaListByUser.getAreaListByUserId(Constant.CURR_USER_ID);
        
        areaIdList = new String[areaList.size()];
        areaNameList =  new String[areaList.size()];
        
		for(int i=0;i<areaList.size();i++){
		    Area tempArea =(Area)areaList.get(i); 
		    areaIdList[i] = tempArea.getId();
		    areaNameList[i] = tempArea.getName();
		  }
	}
	
    public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
     * @return 返回 addr。
     */
    public String getAddr() {
        return addr;
    }
    /**
     * @param addr 要设置的 addr。
     */
    public void setAddr(String addr) {
        this.addr = addr;
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
     * @return 返回 familyphone。
     */
    public String getFamilyphone() {
        return familyphone;
    }
    /**
     * @param familyphone 要设置的 familyphone。
     */
    public void setFamilyphone(String familyphone) {
        this.familyphone = familyphone;
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
     * @return 返回 mobile。
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * @param mobile 要设置的 mobile。
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * @return 返回 officephone。
     */
    public String getOfficephone() {
        return officephone;
    }
    /**
     * @param officephone 要设置的 officephone。
     */
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
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
    
    

    /**
     * @return 返回 areaId。
     */
    public String getAreaId() {
        return areaId;
    }
    /**
     * @param areaId 要设置的 areaId。
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
    /**
     * @return 返回 areaIdList。
     */
    
    /**
     * @return 返回 areaIdList。
     */
    public String[] getAreaIdList() {
        return areaIdList;
    }
    /**
     * @param areaIdList 要设置的 areaIdList。
     */
    public void setAreaIdList(String[] areaIdList) {
        this.areaIdList = areaIdList;
    }
    /**
     * @return 返回 areaNameList。
     */
    public String[] getAreaNameList() {
        return areaNameList;
    }
    /**
     * @param areaNameList 要设置的 areaNameList。
     */
    public void setAreaNameList(String[] areaNameList) {
        this.areaNameList = areaNameList;
    }
    
    
    /**
     * @return 返回 smsService。
     */
    public String getSmsService() {
        return smsService;
    }
    
    /**
     * @param smsService 要设置的 smsService。
     */
    public void setSmsService(String smsService) {
        this.smsService = smsService;
    }
    
    
}
