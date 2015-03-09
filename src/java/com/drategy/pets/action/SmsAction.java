//$Source: /petSys/petSys/src/java/com/drategy/pets/action/SmsAction.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/02/21 01:07:30 $

package com.drategy.pets.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
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
import com.drategy.pets.util.Tools;

/**
 * SmsAction 所有与News有关操作
 * 
 * @author Jackie Dong
 * @author $Author: jason.jiang $
 * @$Revision: 1.4 $
 */
public class SmsAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 得到ActionForm* */
		SmsForm smsForm = (SmsForm) (form);

		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 创建一个Sms对象* */
		Sms sms = new Sms();

		try {
			/** 复制属性* */
			BeanUtils.copyProperties(sms, smsForm);
		} catch (Exception ex) {
			SystemLogger.error("SmsAction add method 拷贝属性出错:" + ex.toString());
		}

		sms.setId(Tools.genResourceId(""));

		// 如果日期为空，那么就将当前日期添加到系统
		if (sms.getSendTime().equals("")) {
			Calendar rightNow = Calendar.getInstance();

			sms.setSendTime(rightNow.get(Calendar.YEAR) + "-"
					+ covertMonth(rightNow.get(Calendar.MONTH)) + "-"
					+ convertDay(rightNow.get(Calendar.DAY_OF_MONTH)));
		}
        
		sms.setSend(Sms.SendNow_No);
		
		/** 设置request属性* */
		request.setAttribute("method", BaseForm.METHOD_ADD);

		if (smsForm.getContent().trim().length() == 0) {
			ActionErrors errors = new ActionErrors();
			errors.add("sms.content.empty", new ActionError(
					"sms.content.empty", null));
			super.saveErrors(request, errors);

			// 获得AreaList
			List areaList = new ArrayList();
			areaList = Constant.getAllArea();
			request.setAttribute("areaList", areaList);

			return mapping.getInputForward();
		}

		structService.addSms(sms);

		/** 操作成功返回jsp页面* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 得到ActionForm* */
		SmsForm smsForm = (SmsForm) (form);

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		Sms sms = new Sms();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(sms, smsForm);
		} catch (Exception ex) {
			SystemLogger.error("SmsAction method edit 拷贝属性错误:" + ex.toString());
		}

		/** 设置request参数* */
		request.setAttribute("method", BaseForm.METHOD_EDIT);

		if (smsForm.getContent().trim().length() == 0) {
			ActionErrors errors = new ActionErrors();
			errors.add("sms.content.empty", new ActionError(
					"sms.content.empty", null));
			super.saveErrors(request, errors);

			// 获得AreaList
			List areaList = new ArrayList();
			areaList = Constant.getAllArea();
			request.setAttribute("areaList", areaList);

			return mapping.getInputForward();
		}

		// 如果日期为空，那么就将当前日期添加到系统
		if (sms.getSendTime().equals("")) {
			Calendar rightNow = Calendar.getInstance();

			sms.setSendTime(rightNow.get(Calendar.YEAR) + "-"
					+ covertMonth(rightNow.get(Calendar.MONTH)) + "-"
					+ convertDay(rightNow.get(Calendar.DAY_OF_MONTH)));
		}
		
		sms.setSend(Sms.SendNow_No);
		
		structService.updateSms(sms);

		/** 成功后转向* */
		return mapping.findForward("success");

	}

	// 格式化天数
	private String convertDay(int i) {
		if (i < 10)
			return "0" + Integer.toString(i);
		return Integer.toString(i);
	}

	// 格式化月数
	private String covertMonth(int i) {
		int month = i + 1;
		if (month < 10)
			return "0" + Integer.toString(month);
		return Integer.toString(month);
	}

}