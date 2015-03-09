
package com.drategy.pets.dao.hibernate;


import java.util.List;

import com.drategy.pets.dao.*;
import com.drategy.pets.bom.Phone;

public class PhoneDAOImpl implements PhoneDAO {
    
    private BaseDAO baseDAO ;
	private String orderBy ;
	private Class clasz;
	
	public PhoneDAOImpl(){
	   clasz = Phone.class ;
	   this.orderBy = " order by id desc";
	}
    
	
   public BaseDAO getBaseDAO() {
        return baseDAO;
      }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }     
    
	    
    /**
     * ͨ��id 
     * @param id
     * @return
     */
     public Phone find(String id){
         return (Phone)baseDAO.loadEntity(clasz,id);
     }
     /**
      * �༭
      * @param phone
      */
     public void edit(Phone phone){
         baseDAO.updateEntity(phone);
     }
     /**
      * ɾ��
      * @param phone
      */
     public void delete(Phone phone){
         baseDAO.removeEntity(phone);
     }
     /**
      * ��ѯ
      * @return
      */
     public List  findAll(){
        return baseDAO.findAll(clasz);   
     }
     
     /**
      * ���
      * @param phone
      */
     public void add(Phone phone){        
         baseDAO.saveEntity(phone);
     }
}
