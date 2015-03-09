//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/VarietyDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.dao.VarietyDAO;
import com.drategy.pets.domain.Variety;

/**
 * 系统的基础NewsDAO实现类
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.2 $
 */
public class VarietyDAOImpl implements VarietyDAO {
	/** DAO的基础类 */
	private BaseDAO baseDAO;

	/** 排序字段 */
	private String orderBy;

	/** 要操作类 */
	private Class clasz;

	public VarietyDAOImpl() {
		this.clasz = com.drategy.pets.domain.Variety.class;
		this.orderBy = " order by id desc";
	}

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

	public void add(Variety variety) {
		baseDAO.saveEntity(variety);

	}

	public void delete(Variety variety) {
		baseDAO.removeEntity(variety);
	}

	public Variety find(String id) {
		return (Variety) baseDAO.loadEntity(clasz, id);
	}

	public List getAll() {
		return baseDAO.findAll(clasz);
	}

	public void update(Variety variety) {
		baseDAO.updateEntity(variety);

	}

	public Variety findByName(String name) {
		MyQuery query = new MyQuery();
		query.setQueryString("from Variety v where v.name=?");
		query.addPara(name, Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
			return null;
		} else {
			return (Variety) objList.get(0);
		}
	}

}
