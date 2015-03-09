//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/VaccineDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao;

import com.drategy.pets.domain.Vaccine;

/**
* 系统的基础VaccineDAO
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/

public interface VaccineDAO {

    /**增加*/
    public void add(Vaccine vaccine);
    
    /**查询*/
    public Vaccine find(String id);
    
    /**删除*/
    public void delete(Vaccine vaccine);
    
    /**修改*/
    public void update(Vaccine vaccine);
}
