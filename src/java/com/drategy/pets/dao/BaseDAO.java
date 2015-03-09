//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/BaseDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:12:21 $
package com.drategy.pets.dao;


import java.io.*;

/**
 * 系统的基础dao接口
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.4 $
 */
public interface BaseDAO {

  /**
   * 查找对象
   * @param query 
   * @return
   */
  java.util.List findEntity(String query);

  /**!
   * 查找对象list
   * @param query
   * @return 
   */
  java.util.List findEntity(MyQuery query);

  /**
   * 加载对象 
   * @param obj 
   * @param id 
   * @return 
   */
  Object loadEntity(Object obj, Serializable id);

  /**
   * 加载对象 
   * @param clasz 
   * @param id 
   * @return 
   */
  Object loadEntity(Class clasz, Serializable id);

  /**
   * DOCUMENT ME!
   *
   * @param obj DOCUMENT ME!
   */
  void removeEntity(Object obj);

  /**
   * 删除对象
   * @param obj 
   */
  void removeEntity(MyQuery query);
  
  /**
   * 删除对象
   * @param query
   */
  void removeEntity(String query);
  
  /**
   * 删除对象
   * @param clasz Class
   * @param id Serializable
   */
  void removeEntity(Class clasz, java.io.Serializable id);

  /**
   * 保存对象
   * @param obj 
   * @return 
   *
   */
  Object saveEntity(Object obj);
  
  /**
   * 保存并修改对象
   * @param obj Object
   * @return Object
   */
  Object saveOrUpdateEntity(Object obj);

  /**
   * 修改对象
   * @param obj 
   * @return
   */
  Object updateEntity(Object obj);

  /**
   * 查询出所有对象
   * @return List
   */
  java.util.List findAll(Class clasz);

  /**
   * 查询出所有对象并排序
   * @return List
   */
  java.util.List findAll(Class clasz, String order);

  /**
   * 是否存在一个属性的值为propValue的对象
   * @param clasz
   * @param propName
   * @param propValue
   * @return
   */
  boolean isExists(Class clasz, String propName, Object propValue);

}
