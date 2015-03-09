//$Source: /petSys/petSys/src/java/com/drategy/pets/filter/EncodeFilter.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/28 08:48:59 $


package com.drategy.pets.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.drategy.pets.context.Global;
import com.drategy.pets.context.HibernateCfg;


/**
* 系统的EncodeFiler
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.4 $
*/

public class EncodeFilter
    extends HttpServlet
    implements Filter {
 /**���ù����� */
  private FilterConfig filterConfig;
  /** ����  */ 
  protected String encoding = null;
  /** �Ƿ� ignore*/
  protected boolean ignore = true;
 /** �Ƿ� postOnly*/
  protected boolean postOnly = false;

  //Handle the passed-in FilterConfig
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    this.encoding = filterConfig.getInitParameter("encoding");
    String value = filterConfig.getInitParameter("ignore");

    if (value == null) {
      this.ignore = true;
    }
    else if (value.equalsIgnoreCase("true")) {
      this.ignore = true;
    }
    else if (value.equalsIgnoreCase("yes")) {
      this.ignore = true;
    }
    else {
      this.ignore = false;
    }

    value = filterConfig.getInitParameter("postOnly");
    if (value == null) {
      this.postOnly = false;
    }
    else if (value.equalsIgnoreCase("true")) {
      this.postOnly = true;
    }
    else if (value.equalsIgnoreCase("yes")) {
      this.postOnly = true;
    }
    else {
      this.postOnly = false;
    }

  }

  //Process the request/response pair
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain filterChain) throws ServletException,
      IOException {
    if (ignore || (request.getCharacterEncoding() == null)) {
      String encoding = selectEncoding(request);
      if (encoding != null) {
        try {
          if (!postOnly ||
              postOnly &&
              ( (HttpServletRequest) request).getMethod().
              toLowerCase().equals("post")) {
            request.setCharacterEncoding(encoding);
          }
        }
        catch (Exception e) {}
      }
    }

    try {
      filterChain.doFilter(request, response);
    }
    catch (ServletException sx) {
      filterConfig.getServletContext().log(sx.toString());
      throw sx;
    }
    catch (IOException iox) {
      filterConfig.getServletContext().log(iox.toString());
      throw iox;
    }
    finally {
      HibernateCfg cfg = (HibernateCfg) Global.getInstance().getService(
          "hibernateCfg");
      cfg.closeSession();
    }
  }

  //Clean up resources
  public void destroy() {
  }

  protected String selectEncoding(ServletRequest request) {

    return (this.encoding);
  }

}
