//$Source: /petSys/petSys/src/java/com/drategy/pets/action/TreatmentRecordAction.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtils;

import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.*;
import com.drategy.pets.exception.DAOException;
import com.drategy.pets.form.TreatmentRecordForm;

/**
* 
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.5 $
*/
public class TreatmentRecordAction extends BaseDispatchAction {

    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {       
        /**取得actionForm**/
        TreatmentRecordForm treatmentRecordForm = (TreatmentRecordForm)form;
        
        /**取得request的参数**/
        String petId = treatmentRecordForm.getPetId();
        
        /**创建treatmentRecord对象**/
        TreatmentRecord  treatmentRecord = new TreatmentRecord();
        
        /**设置属性**/
        try{
            BeanUtils.copyProperties(treatmentRecord,treatmentRecordForm);
        }catch(Exception ex){
           SystemLogger.error("TreatmentRecordAction method add 拷贝属性错误:"+ex.toString());
        }
        
        treatmentRecord.setId(Tools.genResourceId(""));
        
        /**保存对象**/
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        Pet pet = null;
        
        try{
            pet = structService.findPet(petId);         
         }catch(DAOException daoException){
             throw new  DAOException("error.pet.dbException");
         }
         
        treatmentRecord.setPet(pet);
        structService.addTreatmentRecord(treatmentRecord);
        
        /**设置request属性**/
        request.setAttribute("petId",petId);
        request.setAttribute("method",treatmentRecordForm.getMethod()); 
        request.setAttribute("chipNo",pet.getRfidChip().getCode()); 
        request.setAttribute("pet",pet);
        request.setAttribute("petImageId",pet.getPetImage().getId());
        
        /**页面转向**/
        return mapping.findForward("success");
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**取得actinForm**/
        TreatmentRecordForm treatmentRecordForm = (TreatmentRecordForm)form;
        String treatmentRecordId = treatmentRecordForm.getId();
        String petId = treatmentRecordForm.getPetId();
        
        /**创建对象**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        TreatmentRecord treatmentRecord = structService.findTreatmentRecord(treatmentRecordId);
        
        /**设置属性**/
        try{
            BeanUtils.copyProperties(treatmentRecord,treatmentRecordForm);
        }catch(Exception ex){
            SystemLogger.error(ex.toString());
        }
        
        /**保存对象**/  
        structService.updateTreatmentRecord(treatmentRecord);
        
        Pet pet = structService.findPet(petId);
        
        /**设置request属性**/
        request.setAttribute("method",treatmentRecordForm.getMethod());
        request.setAttribute("treatmentRecordId",treatmentRecord.getId()); 
        request.setAttribute("chipNo",treatmentRecord.getPet().getRfidChip().getCode()); 
        request.setAttribute("chipNo",pet.getRfidChip().getCode()); 
        request.setAttribute("pet",pet);
        request.setAttribute("petImageId",pet.getPetImage().getId());
        
        /**页面转向**/
        return mapping.findForward("success");
    }   
   

}
