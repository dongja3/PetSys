//$Source: /petSys/petSys/src/java/com/drategy/pets/form/TreatmentRecordForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/15 07:54:52 $

package com.drategy.pets.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Pet;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.Tools;

/**
 * 宠物看病信息 Form
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.5 $
 */

public class TreatmentRecordForm extends BaseForm {
    
    private String id;
    
	private String date = null;

	private int visitDoctorTime = 0;

	private String description = null;

	private String prescrition = null;

	private String diseaseState = null;

	private String diseaseHistory = null;	
	
	private String petId;
	
    /**
     * @return 返回 date。
     */
    public String getDate() {
        return date;
    }
    
    /**
     * @param date 要设置的 date。
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * @return 返回 diseaseHistory。
     */
    public String getDiseaseHistory() {
        return diseaseHistory;
    }
    
    /**
     * @param diseaseHistory 要设置的 diseaseHistory。
     */
    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }
    
    /**
     * @return 返回 diseaseState。
     */
    public String getDiseaseState() {
        return diseaseState;
    }
    
    /**
     * @param diseaseState 要设置的 diseaseState。
     */
    public void setDiseaseState(String diseaseState) {
        this.diseaseState = diseaseState;
    }
    /**
     * @return 返回 petId。
     */
    public String getPetId() {
        return petId;
    }
    
    /**
     * @param petId 要设置的 petId。
     */
    public void setPetId(String petId) {
        this.petId = petId;
    }
    
    /**
     * @return 返回 prescrition。
     */
    public String getPrescrition() {
        return prescrition;
    }
    
    /**
     * @param prescrition 要设置的 prescrition。
     */
    public void setPrescrition(String prescrition) {
        this.prescrition = prescrition;
    }
    
    /**
     * @return 返回 treament。
     */
    public String getdescription() {
        return description;
    }
    
    /**
     * @param treament 要设置的 treament。
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return 返回 visitDoctorTime。
     */
    public int getVisitDoctorTime() {
        return visitDoctorTime;
    }
    
    /**
     * @param visitDoctorTime 要设置的 visitDoctorTime。
     */
    public void setVisitDoctorTime(int visitDoctorTime) {
        this.visitDoctorTime = visitDoctorTime;
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
    
    public ActionErrors validate(ActionMapping map,HttpServletRequest request){
        
        /**如果是delete操作就直接返回**/
        if(this.method.equals(BaseForm.METHOD_DELETE)){
            return null;
        }        
        
        /**创建一个errors**/
        ActionErrors errors = new ActionErrors();
        
        /**创建一个服务**/
        StructService structService =(StructService)Global.getInstance().getService("structService");
        
        /**创建一个pet**/        
        Pet pet = structService.findPet(request.getParameter("petId"));
       
        /**保存errors**/
        if(Tools.isNullOrEmpty(this.date)){
            errors.add("error.treatmentRecordForm.date",new ActionError("error.treatmentRecordForm.date.required"));
        }
        
        /**设置request属性**/        
        request.setAttribute("pet",pet);
        request.setAttribute("chipNo",pet.getRfidChip().getCode());
        request.setAttribute("petImageId",pet.getPetImage().getId());
        request.setAttribute("variety",pet.getVariety().getName());
        
        /**返回结果**/
        return errors;
    }
}
