//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitVaccineAction.java,v $
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
import com.drategy.pets.form.VaccineRecordForm;

/**
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.11 $
 */
public class InitVaccineAction extends BaseDispatchAction {

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
		VaccineRecordForm vaccineRecordForm = new VaccineRecordForm();

		/** 设置属性* */
		vaccineRecordForm.setMethod("add");
		vaccineRecordForm.setPetId(pet.getId());

		/** 设置request属性 * */
		request.setAttribute("vaccineRecordForm", vaccineRecordForm);
		request.setAttribute("pet", pet);
		request.setAttribute("chipNo", chipNo);
		request.setAttribute("petImageId", pet.getPetImage().getId());
		request.setAttribute("variety",pet.getVariety().getName());

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request参数* */
		String vaccineId = request.getParameter("vaccineId");

		/** 保存对象* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		Vaccine vaccine = structService.findVaccine(vaccineId);

		/** 创建actionForm对象* */
		VaccineRecordForm vaccineRecordForm = new VaccineRecordForm();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(vaccineRecordForm, vaccine);
		} catch (Exception ex) {
			SystemLogger.error("InitVaccineAction Error");
		}

		/** 设置属性* */
		vaccineRecordForm.setMethod(BaseForm.METHOD_EDIT);
		vaccineRecordForm.setPetId(vaccine.getPet().getId());

		/** 设置request属性 * */
		request.setAttribute("vaccineRecordForm", vaccineRecordForm);
		request.setAttribute("pet", vaccine.getPet());
		request.setAttribute("chipNo", vaccine.getPet().getRfidChip().getCode());
		request.setAttribute("petImageId", vaccine.getPet().getPetImage()
				.getId());
		request.setAttribute("variety",vaccine.getPet().getVariety().getName());
		
		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request参数* */
		String vaccineId = request.getParameter("vaccineId");

		/** 删除对象* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		Vaccine vaccine = structService.findVaccine(vaccineId);
		structService.deleteVaccine(vaccine);

		/** 设置request属性 * */
		request.setAttribute("petId", vaccine.getPet().getId());
		request
				.setAttribute("chipNo", vaccine.getPet().getRfidChip()
						.getCode());
		request.setAttribute("method", "delete");

		/** 页面转向* */
		return mapping.findForward("delete");
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得request参数* */
		String vaccineId = request.getParameter("vaccineId");

		/** 保存对象* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		Vaccine vaccine = structService.findVaccine(vaccineId);


		/** 设置request属性 * */
		request.setAttribute("vaccine", vaccine);
		request.setAttribute("pet", vaccine.getPet());
		request.setAttribute("chipNo", vaccine.getPet().getRfidChip().getCode());
		request.setAttribute("petImageId", vaccine.getPet().getPetImage()
				.getId());
		request.setAttribute("variety",vaccine.getPet().getVariety().getName());
		
		/** 页面转向* */
		return mapping.findForward("show");
	}

}