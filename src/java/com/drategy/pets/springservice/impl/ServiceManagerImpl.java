//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/impl/ServiceManagerImpl.java,v $
//LasterModified By:$Author $
//$Date $
package com.drategy.pets.springservice.impl;

import com.drategy.pets.springservice.ServiceManager;
import com.drategy.pets.context.HibernateCfg;
import java.util.List;
import net.sf.hibernate.Session; 
import java.sql.Connection; 
import java.sql.Statement;
import com.drategy.pets.springservice.BaseService;
import com.drategy.pets.util.SystemLogger;
/**
* 系统的ServiceManager
* @author Jason Jiang
* @author $Author $
* @$Revision: 1.4 $
*/
public class ServiceManagerImpl implements ServiceManager{
    
    /**服务list*/
    private List serviceList;
    
    /**要执行的sql语句*/
    private List sql;
    
    /** 是否要初始化数据 */
    private boolean initData;
    
    /**HibernateCfg 配置*/
    private HibernateCfg hibernateCfg;
    
    /**
     * 
     *构造函数 
     */    
    public ServiceManagerImpl() {

    }

    /**
     * 初始化数据
     */
    public void initData() {
      
      //是否要初始化
      if (!initData) {
        return;
      }

      //首先运行SQL语句
      Session ses = hibernateCfg.openSession();
      
      //执行sql语句
      try {
        Connection con = ses.connection();
        con.setAutoCommit(true);
        Statement stmt = con.createStatement();
        
        //循环执行
        for (int i = 0; i < sql.size(); i++) {
          try {
            stmt.execute(sql.get(i).toString());
          }
          catch (Exception e) {
              SystemLogger.warn(e.toString());
          }
        }
      }
      catch (Exception ex) {
          SystemLogger.warn(ex.toString());
      }
      
      hibernateCfg.closeSession();
      
      //是否有服务list
      if (serviceList == null || serviceList.size() == 0) {
        return;
      }
      
      //循环执行
      for (int i = 0; i < serviceList.size(); i++) {
        BaseService service = null;
        
        try {
          service = (BaseService) serviceList.get(i);
          service.initData();
        }
        catch (ClassCastException ex) {
            SystemLogger.warn("类：" + service.getClass().toString() +
                         "没有实现BaseService接口:" +
                         ex.toString());
          continue;
        }
        catch (UnsupportedOperationException ex2) {
          continue;
        }
        catch (Exception e) {
            SystemLogger.warn("类：" + service.getClass().toString() +
                         "运行initData异常:" + e.toString());
          continue;
        }
      }
    }

    public List getServiceList() {
      return serviceList;
    }

    public void setServiceList(List serviceList) {
      this.serviceList = serviceList;
    }

    public boolean isInitData() {
      return initData;
    }

    public void setInitData(boolean initData) {
      this.initData = initData;
    }

    public HibernateCfg getHibernateCfg() {
      return hibernateCfg;
    }

    public void setHibernateCfg(HibernateCfg hibernateCfg) {
      this.hibernateCfg = hibernateCfg;
    }

    public void setSql(List sql) {
      this.sql = sql;
    }

    public List getSql() {
      return sql;
    }
}
