//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/AreaDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $
package com.drategy.pets.dao;

import java.util.List;

import com.drategy.pets.domain.Area;
/**
 * 系统的基础dao接口
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.5 $
 */
public interface AreaDAO {

    /**增加*/
    public void add(Area area);
    
    /**查询*/
    public Area find(String id);
    
    /**删除*/
    public void delete(Area area);
    
    /**修改*/
    public void update(Area area);
    
    /**查询所有**/
    public List getAll();
    
    /**查找一个通过areaName**/
    public Area findByName(String areaName);
    
    /**查找一个通过areaCode**/
    public Area findByCode(String areaCode);
    
    /**通过areaName查找记录号不是该ID的area*/
    public Area findByNameandId(String areaName,String id);
    
    /**通过areaCode查找记录号不是该ID的area*/
    public Area findByCodeandId(String areaCode,String id);
    
    /**查找一个通过areaCode**/
    public List findLikeCode(String areaCode);
    
}
