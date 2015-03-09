//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/Para.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:12:21 $


package com.drategy.pets.dao;

import net.sf.hibernate.type.*;
/**
* 系统的hibernate  parameter 类
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.4 $
*/
public class Para {
  
	/**对象 */
  private Object value;
  
  /**类型*/
  private Type type;
  
  /**
   *构造函数
   */
  public Para() {

  }
  
  /**
   * 构造函数
   * @param value
   * @param type
   */
  public Para(final Object value, final Type type) {
    this.type = type;
    this.value = value;
  }
  
  /**
   * 
   * @return
   */
  public Type getType() {
    return type;
  }
 
  /**
   * 
   * @return
   */
  public Object getValue() {
    return value;
  }
  
  /**
   * 
   * @param type
   */
  public void setType(Type type) {
    this.type = type;
  }
  
  /**
   * 
   * @param value
   */
  public void setValue(Object value) {
    this.value = value;
  }

}
