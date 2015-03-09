//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitUserAction.java,v $
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
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.User;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.UserForm;

/**
* 
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.10 $
*/
public class InitUserAction extends BaseDispatchAction {
 
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	 /**取得request的属性**/
        String employeeId = request.getParameter("employeeId");
        
        /**创建ActionForm**/
        UserForm userForm =new UserForm();
        
        /**设置属性**/
        userForm.setMethod("add");   
        userForm.setEmployeeId(employeeId);
        
        request.setAttribute("userForm",userForm);
        request.setAttribute("employeeId",employeeId);
		
        /**页面转向**/    
        return mapping.findForward("success");
    }
    
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	String userId = request.getParameter("userId");
    	
    	/**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**取得user**/
        User user = structService.findUser(userId);
        
        Area area = user.getArea();
        
        /**创建ActionForm**/
        UserForm userForm =new UserForm();
        
        /**设置属性**/
        try{
            BeanUtils.copyProperties(userForm,user);
        }catch(Exception ex){
            SystemLogger.error("InitUserAction method edit 错误："+ex.toString());
        }
        
        /**获得User对应employee的ID*/
        String employeeId = user.getEmployee().getId();
        
        userForm.setMethod(BaseForm.METHOD_EDIT);
        userForm.setEmployeeId(employeeId);
        userForm.setAreaId(area.getId());
        
        request.setAttribute("userForm",userForm);
        request.setAttribute("employeeId",employeeId);
        
        return mapping.findForward("success");
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得userId* */
		String userId = request.getParameter("userId");

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 取得user* */
		User user = structService.findUser(userId);

		/** 删除对象* */
		structService.deleteUser(user);

		/** 设置request参数* */
		request.setAttribute("method", BaseForm.METHOD_DELETE);
		request.setAttribute("employeeId",user.getEmployee().getId());

		/** 页面转向* */
		return mapping.findForward("delete");
	}
    
    public ActionForward updatePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 得到request对象*/
		String password  = request.getParameter("password");
        
		SystemLogger.debug("password"+password);
		
		/**取得当前的操作用户*/
        User user = (User)super.getSessionUser(request); 
                
		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");	

		/**找到用户*/
		User tempUser = structService.findUser(user.getId());
		tempUser.setPassword(password);
		
		/**修改对象*/
		structService.updateUser(tempUser);

		/** 设置request参数* */
		request.setAttribute("method", "updatePassword");
        
		/**页面转向*/
		return mapping.findForward("updatePassword");
	}
    

}
