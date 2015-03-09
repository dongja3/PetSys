//$Source: /petSys/petSys/src/java/com/drategy/pets/action/StatAction.java,v $
//LasterModified By$
//$Date $

package com.drategy.pets.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.biz.AreaStatSqlGenerator;
import com.drategy.pets.biz.StatResultGenerator;
import com.drategy.pets.biz.VaccineStatSqlGenerator;
import com.drategy.pets.biz.VarietyStatSqlGenerator;
import com.drategy.pets.context.Global;


/**
* 系统统计action
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.5 $
*/

public class StatAction extends BaseDispatchAction{

    public ActionForward statByArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
                

        
        /**创建统计结果生成器*/
        StatResultGenerator statResultGenerator = (StatResultGenerator)Global.getInstance().getService("statResultGenerator");
        
        /**创建areaStatSql生成器*/        
        AreaStatSqlGenerator areaStatSqlGenerator = (AreaStatSqlGenerator)Global.getInstance().getService("areaStatSqlGenerator");
        
        /**取得统计结果*/
        Map resultMap = statResultGenerator.getStatResultData(areaStatSqlGenerator,"");
        
        Map titleMap = statResultGenerator.getTitleMap(areaStatSqlGenerator);
        
        /**设置request参数*/
        request.setAttribute("resultMap",resultMap);
        request.setAttribute("titleMap",titleMap);
        
        /**页面转向*/
        return mapping.findForward("statByArea");
    }
    
    public ActionForward statByVaccine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        /**取得request中参数*/
        String batchNo = request.getParameter("batchNo");
        
        
        /**创建统计结果生成器*/
        StatResultGenerator statResultGenerator = (StatResultGenerator)Global.getInstance().getService("statResultGenerator");
        
        /**创建areaStatSql生成器*/        
        VaccineStatSqlGenerator vaccineStatSqlGenerator = (VaccineStatSqlGenerator)Global.getInstance().getService("vaccineStatSqlGenerator");
        
        /**取得统计结果*/
        Map resultMap = statResultGenerator.getStatResultData(vaccineStatSqlGenerator,batchNo);
        
        Map titleMap = statResultGenerator.getTitleMap(vaccineStatSqlGenerator);
        
        /**设置request参数*/
        request.setAttribute("resultMap",resultMap);
        request.setAttribute("titleMap",titleMap);
        request.setAttribute("batchNo",batchNo);
        
        /**页面转向*/
        return mapping.findForward("statByVaccine");
    }
    
    public ActionForward statByVariety(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        
        /**创建统计结果生成器*/
        StatResultGenerator statResultGenerator = (StatResultGenerator)Global.getInstance().getService("statResultGenerator");
        
        /**创建areaStatSql生成器*/        
        VarietyStatSqlGenerator varietyStatSqlGenerator = (VarietyStatSqlGenerator)Global.getInstance().getService("varietyStatSqlGenerator");
        
        /**取得统计结果*/
        Map resultMap = statResultGenerator.getStatResultData(varietyStatSqlGenerator,"");
        
        Map titleMap = statResultGenerator.getTitleMap(varietyStatSqlGenerator);
        
        Map rowMap = statResultGenerator.getRowMap(varietyStatSqlGenerator);
        
        /**设置request参数*/
        request.setAttribute("resultMap",resultMap);
        request.setAttribute("titleMap",titleMap);
        request.setAttribute("rowMap",rowMap);
        
        /**页面转向*/
        return mapping.findForward("statByVariety");
    }



}
