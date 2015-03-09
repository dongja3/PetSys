//$Source: /petSys/petSys/src/java/com/drategy/pets/form/AreaForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 04:18:34 $

package com.drategy.pets.form;


/**
 * 区域Form
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.9 $
 */

public class AreaForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private String action = null;

	private String name = null;

	private String areaCode = null;

	private String note = null;

	private String fatherId = null;

	private String id = null;
	
	private String fatherAreaName =null;



	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 id。
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id。
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getFatherAreaName() {
		return fatherAreaName;
	}

	public void setFatherAreaName(String fatherAreaName) {
		this.fatherAreaName = fatherAreaName;
	}
}
