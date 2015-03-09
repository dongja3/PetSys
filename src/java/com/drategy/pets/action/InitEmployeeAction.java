//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitEmployeeAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $

package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Employee;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.EmployeeForm;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;

/**
 * initEmployeeAction 所有与Employee初始化有关操作
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.10 $
 */
public class InitEmployeeAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 创建actionForm对象* */
		EmployeeForm employeeForm = new EmployeeForm();

		/** 设置属性* */
		employeeForm.setMethod("add");
		
		request.setAttribute("employeeForm",employeeForm);

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request的参数* */
		String employeeId = request.getParameter("employeeId");

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 取得petOwner* */
		Employee employee = structService.findEmployee(employeeId);

		/** 创建ActionForm* */
		EmployeeForm employeeForm = new EmployeeForm();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(employeeForm, employee);
		} catch (Exception ex) {
			SystemLogger.error("InitEmployeeAction method edit 错误："
					+ ex.toString());
		}

		employeeForm.setMethod(BaseForm.METHOD_EDIT);

		/** 设置request属性* */
		request.setAttribute("employeeForm", employeeForm);

		/** 转向* */
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/**取得employeeId**/
        String employeeId = request.getParameter("employeeId");
        
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**取得Employee**/
        Employee employee = structService.findEmployee(employeeId);
        
        /**如果是没有登陆帐号*/
        if(!(employee.getUserSet().size()==0 ||employee.getUserSet()==null)){
            
            /**设置request参数**/
            request.setAttribute("method",BaseForm.METHOD_DELETE);
            request.setAttribute("action","failed");
            
            /**页面转向**/
            return mapping.findForward("delete");
        }
       
        /**删除对象**/
        structService.deleteEmployee(employee);
        
        /**设置request参数**/
        request.setAttribute("method",BaseForm.METHOD_DELETE);
        request.setAttribute("action","success");
        
        /**页面转向**/
        return mapping.findForward("delete");
	}

}
