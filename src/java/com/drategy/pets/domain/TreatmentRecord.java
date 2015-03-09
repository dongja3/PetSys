//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/TreatmentRecord.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $


package com.drategy.pets.domain;

import com.drategy.pets.domain.Employee;
import com.drategy.pets.domain.Pet;

/**
* 系统的treatmentRecord
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.4 $
*/
public class TreatmentRecord {
    
    /**第一次**/
    public static final int VISIT_DOCTOR_TIME_FIRST = 1;
    
    /**第二次**/
    public static final int VISIT_DOCTOR_TIME_SECOND =0;
    
	/**
     * id 主键
     */
    private String id ;
    /**
     * 临床表现
     */
    private String description;
    /**
     * 日期
     */
    private String date;
    /**
     * 看病医生
     */
    private Employee doctor;
    /**
     * 对应宠物
     */
    private Pet pet;
    
    /**
     * 是否是初诊
     */
    private int visitDoctorTime;
    
    /**
     * 病状态
     */
    private String diseaseState;
       
    /**    
     *  处方 
     */
    private String prescrition; 
    /**
     * 
     * 疾病史
     */
	private String diseaseHistory;
	
	
	 public String getDiseaseHistory() {
	    return diseaseHistory;
	  }
	  public String getDiseaseState() {
	    return diseaseState;
	  }
	  public String getPrescrition() {
	    return prescrition;
	  }
	  public int getVisitDoctorTime() {
	    return visitDoctorTime;
	  }
	  public void setVisitDoctorTime(int visitDoctorTime) {
	    this.visitDoctorTime = visitDoctorTime;
	  }
	  public void setPrescrition(String prescrition) {
	    this.prescrition = prescrition;
	  }
	  public void setDiseaseState(String diseaseState) {
	    this.diseaseState = diseaseState;
	  }
	  public void setDiseaseHistory(String diseaseHistory) {
	    this.diseaseHistory = diseaseHistory;
	  }

	
    public void setPet(Pet pet){
        this.pet = pet ;
    }
    
    public Pet getPet(){
        return this.pet;
    }
    
    public String getDate() {
        return date;
      }
      public String getDescription() {
        return description;
      }
    
      public Employee getDoctor() {
        return doctor;
      }
      public String getId() {
        return id;
      }
      public void setId(String id) {
        this.id = id;
      }
      public void setDoctor(Employee doctor) {
        this.doctor = doctor;
      }
    
      public void setDescription(String description) {
        this.description = description;
      }
      public void setDate(String date) {
        this.date = date;
      }
         
 

}