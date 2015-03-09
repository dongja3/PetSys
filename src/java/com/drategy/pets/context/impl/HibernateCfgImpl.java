//$Source: /petSys/petSys/src/java/com/drategy/pets/context/impl/HibernateCfgImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:12:21 $
package com.drategy.pets.context.impl;


import com.drategy.pets.exception.*;
import com.drategy.pets.util.*;
import com.drategy.pets.context.*;
import net.sf.hibernate.*;
import net.sf.hibernate.type.*;

/**
 * 系统的hibernate接口的实现
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.5 $
 */

public class HibernateCfgImpl
    implements HibernateCfg {
	
  /**sessionFactory  */
  private SessionFactory sessionFactory;
  
  /**本地thread */  
  private static final ThreadLocal threadLocal = new ThreadLocal();
 
  
  public HibernateCfgImpl() {

  }
  /**
   * 得到sessionFactory
   * @return
   */
  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }
  /**
   *  设置sessionFactory
   * @param sessionFactory
   */
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
  /**
   * 打开session
   */
  public Session openSession() {

    Session session = (Session) threadLocal.get();   
    
    //session是null
    if (session == null) {
     
      try {
        session = sessionFactory.openSession(); 
      }
      catch (HibernateException e) {
        SystemLogger.error("从sessionFactory中得到Session 错误:" + e.toString());
      }
      
      threadLocal.set(session);
    }
    
    return session;
  }
  
  /**
   * 关闭session
   */
  public void closeSession() {
    Session session = (Session) threadLocal.get();
    threadLocal.set(null);
    
    //session is null 
    if (session != null) {
      
      try {
        session.close();
      }
      catch (HibernateException e) {
    	  SystemLogger.error("�ر�Session�쳣��" + e.toString());
      }
      
    }
  }
  
  /**
   * 得到hibernate类型��������
   */
  public Type getHibernateType(Object obj) {
    String className = null;
    
    //obj 是不是 java.lang.class
    if (obj instanceof java.lang.Class) {
      className = ( (Class) obj).getName();
    }
    else {
      className = obj.getClass().getName();
    }
    
    //get class name
    if (className.indexOf(".") != -1) {
      className = className.substring(className.lastIndexOf(".") + 1,
                                      className.length()).toLowerCase();
    }

    Type type = TypeFactory.basic(className);
    
    //type  not null
    if (type == null) {
      throw new BaseRunTimeException("没有此种Hibernate类型:" + className);
    }
    
    return type;
  }

}

