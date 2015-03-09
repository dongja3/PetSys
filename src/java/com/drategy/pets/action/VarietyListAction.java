//$Source: /petSys/petSys/src/java/com/drategy/pets/action/VarietyListAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/15 05:25:59 $
package com.drategy.pets.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Global;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;

/**
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.4 $
 */
public class VarietyListAction extends BaseDispatchAction {
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		List varietyList = structService.getAllVariety();

		request.setAttribute("varietyList", varietyList);
		SystemLogger.debug("VarietyList Size:" + varietyList.size());

		// 页面转向
		return mapping.findForward("success");

	}

	public ActionForward partnership(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		List varietyList = structService.getAllVariety();

		request.setAttribute("varietyList", varietyList);

		// 页面转向
		return mapping.findForward("partnership");
	}
}
