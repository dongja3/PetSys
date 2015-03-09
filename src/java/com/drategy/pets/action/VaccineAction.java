//$Source: /petSys/petSys/src/java/com/drategy/pets/action/VaccineAction.java,v $
//LasterModified By:$Author: jackie.dong $
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
import com.drategy.pets.form.VaccineRecordForm;

/**
* 
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.9 $
*/
public class VaccineAction extends BaseDispatchAction {
    
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        /**取得actinForm**/
        VaccineRecordForm vaccineRecordForm = (VaccineRecordForm)form;
        String petId = vaccineRecordForm.getPetId();
        
        /**创建vaccine对象**/
        Vaccine vaccine = new Vaccine();
        
        /**设置属性**/
        try{
            BeanUtils.copyProperties(vaccine,vaccineRecordForm);
        }catch(Exception ex){
            SystemLogger.error("Vaccine add method 拷贝属性错误:"+ex.toString());
        }
        
        vaccine.setId(Tools.genResourceId(""));
        
        /**保存对象**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        Pet pet =null;
        
       
           pet = structService.findPet(petId);         
    
        vaccine.setPet(pet);
        structService.addVaccine(vaccine);
        
        /**设置request属性**/
        request.setAttribute("method",vaccineRecordForm.getMethod());
        request.setAttribute("pet",pet);
        request.setAttribute("petId",petId);
        request.setAttribute("chipNo",pet.getRfidChip().getCode());
        request.setAttribute("petImageId",pet.getPetImage().getId());
        
        /**页面转向**/
        return mapping.findForward("success");
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        /**取得actinForm**/
        VaccineRecordForm vaccineRecordForm = (VaccineRecordForm)form;
        String vaccineId = vaccineRecordForm.getId();
        String petId = vaccineRecordForm.getPetId();
        
        /**创建对象**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        Vaccine vaccine = structService.findVaccine(vaccineId);
        
        Pet pet = structService.findPet(petId);  
        
        /**设置属性**/
        try{
            BeanUtils.copyProperties(vaccine,vaccineRecordForm);
        }catch(Exception ex){
            SystemLogger.error("Vaccine edit method 拷贝属性错误:"+ex.toString());
        }
        
        /**保存对象**/  
        structService.updateVaccine(vaccine);
        
        /**设置request属性**/
        request.setAttribute("method",vaccineRecordForm.getMethod());
        request.setAttribute("vaccineId",vaccine.getId()); 
        request.setAttribute("chipNo",vaccine.getPet().getRfidChip().getCode()); 
        request.setAttribute("petImageId",pet.getPetImage().getId());
        
        /**页面转向**/
        return mapping.findForward("success");
    }    
   
}
