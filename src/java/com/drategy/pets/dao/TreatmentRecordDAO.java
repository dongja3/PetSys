//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/TreatmentRecordDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $

package com.drategy.pets.dao;

import com.drategy.pets.domain.TreatmentRecord;

/**
* 系统的基础TreatmentRecordDAO接口
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/
public interface TreatmentRecordDAO {

    /**增加*/
    public void add(TreatmentRecord treatmentRecord);
    
    /**查询*/
    public TreatmentRecord find(String id);
    
    /**删除*/
    public void delete(TreatmentRecord treatmentRecord);
    
    /**修改*/
    public void update(TreatmentRecord treatmentRecord);
    
}
