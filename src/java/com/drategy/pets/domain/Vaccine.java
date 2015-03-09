//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/Vaccine.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $


package com.drategy.pets.domain;

import com.drategy.pets.domain.Employee;
import com.drategy.pets.domain.Pet;


/**
* 系统的vaccine
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.2 $
*/
public class Vaccine {
	 /**
     * id 是主键
     */
    private String id;
    /**
     * 疫苗的名称
     */
    private String name;
    /**
     * 注射时间
     */
    private String injectDate;
    /**
     * 注射的兽医
     */
    private Employee veterinarian;
    /**
     * 备注信息
     */
    private String description;
    /**
     * 对应的主人
     * 
     */
    private Pet pet;
    /**
     *生产工厂 
     * @param pet
     */
    private String supplierName;
    /**
     * 批次号
     */
	private String batchNo;    
	/**
	 *免疫方式
	 */
	private String actionMethod;
	/**
	 * 体位
	 */
	private String actionLocation;
	/**
	 * 医院名称
	 */
	private String hospitalName;
	
    
	  public String getActionMethod() {
	    return actionMethod;
	  }
	  public String getBatchNo() {
	    return batchNo;
	  }
	  public String getActionLocation() {
	    return actionLocation;
	  }
	  public String getHospitalName() {
	    return hospitalName;
	  }
	  public String getSupplierName() {
	    return supplierName;
	  }
	  public void setSupplierName(String supplierName) {
	    this.supplierName = supplierName;
	  }
	  public void setHospitalName(String hospitalName) {
	    this.hospitalName = hospitalName;
	  }
	  public void setActionLocation(String actionLocation) {
	    this.actionLocation = actionLocation;
	  }
	  public void setBatchNo(String batchNo) {
	    this.batchNo = batchNo;
	  }
	  public void setActionMethod(String actionMethod) {
	    this.actionMethod = actionMethod;
	  }
	  
	public void setPet(Pet pet)
    {
        this.pet = pet;
    }
    
    public Pet getPet(){
        return this.pet;
    }
    
      public String getDescription() {
        return description;
      }
      public String getId() {
        return id;
      }
      public String getName() {
        return name;
      }
      public String getInjectDate() {
        return injectDate;
      }
      public Employee getVeterinarian() {
        return veterinarian;
      }
      public void setVeterinarian(Employee veterinarian) {
        this.veterinarian = veterinarian;
      }
      public void setName(String name) {
        this.name = name;
      }
      public void setInjectDate(String injectDate) {
        this.injectDate = injectDate;
      }
      public void setId(String id) {
        this.id = id;
      }
      public void setDescription(String description) {
        this.description = description;
      }
}