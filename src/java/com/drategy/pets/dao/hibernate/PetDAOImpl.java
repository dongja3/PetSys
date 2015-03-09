//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/PetDAOImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao.hibernate;

import java.util.List;

import net.sf.hibernate.Hibernate;

import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.MyQuery;
import com.drategy.pets.dao.PetDAO;
import com.drategy.pets.domain.Pet;
import com.drategy.pets.util.SystemLogger;

/**
 * 系统的PetDAO实现类
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.6 $
 */
public class PetDAOImpl implements PetDAO {
	/** DAO的基础类 */
	private BaseDAO baseDAO;

	/** 排序字段 */
	private String orderBy;

	/** 要操作类 */
	private Class clasz;

	/** 构造函数 */
	public PetDAOImpl() {
		this.clasz = com.drategy.pets.domain.Pet.class;
		this.orderBy = "order by id desc";
	}

	/** 增加 */
	public void add(Pet pet) {
		baseDAO.saveEntity(pet);
	}

	/** 查询 */
	public Pet find(String id) {
		return (Pet) baseDAO.loadEntity(clasz, id);
	}

	/** 删除 */
	public void delete(Pet pet) {
		baseDAO.removeEntity(pet);
	}

	/** 修改 */
	public void update(Pet pet) {
		baseDAO.updateEntity(pet);
	}

	/** 通过chipNo查找Pet* */
	public Pet findPetByChipNo(String chipNo) {

		MyQuery myQuery = new MyQuery();
		myQuery.setQueryString("from Pet pet where pet.rfidChip.code=?");
		myQuery.addPara(chipNo, Hibernate.STRING);

		/** 查找结果* */
		List resultList = baseDAO.findEntity(myQuery);

		/** 是否存在对应号码的pet* */
		if (resultList.size() == 0) {
			SystemLogger.error("no find pet chipNo=" + chipNo);
			return null;
		} else {
			return (Pet) resultList.get(0);
		}
	}

	/** 通过petNo和petId查找Pet* */
	public Pet findPetByPetNo(String petNo, String petId) {
		MyQuery myQuery = new MyQuery();
		myQuery
				.setQueryString("from Pet pet where pet.petNo=? and pet.id<>?");
		myQuery.addPara(petNo, Hibernate.STRING);
		myQuery.addPara(petId, Hibernate.STRING);

		/** 查找结果* */
		List resultList = baseDAO.findEntity(myQuery);

		/** 是否存在对应号码的pet* */
		if (resultList.size() == 0) {
			return null;
		} else {
			return (Pet) resultList.get(0);
		}
	}

	/** 通过petNo查找Pet* */
	public Pet findPetByPetNo(String petNo) {
		MyQuery myQuery = new MyQuery();
		myQuery.setQueryString("from Pet pet where pet.petNo=?");
		myQuery.addPara(petNo, Hibernate.STRING);

		/** 查找结果* */
		List resultList = baseDAO.findEntity(myQuery);

		/** 是否存在对应号码的pet* */
		if (resultList.size() == 0) {
			return null;
		} else {
			return (Pet) resultList.get(0);
		}
	}
	
	/**通过areat**/
    public List findPetListByAreaCode(String areaCode){
        MyQuery myQuery = new MyQuery();
		myQuery.setQueryString("from Pet pet where pet.petOwner.area.areaCode like ?");
		myQuery.addPara("%"+areaCode+"%", Hibernate.STRING);

		/** 查找结果* */
		List resultList = baseDAO.findEntity(myQuery);

		/** 是否存在对应号码的pet* */
		if (resultList.size() == 0) {
			return null;
		} else {
			return  resultList;
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

}
