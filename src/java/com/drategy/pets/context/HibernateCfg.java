
//$Source: /petSys/petSys/src/java/com/drategy/pets/context/HibernateCfg.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/12 06:13:45 $

package com.drategy.pets.context;

import net.sf.hibernate.*;
import net.sf.hibernate.type.*;


/* Hibernate Configuration Class
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @version $Revision: 1.4 $
 */
public interface HibernateCfg {

	/**
	 * Open a session
	 * @return Session
	 */
	public Session openSession();

	/**
	 * Close a session
	 */
	public void closeSession();

	/**
	 * @param obj
	 * @return Type
	 */
	public Type getHibernateType(Object obj);

}