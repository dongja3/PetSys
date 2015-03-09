//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/AppStartManager.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/12 08:05:32 $

package com.drategy.pets.springservice;

import javax.servlet.*; 
/**
* 系统的初始化manager
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.3 $
*/
public interface AppStartManager {
	
    /**
     * some comment about this method
     * @param ctx
     */
    public  void appStart(ServletContext ctx);
}
