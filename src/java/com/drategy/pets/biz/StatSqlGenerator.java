//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/StatSqlGenerator.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 03:12:51 $

package com.drategy.pets.biz;

import java.util.Map;

/**
* 系统的统计的sql语句产生器
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.4 $
*/

public interface StatSqlGenerator {

    /**取得TitleMap**/
    public Map getTitleMap();
    
    /**取得sqlMap**/
    public Map getSqlMap(String sqlPara);
    
    /**取得RowMap**/
    public Map getRowMap();

}
