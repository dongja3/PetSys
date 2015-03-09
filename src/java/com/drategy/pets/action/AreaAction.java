//$Source: /petSys/petSys/src/java/com/drategy/pets/action/AreaAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 03:13:01 $

package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;
import com.drategy.pets.form.AreaForm;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * AreaAction 所有与Area有关操作
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.17 $
 */
public class AreaAction extends BaseDispatchAction {
     
    
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    	    
		/** 得到ActionForm* */
		AreaForm areaForm = (AreaForm) (form);

		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 查看数据库中是否存在相同名字的Area，如果相同，抛出错误 */
		String areaName = areaForm.getName();
		if ((Area) structService.findAreaByName(areaName) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.area.exist", new ActionError(
					"errors.area.name.exist", null));
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		/** 查看数据库中是否存在相同的AreaCode，如果相同，抛出错误 */
		String areaCode = areaForm.getAreaCode();
		if ((Area) structService.findAreaByCode(areaCode) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.area.exist", new ActionError(
					"errors.area.code.exist", null));
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		/** 创建一个Area对象* */
		Area area = new Area();

		/** 设置属性值* */

		try {
			/** 复制属性* */
			BeanUtils.copyProperties(area, areaForm);
		} catch (Exception ex) {
			SystemLogger.error("AreaAction [add] 拷贝属性出错:" + ex.toString());
		}

		area.setId(Tools.genResourceId(""));

		/** 保存area对象* */
		String fatherId = areaForm.getFatherId();

		/** 如果设置的fatherId是空那么其father应该设置为空 */
		if (fatherId.equals("")) {
			area.setFather(null);
			request.setAttribute("fatherArea", "");
		} else {
			Area fatherArea = structService.findArea(fatherId);
			String tempFatherAreaCode = fatherArea.getAreaCode();
			String tempChildAreaCode = "" ;
			int areaLength = Integer.parseInt(Configuration.getAppConfig().findConfigs("statConfig").find("areaLength"));
            
			if(fatherArea.getId().equals(Area.ROOT_AREA)){
			    area.setFather(fatherArea);
    			request.setAttribute("fatherArea", fatherArea.getName());
			}else{
	            if(fatherArea.getFather().getId().equals(Area.ROOT_AREA)){
	                /**顶级*/
	                tempFatherAreaCode = tempFatherAreaCode.substring(0,areaLength * 1);                
	                tempChildAreaCode =  areaCode.substring(0,areaLength * 1);
	            }else if(fatherArea.getFather().getFather().getId().equals(Area.ROOT_AREA)){
	                /**次级*/
	                tempFatherAreaCode = tempFatherAreaCode.substring(0,areaLength * 2);
	                tempChildAreaCode =  areaCode.substring(0,areaLength * 2);
	                
	            }else if(fatherArea.getFather().getFather().getFather().getId().equals(Area.ROOT_AREA)){
	                /**再级*/
	                tempFatherAreaCode = tempFatherAreaCode.substring(0,areaLength * 3);
	                tempChildAreaCode =  areaCode.substring(0,areaLength * 3);
	            }
				
	            /**判断是否可以是父子关系*/
	            if(tempFatherAreaCode.equals(tempChildAreaCode)){
	                area.setFather(fatherArea);
	    			request.setAttribute("fatherArea", fatherArea.getName());
	            }else{
	                ActionErrors errors = new ActionErrors();
	    			errors.add("errors.area.exist", new ActionError(
	    					"errors.area.errorFather", null));
	    			super.saveErrors(request, errors);
	    			return mapping.getInputForward();
	            }
            }
			
		}

		structService.addArea(area);

		// 更新内存中的areaList
		Constant.updateAreaList(area);

		// 获得所有的Area的List
		 request.setAttribute("areaList", Constant.getAllArea());

		/** 设置request属性* */
		request.setAttribute("method", "add");

		/** 操作成功返回jsp页面* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	      
	    
		/** 得到ActionForm* */
		AreaForm areaForm = (AreaForm) (form);

		/** 取得areaId* */
		String areaId = areaForm.getId();

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 取得一个Area对象* */
		Area area = structService.findArea(areaId);

		String fatherId = areaForm.getFatherId();

		/** 如果设置的fatherId是空那么其father应该设置为空 */
		if (fatherId.equals("")) {
			area.setFather(null);
			request.setAttribute("fatherArea", "");
		} else {
			Area fatherArea = structService.findArea(fatherId);
			area.setFather(fatherArea);
			request.setAttribute("fatherArea", fatherArea.getName());
		}

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(area, areaForm);
		} catch (Exception ex) {
			SystemLogger
					.error("AreaAction method edit 拷贝属性错误:" + ex.toString());
		}

		/** 查看数据库中是否存在相同名字的Area，如果相同，抛出错误 */
		String areaName = areaForm.getName();
		if ((Area) structService.findAreaByNameandId(areaName, areaId) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.area.exist", new ActionError(
					"errors.area.name.exist", null));
			super.saveErrors(request, errors);
			request.setAttribute("areaForm", areaForm);
			return mapping.getInputForward();
		}

		/** 查看数据库中是否存在相同的AreaCode，如果相同，抛出错误 */
		String areaCode = areaForm.getAreaCode();
		if ((Area) structService.findAreaByCodeandId(areaCode, areaId) != null) {
			ActionErrors errors = new ActionErrors();
			errors.add("errors.area.exist", new ActionError(
					"errors.area.code.exist", null));
			super.saveErrors(request, errors);
			request.setAttribute("areaForm", areaForm);
			return mapping.getInputForward();
		}

		structService.updateArea(area);

		/** 设置request参数* */
		request.setAttribute("method", BaseForm.METHOD_EDIT);

		// 更新内存中的areaList
		Constant.updateAreaList(area);

		/** 成功后转向* */
		return mapping.findForward("success");

	}

}