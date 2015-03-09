//$Source: /petSys/petSys/src/java/com/drategy/pets/action/PetAction.java,v $
//LasterModified By:$Author: jason.jiang $
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
import com.drategy.pets.util.Tools;
import com.drategy.pets.bom.Image;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.*;
import com.drategy.pets.form.PetForm;

/**
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.13 $
 */
public class PetAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 得到一个acitonform* */
		PetForm petForm = (PetForm) form;

		/** 创建一个pet对象* */
		Pet pet = new Pet();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(pet, petForm);
		} catch (Exception ex) {
			SystemLogger.error("petAction method add 拷贝属性错误:" + ex.toString());
		}

		/** 得到petOwner的id* */
		String petOwnerId = petForm.getPetOwnerId();
		String chipNo = petForm.getRfidNo();
		String petNo = petForm.getPetNo();

		RfidChip rfidChip = new RfidChip();

		rfidChip.setId(Tools.genResourceId(""));
		rfidChip.setCode(chipNo);
		rfidChip.setUsing(RfidChip.USING_YES);

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 查看该chipId是否已经存在* */
		if (structService.findRfidChipbyCode(chipNo) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.chipNo.exist", new ActionError(
					"errors.chipNo.exist", null));
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		/** 查看该petNo是否已经存在* */
		if (structService.findPetByPetNo(petNo) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.petNo.exist", new ActionError(
					"errors.petNo.exist", null));
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		/** 查看品种的名字是否存在* */
		String varietyName = petForm.getVarietyName();
		Variety variety = structService.findVarietyByName(varietyName);

		// 如果品种不存在，那么创建
		if (variety == null) {
			Variety tVariety = new Variety();
			tVariety.setId(Tools.genResourceId(""));
			tVariety.setName(varietyName);
			structService.addVariety(tVariety);
			pet.setVariety(tVariety);
		} else {
			pet.setVariety(variety);
		}
		

		/** 查找petOwner* */
		PetOwner petOwner = structService.findPetOwner(petOwnerId);

		/** 创建pet图片* */
		Image image = new Image();
		image.setId(Tools.genResourceId(""));
		/** 保存image* */
		setImageData(petForm, image);

		/** 保存certificcate* */
		Image certificateImage = new Image();
		certificateImage.setId(Tools.genResourceId(""));
		setCertificateImageData(petForm, certificateImage);

		/** 设置id* */
		pet.setId(Tools.genResourceId(""));
		pet.setCertificate(certificateImage);
		pet.setPetImage(image);
		pet.setPetOwner(petOwner);
		pet.setRfidChip(rfidChip);
		
		/** 保存rfidChip号码* */
		structService.addRfidChip(rfidChip);
		structService.addImage(image);
		structService.addImage(certificateImage);

		/** 保存pet对象* */
		structService.addPet(pet);

		/** 设置request属性* */
		request.setAttribute("method", petForm.getMethod());
		request.setAttribute("petOwnerId", petForm.getPetOwnerId());

		/** 成功后转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 得到一个acitonform* */
		PetForm petForm = (PetForm) form;
		String petId = petForm.getId();

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		String petNo = petForm.getPetNo();
		/** 查看该petNo是否已经存在* */
		if (structService.findPetByPetNo(petNo, petId) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.petNo.exist", new ActionError(
					"errors.petNo.exist", null));
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		/** 创建一个pet对象* */
		Pet pet = structService.findPet(petForm.getId());

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(pet, petForm);
		} catch (Exception ex) {
			SystemLogger.error("petAction method add 拷贝属性错误:" + ex.toString());
		}

		/** 设置芯片号码* */
		RfidChip rfidChip = pet.getRfidChip();
		rfidChip.setCode(petForm.getRfidNo());

		/** 创建pet图片* */
		Image petImage = pet.getPetImage();

		/** 设置image* */
		setImageData(petForm, petImage);

		/** 保存certificcate* */
		Image certificateImage = pet.getCertificate();
		setCertificateImageData(petForm, certificateImage);

		/** 保存rfidChip号码* */
		structService.updateRfidChip(rfidChip);
		structService.updateImage(petImage);
		structService.updateImage(certificateImage);

		/** 设置属性* */
		pet.setRfidChip(rfidChip);

		/** 查看品种的名字是否存在* */
		String varietyName = petForm.getVarietyName();
		Variety variety = structService.findVarietyByName(varietyName);

		// 如果品种不存在，那么创建
		if (variety == null) {
			Variety tVariety = new Variety();
			tVariety.setId(Tools.genResourceId(""));
			tVariety.setName(varietyName);
			structService.addVariety(tVariety);
			pet.setVariety(tVariety);
		} else {
			pet.setVariety(variety);
		}

		/** 保存pet对象* */
		structService.updatePet(pet);

		/** 设置request属性* */
		request.setAttribute("method", petForm.getMethod());
		request.setAttribute("petId", petForm.getId());

		/** 成功后转向* */
		return mapping.findForward("success");
	}

	/***************************************************************************
	 * 保存宠物图片信息
	 * 
	 * @param petForm
	 * @param image
	 */
	private void setImageData(PetForm petForm, Image image) {
		try {
			if (petForm.getPetImageUpFile() != null
					&& petForm.getPetImageUpFile().getFileData().length > 0) {
				image
						.setImageContent(petForm.getPetImageUpFile()
								.getFileData());
				image.setImageType(Tools.getFileSuffix(petForm
						.getPetImageUpFile().getFileName()));
				image.setImageName(petForm.getPetImageUpFile().getFileName()
						.substring(0, 32));
			}
		} catch (Exception ex) {

		}
	}

	/***************************************************************************
	 * 保存证书信息
	 * 
	 * @param petForm
	 * @param image
	 */
	private void setCertificateImageData(PetForm petForm, Image image) {
		try {
			if (petForm.getPetCertificate() != null
					&& petForm.getPetCertificate().getFileData().length > 0) {
				image
						.setImageContent(petForm.getPetCertificate()
								.getFileData());
				image.setImageType(Tools.getFileSuffix(petForm
						.getPetCertificate().getFileName()));
				image.setImageName(petForm.getPetCertificate().getFileName()
						.substring(0, 32));
			}
		} catch (Exception ex) {

		}
	}
}
