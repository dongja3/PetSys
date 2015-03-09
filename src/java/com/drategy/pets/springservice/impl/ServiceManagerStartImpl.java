//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/impl/ServiceManagerStartImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/14 09:17:15 $
package com.drategy.pets.springservice.impl;


import com.drategy.pets.springservice.AppStart;
import com.drategy.pets.springservice.ServiceManager;
import javax.servlet.*;
/**
* 系统的ServiceManager
* @author Jason Jiang
* @author $Author $
* @$Revision: 1.1 $
*/
public class ServiceManagerStartImpl implements AppStart {
    
    /**服务管理类*/
    private ServiceManager serviceManager;

    /***/
    public ServiceManagerStartImpl() {

    }

    /**
     * 开始初始化
     * @param ctx
     */
    public void start(ServletContext ctx) {
      /**@todo Implement this com.langtong.context.AppStart method*/
      serviceManager.initData();
    }

    public ServiceManager getServiceManager() {
      return serviceManager;
    }

    public void setServiceManager(ServiceManager serviceManager) {
      this.serviceManager = serviceManager;
    }
}
