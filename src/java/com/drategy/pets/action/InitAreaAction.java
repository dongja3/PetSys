//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitAreaAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/01/11 06:52:37 $
package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;

import com.drategy.pets.form.AreaForm;
import com.drategy.pets.form.BaseForm;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;

/**
 * InitAreaAction
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.12 $
 */

public final class InitAreaAction extends BaseDispatchAction {
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 创建AreaForm* */
		AreaForm areaForm = new AreaForm();
		
		String fatherAreaId = request.getParameter("fatherareaId");
		
		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		
		if(fatherAreaId!=null&&fatherAreaId.length()>0){
			Area fatherArea =structService.findArea(fatherAreaId);
			
			/**查找fatherArea，如果传入的Id错误就找不到对应的Area*/
			if(fatherArea==null){
				request.setAttribute("result", new String("noFather"));

				/** 页面转向* */
				return mapping.findForward("fail");
			}
			
			areaForm.setFatherAreaName(fatherArea.getName());
		}

		/** 设置属性* */
		areaForm.setMethod("add");
		
		if(fatherAreaId==null||fatherAreaId.length()==0){
			areaForm.setFatherId("");
		}else{
			areaForm.setFatherId(fatherAreaId);
		}
		

		/** 设置requst属性* */
		request.setAttribute("areaForm", areaForm);

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 得到参数id* */
		String id = request.getParameter("areaId");

		/** 创建AreaForm* */
		AreaForm areaForm = new AreaForm();

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 创建Area对象* */
		Area area = structService.findArea(id);

		try {
			BeanUtils.copyProperties(areaForm, area);
		} catch (Exception ex) {
			SystemLogger.error("initAreaAction edit method 拷贝属性错误:"
					+ ex.toString());
		}

		areaForm.setMethod(BaseForm.METHOD_EDIT);
		

		// 如果area有父亲
		if (area.getFather() != null){
			areaForm.setFatherId(area.getFather().getId());
			areaForm.setFatherAreaName(area.getFather().getName());
		}
		/** 设置request属性* */
		request.setAttribute("areaForm", areaForm);

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 得到参数id* */
		String areaId = request.getParameter("areaId");

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 取得Area* */
		Area area = structService.findArea(areaId);

		if (structService.findUserByAreaId(areaId) != null) {
			
			request.setAttribute("result", new String("areaAssigned"));

			/** 页面转向* */
			return mapping.findForward("fail");
		}

		// 如果area的child不为空的话！
		if (area.getChilds().size() != 0) {

			request.setAttribute("result", new String("childNotEmpty"));

			/** 页面转向* */
			return mapping.findForward("fail");
		}

		/** 删除对象* */
		structService.deleteArea(area);

//		// 更新内存中的areaList
		Constant.deleteArea(area);

		request.setAttribute("result", new String("delete"));

		/** 页面转向* */
		return mapping.findForward("delete");
	}

}
