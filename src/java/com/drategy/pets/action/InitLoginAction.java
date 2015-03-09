//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitLoginAction.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/01/04 07:24:17 $

package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.form.LoginForm;
import com.drategy.pets.context.Configuration;

/**
* Login InitAction
* 
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revisio$
*/

public class InitLoginAction extends BaseDispatchAction {

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    
	    /**创建LoginForm*/
	    LoginForm loginForm = new LoginForm();
	    
	    /**设置request属性**/
	    request.setAttribute("loginForm",loginForm);
	    
	    /****/
	    return mapping.findForward("success");
	}
	
	
	public ActionForward loginOff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    
	    /**设置session 属性**/
	    request.getSession().setAttribute(Configuration.getBaseConfig().findConfigs("baseConfig").find("sessionUserKey"),null);	    
	    
	    /**页面转向**/
	    return mapping.findForward("success");
	
	}
}
