//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/EmployeeDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $


package com.drategy.pets.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.EmployeeDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.domain.Employee;

/**
* 系统的基础EmploeDAO实现类
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.5 $
*/
public class EmployeeDAOImpl implements EmployeeDAO {
    
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;	
    
    /** 构造函数*/
    public EmployeeDAOImpl() {
       this.clasz =com.drategy.pets.domain.Employee.class;
       this.orderBy = "order by id desc" ;
    }
    
    /**增加员工*/
    public void  add(Employee employee){
       baseDAO.saveEntity(employee); 
    }
    
    /**查找员工*/
    public Employee find(String id){
        return (Employee)baseDAO.loadEntity(clasz,id);
    }
	
    /**删除员工*/    
	public void delete(Employee employee){
	    baseDAO.removeEntity(employee);
	}
	
	/**更新员工 */
	public void update(Employee employee){
	    baseDAO.updateEntity(employee);
	}
    
	/**查找一个通过name**/
	public Employee findByName(String name){
	    
	    MyQuery query = new MyQuery();	    
	    query.setQueryString("from Employee e where e.name=?");
	    query.addPara(name,Hibernate.STRING);
	    
	    List objList = new ArrayList();
	    objList = baseDAO.findEntity(query);
	    
	    /**如果他们不存在 return null*/
	    if(objList.size() == 0){
	        return null;
	    }else{
	        return (Employee)objList.get(0);
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
