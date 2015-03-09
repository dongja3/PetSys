//$Source: /petSys/petSys/src/java/com/drategy/pets/action/NewsInfoAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Global;
import com.drategy.pets.domain.News;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.Tools;

/**
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.2 $
 */
public class NewsInfoAction extends BaseDispatchAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		News news = structService.findNews(id);
		
		String content = Tools.convert2HTML(news.getContent());
		
		news.setContent(content);

		/** 设置request参数* */
		request.setAttribute("news", news);
		
		

		/** 成功后转向* */
		return mapping.findForward("success");
	}
}
