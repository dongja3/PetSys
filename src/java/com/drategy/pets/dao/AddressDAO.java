
package com.drategy.pets.dao;

import com.drategy.pets.bom.*;

public interface AddressDAO {
   /**
    * 
    * @param id
    * @return
    */
    public Address find(String id);

    /**
     * 
     * @param address
     */
  
    public void add(Address address);
    /**
     * �޸ĵ�ַ
     * @param address
     */
    public void edit(Address address);
    /**
     * ɾ���ַ
     * @param address
     */
    public void delete(Address address);
}
