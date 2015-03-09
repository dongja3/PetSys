
package com.drategy.pets.dao;

import java.util.List;

import com.drategy.pets.bom.*;

public interface PhoneDAO {
     
    /**
     * ͨ��id 
     * @param id
     * @return
     */
     public Phone find(String id);
     /**
      * �༭
      * @param phone
      */
     public void edit(Phone phone);
     /**
      * ɾ��
      * @param phone
      */
     public void delete(Phone phone);
     /**
      * ��ѯ
      * @return
      */
     public List  findAll();
     
     /**
      * ���
      * @param phone
      */
     public void add(Phone phone);
     
}
