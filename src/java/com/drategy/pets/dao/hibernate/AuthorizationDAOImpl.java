//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/AuthorizationDAOImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $
package com.drategy.pets.dao.hibernate;

import net.sf.hibernate.Hibernate;
import com.drategy.pets.dao.AuthorizationDAO;
import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.domain.Authorization;
import com.drategy.pets.util.SystemLogger;

/**
 * 系统的AuthorizationDAO的实现类
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.2 $
 */
public class AuthorizationDAOImpl implements AuthorizationDAO {
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;	
   
    /**构造函数*/
    public AuthorizationDAOImpl() {
       this.clasz = com.drategy.pets.domain.Authorization.class;
       this.orderBy = "order by id desc";
    }
    
    
    /**增加权限*/
    public void  add(Authorization authorization){
        baseDAO.saveEntity(authorization);
    }
    
    /**查找权限*/
    public Authorization find(String id){
        return (Authorization)baseDAO.loadEntity(clasz,id);
    }
	
    /**删除权限*/    
	public void delete(Authorization authorization){
	    baseDAO.removeEntity(authorization);
	}
	
	/**更新权限 */
	public void update(Authorization authorization){
	    baseDAO.updateEntity(authorization);
	}
    
	/**查找Authorization**/
	public Authorization findBymoudleName(String moudleName,String childMoudleName,String userId){
	    MyQuery myQuery = new MyQuery();
        myQuery.setQueryString("from Authorization au  where  au.moudleName=? and au.childMoudleName=? and au.user.id=?");
        myQuery.addPara(moudleName,Hibernate.STRING);
        myQuery.addPara(childMoudleName,Hibernate.STRING);
        myQuery.addPara(userId,Hibernate.STRING);
        java.util.List list = new java.util.ArrayList();
        list =baseDAO.findEntity(myQuery);
        if (list.size() == 0) {
          SystemLogger.error("Authorization:不存在，"+"moudleName="+moudleName+",childMoudleName="+childMoudleName);
          return null;
        }else{
          return (Authorization) list.get(0);
        }
	}
	
    /**
     * @return 返回 baseDAO。
     */
    public BaseDAO getBaseDAO() {
        return baseDAO;
    }
    /**
     * @param baseDAO 要设置的 baseDAO。
     */
    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }
    /**
     * @return 返回 clasz。
     */
    public Class getClasz() {
        return clasz;
    }
    /**
     * @param clasz 要设置的 clasz。
     */
    public void setClasz(Class clasz) {
        this.clasz = clasz;
    }
    /**
     * @return 返回 orderBy。
     */
    public String getOrderBy() {
        return orderBy;
    }
    /**
     * @param orderBy 要设置的 orderBy。
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
