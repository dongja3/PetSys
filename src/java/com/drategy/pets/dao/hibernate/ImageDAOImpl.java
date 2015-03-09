//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/hibernate/ImageDAOImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.dao.hibernate;

import com.drategy.pets.bom.Image;
import com.drategy.pets.dao.BaseDAO;
import com.drategy.pets.dao.ImageDAO;

/**
* 系统的基础EmploeDAO实现类
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.2 $
*/
public class ImageDAOImpl implements ImageDAO{
   
    /**DAO的基础类*/
    private BaseDAO baseDAO ;
	
    /**排序字段*/
    private String orderBy ;
    
    /**要操作类*/
    private Class clasz;	
    
    public ImageDAOImpl(){
      this.clasz = Image.class;
      this.orderBy = "order by id desc" ;
    }   

    /**增加*/
    public void add(Image image){
        baseDAO.saveEntity(image);
    }
    
    /**查询*/
    public Image find(String id){
        return (Image)baseDAO.loadEntity(clasz,id);
    }
    
    /**删除*/
    public void delete(Image image){
        baseDAO.removeEntity(image);
    }
    
    /**修改*/
    public void update(Image image){
        baseDAO.updateEntity( image);
    }    
    
    /**
     * @return 返回 baseDAO。
     */
    public BaseDAO getBaseDAO() {
        return baseDAO;
    }
    /**
     * @param baseDAO 要设置的 baseDAO。
     */
    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }
    /**
     * @return 返回 clasz。
     */
    public Class getClasz() {
        return clasz;
    }
    /**
     * @param clasz 要设置的 clasz。
     */
    public void setClasz(Class clasz) {
        this.clasz = clasz;
    }
    /**
     * @return 返回 orderBy。
     */
    public String getOrderBy() {
        return orderBy;
    }
    /**
     * @param orderBy 要设置的 orderBy。
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
 
}
