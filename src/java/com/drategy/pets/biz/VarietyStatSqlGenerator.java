/*
 * 创建日期 2006-2-10
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.drategy.pets.biz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.Variety;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.Tools;

/**
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class VarietyStatSqlGenerator implements StatSqlGenerator {

    
    /**设置static常量*/    
    public static final String FIRST_MAP ="FIRST";
    public static final String SECOND_MAP ="SECOND";
    
    /** 列标题* */
    private Map  titleMap;

    /** 基本sql块* */
    private String sqlBlock;

    /** 行标题* */
    private Map rowMap;
    
    /**构造函数*/
    public   VarietyStatSqlGenerator(){
        titleMap = new HashMap();
        rowMap = new HashMap();
    }

    /** 取得TitleList */
    public Map getTitleMap() {
        /**创建StructServices服务*/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**取得所有区域*/
        List areaList = structService.findAllArea();
        
        /**创建HashMap*/
        Map firstMap = new HashMap();
        Map secondMap = new HashMap();
       
        /**循环取得area*/
        for(int i=0;i<areaList.size();i++){
            Area  tempArea = (Area)areaList.get(i);
            if(!tempArea.getId().equals(Area.ROOT_AREA)){
                firstMap.put(tempArea.getName(),tempArea.getAreaCode());
                secondMap.put(tempArea.getName(),tempArea.getAreaCode());
            }
        } 
        
        /**设置titleMap*/
        titleMap.put(FIRST_MAP,firstMap);
        titleMap.put(SECOND_MAP,secondMap);
        
        /**返回结果*/
        return this.titleMap;

    }

    /** 取得sqlMap* */
    public Map getSqlMap(String sqlPara) {
        
        /**创建map */
        Map backMap = new HashMap();
        
        /**取得行标题*/
        Iterator rowKeyItem = getRowMap().keySet().iterator();  
        
        /**循环产生sql*/
        while( rowKeyItem.hasNext()){          
            
            /**创建map */
            Map tempMap = new HashMap();
            
            /**取得行标题*/
            String tempRowKey = (String)rowKeyItem.next();
            
            /**取得行sql条件设置值*/
            String tempRowValue =(String)rowMap.get(tempRowKey);
            
            tempRowValue = " p.variety.id ='"+tempRowValue+"' ";
            
            /**取得titlekeyset*/
            Iterator titleKeyItem = ((Map)this.getTitleMap().get(SECOND_MAP)).keySet().iterator();
            
            /**产生sqlMap*/
            while(titleKeyItem.hasNext()){
                /**取得sql模板*/
                String tempSql = this.sqlBlock ;           
                
                tempSql  = StringUtils.replace(tempSql,"{1}",tempRowValue);
                
                /**取得列标题*/
                String tempTitleKey = (String)titleKeyItem.next();      

                /**创建服务*/
                StructService structService = (StructService) Global.getInstance()
				.getService("structService");  
                
                /**取得列sql条件设置值*/
                String tempTitleValue = (String)((Map)this.getTitleMap().get(SECOND_MAP)).get(tempTitleKey);
                
                /**找到当前Area*/
                Area tempArea = structService.findAreaByCode(tempTitleValue);
                                                
                /**判断是级别*/
                int areaLength = Integer.parseInt(Configuration.getAppConfig().findConfigs("statConfig").find("areaLength"));
                
                if(!tempArea.getId().equals(Area.ROOT_AREA)){
	               
	                if(tempArea.getFather().getId().equals(Area.ROOT_AREA)){
	                    /**顶级*/
	                    tempTitleValue = tempTitleValue.substring(0,areaLength * 1);
	                    
	                }else if(tempArea.getFather().getFather().getId().equals(Area.ROOT_AREA)){
	                    /**次级*/
	                    tempTitleValue = tempTitleValue.substring(0,areaLength * 2);
	                    
	                }else if(tempArea.getFather().getFather().getFather().getId().equals(Area.ROOT_AREA)){
	                    /**再级*/
	                    tempTitleValue = tempTitleValue.substring(0,areaLength * 3);
	                }
	                
	                String tempAreaValue = tempTitleValue ;
	                tempTitleValue = "p.petOwner.area.areaCode like '" + tempTitleValue +"%'";
	                             
	                /**产生一条sql*/
	                tempSql  = StringUtils.replace(tempSql,"{0}",tempTitleValue);	 
	                
	                /**设置外部传入的sql语句*/
	                if(Tools.isNullOrEmpty(sqlPara)){
	                    tempSql  = StringUtils.replace(tempSql,"{2}"," 1=1 ");
	                }else{	                  
		                tempSql  = StringUtils.replace(tempSql,"{2}",sqlPara);
	                }
	                
	                /**设置titile值*/ 
	                tempMap.put(tempTitleKey,tempSql);  
                }  
                
            }
            
            /**设置行值*/
            backMap.put(tempRowKey,tempMap);  
        }
        
        /**返回结果*/
        return backMap;
    }
    
    
    /**
     * @return 返回 rowMap。
     */
    public Map getRowMap() {
        
        /**创建StructServices服务*/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        /**取得所有品种*/
        List varietyList = structService.getAllVariety();        

        /**循环取得品种*/
        for(int i=0;i<varietyList.size();i++){
            Variety  tempVariety = (Variety)varietyList.get(i);           
            rowMap.put(tempVariety.getName(),tempVariety.getId());           
        }        
    
        return rowMap;
    }
    
    /**
     * @param rowMap 要设置的 rowMap。
     */
    public void setRowMap(Map rowMap) {
        this.rowMap = rowMap;
    }
    
    /**
     * @return 返回 sqlBlock。
     */
    public String getSqlBlock() {        
        return sqlBlock;
    }
    
    /**
     * @param sqlBlock 要设置的 sqlBlock。
     */
    public void setSqlBlock(String sqlBlock) {
        this.sqlBlock = sqlBlock;
    }
    
    /**
     * @param titleMap 要设置的 titleMap。
     */
    public void setTitleMap(Map titleMap) {
        this.titleMap = titleMap;
    }

}
