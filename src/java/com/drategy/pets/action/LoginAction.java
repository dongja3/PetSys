//$Source: /petSys/petSys/src/java/com/drategy/pets/action/LoginAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 03:12:51 $
package com.drategy.pets.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.User;
import com.drategy.pets.form.LoginForm;
import com.drategy.pets.security.SystemSecurity;
import com.drategy.pets.springservice.StructService;

/**
 * Login Action
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.15 $
 */

public final class LoginAction extends BaseDispatchAction {
    
    /**
     * 返回版本检测信息
     * @return
     */
    private String securityVerify(){
        
        //创建版本安全检测类
        SystemSecurity systemSecurity = (SystemSecurity) Global.getInstance().getService("systemSecurity");      
        
        //验证有效性
        String returnInfo = systemSecurity.securityVerify(Constant.CONFIG_PATH,Configuration.getAppConfig().findConfigs("security").find("fileName"));
       
        //返回信息
        return returnInfo;  
        
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    
	    //得到安全信息
	    String securityInfo = securityVerify();  
	    
	    //根据信息返回
	    if(securityInfo.equals(SystemSecurity.USER_NOT_AVAILABLE)){
	        
	        //返回版本提示信息
			return mapping.findForward("illegalCopy");
			
	    }else if(securityInfo.equals(SystemSecurity.USER_DATE_OVER)){
	        
	        //返回过期信息
			return mapping.findForward("expiredApp");
	    }
	    
	    
	    /**得到form*/
	    LoginForm loginForm = (LoginForm)form;
	    
	    /**得到参数值*/
	    String userName = loginForm.getUserName();
	    String password = loginForm.getPassword();
	    
	    /**创建Global类*/
	    StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**得到topNews*/
	    List newsList = structService.getTopNews(10);
        request.setAttribute("newsList",newsList);
	    
	    /**查找user对象*/
	    User user = structService.findUserByUserName(userName,password);
	    
	    /**判断user 已经找到*/
	    if(user instanceof User){
	        /**设置session 属性**/
		    request.getSession().setAttribute(Configuration.getBaseConfig().findConfigs("baseConfig").find("sessionUserKey"),user);	    
		    
		    /**设置当前用户的id*/
		    Constant.CURR_USER_ID = user.getId() ;
	        return (mapping.findForward("success"));
	    }else{
	        ActionErrors errors = new ActionErrors();
	        errors.add("errors.login.usernameorpassword",new ActionError("errors.login.usernameorpassword",null));
	        super.saveErrors(request,errors);
	        return mapping.getInputForward();
	    }	

	}

	public ActionForward loginOff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 设置session 属性* */
		request.getSession().setAttribute(
				Configuration.getBaseConfig().findConfigs("baseConfig").find(
						"sessionUserKey"), null);

		/** 创建Global类 */
		StructService structService = (StructService) Global.getInstance()
				.getService("structService");

		/** 得到topNews */
		List newsList = structService.getTopNews(10);
		request.setAttribute("newsList", newsList);

		/** 页面转向* */
		return mapping.findForward("loginOff");

	}

}
