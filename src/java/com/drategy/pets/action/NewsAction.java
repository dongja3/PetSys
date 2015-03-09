//$Source: /petSys/petSys/src/java/com/drategy/pets/action/NewsAction.java,v $
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
import com.drategy.pets.util.Tools;

/**
 * NewsAction 所有与News有关操作
 * 
 * @author Jackie Dong
 * @author $Author: jason.jiang $
 * @$Revision: 1.3 $
 */
public class NewsAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 得到ActionForm* */
		NewsForm newsForm = (NewsForm) (form);

		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		
		String title = newsForm.getTitle();
		SystemLogger.debug("Title Length:" + title.length());
		if(title.length()>200){
			title=title.substring(0,199)+"...";
		}

		/** 创建一个News对象* */
		News news = new News();

		try {
			/** 复制属性* */
			BeanUtils.copyProperties(news, newsForm);
		} catch (Exception ex) {
			SystemLogger.error("NewsAction add method 拷贝属性出错:" + ex.toString());
		}

		news.setId(Tools.genResourceId(""));

		structService.addNews(news);

		/** 设置request属性* */
		request.setAttribute("method", BaseForm.METHOD_ADD);

		/** 操作成功返回jsp页面* */
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 得到ActionForm* */
		NewsForm newsForm = (NewsForm) (form);

		/** 创建服务* */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		
		String title = newsForm.getTitle();
		SystemLogger.debug("Title Length:" + title.length());
		if(title.length()>200){
			title=title.substring(0,199)+"...";
		}

		News news = new News();

		/** 设置属性* */
		try {
			BeanUtils.copyProperties(news, newsForm);
		} catch (Exception ex) {
			SystemLogger
					.error("NewsAction method edit 拷贝属性错误:" + ex.toString());
		}

		structService.updateNews(news);

		/** 设置request参数* */
		request.setAttribute("method", BaseForm.METHOD_EDIT);

		/** 成功后转向* */
		return mapping.findForward("success");

	}

}