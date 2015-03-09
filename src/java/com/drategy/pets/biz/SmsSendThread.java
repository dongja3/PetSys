//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/SmsSendThread.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 04:18:35 $

package com.drategy.pets.biz;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import com.drategy.pets.bom.Phone;
import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Global;
import com.drategy.pets.context.HibernateCfg;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.PetOwner;
import com.drategy.pets.domain.Sms;
import com.drategy.pets.domain.SmsSend;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.DateTools;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * 短信发送线程
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.4 $
 */

public class SmsSendThread extends Thread {
    
    /**服务*/
    private static StructService structService;
    
    protected String sendContext ;
    
    public SmsSendThread(){        
        structService = (StructService)Global.getInstance().getService("structService");
    }
    
    /**
     * 线程启动
     */
    public  void run(){
        
         SystemLogger.info("发送线程已启动");
	     while(true){
	         
	         /**每一分钟检测一次*/
	         try{
	             Thread.sleep(60000);
	         }catch(InterruptedException e){
	             
	         }
	         
	         List smsList = structService.findSmsBySendState(Sms.SendNow_No,DateTools.getStandardYearMonthDay());
	         
	         if(smsList!=null){
	             
		         for(int i=0;i<smsList.size();i++){
		             
		             Sms tempSms = (Sms)smsList.get(i);
		             sendContext =  tempSms.getContent();
		             String areaId = tempSms.getAreaCode();
		             String ownerType = tempSms.getOwnerType();
		             tempSms.setSend(Sms.SendNow_Yes);
		             
		             try{
		                 List petOwnerList = getPetOwnerList(areaId ,ownerType); 
		                 structService.updateSms(tempSms);		
		                 
		                 /**向发送表里插入数据*/
		                 interData(petOwnerList,sendContext);
		             }catch(HibernateException ex){
		                 SystemLogger.error("thread error:"+ex.toString());
		             }            
		             
		         }  
	         }
	         
	         
	     }
    }
    
    
    /**
     * 得到所有要发送的宠物主人的列表
     * @return
     */
    public List getPetOwnerList(String areaId ,String ownerType) throws HibernateException{
        
        
        int areaLength = Integer.parseInt(Configuration.getAppConfig().findConfigs("statConfig").find("areaLength"));
        
        /**找到当前Area*/
        Area tempArea = structService.findArea(areaId);
        String areaCode = tempArea.getAreaCode();
                                        
        
        if(tempArea.getFather().getId().equals(Area.ROOT_AREA)){
            /**顶级*/
            areaCode = areaCode.substring(0,areaLength * 1);
            
        }else if(tempArea.getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**次级*/
            areaCode = areaCode.substring(0,areaLength * 2);
            
        }else if(tempArea.getFather().getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**再级*/
            areaCode = areaCode.substring(0,areaLength * 3);
        }
    
        String strSql = "from PetOwner p" ;      
        
        if(ownerType.equals(Sms.User_ALL)){
            
            strSql = strSql + " where 1=1 and p.area.areaCode like '%"+areaCode+"%'";
            
        }else if(ownerType.equals(Sms.User_InService)){
            
            strSql = strSql + " where p.area.areaCode like '%"+areaCode+"%' and p.smsService='";
            strSql = strSql +Sms.SendNow_Yes +"' and '"+DateTools.getStandardYearMonthDay()+"' between  p.startTime and p.endTime"; 
            
        }else if(ownerType.equals(Sms.User_NotInService)){
            
            strSql = strSql + " where p.area.areaCode like '%"+areaCode+"%' and p.smsService='";
            strSql = strSql +Sms.SendNow_No +"'"; 
           
        }else if(ownerType.equals(Sms.User_willExpired)){
                      
            GregorianCalendar worldTour = new GregorianCalendar(Integer.parseInt(DateTools.getYear()),Integer.parseInt(DateTools.getMonth())-1,Integer.parseInt(DateTools.getDay()));
            worldTour.add(GregorianCalendar.DATE, 5);
            Date d = worldTour.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String addDate = df.format(d);            

            strSql = strSql + " where p.area.areaCode like '%"+areaCode+"%' and p.smsService='";
            strSql = strSql +Sms.SendNow_Yes +"' and p.startTime <'"+DateTools.getStandardYearMonthDay()+"' and  '"+ DateTools.getStandardYearMonthDay() +"'< p.endTime and p.endTime <'"+addDate+"'"; 
            
        }else{
            strSql = strSql + " where p.area.areaCode like '%"+areaCode+"%' and p.smsService='";
            strSql = strSql +Sms.SendNow_Yes +"' and p.endTime < '"+ DateTools.getStandardYearMonthDay() +"'"; 
        }
        
        List list = new ArrayList();

        /** 创建一个hibernate对象 */
        HibernateCfg cfg = (HibernateCfg) com.drategy.pets.context.Global
                .getInstance().getService("hibernateCfg");

        /** 开启一个session */       
        Session ses = cfg.openSession();
        Query query = null;
        query = ses.createQuery(strSql);
        list = query.list();
        cfg.closeSession();
        
        return list;
    }
    
    
    /**
     * 增加要发送的数据
     * @param petOwnerList
     * @param sendContext
     */
    public void interData(List petOwnerList,String sendContext){        
        
        if (petOwnerList!=null){
        
	        for(int i=0;i<petOwnerList.size();i++){
	            
	            /**取得用户*/
	            
	            PetOwner petOwner = (PetOwner)petOwnerList.get(i);
	            Phone phone = petOwner.getPhone();
	            
	            //手机号码是否为null或者是"",就不增加记录
	            if (!Tools.isNullOrEmpty(phone.getMobileTelephone())) {
	                /**新建一个发送对象*/
		            SmsSend smsSend = new SmsSend();
		            smsSend.setId(Tools.genResourceId(""));
		            smsSend.setSmsNo(phone.getMobileTelephone());
		            smsSend.setSmsContext(sendContext);
		            smsSend.setSmsSendState(SmsSend.NOT_SEND);
		            smsSend.setSendTime(0);
		            
		            SystemLogger.debug("sendNo:"+phone.getMobileTelephone());
		            
		            /**保存对象*/
		            structService.addSmsSend(smsSend);  
	            }
	            
	        }  
        }
        
    }
}
