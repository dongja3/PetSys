//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/MyQuery.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:12:21 $

package com.drategy.pets.dao;

import net.sf.hibernate.type.*;
/**
 * 系统的hibernate Query类
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.4 $
 */
public class MyQuery {


  /** 每页的数 */
  private static final int DEFAULT_PAGE_SIZE = 10;

  /**参数集合对象*/
  private ParaList paralist = new ParaList();

  /** 分组字段 */
  private String groupby;

  /**  排序字段 */
  private String orderby;

  /** HQL查询语句� */
  private String queryString;

  /** 是否使用iterate() */
  private boolean isCache = true;

  /**  是否分页  */
  private boolean isOffset = false;

  /**  分页每页的记录数  */
  private int pageSize = DEFAULT_PAGE_SIZE;

  /** 分页起始查询地址 */
  private int pageStartNo;


  /**
   * @param flage The isCache to set.
   */
  public final void setCache(final boolean flage) {

    isCache = flage;
  }

  /**
   * @return Returns the isCache.
   */
  public final boolean isCache() {

    return isCache;
  }

  /**
   * 置分组字段���ֶ�
   * @param str �������
   */
  public final void setGroupby(final String str) {

    groupby = str;
  }

  /**
   * 得分组字段���ֶ�
   * @return �������
   */
  public final String getGroupby() {

    return groupby;
  }

  /**
   * 设置排序
   * @param str �����ֶ�
   */
  public final void setOrderby(final String str) {
    orderby = str;
  }

  /**
   * get排序字段
   * @return �������ֶ�
   */
  public final String getOrderby() {

    return orderby;
  }

  /**
   * 设置页面记录数
   * @param 
   */
  public final void setPageSize(final int i) {

    pageSize = i;
  }

  /**
   * 得到页面记录数
   * @return�
   */
  public final int getPageSize() {
    return pageSize;
  }

  /**�ʼ��ַ
   * 设置开始记录号
   * @param �ʼ��ַ
   */
  public final void setPageStartNo(final int no) {
    pageStartNo = no;
  }

  /**
   * 得到开始记录号
   * @return ʼ��ַ
   */
  public final int getPageStartNo() {
    return pageStartNo;
  }

  /**
   * �
   */
  public final void setParalist(final ParaList pl) {
    paralist = pl;
  }

  /**
   * 
   * @return ��
   */
  public final ParaList getParalist() {
    return paralist;
  }

  /**
   * 设置一个HQL查询字符串ַ�
   * @param str�
   */
  public final void setQueryString(final String str) {

    queryString = str;
  }

  /**
   * ȡHibernate��Query����
   * @return �Query����
   */
  public final String getQueryString() {
    return queryString;
  }

  /**
   * 增加para
   * @param obj �������
   * @param typeNo �������
   */
  public final void addPara(Para para) {
    this.paralist.addPara(para);
  }
  
 /**
  * 增加para
  * @param value
  * @param type
  */
  public final void addPara(Object value, Type type) {
    this.paralist.addPara(new Para(value, type));
  }
  
  /**
   * 增加paraMap
   * @param paraMap
   */
  public final void addParas(java.util.Map paraMap) {

    java.util.Iterator iteValues = paraMap.keySet().iterator();
    
    //循环
    while (iteValues != null && iteValues.hasNext()) {
      Object value = iteValues.next().toString();
      addPara(value, (Type) paraMap.get(value));
    }

  }

  /**
   * @param b
   */
  public void setOffset(boolean b) {
    isOffset = b;
  }

  /**
   * @return
   */
  public boolean isOffset() {
    return isOffset;
  }
}
