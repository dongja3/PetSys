//$Source: /petSys/petSys/src/java/com/drategy/pets/context/impl/MapWraperImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/23 01:40:30 $
package com.drategy.pets.context.impl;

import java.util.*;
import com.drategy.pets.context.MapWraper;

/**
 * 系统的mapWraper接口的实现
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.1 $
 */
public class MapWraperImpl
    implements MapWraper {

  private Map values;

  public Object getValue(String key) {
    /**@todo Implement this com.langtong.MapWraper method*/
    return values.get(key);
  }

  public Object setValue(String key, Object value) {
    /**@todo Implement this com.langtong.MapWraper method*/
    return values.put(key, value);
  }

  public boolean containsKey(String key) {
    /**@todo Implement this com.langtong.MapWraper method*/
    return values.containsKey(key);
  }

  public Map getValues() {
    return values;
  }

  public void setValues(Map values) {
    this.values = values;
  }

  public Iterator keys() {
    return values.keySet().iterator();
  }

}
