//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/RfidChipDAO.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $

package com.drategy.pets.dao;

import com.drategy.pets.domain.RfidChip;

/**
* 系统的基础RfidChipDAO接口
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.3 $
*/

public interface RfidChipDAO {

    /**增加*/
    public void add(RfidChip rfidChip);
    
    /**查询*/
    public RfidChip find(String id);
    
    /**查询*/
    public RfidChip findbyChiCode(String chipCode);
    
    /**删除*/
    public void delete(RfidChip rfidChip);
    
    /**修改*/
    public void update(RfidChip rfidChip);

}
