//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/impl/AppStartImpl.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 04:18:34 $

package com.drategy.pets.springservice.impl;

import java.io.IOException;

import javax.servlet.ServletContext;

import com.drategy.pets.biz.SmsSendThread;
import com.drategy.pets.context.Constant;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Variety;
import com.drategy.pets.springservice.AppStart;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * 系统的AppStartImpl
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.15 $
 */
public class AppStartImpl implements AppStart {

	/** 是否要插入测试数据 */
	private boolean testData;

	public void start(ServletContext ctx) {

		SystemLogger.info("系统的基础常量->开始配置");
		Constant.main(null);
		SystemLogger.info("系统的基础常量->配置完成");

		if (testData) {
			SystemLogger.info("插入测试数据->开始");

			/** 创建服务 */
			StructService structService = (StructService) Global.getInstance()
					.getService("structService");

			for (int i = 1; i < 14; i++) {
				Variety variety = new Variety();
				SystemLogger.info("initVariety,i%14=" + i % 14);
				switch (i % 13) {
				case 0:
					variety.setId(Tools.genResourceId(""));
					variety.setName("苏格兰牧羊犬");
					break;
				case 1:
					variety.setId(Tools.genResourceId(""));
					variety.setName("喜乐蒂");
					break;
				case 2:
					variety.setId(Tools.genResourceId(""));
					variety.setName("哈士奇");
					break;
				case 3:
					variety.setId(Tools.genResourceId(""));
					variety.setName("斑点狗");
					break;
				case 4:
					variety.setId(Tools.genResourceId(""));
					variety.setName("拉布拉多犬");
					break;
				case 5:
					variety.setId(Tools.genResourceId(""));
					variety.setName("金毛犬");
					break;
				case 6:
					variety.setId(Tools.genResourceId(""));
					variety.setName("比熊犬");
					break;
				case 7:
					variety.setId(Tools.genResourceId(""));
					variety.setName("可卡犬");
					break;
				case 8:
					variety.setId(Tools.genResourceId(""));
					variety.setName("京巴狗");
					break;
				case 9:
					variety.setId(Tools.genResourceId(""));
					variety.setName("德国牧羊犬");
					break;
				case 10:
					variety.setId(Tools.genResourceId(""));
					variety.setName("博美犬");
					break;
				case 11:
					variety.setId(Tools.genResourceId(""));
					variety.setName("日本尖嘴");
					break;
				case 12:
					variety.setId(Tools.genResourceId(""));
					variety.setName("约克夏");
					break;
				default:

				}

				// 保存品种
				structService.addVariety(variety);

			}

			/** 完成数据插入 */
			SystemLogger.info("插入测试数据->完成");
		}

		/** 启动线程 */
		SystemLogger.info("启动发送线程");
		Thread sendThread = new SmsSendThread();
		sendThread.start();

		/** 启动发送服务器 */
		try {
			SystemLogger.info("发送服务器启动");
			Runtime.getRuntime().exec(Constant.CONFIG_PATH + "smsSend.exe");

		} catch (IOException ex) {
			SystemLogger.error("启动发送服务器:" + ex.toString());
		}

	}

	/**
	 * @return 返回 testData。
	 */
	public boolean isTestData() {
		return testData;
	}

	/**
	 * @param testData
	 *            要设置的 testData。
	 */
	public void setTestData(boolean testData) {
		this.testData = testData;
	}
}
