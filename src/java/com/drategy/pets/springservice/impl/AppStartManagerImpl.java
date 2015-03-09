//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/impl/AppStartManagerImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/01/11 03:34:32 $

package com.drategy.pets.springservice.impl;

import javax.servlet.ServletContext;

import com.drategy.pets.springservice.AppStartManager;
import java.util.*;
import com.drategy.pets.springservice.*;
import com.drategy.pets.util.*;
import com.drategy.pets.exception.*;

/**
 * 系统的AppStartManagerImpl
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.8 $
 */
public class AppStartManagerImpl implements AppStartManager {
	/** �启动list */
	private List appStartList;

	public List getAppStartList() {
		return appStartList;
	}

	public void setAppStartList(List appStartList) {
		this.appStartList = appStartList;
	}

	/**
	 * 开始方法
	 */
	public void appStart(ServletContext ctx) {

		// 没有开始类
		if (appStartList == null || appStartList.size() == 0) {
			return;
		}
		
		SystemLogger.info("系统要初始化的信息->开始初始化");
		
		for (int i = 0; i < appStartList.size(); i++) {
			AppStart appStart = null;
			try {
				appStart = (AppStart) appStartList.get(i);
			} catch (ClassCastException ex) {
				SystemLogger.error("类" + appStart.getClass().toString()
						+ "没有实现com.langtong.context.AppStart接口:"
						+ ex.toString());
				throw new AppStartException("类："
						+ appStart.getClass().toString()
						+ "没有实现com.langtong.context.AppStart接口:"
						+ ex.toString());
			}

			try {

				appStart.start(ctx);

			} catch (Exception ex) {
				SystemLogger.error("类" + appStart.getClass().toString()
						+ "启动异常" + ex.toString());
				throw new AppStartException("类"
						+ appStart.getClass().toString() + "启动异常"
						+ ex.toString());
			}
		}
		
		SystemLogger.info("系统要初始化的信息->初始化完成");
		
	}

}
