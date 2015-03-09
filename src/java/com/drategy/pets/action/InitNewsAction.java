//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitNewsAction.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/01/16 08:45:55 $
package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Global;
import com.drategy.pets.domain.News;

import com.drategy.pets.form.BaseForm;
import com.drategy.pets.form.NewsForm;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;

/**
 * InitNewsAction
 * 
 * @author Jackie Dong
 * @author $Author: jason.jiang $
 * @$Revision: 1.2 $
 */

public final class InitNewsAction extends BaseDispatchAction {
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 创建NewsForm* */
		NewsForm newsForm = new NewsForm();
		
		/** 设置属性* */
		newsForm.setMethod(BaseForm.METHOD_ADD);

		/** 设置requst属性* */
		request.setAttribute("newsForm", newsForm);

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 得到参数id* */
		String id = request.getParameter("id");
		
		SystemLogger.debug("NewsId to edit:" + id);

		/** 创建NewsForm* */
		NewsForm newsForm = new NewsForm();

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 创建News对象* */
		News news = structService.findNews(id);

		try {
			BeanUtils.copyProperties(newsForm, news);
		} catch (Exception ex) {
			SystemLogger.error("initNewsAction edit method 拷贝属性错误:"
					+ ex.toString());
		}

		newsForm.setMethod(BaseForm.METHOD_EDIT);
		/** 设置request属性* */
		request.setAttribute("newsForm", newsForm);

		/** 页面转向* */
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 得到参数id* */
		String id = request.getParameter("id");

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 取得news* */
		News news = structService.findNews(id);

		/** 删除对象* */
		structService.deleteNews(news);

		request.setAttribute("result", new String("delete"));

		/** 页面转向* */
		return mapping.findForward("delete");
	}

}
