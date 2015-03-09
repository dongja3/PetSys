//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/PetOwnerDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao;

import com.drategy.pets.domain.PetOwner;
/**
* 系统的基础PetOwner接口
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.2 $
*/
public interface PetOwnerDAO {

    /**增加*/
    public void add(PetOwner petOwner);
    
    /**查询*/
    public PetOwner find(String id);
    
    /**删除*/
    public void delete(PetOwner petOwner );
    
    /**修改*/
    public void update(PetOwner petOwner);
    
    /**通过身份证找petOwner*/
    public PetOwner findByResidentID(String residentID);
}
