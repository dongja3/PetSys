//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/impl/ConfigServiceImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/22 10:10:04 $

package com.drategy.pets.springservice.impl;

import com.drategy.pets.context.MapWraper;
import com.drategy.pets.biz.SysMoudleConfig;
import com.drategy.pets.springservice.*;

/**
 * 系统的configServiceimpl
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.4 $
 */
public class ConfigServiceImpl implements ConfigService {

    /**
     * 要初始化数据
     */
    public void initData() {
        throw new java.lang.UnsupportedOperationException(
                "Method initData() not yet implemented.");
    }

    /** 系统模块配置 */
    private SysMoudleConfig sysMoudleConfig;

    /** 搜索字段值配置器* */
    private MapWraper searchFields;

    public SysMoudleConfig getSysMoudleConfig() {
        return sysMoudleConfig;
    }

    public void setSysMoudleConfig(SysMoudleConfig sysMoudleConfig) {
        this.sysMoudleConfig = sysMoudleConfig;
    }

    /**
     * @return 返回 searchFields。
     */
    public MapWraper getSearchFields() {
        return searchFields;
    }

    /**
     * @param searchFields
     *            要设置的 searchFields。
     */
    public void setSearchFields(MapWraper searchFields) {
        this.searchFields = searchFields;
    }
}