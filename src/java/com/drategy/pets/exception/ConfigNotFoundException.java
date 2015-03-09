//$Source: /petSys/petSys/src/java/com/drategy/pets/exception/ConfigNotFoundException.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:12:20 $

package com.drategy.pets.exception;

/**
* 系统的ConfigNotFoundException
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.4 $
*/
public class ConfigNotFoundException
    extends BaseRunTimeException {

  /**
   * 构造函数�캯��
   */
  public ConfigNotFoundException() {
    super("没有此配置键值");
  }

  /**
   * 构造函数��캯��
   * @param message String
   */
  public ConfigNotFoundException(String message) {
    super("没有此配置键值：" + message);
  }

}