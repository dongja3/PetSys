//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/PetDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao;

import java.util.List;

import com.drategy.pets.domain.Pet;
/**
* 系统的基础PetDAO接口
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.4 $
*/
public interface PetDAO {

    /**增加*/
    public void add(Pet pet);
    
    /**查询*/
    public Pet  find(String id);
    
    /**删除*/
    public void delete(Pet pet);
    
    /**修改*/
    public void update(Pet pet);
    
    /**通过chipNo查找Pet**/
    public Pet findPetByChipNo(String chipNo);
    
    /**通过petNo查找Pet**/
    public Pet findPetByPetNo(String petNo);
    
    /**通过petNo和petId查找Pet**/
    public Pet findPetByPetNo(String petNo,String petId);
    
    /**通过areat**/
    public List findPetListByAreaCode(String areaCode);
}
