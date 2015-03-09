//$Source: /petSys/petSys/src/java/com/drategy/pets/action/SmsListAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.biz.SearchGenertor;
import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.context.HibernatePage;
import com.drategy.pets.context.MapWraper;
import com.drategy.pets.exception.HibernatePageException;
import com.drategy.pets.springservice.ConfigService;
import com.drategy.pets.util.SystemLogger;

/**
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.3 $
 */
public class SmsListAction extends BaseDispatchAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 创建对象* */
		ConfigService configService = (ConfigService) Global.getInstance()
				.getService("configService");

		/** 取得搜索的字段* */
		MapWraper searchFields = configService.getSearchFields();

		/** 搜索语句产生器* */
		SearchGenertor searchGenertor = (SearchGenertor) Global.getInstance()
				.getService("searchGenertor");
		searchGenertor.setSearchFields(searchFields.getValue("sms").toString());

		/** hibernate 分页* */
		HibernatePage hPage = new HibernatePage(request, response);
		String pageSize = searchFields.getValue("smsPageSize").toString();
		/** 设置每页记录数* */
		hPage.setPageSize(Integer.parseInt(pageSize));

		try {
			hPage.init(searchGenertor.getQueryString(), searchGenertor
					.getFieldDBValues(), searchGenertor.getFieldDBTypes());
		} catch (HibernatePageException ex) {
			SystemLogger.error("SmsListAction 错误：" + ex.toString());
		}
		List smsList = hPage.getCurrentResult();

		// 保存列表属性
		request.setAttribute("smsList", smsList);

		// 获得AreaList
		List areaList = new ArrayList();
		areaList = Constant.getAllArea();
		request.setAttribute("areaList", areaList);

		// 保存分页信息属性
		request.setAttribute("hPage", hPage);

		// 保存页面号属性
		request.setAttribute("params", searchGenertor.getParameter());

		// 页面转向
		return mapping.findForward("success");

	}
}