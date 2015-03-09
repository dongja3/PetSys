//$Source: /petSys/petSys/src/java/com/drategy/pets/form/SmsForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/08 06:56:45 $

package com.drategy.pets.form;

/**
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.3 $
 */

public class SmsForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private String action = null;

	private String id = null;

	private String areaCode = null;

	private String ownerType = null;

	private String content = null;

	private String sendTime = null;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	
}
