//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitPetAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $

package com.drategy.pets.action;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtils;

import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.bom.Address;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.*;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.PetForm;

/**
* petAction 所有与petAction初始化有关操作 
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.16 $
*/
public class InitPetAction extends BaseDispatchAction{
    
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**取得request的属性**/
        String petOwnerId = request.getParameter("petOwnerId");
        
        /**创建petForm对象**/
        PetForm petForm = new PetForm();
        
        /**设置属性**/
        petForm.setMethod("add");
        petForm.setPetOwnerId(petOwnerId);
        petForm.setPetImageId("");
        petForm.setPetCertificateId("");
        
        /**设置request的属性**/
        request.setAttribute("petForm",petForm);  
        
        /**成功后转向**/
        return mapping.findForward("success");
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**取得request参数**/
        String petId = request.getParameter("petId");
        PetForm petForm = new PetForm();
        
        petForm.setId(petId);
        
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        Pet pet = structService.findPet(petId);
        
        /**设置属性**/
        try{
            BeanUtils.copyProperties(petForm,pet);
        }catch(Exception ex){
            SystemLogger.error("initPetAction method edit 错误:"+ex.toString());
        }
        
        petForm.setPetOwnerId(pet.getPetOwner().getId());
        petForm.setRfidNo(pet.getRfidChip().getCode());
        petForm.setPetImageId(pet.getPetImage().getId());
        petForm.setPetCertificateId(pet.getCertificate().getId());
        petForm.setVarietyName(pet.getVariety().getName());
        petForm.setMethod("edit");
        
        /**设置request的属性**/
        request.setAttribute("petForm",petForm);        
         
        /**成功后转向**/
        return mapping.findForward("success");
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**取得request参数**/
        String petId = request.getParameter("petId");
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        Pet pet = structService.findPet(petId);
        
        /**petOwnerId**/
        String petOwnerId = pet.getPetOwner().getId();
        
        /**删除对象**/
        structService.deletePet(pet);
        
        /**设置request属性**/
        request.setAttribute("method",BaseForm.METHOD_DELETE);
        request.setAttribute("petOwnerId",petOwnerId);       
             
        /**成功后转向**/
        return mapping.findForward("delete");
    }
    
    
    public ActionForward showInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
        /**取得request参数**/
        String petId = request.getParameter("petId");
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**查找宠物**/
        Pet pet = structService.findPet(petId);
        
       String petOwnerId = pet.getPetOwner().getId();
	    
        /**设置request 属性**/
        request.setAttribute("pet",pet);        
        request.setAttribute("petImageId",pet.getPetImage().getId());
        request.setAttribute("petOwnerId",petOwnerId);
        request.setAttribute("chipNo",pet.getRfidChip().getCode());
        request.setAttribute("varietyName",pet.getVariety().getName());
        
        /**页面转向**/
        return mapping.findForward("showInfo");
    }

    
    public ActionForward partnerInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
        /**取得request参数**/
        String petId = request.getParameter("petId");
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**查找宠物**/
        Pet pet = structService.findPet(petId);
        
        /**设置request 属性**/
        request.setAttribute("pet",pet);        
        request.setAttribute("petOwner",pet.getPetOwner());
        request.setAttribute("phone",pet.getPetOwner().getPhone());
        request.setAttribute("varietyName",pet.getVariety().getName());
        
        Set addrSet = pet.getPetOwner().getAddrSet();
        Address  address = (Address) addrSet.iterator().next();        
        request.setAttribute("address",address);
        request.setAttribute("petImageId",pet.getPetImage().getId());
        request.setAttribute("areaName",pet.getPetOwner().getArea().getName());
        
        /**页面转向**/
        return mapping.findForward("partnerInfo");
    } 

    
    public ActionForward showPetInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
        /**取得request参数**/
        String petId = request.getParameter("petId");
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**查找宠物**/
        Pet pet = structService.findPet(petId);
        
       String petOwnerId = pet.getPetOwner().getId();
	    
        /**设置request 属性**/
        request.setAttribute("pet",pet);        
        request.setAttribute("petImageId",pet.getPetImage().getId());
        request.setAttribute("petOwnerId",petOwnerId);
        request.setAttribute("chipNo",pet.getRfidChip().getCode());
        request.setAttribute("varietyName",pet.getVariety().getName());
        
        /**页面转向**/
        return mapping.findForward("showPetInfo");
    }

}
