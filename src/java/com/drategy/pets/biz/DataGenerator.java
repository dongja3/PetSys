//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/DataGenerator.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/02/24 08:44:47 $

package com.drategy.pets.biz;

import java.util.ArrayList;
import java.util.List;

import com.drategy.pets.context.HibernateCfg;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * sql数据产生器
 * @author Jason Jiang
 * @author $Author $
 * @$Revision: 1.4 $
 */
public class DataGenerator {

    /**
     * 对传入的sql语句,得到一个统计数据
     * 
     * @param sql
     * @return
     * @throws HibernateException
     */
    public int generatorIntBySql(String sql) throws HibernateException {

        /** 设置初始值为零 */
        int returnValue = 0;
        List list = new ArrayList();

        /** 创建一个hibernate对象 */
        HibernateCfg cfg = (HibernateCfg) com.drategy.pets.context.Global
                .getInstance().getService("hibernateCfg");

        /** 开启一个session */
        Session ses = cfg.openSession();
        Query query = null;
        query = ses.createQuery(sql);
        list = query.list();
        cfg.closeSession();
        
        /** 返回统计数值 */
        returnValue = Integer.parseInt(list.get(0).toString());

        /** 返回结果 */
        return returnValue;
    }

}