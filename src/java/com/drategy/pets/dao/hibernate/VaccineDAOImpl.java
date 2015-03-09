//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/VaccineDAOImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao.hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.VaccineDAO;

import com.drategy.pets.domain.Vaccine;

/**
* 系统的基础VaccineDAOImpl
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/
public class VaccineDAOImpl implements VaccineDAO{
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;		
    
    /**构造函数*/
    public VaccineDAOImpl() {
      this.clasz =Vaccine.class;
      this.orderBy = "order by id desc";
    }
    
    /**增加*/
    public void add(Vaccine vaccine){
        baseDAO.saveEntity(vaccine);
    }
    
    /**查询*/
    public Vaccine find(String id){
        return (Vaccine)baseDAO.loadEntity(clasz,id);
    }
    
    /**删除*/
    public void delete(Vaccine vaccine){
        baseDAO.removeEntity(vaccine);
    }
    
    /**修改*/
    public void update(Vaccine vaccine){
        baseDAO.updateEntity(vaccine);
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
