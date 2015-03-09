//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/UserDAOImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/01/16 08:45:55 $
package com.drategy.pets.dao.hibernate;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.UserDAO;
import com.drategy.pets.domain.User;
import com.drategy.pets.dao.MyQuery;
import java.util.List;
import java.util.ArrayList;

import com.drategy.pets.util.SystemLogger;
 /**
 * 系统的基础dao接口
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.9 $
 */
public class UserDAOImpl implements UserDAO {
    
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;		
    
    /**
     * 构造函数初始化参数
     */    
	public UserDAOImpl(){
		this.clasz = com.drategy.pets.domain.User.class;
	    this.orderBy = " order by id desc";
	}
        
    /**增加用户*/
    public void  add(User user){
        baseDAO.saveEntity(user);
    }
    
    /**查找用户*/
    public User find(String id){
        return (User) baseDAO.loadEntity(clasz,id);
    }
	
    /**删除用户*/    
	public void delete(User user){
	    baseDAO.removeEntity(user);
	}
	
	/**更新用户 */
	public void update(User user){
	    baseDAO.updateEntity(user);
	}
	
	/**通过userName查找User*/
	public User findByUserName(String userName,String password){
	    MyQuery query = new MyQuery();	    
	    query.setQueryString("from User u where u.userName=? and u.password=?");
	    query.addPara(userName,Hibernate.STRING);
	    query.addPara(password,Hibernate.STRING);
	    List objList = new ArrayList();
	    objList = baseDAO.findEntity(query);
	     
	    /**如果他们不存在 return null*/
	    if(objList.size() == 0){
	        return null;
	    }else{
	        return (User)objList.get(0);
	    }		    
	}
	
	/**查找一个通过name**/
	public User findByName(String name){
	    
	    MyQuery query = new MyQuery();	    
	    query.setQueryString("from User u where u.userName=?");
	    query.addPara(name,Hibernate.STRING);
	    
	    List objList = new ArrayList();
	    objList = baseDAO.findEntity(query);
	    
	    /**如果他们不存在 return null*/
	    if(objList.size() == 0){
            SystemLogger.debug("不存在这样的登录用户,userName:="+name);
	        return null;
	    }else{
	        return (User)objList.get(0);
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

	public List findbyAreaId(String areaId) {
		
		MyQuery query = new MyQuery();	    
	    query.setQueryString("from User u where u.area.id=?");
	    query.addPara(areaId,Hibernate.STRING);
	    
	    List objList = new ArrayList();
	    objList = baseDAO.findEntity(query);
	    
	    /**如果他们不存在 return null*/
	    if(objList.size() == 0){
	        return null;
	    }else{
	        return objList;
	    }	
	}
}
