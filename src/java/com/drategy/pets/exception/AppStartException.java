//$Source: /petSys/petSys/src/java/com/drategy/pets/exception/AppStartException.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2005/12/28 08:48:58 $

package com.drategy.pets.exception;

/**
 * 系统的appStartException
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.4 $
 */
public class AppStartException extends BaseRunTimeException {

	/**
	 * 
	 * 构造函数
	 */
	public AppStartException() {
	}

	/**
	 * *构造函数
	 * 
	 * @param p0
	 * @param p1
	 */
	public AppStartException(String p0, Throwable p1) {
		super(p0, p1);
	}

	/**
	 * *构造函数
	 * 
	 * @param p0
	 */
	public AppStartException(Throwable p0) {
		super(p0);
	}

	/**
	 * *构造函数
	 * 
	 * @param p0
	 */
	public AppStartException(String p0) {
		super(p0);
	}
}
