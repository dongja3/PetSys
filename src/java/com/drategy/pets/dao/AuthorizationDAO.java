//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/AuthorizationDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $
package com.drategy.pets.dao;

import com.drategy.pets.domain.Authorization;


/**
 * 系统的基础Authorization接口
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.2 $
 */

public interface AuthorizationDAO {
    
    /**增加权限*/
    public void  add(Authorization authorization);    
    
    /**查找权限*/
    public Authorization find(String id);
	
    /**删除权限*/    
	public void delete(Authorization authorization);
	
	/**更新权限 */
	public void update(Authorization authorization);
	
	/**查找Authorization**/
	public Authorization findBymoudleName(String moudleName,String childMoudleName,String userId);
}
