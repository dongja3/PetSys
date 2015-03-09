//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/SmsDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.dao.SmsDAO;
import com.drategy.pets.domain.Sms;

/**
 * 系统的基础SmsDAO实现类
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.4 $
 */
public class SmsDAOImpl implements SmsDAO {
	/** DAO的基础类 */
	private BaseDAO baseDAO;

	/** 排序字段 */
	private String orderBy;

	/** 要操作类 */
	private Class clasz;

	public SmsDAOImpl() {
		this.clasz = com.drategy.pets.domain.Sms.class;
		this.orderBy = " order by id desc";
	}

	public void add(Sms sms) {
		baseDAO.saveEntity(sms);
	}

	public Sms find(String id) {
		return (Sms) baseDAO.loadEntity(clasz, id);
	}
    
	/**通过发送状态查询*/
    public List findSms(String sendState,String sendDate){
        MyQuery query = new MyQuery();
		query.setQueryString("from Sms s where s.send=? and s.sendTime =?");
		query.addPara(sendState, Hibernate.STRING);
		query.addPara(sendDate, Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
			return null;
		} else {
			return  objList;
		}      
    }
	
	public void update(Sms sms) {
		baseDAO.updateEntity(sms);
	}

	public List getAll() {
		return baseDAO.findAll(clasz);
	}

	/** get and Setter* */
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public Class getClasz() {
		return clasz;
	}

	public void setClasz(Class clasz) {
		this.clasz = clasz;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void delete(Sms sms) {
		baseDAO.removeEntity(sms);
		
	}

}
