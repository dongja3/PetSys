//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/UserDAO.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/31 07:14:49 $
package com.drategy.pets.dao;

import java.util.List;

import com.drategy.pets.domain.User;
/**
 * 系统的基础dao接口
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.5 $
 */
public interface UserDAO {
   
    /**增加用户*/
    public void  add(User user);    
    
    /**查找用户*/
    public User find(String id);
	
    /**删除用户*/    
	public void delete(User user);
	
	/**更新用户 */
	public void update(User user);
	
	  /**通过区域ID查找用户*/
    public List findbyAreaId(String areaId);
    
	/**通过userName查找User*/
	public User findByUserName(String userName,String password);
	
	/**查找一个通过name**/
	public User findByName(String name);
   
}
