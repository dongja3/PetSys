//$Source: /petSys/petSys/src/java/com/drategy/pets/exception/UnloginException.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/13 06:07:32 $

package com.drategy.pets.exception;

import com.drategy.pets.exception.BaseException;

/**
 * 系统的UnloginException
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.4 $
 */
public class UnloginException extends BaseException {

	private static final long serialVersionUID = 3946605337243992329L;

	public UnloginException() {
		super("UnloginException");
	}

	public UnloginException(String msg) {
		super("UnloginException:" + msg);
	}

}