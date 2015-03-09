//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/Area.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $

package com.drategy.pets.domain;

import java.util.Set;

/**
 * 系统的区域
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.7 $
 */

public class Area {
    public static final String ROOT_AREA ="rootArea";
    
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 区域名称
	 */
	private String name;

	/**
	 * 行政编号
	 */
	private String areaCode;

	/**
	 * 描述
	 */
	private String note;

	/**
	 * 上级区域描述
	 */
	private Area father;

	private Set childs;

	public Area getFather() {
		return father;
	}

	public void setFather(Area father) {
		this.father = father;
	}

	public String getAreaCode() {
		return areaCode;
	}


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getChilds() {
		return childs;
	}

	public void setChilds(Set childs) {
		this.childs = childs;
	}
}