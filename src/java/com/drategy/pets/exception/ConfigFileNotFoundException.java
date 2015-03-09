//$Source: /petSys/petSys/src/java/com/drategy/pets/exception/ConfigFileNotFoundException.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:35:46 $


package com.drategy.pets.exception;


/**
* 系统的configFileNotFoundException
* 
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.5 $
*/

public class ConfigFileNotFoundException
    extends BaseRunTimeException {

  /**
   * 构造函数��캯��
   */
  public ConfigFileNotFoundException() {
    super("配置文件不存在:");
  }

  /**
   * ���캯��
   * @param message String 
   */
  public ConfigFileNotFoundException(String message) {
    super("配置文件不存在：" + message);
  }

}