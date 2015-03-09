//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/NewsDAO.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.dao;

import java.util.List;

import com.drategy.pets.domain.News;

/**
 * News DAO
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.2 $
 */

public interface NewsDAO {
	
	/**增加*/
    public void add(News news);
    
    /**查询*/
    public News find(String id);
    
    /**删除*/
    public void delete(News news);
    
    /**修改*/
    public void update(News news);
    
    /**查询所有**/
    public List getAll();
    
    /**查询前count个news**/
    public List getTop(int count);

}
