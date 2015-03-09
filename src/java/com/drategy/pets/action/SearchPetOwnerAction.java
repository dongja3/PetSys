//$Source: /petSys/petSys/src/java/com/drategy/pets/action/SearchPetOwnerAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $

package com.drategy.pets.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.Hibernate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.biz.SearchGenertor;
import com.drategy.pets.context.Global;
import com.drategy.pets.context.HibernatePage;
import com.drategy.pets.context.MapWraper;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.User;
import com.drategy.pets.exception.HibernatePageException;
import com.drategy.pets.springservice.ConfigService;
import com.drategy.pets.util.SystemLogger;

/**
 * 查找petOwner的action
 * 
 * @author JackieDong
 * @author $Author: jackie.dong $
 * @$Revision: 1.1 $
 */

public class SearchPetOwnerAction extends BaseDispatchAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 取得当前的操作用户 */
		User user = (User) super.getSessionUser(request);
		

		/** 所在的区域 */
		Area currArea = user.getArea();

		/** 创建对象* */
		ConfigService configService = (ConfigService) Global.getInstance()
				.getService("configService");

		/** 取得搜索的字段* */
		MapWraper searchFields = configService.getSearchFields();

		/** 搜索语句产生器* */
		SearchGenertor searchGenertor = (SearchGenertor) Global.getInstance()
				.getService("searchGenertor");
		searchGenertor.setSearchFields(searchFields.getValue("petOwner")
				.toString());

		/** 设置搜索条件 */
		if (!(user.getId().equals("admin"))) {
			searchGenertor.setLikeParater("po", "area.areaCode",
					Hibernate.STRING, currArea.getAreaCode(), "areaCode",
					currArea.getAreaCode());
		}

		/** 设置排序 */
		searchGenertor.setOrderByString(" order by po.id desc");

		/** hibernate 分页* */
		HibernatePage hPage = new HibernatePage(request, response);
		hPage.setObjectName("pet owner");
		String pageSize = searchFields.getValue("petOwnerPageSize").toString();

		/** 设置每页记录数* */
		hPage.setPageSize(Integer.parseInt(pageSize));

		try {
			hPage.init(searchGenertor.getQueryString(), searchGenertor
					.getFieldDBValues(), searchGenertor.getFieldDBTypes());
		} catch (HibernatePageException ex) {
			SystemLogger.error("PetOwnerListAction 错误：" + ex.toString());
		}
		List ownerList = hPage.getCurrentResult();

		/** 保存属性* */
		request.setAttribute("ownerList", ownerList);

		/** 保存分页信息属性* */
		request.setAttribute("hPage", hPage);

		/** 保存页面号属性* */
		request.setAttribute("params", searchGenertor.getParameter());

		/** 页面转向* */
		return mapping.findForward("success");
	}
}
