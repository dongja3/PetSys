//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/ParaList.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/28 08:48:59 $

package com.drategy.pets.dao;

import java.util.*;

/**
* 系统的hibernate parameter list
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.5 $
*/
public class ParaList
    extends ArrayList {
   
	private static final long serialVersionUID = 1L;

	/**
	   * 取得指定位置的参数对象
	   * @param index ：参数的索引值
	   * @return ：参数对象
	   */
	  public final Para getParas(final int index) {

	    return (Para)super.get(index);
	  }

	  /**
	   * 在指定位置添加一个参数对象
	   * @param index ：参数的索引值
	   * @param p ：需要加入的参数对象
	   */
	  public final void addPara(final int index, final Para p) {

	    super.add(index, p);
	  }

	  /**
	   * 在集合的最后位置添加一个参数对象
	   * @param p ：需要加入的参数对象
	   */
	  public final void addPara(final Para p) {

	    super.add(p);
	  }

	  /**
	   * 取得指定参数的索引
	   * @param p ：参数对象
	   * @return ：参数索引
	   */
	  public final int indexofPara(final Para p) {

	    return super.indexOf(p);
	  }

	  /**
	   * 从集合中去掉一个指定的参数对象
	   * @param index ：参数索引
	   */
	  public final void removePara(final int index) {
	    super.remove(index);
	  }
}