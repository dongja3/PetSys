//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/AppStart.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/12 06:14:40 $


package com.drategy.pets.springservice;

import javax.servlet.*;
/**
* 系统的初始化的接口
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.3 $
*/
public interface AppStart {
    public void start(ServletContext ctx);
}
