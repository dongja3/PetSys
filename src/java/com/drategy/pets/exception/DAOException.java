//$Source: /petSys/petSys/src/java/com/drategy/pets/exception/DAOException.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/12 06:14:10 $


package com.drategy.pets.exception;

/**
* 系统的DAOException
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.3 $
*/
public class DAOException
extends BaseRunTimeException {

public DAOException() {
	super();
}

/**
* @param message
*/
public DAOException(String message) {
	super(message);
}

/**
* @param message
* @param cause
*/
public DAOException(String message, Throwable cause) {
	super(message, cause);
}

/**
* @param cause
*/
public DAOException(Throwable cause) {
	super(cause);
}
}