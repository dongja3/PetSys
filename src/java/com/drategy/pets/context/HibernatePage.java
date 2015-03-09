//$Source: /petSys/petSys/src/java/com/drategy/pets/context/HibernatePage.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/01/16 08:45:55 $
package com.drategy.pets.context;


import java.util.*;
import javax.servlet.http.*;
import net.sf.hibernate.*;
import net.sf.hibernate.type.*;
import org.apache.commons.lang.*;

import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;
import com.drategy.pets.exception.HibernatePageException;
import com.drategy.pets.context.Global;

/**
 * hibernate分页类
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.9 $
 */
public class HibernatePage {

  /**分页的资料文件*/
  public static final String HIBERNAGE_PAGE_PROPERTIES_FILE ="HibernatePage.properties";

 /**分页提示键*/
  private static String PAGE_INFO;

  /**分页跳转键*/
  private static String CLICK_PAGE;

 /**分页直接输入页码*/
  private static String INPUT_PAGE;

  static {
    Properties propes = new Properties();
    try {
      propes.load(HibernatePage.class.getResourceAsStream(
          HIBERNAGE_PAGE_PROPERTIES_FILE));
      PAGE_INFO = propes.getProperty("pageInfo");
      if (PAGE_INFO == null) {
        SystemLogger.error("属性键pageInfo没有找到");
      }
      PAGE_INFO = Tools.toChinese(PAGE_INFO);

      CLICK_PAGE = propes.getProperty("clickPage");
      if (CLICK_PAGE == null) {
        SystemLogger.error("属性键clickPage没有找到");
      }
      CLICK_PAGE = Tools.toChinese(CLICK_PAGE);

      INPUT_PAGE = propes.getProperty("inputPage");
      if (INPUT_PAGE == null) {
       SystemLogger.error("属性键inputPage没有找到");
      }
      INPUT_PAGE = Tools.toChinese(INPUT_PAGE);

    }
    catch (Exception ex) {
      SystemLogger.error("hibernatePage 初始化错误："+ex.toString());
    }
  }
  
  /**request*/
  private HttpServletRequest request;
  
  /**response*/
  private HttpServletResponse response;

  /**分页提示信息串*/
  private String pageInfo;

  /**分页跳转信息*/
  private String goPages;

  /**分页物体名*/
  private String objectName;

  /**分页页号*/
  private int pageId;

  /**分页页大小*/
  private int pageSize;

  /**分页页数*/
  private int pageNum;

  /**结果数量*/
  private int resultNum;

  /** 当前结果*/
  private List currentResult;
  
  /***
   * 构造函数,初始化参数
   * @param request
   * @param response
   */
  public HibernatePage(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
    pageSize = 10;
    pageNum = 0;
    resultNum = 0;

    try {
      pageId = Integer.parseInt(request.getParameter("pageId"));
    }
    catch (Exception ex) {
      try {
        pageId = Integer.parseInt(request.getParameter("pageId"));
      }
      catch (Exception ex2) {
        pageId = 1;
      }
    }
  }
 
  /**
   * 初始化参数
   * @param querySql
   * @param args
   * @param types
   * @return
   * @throws HibernatePageException
   */
  public boolean init(String querySql, Object[] args, Type[] types) throws
      HibernatePageException {

    HibernateCfg cfg = (HibernateCfg) Global.getInstance().
        getService("hibernateCfg");
    Session ses = cfg.openSession();
    Query query = null;

    /**统计查询总数*/
    int intOrder = querySql.indexOf(" order ") ;
    
    String countSql ="" ;
    
    if(intOrder==-1){
        countSql = "select count(*) from " +
        querySql.substring(querySql.indexOf(" from ") + 6, querySql.length());   
    }else{
        countSql = "select count(*) from " +
        querySql.substring(querySql.indexOf(" from ") + 6, intOrder);
    }
    /**创建查询*/
    try {
      query = ses.createQuery(countSql);
    }
    catch (HibernateException ex) {
      SystemLogger.error(ex.toString());
      throw new HibernatePageException(ex.toString());
    }

    /**设置参数*/
    for (int i = 0; args != null && i < args.length; i++) {
      query.setParameter(i, args[i], types[i]);
    }
    
    /**得到结果数*/
    Object resultNumObj = null;
    try {
        
      java.util.List resultList = query.list();
      SystemLogger.debug(" countSql:"+query.getQueryString());
      
      if (resultList.size() == 0) {
        currentResult = new java.util.ArrayList();
        return true;
      }
      
      resultNumObj = resultList.get(0);
    }
    catch (HibernateException ex) {
      SystemLogger.error(ex.toString()+" countSql:"+query.getQueryString());
      throw new HibernatePageException(ex.toString());
    }

    if (resultNumObj == null) {
      currentResult = new java.util.ArrayList();
      return true;
    }
    else {
      resultNum = Integer.parseInt(resultNumObj.toString());
      if (resultNum == 0) {
        currentResult = new java.util.ArrayList();
        return true;
      }
    }

    /**计算页数*/
    if (this.resultNum == (resultNum / pageSize) * pageSize) {
      this.pageNum = this.resultNum / this.pageSize;
    }
    else {
      this.pageNum = this.resultNum / this.pageSize + 1;
    }

    /**创建查询结果*/
    try {
      SystemLogger.debug("Sql语句："+querySql);
      query = ses.createQuery(querySql);
    }
    catch (HibernateException ex) {
        SystemLogger.error(ex.toString());
      throw new HibernatePageException(ex.toString());
    }

    /**设置参数*/
    for (int i = 0; args != null && i < args.length; i++) {
      query.setParameter(i, args[i], types[i]);
    }

    if (pageId > pageNum) {
      pageId = pageNum;
    }

    query.setFirstResult( (pageId - 1) * pageSize);
    query.setMaxResults(pageSize);

    try {
      currentResult = query.list();
    }
    catch (HibernateException ex) {
      SystemLogger.error(ex.toString());
      throw new HibernatePageException(ex.toString());
    }
    return true;
  }

