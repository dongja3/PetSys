//$Source: /petSys/petSys/src/java/com/drategy/pets/util/SystemLogger.java,v $
//LastModified By: $Author: jason.jiang $
//$Date: 2006/03/16 07:34:50 $

package com.drategy.pets.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
* SystemLogger class for system Logging.
*
* @author Jackie Dong
* @author $Author: jason.jiang $
* @version $Revision: 1.5 $
*/
public class SystemLogger {

  /**log4j 配置文件相对路径*/
  private static final String CONFIG_FILE = "/conf/systemlog.properties";
  
  /**log4j 日志文件相对路径*/
  private static final String LOG_FILE = "/logs/system.log";
  
  /**log4j 配置文件中的一个item配置信息*/
  private static final String FILE_KEY = "log4j.appender.IntFile.File";

  /**Servlet绝对在系统中的绝对路径*/
  private static String servletRoot;

  /**
   * constructor
   */
  public SystemLogger() {
  	 	
  }

  private static Logger logger;


  /**
   * logging debug info
   *
   * @param s logged message
   */
  public static void debug(String s) {
      logger.debug(s);
  }

  /**
   * logging info info
   *
   * @param s logged message
   */
  public static void info(String s) {
      logger.info(s);
  }

  /**
   * logging warn info
   *
   * @param s logged messge
   */
  public static void warn(String s) {
      logger.warn(s);
  }

  /**
   * logging error info
   *
   * @param s logged message
   */
  public static void error(String s) {
      logger.error(s);
  }

  /**
   * logging fatal info
   *
   * @param s
   */
  public static void fatal(String s) {
      logger.fatal(s);
  }

  /**
   * set absolute path of web application root
   * and set config file and log file of log4j.
   *
   * @param path absolute path of web application root
   */
  public static void setServletRoot(String path) {
      servletRoot = path;
      Properties properties = new Properties();

      try {
          InputStream stream = new FileInputStream(path + CONFIG_FILE);
          properties.load(stream);
      } catch (Exception e) {

      }

      // set FILE property
      properties.setProperty(FILE_KEY, path + LOG_FILE);      
      

      PropertyConfigurator.configure(properties);
      logger = Logger.getLogger(SystemLogger.class);
  }

  /**
   * Return servlet root.
   *
   * @return servlet root
   */
  public static String getServletRoot() {
      return servletRoot;
       
  }

}
