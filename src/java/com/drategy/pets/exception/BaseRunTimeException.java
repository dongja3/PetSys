//$Source: /petSys/petSys/src/java/com/drategy/pets/exception/BaseRunTimeException.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/13 06:12:20 $
package com.drategy.pets.exception;

import com.drategy.pets.util.*;

/**
* 系统的BaseRunTimeException
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.5 $
*/
public class BaseRunTimeException
    extends RuntimeException {

  /** DOCUMENT ME! */
  private Throwable rootCause;

  /**
   * default constructor
   */
  public BaseRunTimeException() {
    super();
  }

  /**
   * @param arg0 message
   */
  public BaseRunTimeException(final String arg0) {

    this(arg0, null);
    rootCause = this;
  }

  /**
   * @param arg0 throwable
   */
  public BaseRunTimeException(final Throwable arg0) {
    this("", arg0);
  }

  /**
   * @param arg0 message
   * @param arg1 throwable
   */
  public BaseRunTimeException(final String arg0, final Throwable arg1) {
    super(arg0, arg1);

    if (arg1 instanceof BaseRunTimeException) {
      rootCause = ( (BaseRunTimeException) arg1).rootCause;
    }
    else {
      rootCause = arg1;
    }

    SystemLogger.error(arg0+arg1.toString());
  }
}