//$Source: /petSys/petSys/src/java/com/drategy/pets/servlet/InitSystemLoggerServlet.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/28 08:48:59 $
package com.drategy.pets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drategy.pets.util.SystemLogger;



/**
* IinitSystemLogger servlet
* @author Jason Jiang
* @author  $
* @$Revision $
*/
public class InitSystemLoggerServlet extends HttpServlet{

   /**
    * servlet init ����
    */
   public void init() throws ServletException { 
       SystemLogger.setServletRoot(this.getServletContext().getRealPath(""));
       SystemLogger.info("系统日志初始化成功！");
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
    
}
