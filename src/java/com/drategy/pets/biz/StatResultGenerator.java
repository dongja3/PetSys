//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/StatResultGenerator.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/02/22 08:44:38 $

package com.drategy.pets.biz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.drategy.pets.util.SystemLogger;

import net.sf.hibernate.HibernateException;

/**
 *  统计结果产生器
 * @author Jason Jiang
 * @author $Author $
 * @$Revision: 1.5 $
 */
public class StatResultGenerator {

    /**统计数据产生器*/
    private DataGenerator dataGenerator;
    
    /***
     * 取得结果数据
     * @param statSqlGenerator
     * @return
     */
  	public Map getStatResultData(StatSqlGenerator statSqlGenerator,String para){
      /**得到sqlMap*/
      Map sqlResultMap = statSqlGenerator.getSqlMap(para);
      
      /**创建返回dataResultMap*/
      Map dataResultMap = new HashMap();
      
      /**sqlKeyItem*/
      Iterator sqlItem = sqlResultMap.keySet().iterator();
      
      /**循环读书sql语句*/
      while(sqlItem.hasNext()){
          
	          /**行标题*/
	          String rowKey = (String)sqlItem.next();
	          
	          /**取得行sqlMap*/
	          Map rowSqlMap =(Map)sqlResultMap.get(rowKey);
	          
	          /**创建行returnDataMap*/
	          Map rowDataMap = new HashMap();
	          
	          Iterator rowSqlItem = rowSqlMap.keySet().iterator();
	          
	          while( rowSqlItem.hasNext()){
	             
	             /**列标题*/
	             String titleKey = (String)rowSqlItem.next();
	             
	             /**title sql*/
	             String titleSql = (String)rowSqlMap.get(titleKey);
	             
	             /**得到统计数据放到结果map*/
	             try{
		             int resultData = dataGenerator.generatorIntBySql(titleSql);
		             SystemLogger.debug("titleKey:="+titleKey);
		             SystemLogger.debug("sql:="+titleSql);
		             rowDataMap.put(titleKey,String.valueOf(resultData));
	             }catch(HibernateException ex){
	                 SystemLogger.error("StatResultGenerator getStatResultData :"+ex.toString());
	             }             
	          }
          
			/**设置row结果数据*/
			dataResultMap.put(rowKey,rowDataMap);  
          
      	}
      
      	/**返回结果*/
      	return dataResultMap;
	}
  	
      /**
       * 取得列表Map
       * @param statSqlGenerator
       * @return
       */
	  public Map getTitleMap(StatSqlGenerator statSqlGenerator){
	      return statSqlGenerator.getTitleMap() ;
	  }
	   
	  
	  /**
       * 取得列表Map
       * @param statSqlGenerator
       * @return
       */
	  public Map getRowMap(StatSqlGenerator statSqlGenerator){
	      return statSqlGenerator.getRowMap();
	  }
	   
	  
	  /**
	   * @return 返回 dataGenerator。
	   */
	  public DataGenerator getDataGenerator() {
	      return dataGenerator;
	  }
	  /**
	   * @param dataGenerator 要设置的 dataGenerator。
	   */
	  public void setDataGenerator(DataGenerator dataGenerator) {
	      this.dataGenerator = dataGenerator;
	  }
}
