//$Source: /petSys/petSys/src/java/com/drategy/pets/context/Global.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/03/12 02:53:56 $
package com.drategy.pets.context;

import java.io.*;

import org.springframework.context.*;
import org.springframework.context.support.*;
import com.drategy.pets.util.*;

/**
 * 系统的全局的一个类Global
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.9 $
 */
public final class Global {

	/** 一个static Global */
	private static Global global = new Global();;

	/** applicationContext */
	private ApplicationContext context;

	/**
	 * Constructor
	 */
	private Global() {
	}

	/**
	 * get Global's instance
	 * 
	 * @return Global
	 */
	public static Global getInstance() {
		return global;
	}

	/**
	 * 通过名称得到配置Bean
	 * 
	 * @param serviceName
	 * @return DOCUMENT ME!
	 */
	public Object getService(final String serviceName) {
		if (context == null) {
			init();
		}
		return context.getBean(serviceName);
	}

	/**
	 * 初始化spring配置�ʼ������
	 */
	private static synchronized void init() {

		// context是否为null
		if (global.context != null) {
			return;
		}

		SystemLogger
				.info("读取配置文件失败，重新加载Spring");
		String path = Constant.CONFIG_PATH + java.io.File.separator + "classes"
				+ java.io.File.separator + "spring";
		SystemLogger.debug("spring 配置文件路径" + path);

		File dir = new File(path);
		String[] files = dir.list();
		SystemLogger.debug("spring配置文件数目" + files.length);

		// 文件数组
		for (int i = 0, n = files.length; i < n; i++) {
			files[i] = "/spring/" + files[i];
		}

		global.context = new ClassPathXmlApplicationContext(files);
		
		SystemLogger
				.info("Spr重新加载Springng加载完成");
	}

	/**
	 * 得到applicationContext
	 * 
	 * @return
	 */
	public ApplicationContext getContext() {
		return context;
	}

	/**
	 * 设置启动applicationContext
	 * 
	 * @param context
	 */
	public void setContext(ApplicationContext context) {
		this.context = context;
	}

}
