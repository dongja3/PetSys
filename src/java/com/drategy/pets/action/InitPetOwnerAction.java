//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitPetOwnerAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $

package com.drategy.pets.action;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.bom.Address;
import com.drategy.pets.bom.Phone;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Pet;
import com.drategy.pets.domain.PetOwner;
import com.drategy.pets.domain.Sms;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.PetOwnerForm;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
* 
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.8 $
*/
public class InitPetOwnerAction extends BaseDispatchAction {
    
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**创建一个petOwnerForm对象**/
        PetOwnerForm petOwnerForm = new PetOwnerForm();
        
        
        /**设置属性**/
        petOwnerForm.setMethod("add");   

        petOwnerForm.setSmsService(Sms.SendNow_No);
        
        /**设置reuqest属性**/
        request.setAttribute("petOwnerForm",petOwnerForm);
        
        /**成功后转向**/
        return mapping.findForward("success");
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**取得request的参数**/
        String petOwnerId = request.getParameter("petOwnerId");
        
        String petId = request.getParameter("petId");
        
        
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**是否为null*/
        if(!Tools.isNullOrEmpty(petId)){
           Pet pet =  structService.findPet(petId);
           petOwnerId = pet.getPetOwner().getId();
        }        
        
        /**取得petOwner**/
        PetOwner petOwner = structService.findPetOwner(petOwnerId);
        
        /**创建ActionForm**/
        PetOwnerForm petOwnerForm = new PetOwnerForm();
        
        /**设置属性**/
        try{
            BeanUtils.copyProperties(petOwnerForm,petOwner);
        }catch(Exception ex){
            SystemLogger.error("InitPetOwnerAction method edit 错误："+ex.toString());
        }        
        
        /**取得地址**/
        Set addrSet= petOwner.getAddrSet();
        Iterator itemAddr = addrSet.iterator();
        
        /**如果有地址对象**/
        if(itemAddr.hasNext()){
            Address addr = (Address)itemAddr.next();
            petOwnerForm.setAddr(addr.getDetailAddress());
            
        }
        
        petOwnerForm.setAreaId(petOwner.getArea().getId());
        
        /**设置联系电话**/
        Phone phone = petOwner.getPhone();
        petOwnerForm.setFamilyphone(phone.getHomeTelephone());
        petOwnerForm.setMobile(phone.getMobileTelephone());
        petOwnerForm.setOfficephone(phone.getCompanyTelephone());
        
        petOwnerForm.setMethod(BaseForm.METHOD_EDIT);
        
        /**设置request属性**/
        request.setAttribute("petOwnerForm",petOwnerForm);
        
        /**转向**/
        return mapping.findForward("success");
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**取得petOwnerId**/
        String petOwnerId = request.getParameter("petOwnerId");
        
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**取得petOwner**/
        PetOwner petOwner = structService.findPetOwner(petOwnerId);
        
        /**删除对象**/
        structService.deletePetOwner(petOwner);       
        
        /**设置request参数**/
        request.setAttribute("method",BaseForm.METHOD_DELETE);
        
        /**页面转向**/
        return mapping.findForward("delete");
    }

}
