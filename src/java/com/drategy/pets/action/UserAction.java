//$Source: /petSys/petSys/src/java/com/drategy/pets/action/UserAction.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.action;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtils;

import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.*;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.UserForm;

/**
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.15 $
 */
public class UserAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得ActionForm* */
		UserForm userForm = (UserForm) form;

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 查看数据库中是否存在相同名字的User，如果相同，抛出错误 */
		String userName = userForm.getUserName();
		
		if ((User) structService.findUserByName(userName) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.user.exist", new ActionError(
					"errors.user.exist", null));
			super.saveErrors(request, errors);
			
			request.setAttribute("employeeId",userForm.getEmployeeId());
			
			SystemLogger.debug("有相同的登陆帐号");
			return mapping.getInputForward();
		}

		/** 创建user* */
		User user = new User();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(user, userForm);
		} catch (Exception ex) {
			SystemLogger.error("UserAction method add 拷贝属性错误:"+ex.toString());
		}

		user.setId(Tools.genResourceId(""));

		Employee employee = structService
				.findEmployee(userForm.getEmployeeId());
		Area area = structService.findArea(userForm.getAreaId());
		
		
		SystemLogger.debug("areaId:"+area.getId());
	 
		user.setArea(area);
		
		user.setEmployee(employee);
		
		/**配置权限*/
		configAuthorizaton(structService,user,userForm.getSysRole());
		
		structService.addUser(user);

		/** 设置request属性* */
		request.setAttribute("method",BaseForm.METHOD_ADD);
		request.setAttribute("employeeId", employee.getId());

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 得到UserForm对象
		UserForm userForm = (UserForm) form;

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		String employeeId = userForm.getEmployeeId();

		// get User by Id
		User user = structService.findUser(userForm.getId());
		Area area = structService.findArea(userForm.getAreaId());
		
		/**配置权限*/
		if(!user.getSysRole().equals(userForm.getSysRole())){
			Set authorizationSet = user.getAuthorizationSet();
			Iterator item = authorizationSet.iterator();
			
			/**循环删除权限*/
			while(item.hasNext()){
			    
	            Authorization tempAuthorization = (Authorization)item.next();
	            
	            SystemLogger.debug("删除权限");
	            structService.deleteAuthorization(tempAuthorization);
			}
			
			configAuthorizaton( structService,user,userForm.getSysRole());
		}
		
		/** 设置属性* */
		try {
			BeanUtils.copyProperties(user, userForm);
		} catch (Exception ex) {
			SystemLogger
					.error("UserAction method edit 拷贝属性错误:" + ex.toString());
		}

		user.setArea(area);
        
		
		
		/**更新对象*/
		structService.updateUser(user);

		/** 设置request参数* */
		request.setAttribute("method", BaseForm.METHOD_EDIT);
		request.setAttribute("employeeId", employeeId);

		return mapping.findForward("success");
	}
	
	/**配置权限*/
	private void configAuthorizaton(StructService structService,User  user ,String sysRole){
	    
	    SystemLogger.debug("设置权限");
	    /**创建一个权限数组*/
	    Set authorizationSet = new java.util.HashSet();
	    
	    /**取得一个角色对应的权限模块*/
	    String[] moudleNames = Authorization.getMoudleNamesValue(sysRole);
	    for(int i=0 ;i<moudleNames.length;i++){
	        String moudleName = moudleNames[i];
	        String[] childNames = Authorization.getChildMoudleNamesValue(moudleName);
	        for(int ii=0;ii<childNames.length;ii++){
	            
	            String childName = childNames[ii];
	            
	            /**创建一个权限*/
	            Authorization tempAuthorization = new Authorization();
	            tempAuthorization.setActAdd(Authorization.ACT_PRRMIT_YES);   
                tempAuthorization.setActDelete(Authorization.ACT_PRRMIT_YES);
                tempAuthorization.setActUpdate(Authorization.ACT_PRRMIT_YES);
                tempAuthorization.setChildMoudleName(childName);
                tempAuthorization.setMoudleName(moudleName);
                tempAuthorization.setId(Tools.genResourceId("")); 
                
                structService.addAuthorization(tempAuthorization);
                /**装入结果*/
                authorizationSet.add(tempAuthorization);    
	        }
	        
	    }
	    
	    user.setAuthorizationSet(authorizationSet);	    
	    
	    SystemLogger.debug("设置权限完成");
	}
	

}
