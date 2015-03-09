//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/RfidChipDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $


package com.drategy.pets.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.dao.RfidChipDAO;
import com.drategy.pets.domain.RfidChip;

/**
* 系统的基础RfidDAO接口的实现类
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.3 $
*/
public class RfidChipDAOImpl implements RfidChipDAO{
     
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;	
    
    /**构造函数*/
    public RfidChipDAOImpl() {
       this.clasz = com.drategy.pets.domain.RfidChip.class;
       this.orderBy = "order by  id desc" ;
    }
    
    /**增加*/
    public void add(RfidChip rfidChip){
        baseDAO.saveEntity(rfidChip);
    }    
    
    /**查询*/
    public RfidChip find(String id){
        return (RfidChip)baseDAO.loadEntity(clasz,id);       
    }
    
    /**查询*/
    public RfidChip findbyChiCode(String chipCode){
    	MyQuery query = new MyQuery();
		query.setQueryString("from RfidChip chip where chip.code=?");
		query.addPara(chipCode, Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
			return null;
		} else {
			return (RfidChip) objList.get(0);
		}      
    }
    
    /**删除*/
    public void delete(RfidChip rfidChip){
        baseDAO.removeEntity(rfidChip);
    }
    
    /**修改*/
    public void update(RfidChip rfidChip){
        baseDAO.updateEntity(rfidChip);
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
