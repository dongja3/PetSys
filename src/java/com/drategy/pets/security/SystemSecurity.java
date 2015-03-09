
package com.drategy.pets.security;

import java.io.*;
import java.security.*;
import sun.misc.*;
import java.util.Properties;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.DateTools;

/**
 * <p>Title: 判断系统是否是正式版还是试用版</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class SystemSecurity {
  
   //有效用户
  public static final String  USER_AVAILABLE="pass";
  
   //非法用户
  public static final String  USER_NOT_AVAILABLE="no_pass";
  
  //试用版过期用户
  public static final String  USER_DATE_OVER="date_over";
  
  public SystemSecurity() {

  }
  
  /**
   * 验证数字签名
   * @param messages
   * @param digest
   * @param keyFilePath
   * @return
   */
  public  boolean verify(String messages[], String digest, String keyFilePath) {

    //创建一个公钥
    PublicKey publicKey = null ;

    //载入PublicKey
      try {
          //创建一个输入流
          ObjectInputStream in = new ObjectInputStream(new FileInputStream(keyFilePath+"pubkey.dat"));
          publicKey = (PublicKey)in.readObject();
          in.close();
      }
      catch (Exception ex) {
          SystemLogger.error("导入公钥失败："+ex.toString());
      }

      //BASE64解码 
      BASE64Decoder decoder = new BASE64Decoder();

      byte[] signed = null;
      try {
          signed = decoder.decodeBuffer(digest);
      }
      catch (IOException ex) {
          SystemLogger.error(ex.toString());
          return false;
      }

      //验证密钥对
      Signature signature = null;
      try {
          signature = Signature.getInstance("DSA");
      }
      catch (java.security.NoSuchAlgorithmException ex) {
          SystemLogger.error(ex.toString());
          return false;
      }
      try {
          signature.initVerify(publicKey);
          for (int i = 0; i < messages.length; i++) {
              signature.update(messages[i].getBytes());
          }
          return signature.verify(signed);
      }
      catch (Exception ex) {
          SystemLogger.error(ex.toString());
          return false;
      }
  }

  /**
   * 从properties文件,验证有效性
   * @param filePath String
   * @param fileName String
   * @return boolean
   */
  public  boolean verifyByProperties(String filePath,String fileName){
    
    //创建一个属性实例
    Properties properties = new Properties();

    //装载license.properties
    try{
       InputStream inputStream = new FileInputStream(filePath +fileName);
       properties.load(inputStream);
    }catch(Exception e){
        SystemLogger.error(e.toString());
    }

    String  sign = properties.getProperty("sign");

    //BASE64解码
     BASE64Decoder decoder = new BASE64Decoder();

    //设置参数
    String[] paraArray = new String[3];
    
    //设置参数
    try{
       paraArray[0] = new String(decoder.decodeBuffer(properties.getProperty("companyName")),"GBK");
       paraArray[1] = new String(decoder.decodeBuffer(properties.getProperty("regTime")),"GBK");
       paraArray[2] = new String(decoder.decodeBuffer(properties.getProperty("systemMode")),"GBK");
    }
    catch (IOException ex) {
        SystemLogger.error(ex.toString());
    }
    
    //是否通过数字签名
    if (verify(paraArray, sign, filePath)) {
      return true;
    }
    else {
      return false;
    }
    
  }

  
  /**
   * 验证各种用户,返回有效性
   * @param filePath
   * @param propertiesFileName
   * @return
   */
  public  String securityVerify(String filePath,String propertiesFileName){
                  
      //创建临时文件
      File tempFile = new File(filePath +propertiesFileName);
      
      if(!tempFile.exists()){
          return this.USER_NOT_AVAILABLE;
      }
      
      //创建一个属性实例
      Properties properties = new Properties();
      
      // 装载license.properties
      try{
          InputStream inputStream = new FileInputStream(filePath +propertiesFileName);
          properties.load(inputStream);
      }catch(Exception e){
          SystemLogger.error(e.toString());
      }
      
      //BASE64解码
      BASE64Decoder decoder = new BASE64Decoder();
      
      //验证数字签名
      if(verifyByProperties(filePath,propertiesFileName)){
          
          try{
              //注册日期
              String regTime =  new String(decoder.decodeBuffer(properties.getProperty("regTime")),"GBK");
              
              //系统运行模式
              String systemMode = new String(decoder.decodeBuffer(properties.getProperty("systemMode")),"GBK");
              
              //当前日期
              String currDate =DateTools.getStandardYearMonthDay();
              
              //SystemLogger.info("currDate=:"+currDate);
              
              //取得年月日
              int tempYear = Integer.parseInt(regTime.substring(0,4));
              int tempMonth =Integer.parseInt( regTime.substring(5,7));
              int tempDay  = Integer.parseInt(regTime.substring(8,10));
              
              //如果运行版本是正式版,就直接返回有效用户
              if(systemMode.equals("正式版")){
                  return this.USER_AVAILABLE ;
              }else{
                  
                  //如果是试用版,检测是否超出90天
                  String tempDate = DateTools.addDate(tempYear,tempMonth,tempDay,30);                 
                  //SystemLogger.info("tempDate:="+tempDate+"currDate=:"+currDate);
                  if(tempDate.compareTo(currDate)> 0 ) {                      
                      return this.USER_AVAILABLE ;
                  }else{
                      return this.USER_DATE_OVER ;
                  }
                 
              }
              
          }catch(UnsupportedEncodingException e){
              return this.USER_NOT_AVAILABLE;
          }catch(IOException ex){
              return this.USER_NOT_AVAILABLE;
          }          
        
          
      }else{
          return this.USER_NOT_AVAILABLE;
      }
           
  }   
  

}
