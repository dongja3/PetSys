//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/EmployeeDAO.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.dao;

import com.drategy.pets.domain.Employee;

/**
* 系统的基础employeeDAO接口
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.3 $
*/
public interface EmployeeDAO {
  /**增加员工*/
  public void  add(Employee employee);    
  
  /**查找员工*/
  public Employee find(String id);
	
  /**删除员工*/    
  public void delete(Employee employee);
	
  /**更新员工 */
  public void update(Employee employee);
  
  /**查找一个通过name**/
  public Employee findByName(String name);
}
