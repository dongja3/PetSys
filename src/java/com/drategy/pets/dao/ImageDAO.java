//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/ImageDAO.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.dao;

import com.drategy.pets.bom.Image;

/**
* 系统的基础imageDAO接口
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.2 $
*/
public interface ImageDAO {
  
    /**增加*/
    public void add(Image image);
    
    /**查询*/
    public Image find(String id);
    
    /**删除*/
    public void delete(Image image);
    
    /**修改*/
    public void update(Image image);
    
}
