//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/Variety.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $
package com.drategy.pets.domain;
/**
 * 系统的区域
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.2 $
 */
public class Variety {
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 分类名字
	 */
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
