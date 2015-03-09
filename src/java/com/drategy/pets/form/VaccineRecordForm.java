//$Source: /petSys/petSys/src/java/com/drategy/pets/form/VaccineRecordForm.java,v $
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

/* 
 * 疫苗注射记录 Form
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.8 $
 */

public class VaccineRecordForm extends BaseForm {
	
    private static final long serialVersionUID = 1L;
    
	private String id = null;

	private String method = null;

	private String name = null;

	private String supplierName = null;

	private String batchNo = null;

	private String injectDate = null;

	private String actionMethod = null;

	private String actionLocation = null;

	private String hospitalName = null;

	private String description = null;
	
	private String petId = null ;
	
    /**
     * @return 返回 actionLocation。
     */
    public String getActionLocation() {
        return actionLocation;
    }
    /**
     * @param actionLocation 要设置的 actionLocation。
     */
    public void setActionLocation(String actionLocation) {
        this.actionLocation = actionLocation;
    }
    /**
     * @return 返回 actionMethod。
     */
    public String getActionMethod() {
        return actionMethod;
    }
    /**
     * @param actionMethod 要设置的 actionMethod。
     */
    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }
    /**
     * @return 返回 batchNo。
     */
    public String getBatchNo() {
        return batchNo;
    }
    /**
     * @param batchNo 要设置的 batchNo。
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    /**
     * @return 返回 description。
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description 要设置的 description。
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return 返回 hospitalName。
     */
    public String getHospitalName() {
        return hospitalName;
    }
    /**
     * @param hospitalName 要设置的 hospitalName。
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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
     * @return 返回 injectDate。
     */
    public String getInjectDate() {
        return injectDate;
    }
    /**
     * @param injectDate 要设置的 injectDate。
     */
    public void setInjectDate(String injectDate) {
        this.injectDate = injectDate;
    }
    /**
     * @return 返回 method。
     */
    public String getMethod() {
        return method;
    }
    /**
     * @param method 要设置的 method。
     */
    public void setMethod(String method) {
        this.method = method;
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
     * @return 返回 supplierName。
     */
    public String getSupplierName() {
        return supplierName;
    }
    /**
     * @param supplierName 要设置的 supplierName。
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
        if(Tools.isNullOrEmpty(this.name)){
            errors.add("error.vaccineForm.name",new ActionError("error.vaccineForm.name.required"));
        }
        
        if(Tools.isNullOrEmpty(this.batchNo)){
            errors.add("error.vaccineForm.batchNo",new ActionError("error.vaccineForm.batchNo.required"));
        }
        
        if(Tools.isNullOrEmpty(this.injectDate)){
            errors.add("error.vaccineForm.injectDate",new ActionError("error.vaccineForm.injectDate.required"));
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
