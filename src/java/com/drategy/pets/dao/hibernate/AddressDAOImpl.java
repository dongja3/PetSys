
package com.drategy.pets.dao.hibernate;

import com.drategy.pets.bom.Address;
import com.drategy.pets.dao.AddressDAO;
import com.drategy.pets.dao.BaseDAO;


public class AddressDAOImpl implements AddressDAO {
    
    private BaseDAO baseDAO;
    private String orderBy;
    private Class clasz;
    
    public BaseDAO getBaseDAO() {
        return baseDAO;
      }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }     
    
    public AddressDAOImpl(){
        this.clasz = Address.class ;
        this.orderBy = "order by id desc";
    }
    /**
     * ͨ��id��ѯ
     * @param id
     * @return
     */
    public Address find(String id){
        return (Address)baseDAO.loadEntity(clasz,id);
    }
    /**
     * ��ӵ�ַ
     * @param address
     */
    public void add(Address address){
        baseDAO.saveEntity(address);
    }
    /**
     * �޸ĵ�ַ
     * @param address
     */
    public void edit(Address address){
        baseDAO.updateEntity(address);
    }
    /**
     * ɾ���ַ
     * @param address
     */
    public void delete(Address address){
        baseDAO.removeEntity(address);
    }
}
