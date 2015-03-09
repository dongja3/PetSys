//$Source: /petSys/petSys/src/java/com/drategy/pets/action/BaseDispatchAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/16 06:13:20 $
package com.drategy.pets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;

import com.drategy.pets.exception.UnloginException;
import com.drategy.pets.util.SystemLogger;


/**
 * 系统的基础DispatchAction，所有DispatchAction必须继承该类
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.6 $
 */
public abstract class BaseDispatchAction
    extends DispatchAction {
  
	/**
	 * 保存method
	 * @param actionForm
	 * @param httpServletRequest
	 */
	protected void saveMethod(ActionForm actionForm,
	                        HttpServletRequest httpServletRequest) {
	  com.drategy.pets.form.BaseForm baseForm = (com.drategy.pets.form.BaseForm) actionForm;
	  httpServletRequest.setAttribute("method", baseForm.getMethod());
	}
    
	/**
	 * saveMethod
	 * @param method
	 * @param httpServletRequest
	 */
	 protected void saveMethod(String method,
	                            HttpServletRequest httpServletRequest) {
	    httpServletRequest.setAttribute("method", method);
	 }
     
	 /**
	  * saveMsg
	  * @param msg
	  * @param httpServletRequest
	  */
	 protected void saveMsg(String msg,
	                         HttpServletRequest httpServletRequest) {
	    httpServletRequest.setAttribute("msg", msg);
	 }

  /**
   * 参考范例
   * @param actionMapping ActionMapping
   * @param actionForm ActionForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return ActionForward
   */
  public ActionForward demo(ActionMapping actionMapping, ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
	  SystemLogger.warn("BaseDispatchAction is executed, means Children has not been executed");
    return null;
  }



  /**
   * 检测用户是否登陆
   * @param request HttpServletRequest
   * @return boolean
   */
  protected void checkLogin(HttpServletRequest request) throws
      com.drategy.pets.exception.UnloginException {
    if (request.getSession().getAttribute(com.drategy.pets.context.Configuration.getBaseConfig().
                                          findConfigs("baseConfig").find(
        "sessionUserKey")) == null) {
      throw new com.drategy.pets.exception.UnloginException();
    }
  }

  /**
   * 
   * @param request
   * @return 得到一个登录成功用户
   * @throws UnloginException
   */
  protected Object getSessionUser(HttpServletRequest request) throws
  com.drategy.pets.exception.UnloginException {
    Object obj = request.getSession().getAttribute(com.drategy.pets.context.Configuration.getBaseConfig().
        findConfigs("baseConfig").find("sessionUserKey"));
    if (obj == null) {
      throw new com.drategy.pets.exception.UnloginException();
    }
    return obj;
  }
  
  /**
   * 通过benaName 从spring配置
   * 得到bean   * 
   * @param beanName
   * @return
   */
  protected final Object getBean(String beanName) {
   
  	Object obj = this.servlet.getServletContext().getAttribute(org.
        springframework.web.context.WebApplicationContext.
        ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    
  	//obj是null 没有配置文件
    if (obj == null) {
      throw new RuntimeException("Not found org.springframework.web.context.WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE:" +
                                 org.springframework.web.context.
                                 WebApplicationContext.
                                 ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }
    
    return ( (ApplicationContext) obj).getBean(beanName);
  }

  /**
   * 保存分页参数
   * @param request HttpServletRequest
   * @param pNames String[] 参数名
   * @param pValues String[] 参数值
   */
  protected void saveHibernatePageParmas(HttpServletRequest request, String[] pNames, String[] pValues) {
    request.setAttribute("params",com.drategy.pets.util.Tools.wrapRequestParams(pNames, pValues));
    for (int i = 0; i < pNames.length; i++) {
      request.setAttribute(pNames[i], pValues[i]);
    }
  }
}