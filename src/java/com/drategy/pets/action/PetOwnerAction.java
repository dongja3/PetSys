//$Source: /petSys/petSys/src/java/com/drategy/pets/action/PetOwnerAction.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.bom.Address;
import com.drategy.pets.bom.Phone;
import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.PetOwner;
import com.drategy.pets.domain.User;
import com.drategy.pets.form.PetOwnerForm;
import com.drategy.pets.security.SystemSecurity;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.10 $
 */
public class PetOwnerAction extends BaseDispatchAction {
    
    /**
     * 返回版本检测信息
     * @return
     */
    private String securityVerify(){
        
        //创建版本安全检测类
        SystemSecurity systemSecurity = (SystemSecurity) Global.getInstance().getService("systemSecurity");      
        
        //验证有效性
        String returnInfo = systemSecurity.securityVerify(Constant.CONFIG_PATH,Configuration.getAppConfig().findConfigs("security").find("fileName"));
       
        //返回信息
        return returnInfo;
        
    }
    
    
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        //得到安全信息
	    String securityInfo = securityVerify();  
	    
	    //根据信息返回
	    if(securityInfo.equals(SystemSecurity.USER_NOT_AVAILABLE)){
	        
	        //返回版本提示信息
			return mapping.findForward("illegalCopy");
			
	    }else if(securityInfo.equals(SystemSecurity.USER_DATE_OVER)){
	        
	        //返回过期信息
			return mapping.findForward("expiredApp");
	    }
	    
	    
		/** 取得当前的操作用户 */
		User user = (User) super.getSessionUser(request);

		/** 得到petOwnerForm对象* */
		PetOwnerForm petOwnerForm = (PetOwnerForm) form;

		/** 创建一个petOwner对象* */
		PetOwner petOwner = new PetOwner();
		
		//是否开通短信
		String smsService = petOwnerForm.getSmsService();
		
		//如果是开通服务
		if(smsService.equals("Y")){
		    
		    //开始时间或者结束时间就不能为空
		    if ((petOwnerForm.getStartTime().length() == 0 || petOwnerForm.getEndTime().length() == 0)) {
				ActionErrors errors = new ActionErrors();
				errors.add("owner.time.notMatch", new ActionError(
						"owner.time.notMatch", null));
				super.saveErrors(request, errors);
				return mapping.getInputForward();
			}

		    
		    //如果开通了短信服务，手机号码就不能为空
		    if(petOwnerForm.getMobile().trim().equals("")||petOwnerForm.getMobile()==null){

				ActionErrors errors = new ActionErrors();
				errors.add("owner.mobile.required", new ActionError(
						"owner.mobile.required", null));
				super.saveErrors(request, errors);
				return mapping.getInputForward();
		    }
		}
		
		

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(petOwner, petOwnerForm);
		} catch (Exception ex) {
			SystemLogger.error("petOwnerAction method add 拷贝属性错误:"
					+ ex.toString());
		}

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 取得身份证号 */
		String residentID = petOwnerForm.getResidentID();

		PetOwner tempPetOwner = structService
				.findPetOwnerByResidentID(residentID);

		if ((tempPetOwner instanceof PetOwner)) {
			ActionErrors errors = new ActionErrors();
			errors.add("error.petOwnerForm.residentIDExist", new ActionError(
					"error.petOwnerForm.residentIDExist"));
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		petOwner.setId(Tools.genResourceId(""));

		/** 创建对象* */
		java.util.Set addrSet = new HashSet();

		/** 创建地址* */
		Address address = new Address();
		address.setDetailAddress(petOwnerForm.getAddr());
		address.setId(Tools.genResourceId(""));
		structService.addAddress(address);
		addrSet.add(address);

		/** 创建联系电话* */
		Phone phone = new Phone();
		phone.setCompanyTelephone(petOwnerForm.getOfficephone());
		phone.setHomeTelephone(petOwnerForm.getFamilyphone());
		phone.setMobileTelephone(petOwnerForm.getMobile());
		phone.setId(Tools.genResourceId(""));

		/** 保存对象* */
		/** 所在的区域 */
		Area currArea = structService.findArea(petOwnerForm.getAreaId());
		petOwner.setArea(currArea);
		petOwner.setPhone(phone);
		petOwner.setAddrSet(addrSet);
		structService.addPhone(phone);
		structService.addPetOwner(petOwner);

		/** 设置request属性* */
		request.setAttribute("method", petOwnerForm.getMethod());

		/** 成功后转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 得到petOwnerForm对象* */
		PetOwnerForm petOwnerForm = (PetOwnerForm) form;

		/** 取得petOwnerId* */
		String petOwnerId = petOwnerForm.getId();
         
        //是否开通短信
		String smsService = petOwnerForm.getSmsService();
		
        //如果是开通服务
		if(smsService.equals("Y")){
		    
		    //开始时间或者结束时间就不能为空
		    if ((petOwnerForm.getStartTime().length() == 0 || petOwnerForm.getEndTime().length() == 0)) {
				ActionErrors errors = new ActionErrors();
				errors.add("owner.time.notMatch", new ActionError(
						"owner.time.notMatch", null));
				super.saveErrors(request, errors);
				return mapping.getInputForward();
			}

		    
		    //如果开通了短信服务，手机号码就不能为空
		    if(petOwnerForm.getMobile().trim().equals("")||petOwnerForm.getMobile()==null){

				ActionErrors errors = new ActionErrors();
				errors.add("owner.mobile.required", new ActionError(
						"owner.mobile.required", null));
				super.saveErrors(request, errors);
				return mapping.getInputForward();
		    }
		}

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 取得一个petOwner对象* */
		PetOwner petOwner = structService.findPetOwner(petOwnerId);

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(petOwner, petOwnerForm);
		} catch (Exception ex) {
			SystemLogger.error("petOwnerAction method edit 拷贝属性错误:"
					+ ex.toString());
		}

		/** 取得地址* */
		Set addrSet = petOwner.getAddrSet();
		Iterator itemAddr = addrSet.iterator();

		Address addr = new Address();

		/** 如果有地址对象* */
		if (itemAddr.hasNext()) {
			addr = (Address) itemAddr.next();
			addr.setDetailAddress(petOwnerForm.getAddr());
		}

		/** 保存地址* */
		structService.updateAddress(addr);

		/** 创建对象* */
		java.util.Set addrTempSet = new HashSet();
		addrTempSet.add(addr);

		/** 创建联系电话* */
		Phone phone = petOwner.getPhone();
		phone.setCompanyTelephone(petOwnerForm.getOfficephone());
		phone.setHomeTelephone(petOwnerForm.getFamilyphone());
		phone.setMobileTelephone(petOwnerForm.getMobile());
        
		/** 所在的区域 */
		Area currArea = structService.findArea(petOwnerForm.getAreaId());
		petOwner.setArea(currArea);
		
		/** 保存对象* */
		petOwner.setPhone(phone);
		petOwner.setAddrSet(addrSet);
		structService.updatePhone(phone);
		structService.addPetOwner(petOwner);

		/** 设置request属性* */
		request.setAttribute("method", petOwnerForm.getMethod());
		request.setAttribute("petOwnerId", petOwner.getId());

		/** 成功后转向* */
		return mapping.findForward("success");
	}

}
