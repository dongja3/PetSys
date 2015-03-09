//$Source: /petSys/petSys/src/java/com/drategy/pets/action/SearchPetAction.java,v $
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
import com.drategy.pets.exception.HibernatePageException;
import com.drategy.pets.springservice.ConfigService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * 查找pet的action
 * 
 * @author JackieDong
 * @author $Author: jackie.dong $
 * @$Revision: 1.3 $
 */

public class SearchPetAction extends BaseDispatchAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    /***取得request的参数*/
	    String petNo = Tools.toChinese(request.getParameter("petNo")); 
        String rfidNo = Tools.toChinese( request.getParameter("rfidNo"));
        
        /**创建对象**/
        ConfigService configService = (ConfigService)Global.getInstance().getService("configService");
        
        /**取得搜索的字段**/
        MapWraper searchFields =configService.getSearchFields();
        
        /**搜索语句产生器**/        
        SearchGenertor  searchGenertor =(SearchGenertor)Global.getInstance().getService("searchGenertor");  
        
        /**设置产生器参数**/       
        searchGenertor.setSearchFields(searchFields.getValue("pet").toString());
        
        if(!Tools.isNullOrEmpty(petNo)){
            searchGenertor.setLikeParater("p","petNo",Hibernate.STRING,petNo ,"petNo",petNo);
        }
        if(!Tools.isNullOrEmpty(rfidNo)){
            searchGenertor.setLikeParater("p","rfidChip.code",Hibernate.STRING,rfidNo,"rfidNo",rfidNo);
        }
        
        /**hibernate 分页**/    
        HibernatePage hPage = new HibernatePage(request,response);
        String pageSize = searchFields.getValue("petPageSize").toString();
        
        /**设置每页参数**/
        hPage.setPageSize(Integer.parseInt(pageSize));
        
        try{
            hPage.init(searchGenertor.getQueryString(),searchGenertor.getFieldDBValues(),searchGenertor.getFieldDBTypes());
        }catch(HibernatePageException ex ){
            SystemLogger.error("SearchPetAction 错误："+ex.toString());
        }
        hPage.getCurrentResult(); 
        List petList = hPage.getCurrentResult(); 
 
        /**保存属性**/     
        request.setAttribute("petList",petList); 
        
        /**页面转向**/          
        return mapping.findForward("success");

	}
}
