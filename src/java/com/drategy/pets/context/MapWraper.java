//$Source: /petSys/petSys/src/java/com/drategy/pets/context/MapWraper.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/22 10:07:33 $
package com.drategy.pets.context;

import java.util.*;

/**
 * 用于有关map类型的类操作
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.1 $
 */
public interface MapWraper {

  Object getValue(String key);

  Object setValue(String key, Object value);

  boolean containsKey(String key);

  public Iterator keys();

}
