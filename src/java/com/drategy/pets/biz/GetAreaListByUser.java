//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/GetAreaListByUser.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/03/16 07:34:51 $
package com.drategy.pets.biz;

import java.util.List;

import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.User;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;

/**
 * 得到用户的所在区域
 * @author Jason Jiang
 * @author $Author $
 * @$Revision: 1.3 $
 */

public class GetAreaListByUser {
 
    public List getAreaListByUserId(String userId){      
        
        
        /**创建服务*/
        StructService  structService = (StructService)Global.getInstance().getService("structService") ;
        
        /**得到当前用户*/
        User currUser = structService.findUser(userId);
        
        /**取得当前所在的区域*/
        Area  currArea = currUser.getArea();
        
        /**当前区域的号码*/
        String currAreaCode = currArea.getAreaCode();
        
        /**判断是级别*/
        int areaLength = Integer.parseInt(Configuration.getAppConfig().findConfigs("statConfig").find("areaLength"));
       
        if(currArea.getFather().getId().equals(Area.ROOT_AREA)){
            /**顶级*/
            currAreaCode = currAreaCode.substring(0,areaLength * 1);
            
        }else if(currArea.getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**次级*/
            currAreaCode = currAreaCode.substring(0,areaLength * 2);
            
        }else if(currArea.getFather().getFather().getFather().getId().equals(Area.ROOT_AREA)){
            /**再级*/
            currAreaCode = currAreaCode.substring(0,areaLength * 3);
        }                            
        
        SystemLogger.debug("currAreaCode:="+currAreaCode);
        
        /**找到本级和下级节点*/
        List returnAreaList = structService.findAreaListByCode(currAreaCode);
        
        /**返回结果*/
        SystemLogger.debug("returnAreaList size:="+returnAreaList.size());
        return returnAreaList;
    }
   
}
