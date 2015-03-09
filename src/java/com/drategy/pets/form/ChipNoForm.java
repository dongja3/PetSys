//$Source: /petSys/petSys/src/java/com/drategy/pets/form/ChipNoForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/22 02:53:02 $

package com.drategy.pets.form;

/**
 * 通过芯片号码查询的 Form
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.2 $
 */

public class ChipNoForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private String chipNo = null;
	
	private String method = null;


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getChipNo() {
		return chipNo;
	}

	public void setChipNo(String chipNo) {
		this.chipNo = chipNo;
	}

}
