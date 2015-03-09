//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/PetOwnerDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $


package com.drategy.pets.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.dao.PetOwnerDAO;
import com.drategy.pets.domain.PetOwner;

/**
* 系统的基础PetOwnerDAO接口的实现类
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.5 $
*/
public class PetOwnerDAOImpl implements PetOwnerDAO{
    
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;    
    
    /**构造函数*/
    public PetOwnerDAOImpl() {
      this.clasz = PetOwner.class ;
      this.orderBy = "order by id desc";
    } 
    
    /**增加*/
    public void add(PetOwner petOwner){
        baseDAO.saveEntity(petOwner);
    }
    
    /**查询*/
    public PetOwner find(String id){
        return (PetOwner)baseDAO.loadEntity(clasz,id);
    }
    
    /**删除*/
    public void delete(PetOwner petOwner){
        baseDAO.removeEntity(petOwner);
    }
    
    /**修改*/
    public void update(PetOwner petOwner){
        baseDAO.updateEntity(petOwner);
    }
    
    /**通过身份证找petOwner*/
    public PetOwner findByResidentID(String residentID){

	    MyQuery query = new MyQuery();	    
	    query.setQueryString("from PetOwner po where po.residentID=?");
	    query.addPara(residentID,Hibernate.STRING);
	    
	    List objList = new ArrayList();
	    objList = baseDAO.findEntity(query);
	    
	    /**如果他们不存在 return null*/
	    if(objList.size() == 0){
	        return null;
	    }else{
	        return (PetOwner)objList.get(0);
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
