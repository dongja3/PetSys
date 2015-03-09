//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/NewsDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.NewsDAO;
import com.drategy.pets.domain.News;

/**
 * 系统的基础NewsDAO实现类
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.2 $
 */
public class NewsDAOImpl implements NewsDAO {
	/** DAO的基础类 */
	private BaseDAO baseDAO;

	/** 排序字段 */
	private String orderBy;

	/** 要操作类 */
	private Class clasz;

	public NewsDAOImpl() {
		this.clasz = com.drategy.pets.domain.News.class;
		this.orderBy = " order by id desc";
	}

	public void add(News news) {
		baseDAO.saveEntity(news);
	}

	public News find(String id) {
		return (News)baseDAO.loadEntity(clasz,id);
	}

	public void delete(News news) {
		baseDAO.removeEntity(news);
	}

	public void update(News news) {
		baseDAO.updateEntity(news);
	}

	public List getAll() {
		return baseDAO.findAll(clasz);
	}

	public List getTop(int count) {
		List newsList = baseDAO.findAll(clasz);
		
		/**如果所有新闻的数量大于count，返回count数量的news**/
		if(newsList.size()>count){
			List returnList=new ArrayList();
			for(int i=0;i<count;i++){
				returnList.add(newsList.get(i));
			}
			return returnList;
		}
		
		/**如果所有新闻的数量小于count，直接返回所有的news**/
		return newsList;
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

}
