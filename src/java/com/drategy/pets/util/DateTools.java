//$Source: /petSys/petSys/src/java/com/drategy/pets/util/DateTools.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 04:18:35 $


package com.drategy.pets.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
;

/**
* 系统的DateTools
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.9 $
*/
public class DateTools {

	 /**
	   * 返回当前时间，iFlag == 0，返回年月日时分秒
	   * iFlag == 1，返回年－月－日 时：分：秒
	   * iFlag == 2，返回年月日
	   * iFlag == 3，返回年－月－日
	   * iFlag == 4，返回年－月
	   * @param iFlag
	   * @return
	   */
	  private static String getFullTime(int iFlag) {
	    Date date = new Date();
	    SimpleDateFormat simpleDateFormat;
	    if (iFlag == 0) {
	      simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    }
	    else if (iFlag == 1) {
	      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    }
	    else if (iFlag == 2) {
	      simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	    }
	    else if (iFlag == 3) {
	      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    }
	    else if (iFlag == 4) {
	      simpleDateFormat = new SimpleDateFormat("yyyy-MM");
	    }
	    else if (iFlag == 5) {
	      simpleDateFormat = new SimpleDateFormat("yyyy-MM HH");
	    }
	    else {
	      simpleDateFormat = new SimpleDateFormat("yyyy-MM HH:mm");
	    }
	    return simpleDateFormat.format(date);

	  }

	  /**
	   * 解析字符串格式时间为标准格式 iFlag == 0 返回年－月－日 时：分：秒
	   * iFlag == 1 返回年－月－日
	   * @param sFullTime
	   * @param iFlag
	   * @return
	   */
	  private static String getFullTime(String sFullTime, int iFlag) {
	    
	  	if (sFullTime == null || sFullTime.trim().equals("")) {
	      return "";
	    }
	  	
	    StringBuffer sDateBuffer = new StringBuffer();
	    sDateBuffer.append(sFullTime.substring(0, 4) + "-");
	    sDateBuffer.append(sFullTime.substring(4, 6) + "-");
	    sDateBuffer.append(sFullTime.substring(6, 8) + " ");
	    
	    if (iFlag == 1) {
	      return sDateBuffer.toString();
	    }

	    if (iFlag == 2) {
	      sDateBuffer.append(sFullTime.substring(8, 10));
	      sDateBuffer.append("时");
	      return sDateBuffer.toString();
	    }
	    else {
	      sDateBuffer.append(sFullTime.substring(8, 10) + ":");
	    }

	    if (iFlag == 3) {
	      sDateBuffer.append(sFullTime.substring(10, 12));
	      return sDateBuffer.toString();
	    }
	    else {
	      sDateBuffer.append(sFullTime.substring(10, 12) + ":");
	    }

	    sDateBuffer.append(sFullTime.substring(12, 14));
	    return sDateBuffer.toString();

	  }

	  /**
	   * 返回当前时间的标准格式： 年－月－日 时：分：秒
	   * @return
	   */
	  public static String getStandardFullTime() {
	    return getFullTime(1);
	  }

	  /**
	   * 解析字符串格式时间为标准格式 年月日时分秒 -> 年－月－日 时：分：秒
	   * @param sFullTime
	   * @return
	   */
	  public static String getStandardFullTime(String sFullTime) {
	    return getFullTime(sFullTime, 0);
	  }

	  /**
	   * 返回当前时间的字符串格式：年月日时分秒
	   * @return
	   */
	  public static String getStringFullTime() {
	    return getFullTime(0);
	  }

	  /**
	   * 返回当前时间的标准格式：年－月－日
	   * @return
	   */
	  public static String getStandardYearMonthDay() {
	    return getFullTime(3);
	  }

	  /**
	   * 返回当前时间的标准格式：年－月－日
	   * @return
	   */
	  public static String getStandardYearMonth() {
	    return getFullTime(4);
	  }

	  public static String getStandardYearMonthDayHour() {
	    return getFullTime(5);
	  }

	  public static String getStandardYearMonthDayHourMinute() {
	    return getFullTime(6);
	  }

	  /**
	   * 解析字符串格式时间为标准格式  年月日 -> 年－月－日
	   * @param sYearMonthDay
	   * @return
	   */
	  public static String getStandardYearMonthDay(String sYearMonthDay) {
	    return getFullTime(sYearMonthDay, 1);
	  }

	  public static String getStandardYearMonthDayHour(String sFullTime) {
	    return getFullTime(sFullTime, 2);
	  }

	  public static String getStandardYearMonthDayHourMinute(String sFullTime) {
	    return getFullTime(sFullTime, 3);
	  }

	  /**
	   * 返回当前时间的字符串格式:年月日
	   * @return
	   */
	  public static String getStringYearMonthDay() {
	    return getFullTime(2);
	  }

	  /**
	   * 返回当前时间的字符串格式:年月日
	   * @return
	   */
	  public static String getYear() {
	    return getFullTime(2).substring(0, 4);
	  }

	  /**
	  * 返回当前时间的字符串格式:年月日
	  * @return
	  */
	 public static String getMonth() {
	   return getFullTime(2).substring(4, 6);
	 }


	 /**
	   * 返回当前时间的字符串格式:年月日
	   * @return
	   */
	  public static String getDay() {
	    return getFullTime(2).substring(6, 8);
	  }



	  /**
	   * 转换年－月－日 到年月日
	   * @param date
	   * @return
	   */
	  public static String parseYearMonthDay(String date) {
	    try {
	      return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
	    }
	    catch (Exception e) {
	      return null;
	    }
	  }
     
	  /**
	   * 
	   * @param date
	   * @param split
	   * @return
	   */
	 public static String parseDate(Date date, String split) {
	   
	   if (date == null) {
	     return null;
	   }
	   StringBuffer retBuffer = new StringBuffer();
	   retBuffer.append(date.getYear()+1900)
	       .append(split)
	       .append(date.getMonth()+1)
	       .append(split)
	       .append(date.getDate());
	   return retBuffer.toString();
	 }
	 
	 
	 /**
	  * 输入一个年,月,日,和增加天数,得到一个新的date 年-月-日
	  * @return string
	  */
	 public static String addDate(int year,int month,int day,int addDay){
	     
	     GregorianCalendar worldTour = new GregorianCalendar(year,month-1,day);
         worldTour.add(GregorianCalendar.DATE, addDay);
         Date d = worldTour.getTime();
         DateFormat df = DateFormat.getDateInstance();
         String addDate = df.format(d);            

	     return parseStandardYMD(addDate);
	 }
	 
	 /**
	   * 转换年－月－日 到 xxxx-xx-xx
	   * @param date
	   * @return
	   */
	 public static String parseStandardYMD(String date){
	     
	     //分割成year、month、day
	     String[] dateArray = StringUtils.split(date,"-");
	     
	     String backDate ="" ;
	     
	     for(int i=0;i<3;i++){
	         switch(i){
	            case 0:
	                backDate = backDate + dateArray[i] ;
	                break;
	            case 1:
	                if(dateArray[i].length()==1){
	                    backDate = backDate + "-0"+dateArray[i]; 
	                }else{
	                    backDate = backDate + "-"+dateArray[i];
	                }
	                
	                break;
	            case 2:
	                if(dateArray[i].length()==1){
	                    backDate = backDate + "-0"+dateArray[i]; 
	                }else{
	                    backDate = backDate + "-"+dateArray[i];
	                }
	                
	                break;
	         }
	     }
	     
	     return backDate;
	 }
}