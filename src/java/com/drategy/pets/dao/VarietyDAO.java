package com.drategy.pets.dao;

import java.util.List;

import com.drategy.pets.domain.Variety;

/**
 * Variety DAO
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.2 $
 */
public interface  VarietyDAO {
	/**增加*/
    public void add(Variety variety);
    
    /**查询*/
    public Variety find(String id);
    
    /**按照名字查询**/
    public Variety findByName(String name);
    
    /**删除*/
    public void delete(Variety variety);
    
    /**修改*/
    public void update(Variety variety);
    
    /**查询所有**/
    public List getAll();
}
