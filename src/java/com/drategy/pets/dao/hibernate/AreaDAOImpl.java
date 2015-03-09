//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/AreaDAOImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $
package com.drategy.pets.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.AreaDAO;
import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.domain.Area;
import com.drategy.pets.util.SystemLogger;

/**
 * 系统的基础areaDAO实现类
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.9 $
 */
public class AreaDAOImpl implements AreaDAO {

	/** DAO的基础类 */
	private BaseDAO baseDAO;

	/** 排序字段 */
	private String orderBy;

	/** 要操作类 */
	private Class clasz;

	/** 构造函数 */
	public AreaDAOImpl() {
		this.clasz = com.drategy.pets.domain.Area.class;
		this.orderBy = " order by id desc";
	}

	/** 增加 */
	public void add(Area area) {
		baseDAO.saveEntity(area);
	}

	/** 查询 */
	public Area find(String id) {
		return (Area) baseDAO.loadEntity(clasz, id);
	}

	/** 删除 */
	public void delete(Area area) {
		baseDAO.removeEntity(area);
	}

	/** 修改 */
	public void update(Area area) {
		baseDAO.updateEntity(area);
	}

	/** 查找一个通过name* */
	public Area findByName(String name) {

		MyQuery query = new MyQuery();
		query.setQueryString("from Area a where a.name=?");
		query.addPara(name, Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
			return null;
		} else {
			return (Area) objList.get(0);
		}
	}

	public Area findByCode(String areaCode) {
		MyQuery query = new MyQuery();
		query.setQueryString("from Area a where a.areaCode=? order by a.areaCode ");
		query.addPara(areaCode, Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
		    SystemLogger.debug("不存在这样area areaCode=:"+areaCode);
			return null;
		} else {
			return (Area) objList.get(0);
		}
	}
     
	/**查找一个通过areaCode**/
    public List findLikeCode(String areaCode){
        MyQuery query = new MyQuery();
        areaCode = areaCode + "%"; 
		query.setQueryString("from Area a where a.areaCode like ? order by a.areaCode ");
		query.addPara(areaCode, Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
		    SystemLogger.debug("不存在这样area areaCode=:"+areaCode);
			return null;
		} else {
			return objList;
		}
    }
    
	
	public Area findByCodeandId(String areaCode, String id) {
		MyQuery query = new MyQuery();
		query.setQueryString("from Area a where a.areaCode=? and a.id<>?");
		query.addPara(areaCode, Hibernate.STRING);
		query.addPara(id,Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
		    SystemLogger.debug("不存在这样area areaCode=:"+areaCode);
			return null;
		} else {
			return (Area) objList.get(0);
		}
	}

	public Area findByNameandId(String areaName, String id) {
		MyQuery query = new MyQuery();
		query.setQueryString("from Area a where a.name=? and a.id<>?");
		query.addPara(areaName, Hibernate.STRING);
		query.addPara(id,Hibernate.STRING);

		List objList = new ArrayList();
		objList = baseDAO.findEntity(query);

		/** 如果他们不存在 return null */
		if (objList.size() == 0) {
			return null;
		} else {
			return (Area) objList.get(0);
		}
	}

	/**
	 * @return 返回 baseDAO。
	 */
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	/**
	 * @param baseDAO
	 *            要设置的 baseDAO。
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
	 * @param clasz
	 *            要设置的 clasz。
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
	 * @param orderBy
	 *            要设置的 orderBy。
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public List getAll() {
		return baseDAO.findAll(clasz);
	}

}
