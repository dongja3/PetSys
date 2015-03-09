//$Source: /petSys/petSys/src/java/com/drategy/pets/action/LostandFoundAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.action;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.bom.Address;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Pet;
import com.drategy.pets.springservice.StructService;

/**
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.5 $
 */
public class LostandFoundAction extends BaseDispatchAction {
   
    public ActionForward execute(ActionMapping map, ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response){
        /**取得request的参数**/
        String chipNo = request.getParameter("chipNo");
        
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**查找宠物**/
        Pet pet = structService.findPetByChipNo(chipNo);
        
        /**判断pet是否存在**/
        if(!(pet instanceof Pet)){
            ActionErrors errors = new ActionErrors();
            errors.add("search.chipNo.noExits",new ActionError("search.chipNo.noExits",chipNo));
            super.saveErrors(request,errors);
            return map.getInputForward(); 
        }
        
        
        /**设置request 属性**/
        request.setAttribute("pet",pet);        
        request.setAttribute("petOwner",pet.getPetOwner());
        request.setAttribute("phone",pet.getPetOwner().getPhone());
        request.setAttribute("varietyName",pet.getVariety().getName());
        request.setAttribute("areaName",pet.getPetOwner().getArea().getName());
        
        Set addrSet = pet.getPetOwner().getAddrSet();
        Address  address = (Address) addrSet.iterator().next();        
        request.setAttribute("address",address);
        request.setAttribute("petImageId",pet.getPetImage().getId());
        /**页面转向**/
        return map.findForward("success");
    }
    
}
