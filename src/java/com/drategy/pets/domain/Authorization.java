//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/Authorization.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $

package com.drategy.pets.domain;

import org.apache.commons.lang.StringUtils;

import com.drategy.pets.context.Global;
import com.drategy.pets.domain.User;
import com.drategy.pets.springservice.*;
import com.drategy.pets.biz.SysMoudleConfig;


/**
* 系统的hibernate接口的实现
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.4 $
*/
public class Authorization {
	/**
     * 允许
     */
    public static final String ACT_PRRMIT_YES = "yes";
    /**
     * 不允许 
     */
    public static final String ACT_PRRMIT_NO = "no";
    /**
     * id 主键
     */    
    private String id ;
    /**
     * 模块的名字
     */
    private String moudleName;
    
    /**
     * 子模块的名字
     */    
    private String childMoudleName;
    
    /**
     * 增加操作
     */
    private String actAdd;
    /**
     * 修改操作
     */
    private String actUpdate;
    /**
     * 删除操作
     */
    private String actDelete;
    /**
     * 对应的登陆账号
     */
    private User user;
    
    public User getUser(){
        return this.user;
    }
    
    public void setUser(User user){
        this.user = user;
    }
     
    
    public void setChildMoudleName(String childMoudleName){
        this.childMoudleName = childMoudleName ;
    }
    
    public String getChildMoudleName(){
        return childMoudleName;
    }
    
    public void setId(String id){
        this.id = id ;
    }
    
    public void setMoudleName(String moudleName){
        this.moudleName = moudleName;
    }
    
    public void setActAdd(String actAdd){
        this.actAdd = actAdd;
    }
    
    public void setActUpdate(String actUpdate){
        this.actUpdate = actUpdate;
    }
    
    public void setActDelete(String actDelete){
        this.actDelete = actDelete;
    }
    
    
    public String  getId(){
        return this.id ;
    }
    
    public String getMoudleName(){
        return this.moudleName ;
    }
    
    public String getActAdd(){
        return this.actAdd; 
    }
    
    public String getActUpdate(){
        return this.actUpdate;
    }
    
    public String getActDelete(){
        return this.actDelete;
    }    
    /**
     * 得到模块名字
     * @return
     */
    public static String[] getMoudleNamesValue(String sysRoleName){
        
        /**创建服务*/        
        ConfigService configService = (ConfigService)Global.getInstance().getService("configService");
        SysMoudleConfig sysMoudleConfig = configService.getSysMoudleConfig();
        
        /**取得配置*/
        java.util.Map moudleNameMap = sysMoudleConfig.getSysRoleConfigMoudleName();
        
        String tempMoudleNames = (String)moudleNameMap.get(sysRoleName);
        String[] moudleNames = StringUtils.split(tempMoudleNames,"$");  
        
        /**返回结果*/
        return moudleNames;
    }
    
    /**
     * 得到对应的子模块
     * @param moudleName
     * @return
     */
    public static String[] getChildMoudleNamesValue(String moudleName){
        ConfigService configService = (ConfigService)Global.getInstance().getService("configService");
        SysMoudleConfig sysMoudleConfig = configService.getSysMoudleConfig();
        java.util.Map moudleMap = sysMoudleConfig.getMoudleNames();
        String childMoudleNames =(String)moudleMap.get(moudleName);   
        return StringUtils.split(childMoudleNames,"$");
    }

}