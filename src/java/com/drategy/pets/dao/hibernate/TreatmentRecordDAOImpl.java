//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/TreatmentRecordDAOImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao.hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.TreatmentRecordDAO;
import com.drategy.pets.domain.TreatmentRecord;


/**
* 系统的基础TreatmentRecordDAOImpl
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/
public class TreatmentRecordDAOImpl implements TreatmentRecordDAO{
   
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;		
    
    /**构造函数*/
    public TreatmentRecordDAOImpl() {
        this.clasz = TreatmentRecord.class ;
        this.orderBy = "order by id desc" ;
    }
    
    /**增加*/
    public void add(TreatmentRecord treatmentRecord){
        baseDAO.saveEntity(treatmentRecord);
    }
    
    /**查询*/
    public TreatmentRecord find(String id){
        return (TreatmentRecord)baseDAO.loadEntity(clasz,id);
    }
    
    /**删除*/
    public void delete(TreatmentRecord treatmentRecord){
       baseDAO.removeEntity(treatmentRecord);
    }
    
    /**修改*/
    public void update(TreatmentRecord treatmentRecord){
        baseDAO.updateEntity(treatmentRecord);
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
