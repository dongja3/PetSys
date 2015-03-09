//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/Employee.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $

package com.drategy.pets.domain;

import com.drategy.pets.bom.*;
import java.util.Set;



/**
* 系统的员工
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.4 $
*/

public class Employee extends Person{
	
    /** id 是主键  */
    private String id;
     
    /**备注*/
    private String note;
    
    /**宠物的疫苗记录 */
    private java.util.Set vaccineSet;
        
    /** 看病记录   */
    private java.util.Set treatmentRecordSet;
    
	/**登陆帐户*/
    private Set userSet;    
 
    
    public java.util.Set getTreatmentRecordSet() {
	    return treatmentRecordSet;
	  }
    
	 public void setTreatmentRecordSet(java.util.Set treatmentRecordSet) {
	    this.treatmentRecordSet = treatmentRecordSet;
	 }   
   
    
	public void setVaccineSet(java.util.Set vaccineSet){
	    this.vaccineSet =vaccineSet ;
	}
	
	
	public java.util.Set getVaccineSet(){
	    return vaccineSet;
	}
    
	 public String getId() {
	    return id;
	 }
	
	 public String getNote() {
	    return note;
	 }
	 
	 public void setNote(String note) {
	    this.note = note;
	 }
	  
	 public void setId(String id) {
	   this.id = id;
	 }
      
    /**
     * @return 返回 userSet。
     */
    public Set getUserSet() {
        return userSet;
    }
    /**
     * @param userSet 要设置的 userSet。
     */
    public void setUserSet(Set userSet) {
        this.userSet = userSet;
    }

}