//$Source: /petSys/petSys/src/java/com/drategy/pets/servlet/LoadInitLet.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/22 10:07:59 $

package com.drategy.pets.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.lang.*;
import org.springframework.context.*;
import org.springframework.web.context.*;

import com.drategy.pets.context.*;
import com.drategy.pets.util.*;
import com.drategy.pets.exception.*;
import com.drategy.pets.springservice.*;


/**
* 系统启动servlet
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.10 $
*/
public class LoadInitLet
    extends HttpServlet {
  /** 设置字符集 */
  private static final String CONTENT_TYPE = "text/html; charset=GBK";
  
  /**
   * servlet init ����
   */
  public void init() throws ServletException {    
    
    /** 设置Global的applicationContext */
    initGlobal(this.getServletContext());
   
    /**设置常量的设置路径*/ 
    Constant.CONFIG_PATH = this.getServletContext().getRealPath("/conf") +
      "\\";
    /** 开始启动*/
    traceStart();
    /** 启动开始配置*/
    appStart();    
    /**启动完成*/
    traceEnd();
  }

  /**
   * 
   *在后台输出启动完成
   */
  private static void traceEnd() {
    String end = new String(Tools.getFileAsBytes(Constant.CONFIG_PATH +
                                                 "end.jsp"));
    end = initCopyRight(end);
    SystemLogger.info(end);
  }

  /**
   * 在console 台输出启动开始
   * @throws ConfigNotFoundException
   */ 
  private static void traceStart() throws ConfigNotFoundException {
    String start = new String(Tools.getFileAsBytes(Constant.CONFIG_PATH +
        "start.jsp"));
    start = initCopyRight(start);
    SystemLogger.info(start);
  }

  /**
   * Process the HTTP Get request
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {

  }

  /**
   * Clean up resources
   */
  public void destroy() {
  }
  
  /**
   * �
   * @param text
   * @return
   */
  private static String initCopyRight(String text) {
    java.util.Iterator keys = Configuration.getAppConfig().findConfigs(
        "copyRightConfig").keys();
    while (keys.hasNext()) {
      String key = keys.next().toString();
      String value = Configuration.getAppConfig().findConfigs("copyRightConfig").
          find(key);
      text = StringUtils.replace(text, "{" + key + "}", value);

    }
    return text;
  }
  /**
   * Global contextֵ
   * @param ctx
   */
  private void initGlobal(ServletContext ctx) {

    Object obj = ctx.getAttribute(WebApplicationContext.
                                  ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

    if (obj != null) {
      Global.getInstance().setContext( (ApplicationContext) obj);
    }

  }
  
  /**
   * 设置初始化
   */
  public void appStart() {    
    AppStartManager appStartManager = (AppStartManager) Global.getInstance().
    getService("appStartManager");
    appStartManager.appStart(this.getServletContext());
   
  }
  
  
}