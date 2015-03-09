//$Source: /petSys/petSys/src/java/com/drategy/pets/action/PetOwnerListAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.Hibernate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.biz.SearchGenertor;
import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.context.HibernatePage;
import com.drategy.pets.context.MapWraper;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.PetOwner;
import com.drategy.pets.domain.User;
import com.drategy.pets.exception.HibernatePageException;
import com.drategy.pets.springservice.ConfigService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.10 $
 */
public class PetOwnerListAction extends BaseDispatchAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 取得request参数 */
		String petOwnerName = Tools.toChinese(request.getParameter("name"));
		String residentID = Tools.toChinese(request.getParameter("residentID"));

		if (Tools.isNullOrEmpty(petOwnerName)) {
			petOwnerName = "";
		}

		if (Tools.isNullOrEmpty(residentID)) {
			residentID = "";
		}

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
			String areaCode = "";
			int areaLength = Integer.parseInt(Configuration.getAppConfig()
					.findConfigs("statConfig").find("areaLength"));

			if (currArea.getFather().getId().equals(Area.ROOT_AREA)) {
				/** 顶级 */
				areaCode = currArea.getAreaCode().substring(0, areaLength * 1);

			} else if (currArea.getFather().getFather().getId().equals(
					Area.ROOT_AREA)) {
				/** 次级 */
				areaCode = currArea.getAreaCode().substring(0, areaLength * 2);

			} else if (currArea.getFather().getFather().getFather().getId()
					.equals(Area.ROOT_AREA)) {
				/** 再级 */
				areaCode = currArea.getAreaCode().substring(0, areaLength * 3);
			}

			searchGenertor.setLikeParater("po", "area.areaCode",
					Hibernate.STRING, areaCode, "areaCode", currArea
							.getAreaCode());
		}

		if (!Tools.isNullOrEmpty(petOwnerName)) {
			searchGenertor.setLikeParater("po", "name", Hibernate.STRING,
					petOwnerName, "name", petOwnerName);
		}

		if (!Tools.isNullOrEmpty(residentID)) {
			searchGenertor.setLikeParater("po", "residentID", Hibernate.STRING,
					residentID, "residentID", residentID);
		}

		/** 设置排序 */
		searchGenertor.setOrderByString(" order by po.id desc");

		/** hibernate 分页* */
		HibernatePage hPage = new HibernatePage(request, response);
		hPage.setObjectName("pet owner");
		String pageSize = searchFields.getValue("petOwnerPageSize").toString();

		/** 设置每页记录数* */
		hPage.setPageSize(Integer.parseInt(pageSize));

		String querString = searchGenertor.getQueryString();

		try {
			hPage.init(querString, searchGenertor.getFieldDBValues(),
					searchGenertor.getFieldDBTypes());
		} catch (HibernatePageException ex) {
			SystemLogger.error("PetOwnerListAction 错误：" + ex.toString());
		}
		
		List ownerList = hPage.getCurrentResult();
		
		/**循环将areaName赋值用于显示**/
		List returnList = new ArrayList();
		for(int i=0;i<ownerList.size();i++){
			PetOwner owner = (PetOwner)ownerList.get(i);
			owner.setAreaName(owner.getArea().getName());
			returnList.add(owner);
		}

		/** 保存属性* */
		request.setAttribute("ownerList", returnList);

		/** 保存分页信息属性* */
		request.setAttribute("hPage", hPage);

		/** 保存页面号属性* */
		request.setAttribute("params", searchGenertor.getParameter());

		/** 保存AreaList* */
		request.setAttribute("areaList", Constant.getAllArea());

		/** 页面转向* */
		return mapping.findForward("success");

	}
}