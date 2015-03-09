//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/PetOwner.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $


package com.drategy.pets.domain;

import com.drategy.pets.bom.*;


/**
* 系统的petOwner
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.8 $
*/
public class PetOwner extends Person {
	
	/**
     * 主键
     */
	private String id;    
    
    /**
	 * 服务开始时间
	 */
    private String startTime;
    
    /**
	 * 服务结束时间
	 */
    private String endTime;
    
    /**用于显示**/
    private String areaName;

    /**
     * 对应的宠物集
     */
    private java.util.Set petSet;
    
    /**主人所在区域*/
    private Area area;    

    /**是否开通短信*/
    private String smsService;
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return this.id;
    } 
    
    public void setPetSet(java.util.Set petSet){
        this.petSet = petSet;
    }
    
    public java.util.Set getPetSet(){
        return this.petSet;
    }

    /**
     * @return 返回 area。
     */
    public Area getArea() {
        return area;
    }
    /**
     * @param area 要设置的 area。
     */
    public void setArea(Area area) {
        this.area = area;
    }
    
    /**
     * @return 返回 endTime。
     */
    public String getEndTime() {
        return endTime;
    }
    /**
     * @param endTime 要设置的 endTime。
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
    /**
     * @return 返回 startTime。
     */
    public String getStartTime() {
        return startTime;
    }
    /**
     * @param startTime 要设置的 startTime。
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}