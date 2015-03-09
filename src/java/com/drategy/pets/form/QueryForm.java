//$Source: /petSys/petSys/src/java/com/drategy/pets/form/QueryForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/20 06:20:08 $

package com.drategy.pets.form;

/**
 * 查询Form
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.1 $
 */

public class QueryForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private String action = null;

	private String petOwnerName = null;

	private String petOwnerId = null;

	private String petId = null;

	private String petChipNo = null;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPetChipNo() {
		return petChipNo;
	}

	public void setPetChipNo(String petChipNo) {
		this.petChipNo = petChipNo;
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	public String getPetOwnerId() {
		return petOwnerId;
	}

	public void setPetOwnerId(String petOwnerId) {
		this.petOwnerId = petOwnerId;
	}

	public String getPetOwnerName() {
		return petOwnerName;
	}

	public void setPetOwnerName(String petOwnerName) {
		this.petOwnerName = petOwnerName;
	}

}
