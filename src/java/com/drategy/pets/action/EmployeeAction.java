//$Source: /petSys/petSys/src/java/com/drategy/pets/action/EmployeeAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 03:13:05 $

package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Employee;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.EmployeeForm;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * EmployeeAction 所有与Employee有关操作
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.11 $
 */
public class EmployeeAction extends BaseDispatchAction {    
 
    
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   
	    
		/** 取得ActionForm* */
		EmployeeForm employeeForm = (EmployeeForm) form;
		
		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
		.getService("structService");
		
	   /**查看数据库中是否存在相同名字的Employee，如果相同，抛出错误*/
		String employeeName = employeeForm.getName();
		if ((Employee)structService.findEmployeeByName(employeeName) != null){
			ActionErrors errors = new ActionErrors();
	        errors.add("errors.employee.exist",new ActionError("errors.employee.exist",null));
	        super.saveErrors(request,errors);
	        return mapping.getInputForward();
		}

		/** 创建employee对象* */
		Employee employee = new Employee();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(employee, employeeForm);
		} catch (Exception ex) {
			SystemLogger.error("EmployeeAction [add] 拷贝属性出错:"+ ex.toString());
		}

		employee.setId(Tools.genResourceId(""));


		/** 保存对象* */
		structService.addEmployee(employee);

		/** 保存属性* */
		request.setAttribute("method",BaseForm.METHOD_ADD);

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 得到employeeForm对象* */
		EmployeeForm employeeForm = (EmployeeForm) form;
		
		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
		.getService("structService");

		/** 取得employeeId* */
		String employeeId = employeeForm.getId();

		/** 取得一个employee对象* */
		Employee employee = structService.findEmployee(employeeId);

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(employee, employeeForm);
		} catch (Exception ex) {
			SystemLogger.error("employeeAction method edit 拷贝属性错误:"
					+ ex.toString());
		}
		
		structService.updateEmployee(employee);
		
		/**设置request参数**/
        request.setAttribute("method",BaseForm.METHOD_EDIT);
        request.setAttribute("employeeId",employeeId);
         
        /**成功后转向**/
        return mapping.findForward("success");

	}

}