  public List getCurrentResult() {
    return currentResult;
  }

  public int getPageId() {
    return pageId;
  }

  public int getPageNum() {
    return pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getResultNum() {
    return resultNum;
  }

  public void setPageId(int pageId) {
    this.pageId = pageId;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getPageInfo() {

    if (pageInfo != null) {
      return pageInfo;
    }

    pageInfo = PAGE_INFO;
    pageInfo = StringUtils.replace(pageInfo, "{objectName}", objectName);
    pageInfo = StringUtils.replace(pageInfo, "{pageNum}",
                                   Integer.toString(pageNum));
    pageInfo = StringUtils.replace(pageInfo, "{resultNum}",
                                   Integer.toString(resultNum));
    pageInfo = StringUtils.replace(pageInfo, "{currentResultNum}",
                                   Integer.toString(this.currentResult.size()));
    pageInfo = StringUtils.replace(pageInfo, "{pageId}",
                                   Integer.toString(pageId));
    System.out.println(pageInfo);
    return pageInfo;
  }

  public String getGoPages() {

    if (resultNum == 0) {
      return "";
    }

    if (goPages != null) {
      return goPages;
    }

    StringBuffer goPagesBuf = new StringBuffer();

    int forward;
    int backword;
    int temp1;
    if (this.pageId > 5) {
      forward = ( (int) (this.pageId - 1) / 5) * 5;
      String tmpClickPage = CLICK_PAGE;
      tmpClickPage = StringUtils.replace(tmpClickPage, "{pageId}",
                                         Integer.toString(forward));
      tmpClickPage = StringUtils.replace(tmpClickPage, "{pageIdView}", "<<");
      goPagesBuf.append(tmpClickPage)
          .append("&nbsp;");
    }

    temp1 = ( (int) (this.pageId - 1) / 5) * 5;
    for (int i = 1; (i + temp1 - 1) * this.pageSize < this.resultNum && i < 6;
         i++) {
      String tmpClickPage = CLICK_PAGE;
      tmpClickPage = StringUtils.replace(tmpClickPage, "{pageId}",
                                         Integer.toString(temp1 + i));
      tmpClickPage = StringUtils.replace(tmpClickPage, "{pageIdView}",
                                         "[" + Integer.toString(temp1 + i) +
                                         "]");
      goPagesBuf.append(tmpClickPage)
          .append("&nbsp;");
    }

    /**是否要显示快速后之指按钮*/
    temp1 = ( ( (int) (this.pageId - 1) / 5) + 1) * 5 * pageSize;
    if (temp1 < resultNum) {
      backword = ( ( (int) (this.pageId - 1) / 5) + 1) * 5 + 1;
      String tmpClickPage = CLICK_PAGE;
      tmpClickPage = StringUtils.replace(tmpClickPage, "{pageId}",
                                         Integer.toString(backword));
      tmpClickPage = StringUtils.replace(tmpClickPage, "{pageIdView}", ">>");
      goPagesBuf.append(tmpClickPage)
          .append("&nbsp;");
    }

    /**显示跳转按钮*/
    goPagesBuf.append(INPUT_PAGE)
        .append("&nbsp;");
    goPages = goPagesBuf.toString();
    return goPages;
  }

  public String getObjectName() {
    return objectName;
  }

  public void setObjectName(String objectName) {
    this.objectName = objectName;
  }

}
