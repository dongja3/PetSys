//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/BaseDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/13 06:07:32 $

package com.drategy.pets.dao.hibernate;

import java.io.*;
import java.util.*;

import org.springframework.dao.*;
import org.springframework.orm.hibernate.support.*;

import net.sf.hibernate.*;
import net.sf.hibernate.type.*;
import com.drategy.pets.context.*;
import com.drategy.pets.dao.*;
import com.drategy.pets.exception.*;
import com.drategy.pets.util.*;

/**
 * 系统的BaseDAO接口的实现类
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.5 $
 */
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {

	private HibernateCfg hibernateCfg;

	/** * Creates a new BaseDAOImpl object. */
	public BaseDAOImpl() {

	}

	/**
	 * �找bean list
	 * 
	 * @param query
	 *            MyQuery
	 * @return entity array
	 */
	public final List findEntity(final MyQuery myQuery) {
		StringBuffer querystr = new StringBuffer(myQuery.getQueryString());
		if (myQuery.getOrderby() != null) {
			querystr.append(myQuery.getOrderby());
		}
		if (myQuery.getGroupby() != null) {
			querystr.append(myQuery.getGroupby());
		}
		Query returnQuery = null;
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			returnQuery = session.createQuery(querystr.toString());
			if (myQuery.getParalist() != null) {
				List list = myQuery.getParalist();
				for (int i = 0, n = list.size(); i < n; i++) {
					Para param = (Para) list.get(i);
					if (param.getValue() == null) {
						throw new DAOException("Query:" + querystr.toString()
								+ ", Param" + i + " is NULL");
					}
					returnQuery.setParameter(i, param.getValue(), param
							.getType());
				}
			}
			if ((myQuery.getPageStartNo() != 0) || myQuery.isOffset()) {
				int pageno = myQuery.getPageStartNo();
				returnQuery.setFirstResult(pageno);
				returnQuery.setMaxResults((pageno) + myQuery.getPageSize());
			}
			returnQuery.setCacheable(true);
			returnQuery.setCacheRegion("frontpages");
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			SystemLogger.error("[BaseDAOImpl]Create query from session error:"
					+e.toString());
		} catch (DataAccessException e) {
			SystemLogger.error("[BaseDAOImpl]Create query from session error:"
					+e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {
			SystemLogger.error("[BaseDAOImpl]Create query from session error:"
					+ e.toString());
			throw new DAOException(e);
		}
		try {
			return returnQuery.list();
		} catch (HibernateException ex) {
			return null;
		}
	}

	/**
	 * ͨ通过 query String ������
	 */
	public final List findEntity(final String query) {

		MyQuery myquery = new MyQuery();
		myquery.setQueryString(query);

		return findEntity(myquery);
	}

	/**
	 * 从数据库删除一个对象�����
	 */
	public final void removeEntity(final String query) {

		MyQuery myquery = new MyQuery();
		myquery.setQueryString(query);
		removeEntity(myquery);
	}

	/**
	 * ��从数据库删除一个对象�������
	 */
	public final void removeEntity(final MyQuery myquery) {
		Session ses = null;
		try {
			ses = this.getHibernateTemplate().getSessionFactory().openSession();
			if (myquery.getParalist() != null) {
				List list = myquery.getParalist();
				Object[] values = new Object[list.size()];
				Type[] types = new Type[list.size()];
				for (int i = 0, n = list.size(); i < n; i++) {
					Para param = (Para) list.get(i);
					values[i] = param.getValue();
					types[i] = param.getType();
				}
				ses.delete(myquery.getQueryString(), values, types);
			} else {
				ses.delete(myquery.getQueryString());
			}
			ses.close();
		} catch (Exception ex) {
			SystemLogger.error(ex.toString());
		}

	}

	public void removeEntity(Class clasz, java.io.Serializable id) {

		removeEntity(this.loadEntity(clasz, id));
	}

	/**
	 * ��加载一个类�����ݿ���
	 * 
	 * @param obj
	 *            Object
	 * @param id
	 *            Serializable
	 * @return entity
	 */
	public final Object loadEntity(final Object obj, final Serializable id) {

		Object entity;

		try {

			entity = getHibernateTemplate().load(obj.getClass(), id);
		} catch (DataAccessException e) {

			SystemLogger.error("[BaseDAOImpl]load entity error: " +e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {

			SystemLogger.error("[BaseDAOImpl]load entity error: " +e.toString());
			throw new DAOException(e);
		}

		return entity;
	}

	/**
	 * ���加载一个类������ݿ���
	 */
	public final Object loadEntity(final Class clasz, final Serializable id) {
		Object obj = null;

		try {

			obj = getHibernateTemplate().load(clasz, id);
		} catch (DataAccessException e) {

			SystemLogger.error("[BaseDAOImpl]load entity error: " +e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {

			SystemLogger.error("[BaseDAOImpl]load entity error: " +e.toString());
			throw new DAOException(e);
		}

		return obj;
	}

	/**
	 * Remove a object from database
	 * 
	 * @param obj
	 *            Object
	 * 
	 */
	public final void removeEntity(final Object obj) {
		try {

			getHibernateTemplate().delete(obj);
		} catch (DataAccessException e) {

			SystemLogger.error("[BaseDAOImpl]remove entity error: " +e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {

			SystemLogger.error("[BaseDAOImpl]remove entity error: " +e.toString());
			throw new DAOException(e);
		}
	}

	/**
	 * insert or update a object to database
	 * 
	 * @param obj
	 *            Object
	 * @return entity
	 */
	public final Object saveEntity(final Object obj) {

		try {

			getHibernateTemplate().save(obj);
		} catch (DataAccessException e) {

			SystemLogger.error("[BaseDAOImpl]save entity error: " +e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {

			SystemLogger.error("[BaseDAOImpl]save entity error: " +e.toString());
			throw new DAOException(e);
		}

		return obj;
	}

	/**
	 * save or update a object to database
	 * 
	 * @param obj
	 *            Object
	 * @return entity
	 */
	public Object saveOrUpdateEntity(Object obj) {
		try {

			getHibernateTemplate().saveOrUpdate(obj);
		} catch (DataAccessException e) {

			SystemLogger.error("[BaseDAOImpl]save entity error: " +e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {

			SystemLogger.error("[BaseDAOImpl]save entity error: " +e.toString());
			throw new DAOException(e);
		}

		return obj;

	}

	/**
	 * update a object
	 * 
	 * @param obj Object
	 * @return entity Object
	 */
	public final Object updateEntity(final Object obj) {

		try {

			getHibernateTemplate().update(obj);
		} catch (DataAccessException e) {

			SystemLogger.error("[BaseDAOImpl]update entity error: " +e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {

			SystemLogger.error("[BaseDAOImpl]update entity error: " +e.toString());
			throw new DAOException(e);
		}

		return obj;
	}

	/**
	 * ��� GetQuery
	 * 
	 * @param obj
	 *            Object
	 * @return Query
	 */
	private Query getQuery(final MyQuery myquery) {
		StringBuffer querystr = new StringBuffer(myquery.getQueryString());
		if (myquery.getOrderby() != null) {
			querystr.append(myquery.getOrderby());
		}
		if (myquery.getGroupby() != null) {
			querystr.append(myquery.getGroupby());
		}
		Query query = null;
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			query = session.createQuery(querystr.toString());
			if (myquery.getParalist() != null) {
				List list = myquery.getParalist();
				for (int i = 0, n = list.size(); i < n; i++) {
					Para param = (Para) list.get(i);
					if (param.getValue() == null) {
						throw new DAOException("[BaseDAOImpl]Query:" + querystr.toString()
								+ ", Param" + i + " is NULL");
					}
					query.setParameter(i, param.getValue(), param.getType());
				}
			}
			if ((myquery.getPageStartNo() != 0) || myquery.isOffset()) {
				int pageno = myquery.getPageStartNo();
				query.setFirstResult(pageno);
				query.setMaxResults((pageno) + myquery.getPageSize());
			}
			query.setCacheable(true);
			query.setCacheRegion("frontpages");
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			SystemLogger.error("[BaseDAOImpl]Create query from session error:" +e.toString());
		} catch (DataAccessException e) {
			SystemLogger.error("[BaseDAOImpl]Create query from session error:" +e.toString());
			throw new DAOException(e);
		} catch (Throwable e) {
			SystemLogger.error("[BaseDAOImpl]Create query from session error:" +e.toString());
			throw new DAOException(e);
		}
		return query;
	}

	/**
	 * find all object to database
	 * 
	 * @param clasz
	 *            Class
	 * @return list
	 */
	public List findAll(Class clasz) {
		return findAll(clasz, null);
	}

	/**
	 * find all object to database
	 * 
	 * @param clasz
	 *            Class
	 * @param order
	 *            String
	 * @return list
	 */
	public List findAll(Class clasz, String order) {
		MyQuery myQuery = new MyQuery();
		myQuery.setQueryString(" from " + clasz.getName());
		if (order != null) {
			myQuery.setQueryString(myQuery.getQueryString() + " " + order);
		}
		return findEntity(myQuery);
	}

	public HibernateCfg getHibernateCfg() {
		return hibernateCfg;
	}

	public void setHibernateCfg(HibernateCfg hibernateCfg) {
		this.hibernateCfg = hibernateCfg;
	}

	/**
	 * 
	 */
	public boolean isExists(Class clasz, String propertyName,
			Object propertyValue) {
		MyQuery myQuery = new MyQuery();
		myQuery.setQueryString("select o." + propertyName + " from "
				+ clasz.getName() + " o where o." + propertyName + "=?");
		myQuery.addPara(propertyValue, hibernateCfg
				.getHibernateType(propertyValue));
		List list = findEntity(myQuery);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

}