//$Source: /petSys/petSys/src/java/com/drategy/pets/exception/BaseException.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/01/10 09:27:50 $
package com.drategy.pets.exception;

import com.drategy.pets.util.*;

/**
 * 系统的BaseException
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.7 $
 */
public class BaseException extends Exception {

	private Throwable rootCause;

	/**
	 * *构造函数
	 */
	public BaseException() {
		super();
	}

	/**
	 * @param s
	 */
	public BaseException(String s) {
		this(s, null);
		rootCause = this;
	}

	/**
	 * Creates a new BaseException object.
	 * 
	 * @param s
	 * @param e
	 */
	public BaseException(String s, Throwable e) {

		super(s);

		// it is BaseException
		if (e instanceof BaseException) {
			rootCause = ((BaseException) e).rootCause;
		} else {
			rootCause = e;
		}
		SystemLogger.error(s + e.toString());
	}

	/**
	 * Creates a new BaseException object.
	 * 
	 * @param e
	 *            DOCUMENT ME!
	 */
	public BaseException(Throwable e) {
		this("", e);
	}

	/**
	 * @return
	 */
	public Throwable getRootCause() {
		return rootCause;
	}

	/**
	 * 转换String
	 */
	public String toString() {
		return this.getMessage() + this.toString();
	}
}