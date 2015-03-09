//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/ConfigService.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/22 10:08:06 $


package com.drategy.pets.springservice;

import com.drategy.pets.biz.SysMoudleConfig;
import com.drategy.pets.context.MapWraper;


/**
* 系统的configService
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.2 $
*/
public interface ConfigService extends BaseService {
	
    /**得到系统模块**/
    public SysMoudleConfig getSysMoudleConfig();
    
    /**搜索字段值配置器**/
    public MapWraper getSearchFields();
 
	   
}
