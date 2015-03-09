//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitSmsAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 03:12:51 $
package com.drategy.pets.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Sms;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.SmsForm;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;

/**
 * InitSmsAction
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.5 $
 */

public final class InitSmsAction extends BaseDispatchAction {
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 创建SmsForm* */
		SmsForm smsForm = new SmsForm();

		/** 设置属性* */
		smsForm.setMethod(BaseForm.METHOD_ADD);

		/** 设置requst属性* */
		request.setAttribute("smsForm", smsForm);

		// 获得AreaList
		List areaList = new ArrayList();
		areaList = Constant.getAllArea();
		request.setAttribute("areaList", areaList);

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 得到参数id* */
		String id = request.getParameter("id");

		SystemLogger.debug("SMS to edit:" + id);

		/** 创建SmsForm* */
		SmsForm smsForm = new SmsForm();

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 创建sms对象* */
		Sms sms = structService.findSms(id);

		try {
			BeanUtils.copyProperties(smsForm, sms);
		} catch (Exception ex) {
			SystemLogger.error("initSmsAction edit method 拷贝属性错误:"
					+ ex.toString());
		}

		// 获得AreaList
		List areaList = new ArrayList();
		areaList = Constant.getAllArea();
		request.setAttribute("areaList", areaList);

		smsForm.setMethod(BaseForm.METHOD_EDIT);
		/** 设置request属性* */
		request.setAttribute("smsForm", smsForm);

		/** 页面转向* */
		return mapping.findForward("success");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /**取得request参数**/
        String smsId = request.getParameter("smsId");
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        Sms sms = structService.findSms(smsId);
        
        
        /**删除对象**/
        structService.deleteSms(sms);
        
        /**设置request属性**/
        request.setAttribute("method",BaseForm.METHOD_DELETE);       
             
        /**成功后转向**/
        return mapping.findForward("delete");
    }

}
