//$Source: /petSys/petSys/src/java/com/drategy/pets/context/Configuration.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:36:06 $
package com.drategy.pets.context;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.drategy.pets.exception.ConfigFileNotFoundException;
import com.drategy.pets.exception.ConfigNotFoundException;
import com.drategy.pets.util.SystemLogger;

/**
 * 系统的基础DispatchAction，所有DispatchAction必须继承该类
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.7 $
 */
public class Configuration {
  /**配置group*/ 
  private HashMap configGroup;
  /** 基础Static Configration �� */
  private static Configuration baseConfig;
  /** Static Configration*/
  private static Configuration appConfig;
  /** baseConfig配置文件路径*/
  private static String baseConfigFileName = Constant.CONFIG_PATH +
      "baseConfig.xml";
  /** appConfig 配置文件路径*/
  private static String appConfigFileName = Constant.CONFIG_PATH +
      "appConfig.xml";
  
  private Configuration() {
    configGroup = new HashMap();
  }

  public Configs findConfigs(String name) {
    if (configGroup == null || configGroup.get(name) == null) {
    	SystemLogger.error("无法找到配置组：" + name);
      throw new ConfigNotFoundException(name);
    }
    else {
      return (Configs) configGroup.get(name);
    }
  }

  private synchronized static void initAppConfig() {
    if (appConfig != null) {
      return;
    }
    appConfig = new Configuration();
    appConfig.configGroup = appConfig.parseXMLFile(appConfigFileName);
  }

  private synchronized static void initBaseConfig() {
    if (baseConfig != null) {
      return;
    }
    baseConfig = new Configuration();
    appConfig = new Configuration();
    baseConfig.configGroup = baseConfig.parseXMLFile(baseConfigFileName);
    appConfig.configGroup = appConfig.parseXMLFile(appConfigFileName);
  }

  public synchronized static Configuration getBaseConfig() {
    if (baseConfig == null) {
      initBaseConfig();
    }
    return baseConfig;
  }

  public synchronized static Configuration getAppConfig() {
    if (appConfig == null) {
      initAppConfig();
    }
    return appConfig;
  }

  /**
   * 解析配置文件，取键值对
   * @return
   */
  private HashMap parseXMLFile(String filePath) {
    configGroup = new HashMap();
    SAXReader reader = new SAXReader();
    Document document = null;
    FileInputStream is = null;

    //载入配置文件
    try {
      is = new FileInputStream(filePath);
    }
    catch (IOException ioE) {
    	SystemLogger.error("读取配置文件异常：" + ioE.toString());
      throw new ConfigFileNotFoundException(filePath);
    }

    //解析配置文件
    try {
      document = reader.read(is);
    }
    catch (DocumentException e) {
    	SystemLogger.error("解析XML配置文件错误：" + e.toString());
      try {
        is.close();
      }
      catch (IOException ioE) {}
      return null;
    }
    Element root = document.getRootElement();
    List childElm = root.elements("Configs");
    Element curElm;
    Attribute curAtt;
    String curAttName;
    for (int i = 0; i < childElm.size(); i++) {

      curElm = (Element) childElm.get(i);
      if (curElm == null) {
        continue;
      }
      curAtt = curElm.attribute("name");
      if (curAtt == null) {
        continue;
      }
      curAttName = curAtt.getValue();
      List configElmList = curElm.elements("Config");
      Configs configs = new Configs();
      for (int j = 0; j < configElmList.size(); j++) {
        curElm = (Element) configElmList.get(j);
        if (curElm == null) {
          continue;
        }
        curAtt = curElm.attribute("key");
        if (curAtt == null) {
          continue;
        }
        configs.add(curAtt.getValue(), curElm.getText());
      }
      configGroup.put(curAttName, configs);
    }

    //关闭配置文件
    try {
      is.close();
    }
    catch (IOException e) {
    }

    return configGroup;
  }

  public int getSize() {
    return configGroup.size();
  }

  public static void release() {
    appConfig = null;
    baseConfig = null;
  }

  /**
   *
   * <p>Title: Configs</p>
   * <p>Description: 基于HashMap的配置键值对,添加了自己的异常处理</p>
   * <p>Copyright: Copyright (c) 2003</p>
   * <p>Company: wadin</p>
   * @author chenbug
   * @version 1.0
   */
  public class Configs {

    private HashMap hashConfig;

    public Configs() {
      hashConfig = new HashMap();
    }

    public void add(String key, String value) {
      hashConfig.put(key, value);
    }

    public Iterator keys() {
      return hashConfig.keySet().iterator();
    }

    public String find(String key) throws ConfigNotFoundException {
      if (hashConfig.get(key) == null) {
    	  SystemLogger.error("无法找到配置键：" + key);
        throw new ConfigNotFoundException(key);
      }
      return hashConfig.get(key).toString();
    }

    public int findInt(String key) throws ConfigNotFoundException {
      return Integer.parseInt(find(key));
    }

    public long findLong(String key) throws ConfigNotFoundException {
    return Long.parseLong(find(key));
  }


  public float findFloat(String key) throws ConfigNotFoundException {
      return Float.parseFloat(find(key));
    }

    public boolean findBoolean(String key) throws ConfigNotFoundException {
      return Boolean.getBoolean(find(key));
    }


    public double findDouble(String key) throws ConfigNotFoundException {
      return Double.parseDouble(find(key));
    }


    public int getSize() {
      return hashConfig.size();
    }

  }
}
