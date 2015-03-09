//$Source: /petSys/petSys/src/java/com/drategy/pets/action/StatResultShow.java,v $
//LasterModified By$
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
import com.drategy.pets.biz.VaccineStatSqlGenerator;
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


/**
* 系统统计结果显示action
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.3 $
*/
public class StatResultShow extends BaseDispatchAction{
    
    public ActionForward statByArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /***取得request的参数*/
        String areaCode = request.getParameter("areaCode"); 
        String sex = request.getParameter("sex"); 
        
        /**创建服务*/
        StructService structService = (StructService) Global.getInstance()
		.getService("structService");  
        
        /**找到当前Area*/
        Area tempArea = structService.findAreaByCode(areaCode);
        String tempAreaCode = "" ;
        
        /**判断是级别*/
        int areaLength = Integer.parseInt(Configuration.getAppConfig().findConfigs("statConfig").find("areaLength"));
        
        if(tempArea.getFather().getId().equals(Area.ROOT_AREA)){
            /**顶级*/
            tempAreaCode = areaCode.substring(0,areaLength * 1);
            
        }else if(tempArea.getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**次级*/
            tempAreaCode = areaCode.substring(0,areaLength * 2);
            
        }else if(tempArea.getFather().getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**再级*/
            tempAreaCode = areaCode.substring(0,areaLength * 3);
        }
        
        /**创建对象**/
        ConfigService configService = (ConfigService)Global.getInstance().getService("configService");
        
        /**取得搜索的字段**/
        MapWraper searchFields =configService.getSearchFields();
        
        /**搜索语句产生器**/        
        SearchGenertor  searchGenertor =(SearchGenertor)Global.getInstance().getService("searchGenertor");  
        
        /**设置产生器参数**/       
        searchGenertor.setSearchFields(searchFields.getValue("statByArea").toString());
        
        searchGenertor.setLikeParater("p","petOwner.area.areaCode",Hibernate.STRING,tempAreaCode,"areaCode",areaCode);
        
        if(!Tools.isNullOrEmpty(sex)){
            searchGenertor.setEqualsParater("p","sex",Hibernate.STRING,sex,"sex",sex);
        }
        /**hibernate 分页**/    
        HibernatePage hPage = new HibernatePage(request,response);
        String pageSize = searchFields.getValue("statByAreaPageSize").toString();
        
        /**设置每页参数**/
        hPage.setPageSize(Integer.parseInt(pageSize));
        
        try{
            hPage.init(searchGenertor.getQueryString(),searchGenertor.getFieldDBValues(),searchGenertor.getFieldDBTypes());
        }catch(HibernatePageException ex ){
            SystemLogger.error("PetListAction 错误："+ex.toString());
        }
        hPage.getCurrentResult(); 
        List petList = hPage.getCurrentResult(); 
 
        /**保存属性**/     
        request.setAttribute("petList",petList);  
        
        /**保存分页信息属性**/
        request.setAttribute("hPage",hPage);
        
        /**保存页面号属性**/
        request.setAttribute("params", searchGenertor.getParameter());
        
