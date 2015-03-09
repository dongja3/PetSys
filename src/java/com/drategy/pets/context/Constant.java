//$Source: /petSys/petSys/src/java/com/drategy/pets/context/Constant.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/02/12 04:58:55 $
package com.drategy.pets.context;

import java.util.ArrayList;
import java.util.List;

import com.drategy.pets.domain.Area;
import com.drategy.pets.springservice.StructService;

/**
 * 系统的基础常量配置类
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.15 $
 */
public class Constant {

	public static String CONFIG_PATH = "";
	
	public static String CURR_USER_ID = "";

	private static List areaList = new ArrayList();

	// private static List authList = new ArrayList();

	/**
	 * 字符编码���
	 */
	public static String ENCODDING;

	/** 调试 */
	public static final String SYSTEM_MODE_DEBUG = "debug";

	/** 发布 */
	public static final String SYSTEM_MODE_RELEASE = "release";

	/** 系统模式 */
	public static String SYSTEM_MODE;

	/** 是否发布 */
	public static boolean IS_RELEASE = false;

	/**
	 * 初始化 �����ʼ��
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SYSTEM_MODE = Configuration.getBaseConfig().findConfigs("baseConfig")
				.find("systemMode");

		ENCODDING = Configuration.getBaseConfig().findConfigs("baseConfig")
				.find("encodding");

		// 判断系统是否是发布
		if (SYSTEM_MODE.equals(SYSTEM_MODE_RELEASE)) {
			IS_RELEASE = true;
		}

		initAreaList();

	}

	/**
	 * 得到所有的区域
	 * 
	 * @return List<Area>
	 */
	public static List getAllArea() {
		return areaList;
	}

	/**
	 * 初始化所有的区域到HashMap
	 */
	public static void initAreaList() {

		StructService structService = (StructService) Global.getInstance()
				.getService("structService");
		areaList = structService.findAllArea();
		
		//去除虚拟顶点的Area
		for(int i=0;i<areaList.size();i++){
			if(((Area)areaList.get(i)).getId().equals(Area.ROOT_AREA)){
				areaList.remove(i);
				break;
			}
			
		}
	}

	/**
	 * 添加或者更新一个Area到AreaList
	 * 
	 * @param area
	 */
	public static void updateAreaList(Area area) {
		
		//编辑定级顶点不会更新
		if(area.getId().equals(Area.ROOT_AREA))
			return;
		
		int flag = -1;
		for (int i = 0; i < areaList.size(); i++) {

			// 找到相同的areaId就更新
			Area areaTemp = (Area) areaList.get(i);
			if (areaTemp.getId().equals(area.getId())) {
				flag = i;
			}
		}
		if (flag == -1) {
			// 找不到就添加一个新的Area
			areaList.add(area);
		} else {
			areaList.remove(flag);
			areaList.add(area);
		}
	}

	/**
	 * 从Arealist中删除一个Area
	 * 
	 * @param area
	 */
	public static void deleteArea(Area area) {
		for (int i = 0; i < areaList.size(); i++) {

			// 找到相同的areaId就删除
			Area areaTemp = (Area) areaList.get(i);
			if (areaTemp.getId().equals(area.getId())) {
				areaList.remove(i);
				return;
			}

		}
	}
}
