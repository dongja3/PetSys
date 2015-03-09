//$Source: /petSys/petSys/src/java/com/drategy/pets/dao/SmsDAO.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $
package com.drategy.pets.dao;

import java.util.List;
import com.drategy.pets.domain.Sms;

/**
 * Sms DAO
 * @author Jackie Dong
 * @author $Author: jason.jiang $
 * @$Revision: 1.3 $
 */

public interface SmsDAO {
	
	/**增加*/
    public void add(Sms sms);
    
    /**查询*/
    public Sms find(String id);
    
    /**通过发送状态查询*/
    public List findSms(String sendState,String sendDate);
    
    /**修改*/
    public void update(Sms sms);
    
    /**删除*/
    public void delete(Sms sms);
    
    /**查询所有**/
    public List getAll();

}