        /**页面转向**/          
        return mapping.findForward("statByArea");
        
    }
    
    public ActionForward statByVaccine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /***取得request的参数*/
        String areaCode = request.getParameter("areaCode"); 
        String batchNo = request.getParameter("batchNo"); 
        String inject = Tools.toChinese(request.getParameter("inject")); 
        
        /**创建服务*/
        StructService structService = (StructService) Global.getInstance()
		.getService("structService");  
        
        /**找到当前Area*/
        Area tempArea = structService.findAreaByCode(areaCode);
        String tempAreaCode = "" ;
        
        /**判断是级别*/
        int areaLength = Integer.parseInt(Configuration.getAppConfig().findConfigs("statConfig").find("areaLength"));
        
        if(tempArea.getFather().getId().equals(Area.ROOT_AREA)){
            /**顶级*/
            tempAreaCode = areaCode.substring(0,areaLength * 1);
            
        }else if(tempArea.getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**次级*/
            tempAreaCode = areaCode.substring(0,areaLength * 2);
            
        }else if(tempArea.getFather().getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**再级*/
            tempAreaCode = areaCode.substring(0,areaLength * 3);
        }
        
        /**创建对象**/
        ConfigService configService = (ConfigService)Global.getInstance().getService("configService");
        
        /**取得搜索的字段**/
        MapWraper searchFields =configService.getSearchFields();
        
        /**搜索语句产生器**/        
        SearchGenertor  searchGenertor =(SearchGenertor)Global.getInstance().getService("searchGenertor");  
        
        /**设置产生器参数**/       
       
        if(!inject.equals(VaccineStatSqlGenerator.INJECT)){
            searchGenertor.setSearchFields(searchFields.getValue("statByVaccine1").toString());  
            searchGenertor.setHttpParameter("batchNo",batchNo);
            searchGenertor.setWhereParaterAnd(" p.id not in ( select distinct  p1.id from Pet p1 inner join p1.vaccineSet as v1 where p1.id = v1.pet.id and  p1.petOwner.area.areaCode like '"+ tempAreaCode +"%' and v1.batchNo ='"+batchNo +"')" );
        }else{
            searchGenertor.setSearchFields(searchFields.getValue("statByVaccine").toString());            
            searchGenertor.setEqualsParater("v","batchNo",Hibernate.STRING,batchNo,"batchNo",batchNo);
        }
        
        searchGenertor.setLikeParater("p","petOwner.area.areaCode",Hibernate.STRING,tempAreaCode,"areaCode",areaCode);
        searchGenertor.setHttpParameter("inject",inject);
        /**hibernate 分页**/    
        HibernatePage hPage = new HibernatePage(request,response);
        String pageSize = searchFields.getValue("statByVaccinePageSize").toString();
        
        /**设置每页参数**/
        hPage.setPageSize(Integer.parseInt(pageSize));
        
        try{
            hPage.init(searchGenertor.getQueryString(),searchGenertor.getFieldDBValues(),searchGenertor.getFieldDBTypes());
        }catch(HibernatePageException ex ){
            SystemLogger.error("PetListAction 错误："+ex.toString());
        }        

        hPage.getCurrentResult(); 
        
        List petList =  hPage.getCurrentResult();         
 
        /**保存属性**/     
        request.setAttribute("petList",petList);  
        
        /**保存分页信息属性**/
        request.setAttribute("hPage",hPage);
        
        /**保存页面号属性**/
        request.setAttribute("params", searchGenertor.getParameter());
    
        /**页面转向*/
        return mapping.findForward("statByVaccine");
    }
    
    public ActionForward statByVariety(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /***取得request的参数*/
        String areaCode = request.getParameter("areaCode"); 
        String varietyId = Tools.toChinese(request.getParameter("varietyId")); 
        
        /**创建服务*/
        StructService structService = (StructService) Global.getInstance()
		.getService("structService");  
        
        /**找到当前Area*/
        Area tempArea = structService.findAreaByCode(areaCode);
        String tempAreaCode = "" ;
        
        /**判断是级别*/
        int areaLength = Integer.parseInt(Configuration.getAppConfig().findConfigs("statConfig").find("areaLength"));
        
        if(tempArea.getFather().getId().equals(Area.ROOT_AREA)){
            /**顶级*/
            tempAreaCode = areaCode.substring(0,areaLength * 1);
            
        }else if(tempArea.getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**次级*/
            tempAreaCode = areaCode.substring(0,areaLength * 2);
            
        }else if(tempArea.getFather().getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**再级*/
            tempAreaCode = areaCode.substring(0,areaLength * 3);
        }
        
        /**创建对象**/
        ConfigService configService = (ConfigService)Global.getInstance().getService("configService");
        
        /**取得搜索的字段**/
        MapWraper searchFields =configService.getSearchFields();
        
        /**搜索语句产生器**/        
        SearchGenertor  searchGenertor =(SearchGenertor)Global.getInstance().getService("searchGenertor");  
        
        /**设置产生器参数**/              
       
        searchGenertor.setSearchFields(searchFields.getValue("statByVariety").toString()); 
   
        searchGenertor.setLikeParater("p","petOwner.area.areaCode",Hibernate.STRING,tempAreaCode,"areaCode",areaCode);
       
        searchGenertor.setEqualsParater("p","variety.id",Hibernate.STRING,varietyId,"varietyId",varietyId );
        
        /**hibernate 分页**/    
        HibernatePage hPage = new HibernatePage(request,response);
        String pageSize = searchFields.getValue("statByVarietyPageSize").toString();
        
        /**设置每页参数**/
        hPage.setPageSize(Integer.parseInt(pageSize));
        
        try{
            hPage.init(searchGenertor.getQueryString(),searchGenertor.getFieldDBValues(),searchGenertor.getFieldDBTypes());
        }catch(HibernatePageException ex ){
            SystemLogger.error("PetListAction 错误："+ex.toString());
        }        

        hPage.getCurrentResult(); 
        
        List petList =  hPage.getCurrentResult();         
 
        /**保存属性**/     
        request.setAttribute("petList",petList);  
        
        /**保存分页信息属性**/
        request.setAttribute("hPage",hPage);
        
        /**保存页面号属性**/
        request.setAttribute("params", searchGenertor.getParameter());
    
        /**页面转向*/
        return mapping.findForward("statByVariety");
    }


}
