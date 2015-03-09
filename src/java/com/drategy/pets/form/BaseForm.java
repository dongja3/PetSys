//$Source: /petSys/petSys/src/java/com/drategy/pets/form/BaseForm.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/30 08:29:43 $

package com.drategy.pets.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

/* 
 * 系统的base Form
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.8 $
 */

public class BaseForm extends ValidatorForm implements Serializable {

	/***/
    private static final long serialVersionUID = 1L;

	/** 增加 */
	public static final String METHOD_ADD = "add";

	/** 编辑 */
	public static final String METHOD_EDIT = "edit";

	/** 删除 */
	public static final String METHOD_DELETE = "delete";

	/** window 名称 */
	protected String winTitle;

	/** 方法 */
	protected String method;

	/**
	 * method
	 * @return
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 
	 * @return
	 */
	public String getWinTitle() {
		return winTitle;
	}

	/**
	 * method 
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 
	 * @param winTitle
	 */
	public void setWinTitle(String winTitle) {
		this.winTitle = winTitle;
	}

}