//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitTreatmentRecordAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $

package com.drategy.pets.action;

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
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.*;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.TreatmentRecordForm;

/**
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.9 $
 */
public class InitTreatmentRecordAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request参数* */
		String chipNo = request.getParameter("chipNo");

		/** 保存对象* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		Pet pet = structService.findPetByChipNo(chipNo);

		/** 是否存在一个chipNo为chipNo的宠物* */
		if (!(pet instanceof Pet)) {
			ActionErrors errors = new ActionErrors();
			errors.add("search.chipNo.noExits", new ActionError(
					"search.chipNo.noExits", chipNo));
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		/** 创建actionForm对象* */
		TreatmentRecordForm treatmentRecordForm = new TreatmentRecordForm();

		/** 设置属性* */
		treatmentRecordForm.setMethod(BaseForm.METHOD_ADD);
		treatmentRecordForm.setPetId(pet.getId());

		/** 设置request 属性* */
		request.setAttribute("treatmentRecordForm", treatmentRecordForm);
		request.setAttribute("pet", pet);
		request.setAttribute("chipNo", chipNo);
		request.setAttribute("petImageId", pet.getPetImage().getId());
		request.setAttribute("variety", pet.getVariety().getName());

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request中参数* */
		String treatmentRecordId = request.getParameter("treatmentRecordId");

		/** 保存对象* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		TreatmentRecord treatmentRecord = structService
				.findTreatmentRecord(treatmentRecordId);

		/** 创建actionForm对象* */
		TreatmentRecordForm treatmentRecordForm = new TreatmentRecordForm();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(treatmentRecordForm, treatmentRecord);
		} catch (Exception ex) {
			SystemLogger.error("initTreatmentRecord method edit 错误"
					+ ex.toString());
		}

		treatmentRecordForm.setPetId(treatmentRecord.getPet().getId());
		treatmentRecordForm.setMethod(BaseForm.METHOD_EDIT);

		/** 设置request属性* */
		request.setAttribute("treatmentRecordForm", treatmentRecordForm);
		request.setAttribute("pet", treatmentRecord.getPet());
		request.setAttribute("chipNo", treatmentRecord.getPet().getRfidChip()
				.getCode());
		request.setAttribute("petImageId", treatmentRecord.getPet()
				.getPetImage().getId());
		request.setAttribute("variety", treatmentRecord.getPet().getVariety()
				.getName());

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request参数* */
		String treatmentRecordId = request.getParameter("treatmentRecordId");

		/** 删除对象* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		TreatmentRecord treatmentRecord = structService
				.findTreatmentRecord(treatmentRecordId);
		structService.deleteTreatmentRecord(treatmentRecord);

		/** 设置request属性 * */
		request.setAttribute("petId", treatmentRecord.getPet().getId());
		request.setAttribute("chipNo", treatmentRecord.getPet().getRfidChip()
				.getCode());
		request.setAttribute("method", "delete");

		/** 页面转向* */
		return mapping.findForward("delete");
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request中参数* */
		String treatmentRecordId = request.getParameter("treatmentRecordId");

		/** 保存对象* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		TreatmentRecord treatmentRecord = structService
				.findTreatmentRecord(treatmentRecordId);

		/** 设置request属性* */
		request.setAttribute("treatmentRecord", treatmentRecord);
		request.setAttribute("pet", treatmentRecord.getPet());
		request.setAttribute("chipNo", treatmentRecord.getPet().getRfidChip()
				.getCode());
		request.setAttribute("petImageId", treatmentRecord.getPet()
				.getPetImage().getId());
		request.setAttribute("variety", treatmentRecord.getPet().getVariety()
				.getName());

		/** 页面转向* */
		return mapping.findForward("show");
	}
}
