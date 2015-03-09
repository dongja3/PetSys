//$Source: /petSys/petSys/src/java/com/drategy/pets/action/AuthorizationConfigAction.java,v $
//LasterModified By:$Author $
//$Date: 2005/12/28 05:49:47 $
package com.drategy.pets.action;

import java.util.Set;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Authorization;
import com.drategy.pets.domain.User;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.Tools;


/**
 * AuthorizationConfigAction
 * @author Jason Jiang
 * @author $Author $
 * @$Revision: 1.1 $
 */
public class AuthorizationConfigAction extends BaseDispatchAction{
    public ActionForward execute(ActionMapping map, ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response){
        
        /**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");        
        
        /**取得request参数**/
        String userId = request.getParameter("userId");
        
        /**取得用户**/
        User user = structService.findUser(userId);
        
        Set authorizationSet = new java.util.HashSet();
        
        /***取得页面数*/
        int isum =Integer.parseInt(request.getParameter("authorizationSum"));   
        
        /**循环设置**/
        for(int i = 0 ; i<isum;i++){
            
            String authorizationName = "authorization" + i;  
            
            /**取得页面权限名字**/
            String authorizationId = request.getParameter(authorizationName);
            
            String moudleName = "" ;
            String childMoudleName = "" ;
            String actAdd = "" ;
            String actUpdate = "" ;
            String actDelete = "" ;
            
            /**如果选中**/
            if(!Tools.isNullOrEmpty(authorizationId)){  
                moudleName = request.getParameter("moudleName"+i);
                childMoudleName = request.getParameter("childMoudleName"+i);
                actAdd = request.getParameter("add"+i);
                actUpdate = request.getParameter("modify"+i);
                actDelete = request.getParameter("delete"+i);
                
                /**原来已经有这个权限**/
                Authorization tempAuthorization = structService.findAuthorizationBymoudleName(moudleName,childMoudleName,userId); 
                Set oldAuthorizationSet = user.getAuthorizationSet() ;
                
                /**原来权限集为空**/
                if(Tools.isNullOrEmpty(oldAuthorizationSet.toString())){
                    Authorization authorization = new Authorization();
	                authorization.setId(Tools.genResourceId(""));
	                authorization.setMoudleName(moudleName);
	                authorization.setChildMoudleName(childMoudleName);
	                
	                /**是否选Add**/
	                if(Tools.isNullOrEmpty(actAdd)){
	                    authorization.setActAdd(Authorization.ACT_PRRMIT_NO);
	                }else{
	                    authorization.setActAdd(Authorization.ACT_PRRMIT_YES);
	                }
	                
	                /**是否选Update**/
	                if(Tools.isNullOrEmpty(actUpdate)){
	                    authorization.setActUpdate(Authorization.ACT_PRRMIT_NO);
	                }else{
	                    authorization.setActUpdate(Authorization.ACT_PRRMIT_YES);
	                }  
	                
	                /**是否选delete**/
	                if(Tools.isNullOrEmpty(actDelete)){
	                    authorization.setActDelete(Authorization.ACT_PRRMIT_NO);
	                }else{
	                    authorization.setActDelete(Authorization.ACT_PRRMIT_YES);
	                }
	                structService.addAuthorization(authorization);
	                authorizationSet.add(authorization); 
                    
                }else{
                    
                    /**是否原来就选中**/
	                if(!oldAuthorizationSet.contains(tempAuthorization)){
		                
	                    Authorization authorization = new Authorization();
		                authorization.setId(Tools.genResourceId(""));
		                authorization.setMoudleName(moudleName);
		                authorization.setChildMoudleName(childMoudleName);
		                
		                /**是否选Add**/
		                if(Tools.isNullOrEmpty(actAdd)){
		                    authorization.setActAdd(Authorization.ACT_PRRMIT_NO);
		                }else{
		                    authorization.setActAdd(Authorization.ACT_PRRMIT_YES);
		                }
		                
		                /**是否选update**/
		                if(Tools.isNullOrEmpty(actUpdate)){
		                    authorization.setActUpdate(Authorization.ACT_PRRMIT_NO);
		                }else{
		                    authorization.setActUpdate(Authorization.ACT_PRRMIT_YES);
		                }  
		                
		                /**是否选Delete**/
		                if(Tools.isNullOrEmpty(actDelete)){
		                    authorization.setActDelete(Authorization.ACT_PRRMIT_NO);
		                }else{
		                    authorization.setActDelete(Authorization.ACT_PRRMIT_YES);
		                }
		                structService.addAuthorization(authorization);
		                authorizationSet.add(authorization); 
		                
	                }else{
	                    /**是否选Add**/
	                    if(Tools.isNullOrEmpty(actAdd)){
	                        tempAuthorization.setActAdd(Authorization.ACT_PRRMIT_NO);
		                }else{
		                    tempAuthorization.setActAdd(Authorization.ACT_PRRMIT_YES);
		                }
	                    /**是否选update**/
		                if(Tools.isNullOrEmpty(actUpdate)){
		                    tempAuthorization.setActUpdate(Authorization.ACT_PRRMIT_NO);
		                }else{
		                    tempAuthorization.setActUpdate(Authorization.ACT_PRRMIT_YES);
		                }  
		                
		                /**是否选delete**/
		                if(Tools.isNullOrEmpty(actDelete)){
		                    tempAuthorization.setActDelete(Authorization.ACT_PRRMIT_NO);
		                }else{
		                    tempAuthorization.setActDelete(Authorization.ACT_PRRMIT_YES);
		                }
		                structService.updateAuthorization(tempAuthorization);
		                authorizationSet.add(tempAuthorization); 
	                }
                }
            }              
        }
        
        user.setAuthorizationSet(authorizationSet);
        structService.updateUser(user);
        //设置属性
        request.setAttribute("userId",user.getId());
        //页面转向
        return map.findForward("success");
    }
}
