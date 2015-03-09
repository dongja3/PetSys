//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/SmsSendDAOImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao.hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.SmsSendDAO;
import com.drategy.pets.domain.SmsSend;

/**
 * 系统的基础SmsSendDAO实现类
 * 
 * @author Jason jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.1 $
 */
public class SmsSendDAOImpl implements SmsSendDAO {
   
    /** DAO的基础类 */
	private BaseDAO baseDAO;

	/** 排序字段 */
	private String orderBy;

	/** 要操作类 */
	private Class clasz;

	public SmsSendDAOImpl() {
		this.clasz = com.drategy.pets.domain.SmsSend.class;
		this.orderBy = " order by id desc";
	}
    
    /**增加*/
    public void add(SmsSend smsSend){
        baseDAO.saveEntity(smsSend);
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
