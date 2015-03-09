//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/SearchGenertor.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 03:12:51 $

package com.drategy.pets.biz;

import net.sf.hibernate.type.Type;

import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
* 系统的搜索产生器,searchGenertor
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.4 $
*/

public class SearchGenertor {

    /**搜索的字段**/    
    private String searchFields;
    
    /**搜索字段在数据库的类型**/
    private java.util.List fieldDBTypes;

    /**搜索字段在数据库的值**/
    private java.util.List fieldDBValues;

    /**搜索字段在http的名字**/
    private java.util.List fieldHttpNames;

    /**搜索字段在http的值**/
    private java.util.List fieldHttpValues;
    
    /**排序的字符串**/
    private String orderByString;

    /**临时字符串**/
    private StringBuffer queryBuf = new StringBuffer();

    /**构造函数初始化参数**/ 
    public SearchGenertor() {
      fieldHttpNames = new java.util.ArrayList();
      fieldDBTypes = new java.util.ArrayList();
      fieldDBValues = new java.util.ArrayList();
      fieldHttpValues = new java.util.ArrayList();
      searchFields = "" ;
      orderByString = "";
    }
    
    /**
     * 得到整个搜索字符串�
     * @return
     */
    public String getQueryString(){
        
        /**临时字符串是否为空**/
        if(queryBuf.toString().equals("")){
            queryBuf.append(getSearchFields());
        } 
        
        queryBuf.append(getOrderByString());
        
        return queryBuf.toString();
    }
    
    /**
     * 设置用=的搜索条件字段�
     * @param objectName
     * @param DBfieldName
     * @param DBfieldType
     * @param DBfieldValue
     * @param fieldHttpName
     * @param fieldHttpValue
     */
    public void setEqualsParater(String objectName,String DBfieldName,Object DBfieldType,Object DBfieldValue,String fieldHttpName,String fieldHttpValue){
        
        String tempStr = "" ;
        tempStr = " and "+ objectName+"."+DBfieldName+"=?";
        
        /**临时字符串是否为空**/
        if(queryBuf.toString().equals("")){
            queryBuf.append(getSearchFields());
        }        
        
        queryBuf.append(tempStr);
        fieldDBValues.add(DBfieldValue);
        fieldDBTypes.add(DBfieldType);
        fieldHttpNames.add(fieldHttpName);
        fieldHttpValues.add(fieldHttpValue);        
    }   
    
    /**
     * 设置用like的搜索条件字段�
     * @param objectName
     * @param DBfieldName
     * @param DBfieldType
     * @param DBfieldValue
     * @param fieldHttpName
     * @param fieldHttpValue
     */
    public void setLikeParater(
            String objectName,
            String DBfieldName,
            Object DBfieldType,
            Object DBfieldValue,
            String fieldHttpName,
            String fieldHttpValue){
        
        String tempStr = "" ;
        tempStr = " and "+ objectName+"."+DBfieldName+" like ?";
        
        /**临时字符串是否为空**/
        if(queryBuf.toString().equals("")){
            queryBuf.append(getSearchFields());
        }
        
        queryBuf.append(tempStr);
        fieldDBValues.add("%" + DBfieldValue + "%");
        fieldDBTypes.add(DBfieldType);
        fieldHttpNames.add(fieldHttpName);
        fieldHttpValues.add(fieldHttpValue);        
    }   
    
    
    /**
     * 设置where 条件 and 字段
     * @param objectName
     * @param DBfieldName
     * @param DBfieldType
     * @param DBfieldValue
     * @param fieldHttpName
     * @param fieldHttpValue
     */
    public void setWhereParaterAnd(String wherePara){
        
        String tempStr = "" ;
        tempStr = " and "+ wherePara ;
        
        /**临时字符串是否为空**/
        if(queryBuf.toString().equals("")){
            queryBuf.append(getSearchFields());
        }        
        
        queryBuf.append(tempStr);            
    }   
    
    /**
     * 设置where 条件 or 字段
     * @param objectName
     * @param DBfieldName
     * @param DBfieldType
     * @param DBfieldValue
     * @param fieldHttpName
     * @param fieldHttpValue
     */
    public void setWhereParaterOr (String wherePara){
        
        String tempStr = "" ;
        tempStr = " or "+ wherePara ;
        
        /**临时字符串是否为空**/
        if(queryBuf.toString().equals("")){
            queryBuf.append(getSearchFields());
        }        
        
        queryBuf.append(tempStr);            
    }  
    
    /**
     *  设置搜索的字段
     *  @param searchFields
     */
    public void setSearchFields(String searchFields){
        this.searchFields = searchFields ;
    }
    
    /**
     * get搜索的字段�ֶ�
     * @return
     */ 
    public String getSearchFields(){
        
        /**是否设置好了搜索字段**/
        if(searchFields.equals("")){
            SystemLogger.error("搜索产生器的搜索的字段没有设置(searchField)");
        }
        
        String tempSearchFields =this.searchFields;
        return tempSearchFields;       
    }
    
    
    /**
     * 设置http参数
     */
     public void setHttpParameter(String fieldHttpName,String fieldHttpValue){
        fieldHttpNames.add(fieldHttpName);
        fieldHttpValues.add(fieldHttpValue);    
     }
     
     /**得到参数字符串**/
     public String getParameter() {
        return Tools.wrapRequestParams(getFieldHttpNames(), getFieldHttpValues());
      }
      
      /***把FieldHttpNames 转化String[]*/
      public String[] getFieldHttpNames() {
        return (String[]) fieldHttpNames.toArray(new String[fieldHttpNames.size()]);
      }

      /***
       * 把FieldDBValues 转object[]
       * @return
       */
      public Object[] getFieldDBValues() {
        return (Object[]) fieldDBValues.toArray(new Object[fieldDBValues.size()]);
      }

      /***
       * 把FieldHttpValues 转object[]
       * @return
       */
      public Object[] getFieldHttpValues() {
        return (Object[]) fieldHttpValues.toArray(new Object[fieldHttpValues.size()]);
      }

      /***
       * 把FieldDBTypes 转Type[]
       * @return
       */
      public Type[] getFieldDBTypes() {
        return (Type[]) fieldDBTypes.toArray(new Type[fieldDBTypes.size()]);
      }    
      
      /***
       * 设置排序字段
       * @param orderByString
       */
      public void setOrderByString(String orderByString){
          this.orderByString = orderByString;
      }
      
      /***
       * 
       * @return
       */
      public String getOrderByString(){
          return this.orderByString;
      }
}
