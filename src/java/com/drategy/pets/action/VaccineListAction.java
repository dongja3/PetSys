//$Source: /petSys/petSys/src/java/com/drategy/pets/action/VaccineListAction.java,v $
//LasterModified By:$Author: jason.jiang $
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
import com.drategy.pets.domain.Pet;
import com.drategy.pets.exception.HibernatePageException;
import com.drategy.pets.springservice.ConfigService;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;

/**
* 
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/
public class VaccineListAction extends BaseDispatchAction{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

    	/***取得request的参数*/
        String petId = request.getParameter("petId"); 
        
    	 /**创建对象**/
        ConfigService configService = (ConfigService)Global.getInstance().getService("configService");
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**取得搜索的字段**/
        MapWraper searchFields =configService.getSearchFields();
        
        /**搜索语句产生器**/        
        SearchGenertor  searchGenertor =(SearchGenertor)Global.getInstance().getService("searchGenertor");  
        searchGenertor.setSearchFields(searchFields.getValue("vaccine").toString());
        searchGenertor.setEqualsParater("v","pet.id",Hibernate.STRING,petId,"petId",petId);
        
        /**hibernate 分页**/    
        HibernatePage hPage = new HibernatePage(request,response);          
        String pageSize = searchFields.getValue("vaccinePageSize").toString();
        
        /**设置每页记录数**/
        hPage.setPageSize(Integer.parseInt(pageSize));
       
        try{
            hPage.init(searchGenertor.getQueryString(),searchGenertor.getFieldDBValues(),searchGenertor.getFieldDBTypes());
        }catch(HibernatePageException ex ){
            SystemLogger.error("VaccineListAction 错误："+ex.toString());
        }
        List vaccineList = hPage.getCurrentResult();        
        
        /**查找pet**/
        Pet pet = structService.findPet(petId);
         
        //保存User列表属性
        request.setAttribute("vaccineList",vaccineList);
        request.setAttribute("chipNo",pet.getRfidChip().getCode());
        
        //保存分页信息属性
        request.setAttribute("hPage",hPage);
        
        //保存页面号属性
        request.setAttribute("params", searchGenertor.getParameter());
        
        //页面转向          
        return mapping.findForward("success");
    }
}
