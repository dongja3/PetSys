package com.drategy.pets.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.Hibernate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.biz.SearchGenertor;
import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Global;
import com.drategy.pets.context.HibernatePage;
import com.drategy.pets.context.MapWraper;
import com.drategy.pets.domain.Area;
import com.drategy.pets.exception.HibernatePageException;
import com.drategy.pets.springservice.ConfigService;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

public class PartnerShipAction extends BaseDispatchAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** *取得request的参数 */
		String areaCode = request.getParameter("area");

		String tempCode = areaCode;

		/** 创建对象* */
		ConfigService configService = (ConfigService) Global.getInstance()
				.getService("configService");
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 找到当前Area */

		if (!areaCode.toLowerCase().equals("all")) {

			Area tempArea = structService.findAreaByCode(tempCode);
			
			SystemLogger.debug("Area Code:"+tempArea.getAreaCode() );
			
			/** 判断是级别 */
			int areaLength = Integer.parseInt(Configuration.getAppConfig()
					.findConfigs("statConfig").find("areaLength"));

			if (tempArea.getFather().getId().equals(Area.ROOT_AREA)) {
				/** 顶级 */
				tempCode = tempCode.substring(0, areaLength * 1);
				SystemLogger.debug("tempCode:" + tempCode);

			} else if (tempArea.getFather().getFather().getId().equals(
					Area.ROOT_AREA)) {
				/** 次级 */
				tempCode = tempCode.substring(0, areaLength * 2);
				SystemLogger.debug("tempCode:" + tempCode);

			} else if (tempArea.getFather().getFather().getFather().getId()
					.equals(Area.ROOT_AREA)) {
				/** 再级 */
				tempCode = tempCode.substring(0, areaLength * 3);
				SystemLogger.debug("tempCode:" + tempCode);
			}

		}

		String varietyName = Tools.toChinese(request.getParameter("variety"));

		SystemLogger.debug("varietyName=" + varietyName);
		String sex = request.getParameter("sex");

		/** 取得搜索的字段* */
		MapWraper searchFields = configService.getSearchFields();

		/** 搜索语句产生器* */
		SearchGenertor searchGenertor = (SearchGenertor) Global.getInstance()
				.getService("searchGenertor");

		/** 设置产生器参数* */
		searchGenertor.setSearchFields(searchFields.getValue("pet").toString());

		searchGenertor.setEqualsParater("p", "sex", Hibernate.STRING, sex,
				"sex", sex);
		if (!varietyName.toLowerCase().equals("all")) {
			searchGenertor.setEqualsParater("p", "variety.name",
					Hibernate.STRING, varietyName, "variety", varietyName);
		}

		if (!areaCode.toLowerCase().equals("all")) {
			searchGenertor.setLikeParater("p", "petOwner.area.areaCode",
					Hibernate.STRING, tempCode, "area", tempCode);
		}
		/** hibernate 分页* */
		HibernatePage hPage = new HibernatePage(request, response);
		String pageSize = searchFields.getValue("petPageSize").toString();

		/** 设置每页参数* */
		hPage.setPageSize(Integer.parseInt(pageSize));

		try {
			hPage.init(searchGenertor.getQueryString(), searchGenertor
					.getFieldDBValues(), searchGenertor.getFieldDBTypes());
		} catch (HibernatePageException ex) {
			SystemLogger.error("PetListAction 错误：" + ex.toString());
		}
		hPage.getCurrentResult();
		List petList = hPage.getCurrentResult();

		/** 保存属性* */
		request.setAttribute("petList", petList);

		/** 保存分页信息属性* */
		request.setAttribute("hPage", hPage);

		/** 保存页面号属性* */
		request.setAttribute("params", searchGenertor.getParameter());

		request.setAttribute("areaCode", areaCode);
		request.setAttribute("varietyName", varietyName);
		request.setAttribute("sex", sex);

		/** 页面转向* */
		return mapping.findForward("success");

	}

}
