//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/SysMoudleConfig.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/01/18 08:11:21 $

package com.drategy.pets.biz;

import java.util.Map;

/**
* 系统的sysmoudleConfig
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.4 $
*/
public class SysMoudleConfig {
	
	/** 系统模块名 */
	private Map moudleNames;
    
	/**系统角色配置模块名*/
	private Map sysRoleConfigMoudleName;
	
	/**
	 * 设置
	 * @param moubleNames
	 */
    public void setMoudleNames(Map moubleNames){
        this.moudleNames = moubleNames;
    }
    
    /**
     * 得到
     * @return
     */
    public Map getMoudleNames(){
        return this.moudleNames;
    }
    
    
   
    /**
     * @return 返回 sysRoleConfigMoudleName。
     */
    public Map getSysRoleConfigMoudleName() {
        return sysRoleConfigMoudleName;
    }
    /**
     * @param sysRoleConfigMoudleName 要设置的 sysRoleConfigMoudleName。
     */
    public void setSysRoleConfigMoudleName(Map sysRoleConfigMoudleName) {
        this.sysRoleConfigMoudleName = sysRoleConfigMoudleName;
    }
}
