//$Source: /petSys/petSys/src/java/com/drategy/pets/util/Tools.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/03/16 07:34:50 $

package com.drategy.pets.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.drategy.pets.context.Constant;

/**
 * 系统的Tools
 * 
 * @author Jason Jiang
 * @author $Author: jason.jiang $
 * @$Revision: 1.12 $
 */

public class Tools {

	/** 创建一个长度为32的标志号 int常量 */
	private static final int RESOURCE_RANDOM_LENGTH = 32;
    
	
	/**
	 * @param obj
	 *            Object Type
	 * @return String
	 */
	public static String objToString(Object obj) {

		// 如果对象是null
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}

	}

	/**
	 * 获取文件的字节编码
	 * 
	 * @param filePath
	 *            文件全路径
	 * @return 文件内容的字节编码
	 */
	public static byte[] getFileAsBytes(String filePath) {
		// 如果filePath是null
		if (filePath == null) {
			return null;
		}

		File tmpFile = new File(filePath);
		// 如果temFile不存在
		if (tmpFile == null) {
			return null;
		}

		// 创建一个type型数组
		byte[] retBuffer = new byte[(int) tmpFile.length()];
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(filePath);
			fis.read(retBuffer);
			fis.close();
			return retBuffer;
		} catch (IOException e) {
			return null;
		}

	}

	/**
	 * 读出文件以String型返回
	 * 
	 * @param filePath
	 * @return String
	 */
	public static String getFileAsString(String filePath) {
		// 如果filepath是null
		if (filePath == null) {
			throw new java.lang.IllegalArgumentException("filePath is null");
		}

		File tmpFile = new File(filePath);

		// 如果temFile不存在
		if (tmpFile == null) {
			SystemLogger.warn("file not found:" + filePath);
			return null;
		}

		char[] buffer = new char[1024];
		StringWriter writer = new StringWriter();
		FileReader fr = null;

		// 读出内容
		try {
			fr = new FileReader(filePath);
			int iRead = -1;

			// 循环读出
			while ((iRead = fr.read(buffer, 0, 1024)) != -1) {
				writer.write(buffer, 0, iRead);
			}

			fr.close();
			writer.close();
			return writer.toString();
		} catch (IOException e) {
			SystemLogger.error(e.toString());
			return null;
		}

	}

	/**
	 * 保存文件,以byte[]型
	 * 
	 * @param fullFilePath
	 * @param content
	 */
	public static void saveBytesAsFile(String fullFilePath, byte[] content) {

		// 文件路径为null 或者内容为null
		if (fullFilePath == null || content == null) {
			return;
		}

		File f = new File(getDir(fullFilePath));

		// 文件为null,或者文件不存在
		if (f == null || !f.exists()) {
			f.mkdirs();

		}

		// 保存内容
		try {
			FileOutputStream fos = new FileOutputStream(fullFilePath);
			fos.write(content);
			fos.close();
		} catch (Exception e) {
			SystemLogger.error("写入文件异常:" + e.toString());
		}

	}

	/**
	 * 保存string内容到文件
	 * 
	 * @param fullFilePath
	 * @param content
	 */
	public static void saveStringAsFile(String fullFilePath, String content) {

		// 文件路径null
		if (fullFilePath == null) {
			SystemLogger.warn("路径为空");
			return;
		}

		// 文件内容为null
		if (content == null) {
			SystemLogger.warn("内容为空");
			return;
		}

		File f = new File(getDir(fullFilePath));

		// 文件不存在
		if (f == null || !f.exists()) {
			f.mkdirs();

		}

		// 保存内容
		try {
			FileWriter fos = new FileWriter(fullFilePath);
			fos.write(content);
			fos.close();
		} catch (Exception e) {
			SystemLogger.error("写入文件异常:" + e.toString());
		}
	}

	/**
	 * 根据传入的文件全路径，返回文件所在路径
	 * 
	 * @param fullPath
	 *            文件全路径
	 * @return 文件所在路径
	 */
	public static String getDir(String fullPath) {

		int iPos1 = fullPath.lastIndexOf("/");
		int iPos2 = fullPath.lastIndexOf("\\");
		iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);
		return fullPath.substring(0, iPos1 + 1);

	}

	/**
	 * 根据传入的文件全路径，返回文件全名（包括后缀名）
	 * 
	 * @param fullPath
	 *            文件全路径
	 * @return 文件全名（包括后缀名）
	 */
	public static String getFileName(String fullPath) {
		int iPos1 = fullPath.lastIndexOf("/");
		int iPos2 = fullPath.lastIndexOf("\\");
		iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);
		return fullPath.substring(iPos1 + 1);
	}

	/**
	 * 输入一个包含文件类型的文件名,返回文件名,不包含后缀名
	 * 
	 * @param fileName
	 * @return String
	 */
	public static String getFileSuffix(String fileName) {
		return objToString(fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()));
	}

	/**
	 * 根据传入的文件全名（包括后缀名）或者文件全路径返回文件名（没有后缀名）
	 * 
	 * @param fullPath
	 *            文件全名（包括后缀名）或者文件全路径
	 * @return 文件名（没有后缀名）
	 */
	public static String getPureFileName(String fullPath) {
		String fileFullName = getFileName(fullPath);
		return fileFullName.substring(0, fileFullName.lastIndexOf("."));
	}

	/**
	 * 以为Constant的ENCodding 类型 返回 中文
	 * 
	 * @param strT
	 * @return String
	 */
	public static String toChinese(String strT) {
		return toChinese(strT, Constant.ENCODDING);
	}

	/**
	 * 中文转换,输入一个stirng 和编码,返回以该编码型的字符
	 * 
	 * @param strT
	 * @return String
	 */
	public static String toChinese(String strT, String encodering) {
		try {
			if (strT == null) {
				return "";
			} else {
				strT = new String(strT.getBytes("ISO8859_1"), encodering);
				return strT;
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @param string -
	 *            待判断的字符串是否为null或者空字符串
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String string) {

		// 判断string 是不是null 或者空
		if (string == null || string.trim().equals("")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 进行request参数的中文转换
	 * 
	 * @param req
	 * @param paramName
	 * @param isChinese
	 * @return String
	 */
	public static String getParam(javax.servlet.http.HttpServletRequest req,
			String paramName, boolean isChinese) {

		// 如果需要转换为chinese
		if (isChinese) {
			return toChinese(req.getParameter(paramName));
		} else {
			return toChinese(req.getParameter(paramName));
		}

	}

	/**
	 * 将传入的字符串数组用tag字符串连接，并返回
	 * 
	 * @param strAry -
	 *            待连接字符串的数组
	 * @param tag -
	 *            连接标记
	 * @return String
	 */
	public static String complexString(String[] strAry, String tag) {

		// 如果strAry 为null 或者length为零
		if (strAry == null || strAry.length == 0) {
			return "";
		}

		StringBuffer retBuffer = new StringBuffer();
		int i;

		// 循环读出以tag连接
		for (i = 0; i < strAry.length - 1; i++) {
			retBuffer.append(strAry[i]);
			retBuffer.append(tag);
		}

		retBuffer.append(strAry[i]);
		return retBuffer.toString();
	}

	/**
	 * 将传入的HashMap中的键值对用tag连接，以数组返回
	 * 
	 * @param hashMap
	 *            HashMap
	 * @param tag
	 *            String
	 * @return String[]
	 */
	public static String[] complexString(HashMap hashMap, String tag) {

		// hashMapp是null 或者size为零
		if (hashMap == null || hashMap.size() == 0) {
			return new String[] { "", "" };
		}

		String[] keys = new String[hashMap.size()];
		String[] values = new String[hashMap.size()];
		Iterator ite = hashMap.keySet().iterator();
		int index = 0;

		// 循环读出以tag连接
		while (ite.hasNext()) {
			String key = ite.next().toString();
			keys[index] = key;
			values[index++] = hashMap.get(key).toString();
		}

		String complexKeys = complexString(keys, tag);
		String complexValues = complexString(values, tag);
		return new String[] { complexKeys, complexValues };

	}

	/**
	 * 将传入的HashMap中的键值,根据vKeys的键，用tag连接，返回
	 * 
	 * @param hashMap
	 *            HashMap
	 * @param tag
	 *            String
	 * @return String[]
	 */

	public static String[] complexString(Vector vKeys, HashMap hashMap,
			String tag) {

		// vKeys 是null 或者size为零
		if (vKeys == null || vKeys.size() == 0 || hashMap == null
				|| hashMap.size() == 0) {
			return new String[] { "", "" };
		}

		StringBuffer keysBuf = new StringBuffer();
		StringBuffer valuesBuf = new StringBuffer();

		// 循环读出以tag连接
		for (int i = 0; i < vKeys.size(); i++) {
			String key = vKeys.get(i).toString();
			if (!hashMap.containsKey(key)) {
				continue;
			}
			keysBuf.append(key);
			keysBuf.append(tag);
			valuesBuf.append(hashMap.get(key).toString());
			valuesBuf.append(tag);
		}

		return new String[] { keysBuf.toString(), valuesBuf.toString() };
	}

	/**
	 * 转换文件路径中的\\为/
	 * 
	 * @param filePath
	 * @return String
	 */
	public static String wrapFilePath(String filePath) {

		filePath = objToString(filePath);
		filePath.replace('\\', '/');

		// 设filepath最后一位为“/”
		if (filePath.charAt(filePath.length() - 1) != '/') {
			filePath += "/";
		}
		return filePath;
	}

	/**
	 * 获取本机URL地址
	 * 
	 * @param req
	 * @return 本机URL地址
	 */
	public static String getLocalHost(javax.servlet.http.HttpServletRequest req) {

		String strProtocol = req.getProtocol();
		strProtocol = strProtocol.substring(0, strProtocol.indexOf("/"));
		String strIPAddress = req.getHeader("host");
		String strContextPath = req.getContextPath();
		return strProtocol + "://" + strIPAddress + strContextPath;

	}

	/**
	 * 根据传入时间的返回年月日 传入yyyy-mm-dd hh:mm:ss 返回yyyy-mm-dd
	 * 
	 * @param date -
	 *            待处理日期格式如：yyyy-mm-dd hh:mm:ss
	 * @return String
	 */
	public static String getDate(String date) {
		return date.substring(0, 10);
	}

	public static int FILL_PREFIX = 1;

	public static int FILL_SUFFIX = 2;

	/**
	 * 
	 * @param source
	 * @param filler
	 * @param length
	 * @param type
	 * @return String
	 */
	public static String fillCharacter(String source, String filler,
			int length, int type) {
		source = objToString(source);
		filler = objToString(filler);

		// source.length 小于 length参数
		while (source.length() < length) {

			// 如果type为1
			if (type == FILL_PREFIX) {
				source = filler + source;
			} else if (type == FILL_SUFFIX) {
				source += filler;
			} else {
				break;
			}

		}
		return source;
	}

	/**
	 * 对传入的字符串，按长度降序排列
	 * 
	 * @param source
	 * @return
	 */
	public static String[] sortByLenDesc(String[] source) {
		int i, j;
		int length = source.length;
		String tmpStr;

		// 最外层循环
		for (i = 0; i < length - 1; i++) {
			for (j = i + 1; j < length; j++) {

				// 如果i里的length小于后面的就交换
				if (source[i].length() < source[j].length()) {
					tmpStr = source[i];
					source[i] = source[j];
					source[j] = tmpStr;
				}

			}
		}
		return source;
	}

	/**
	 * 传入两个hashMap，返回一个不包含submap内容的hashmap
	 * 
	 * @param source
	 * @param subMap
	 * @return
	 */
	public static HashMap subHashMap(HashMap source, HashMap subMap) {
		Iterator keys = subMap.keySet().iterator();

		// 循环移除
		while (keys.hasNext()) {
			source.remove(keys.next());
		}

		return source;
	}

	/**
	 * 传入两个Map，返回一个不包含submap内容的hmap
	 * 
	 * @param source
	 * @param subMap
	 * @return Map
	 */
	public static Map subMap(Map source, Map subMap) {
		Iterator keys = subMap.keySet().iterator();

		// 循环移除
		while (keys.hasNext()) {
			source.remove(keys.next());
		}

		return source;
	}

	/**
	 * 传入一个vector 和tag ，返回一个用tag连接的string
	 * 
	 * @param v
	 * @param tag
	 * @return
	 */
	public static String complexString(Vector v, String tag) {

		// v是null 或者v.size是零
		if (v == null || v.size() == 0) {
			return "";
		}

		tag = (tag == null ? "" : tag);
		StringBuffer retBuffer = new StringBuffer();
		int i;

		// 循环进行
		for (i = 0; i < v.size() - 1; i++) {
			retBuffer.append(v.get(i).toString());
			retBuffer.append(tag);
		}

		retBuffer.append(v.get(i).toString());
		return retBuffer.toString();
	}

	/**
	 * 判断src字符串数组中是否包含str字符串
	 * 
	 * @param src
	 * @param str
	 * @return boolean
	 */
	public static boolean isContain(String[] src, String str) {

		// 检测是否已经存在列
		for (int i = 0; i < src.length; i++) {

			// 是否相同
			if (str.equalsIgnoreCase(src[i])) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 把vector转换成string[]
	 * 
	 * @param v
	 *            Vector
	 * @return String[]
	 */
	public static String[] vecotrToString(Vector v) {

		// v是null
		if (v == null) {
			return null;
		}

		String[] ret = new String[v.size()];

		// 循环进行
		for (int i = 0; i < v.size(); i++) {
			ret[i] = v.get(i).toString();
		}

		return ret;
	}

	/**
	 * vector 转换成long[]
	 * 
	 * @param v
	 *            Vector
	 * @return long[]
	 */
	public static long[] vectorToLong(Vector v) {

		// v是null
		if (v == null) {
			return null;
		}

		long[] ret = new long[v.size()];

		// 循环进行
		for (int i = 0; i < v.size(); i++) {

			try {
				ret[i] = Long.parseLong(v.get(i).toString());
			} catch (Exception e) {
				SystemLogger.warn(e.toString());
				SystemLogger.warn("第" + i + "个值格式化异常，设置为-1");
				ret[i] = -1;
				continue;
			}

		}
		return ret;
	}

	/**
	 * url字符串进行encode编码
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String urlEncode(String str) {
		return urlEncode(str, Constant.ENCODDING);
	}

	/**
	 * 对str进行encodding编码
	 * 
	 * @param str
	 *            String
	 * @param encodding
	 *            String
	 * @return String
	 */
	public static String urlEncode(String str, String encodding) {

		try {
			return java.net.URLEncoder.encode(str, encodding);
		} catch (Exception e) {
			SystemLogger.error(e.toString());
			return null;
		}

	}

	/**
	 * 对str进行urlDecode
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String urlDecode(String str) {
		return urlDecode(str, Constant.ENCODDING);
	}

	/**
	 * 对str 进行encodding decode
	 * 
	 * @param str
	 *            String
	 * @param encodding
	 *            String
	 * @return String
	 */
	public static String urlDecode(String str, String encodding) {

		try {
			return java.net.URLDecoder.decode(str, encodding);
		} catch (Exception e) {
			SystemLogger.error(e.toString());
			return null;
		}

	}

	/**
	 * 删除dir
	 * 
	 * @param path
	 *            String
	 */
	public static void deleteDirs(String path) {
		File rootFile = new File(path);

		// rootFile是null
		if (rootFile == null) {
			return;
		}

		File[] files = rootFile.listFiles();

		// files 是 null
		if (files == null) {
			return;
		}

		// 循环删除dir下的内容
		for (int i = 0; i < files.length; i++) {
			File file = files[i];

			// 如果是文件夹
			if (file.isDirectory()) {
				deleteDirs(file.getPath());
			} else {
				file.delete();
			}

		}
		rootFile.delete();

	}

	/**
	 * 判断strAry是否包含str
	 * 
	 * @param strAry
	 *            String
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean contains(String[] strAry, String str) {

		// strAry为null
		if (strAry == null || str == null) {
			return false;
		}

		// 循环判断
		for (int i = 0; i < strAry.length; i++) {
			if (strAry[i].equals(str)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 产生一个长度为32的唯一字符串
	 * 
	 * @param inId
	 *            String
	 * @return String
	 */
	public static String genResourceId(String inId) {

		// inId 是null或者为空
		if (isNullOrEmpty(inId)) {
			inId = "";
		}

		// inId.length大于18
		if (inId.length() > 18) {
			inId = inId.substring(0, 17);
		}

		return DateTools.getStringFullTime()
				+ inId
				+ RandomStringUtils.randomNumeric(RESOURCE_RANDOM_LENGTH
						- inId.length() - 14);
	}

	/**
	 * replace filename中的字符
	 * 
	 * @param fileName
	 * @return
	 */
	public static String wrapFileName(String fileName) {

		// filename is null
		if (fileName == null) {
			return null;
		}

		fileName = StringUtils.replace(fileName, "\\", "");
		fileName = StringUtils.replace(fileName, "/", "");
		fileName = StringUtils.replace(fileName, ":", "");
		fileName = StringUtils.replace(fileName, "*", "");
		fileName = StringUtils.replace(fileName, "?", "");
		fileName = StringUtils.replace(fileName, "\"", "");
		fileName = StringUtils.replace(fileName, "<", "");
		fileName = StringUtils.replace(fileName, ">", "");
		fileName = StringUtils.replace(fileName, "|", "");

		return fileName;

	}

	/**
	 * excel的column号 转换字符
	 * 
	 * @param index
	 *            int
	 * @return String
	 */
	public static String convertExcelColumnId(int index) {

		// index 大于 26
		if (index < 26) {
			return Character.toString((char) (65 + index));
		} else {
			int j = (int) index / 26 - 1;
			index = index % 26;
			return Character.toString((char) (65 + j))
					+ Character.toString((char) (65 + index));
		}

	}

	/**
	 * 得到fileName
	 * 
	 * @param fileName
	 *            String
	 * @return String
	 */
	public static String getDownloadHeader(String fileName) {

		try {
			return "attachment; filename=\""
					+ new String(fileName.toString().getBytes("GBK"),
							"ISO8859-1") + "\"";
		} catch (UnsupportedEncodingException ex) {
			SystemLogger.error(ex.toString());
			return "";
		}

	}

	/**
	 * 读出对象,返回byte[]
	 * 
	 * @param obj
	 *            Object
	 * @return byte[]
	 */
	public static byte[] writeObject(Object obj) {

		ObjectOutputStream os = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			os = new ObjectOutputStream(bos);
		} catch (Exception e) {
			SystemLogger.error("创建流对象异常：" + e.toString());
		}

		try {
			os.writeObject(obj);
		} catch (Exception e) {
			SystemLogger.error("读取对象异常：" + e.toString());
			return null;
		}

		byte[] bRet = bos.toByteArray();

		try {
			bos.close();
			os.close();
		} catch (Exception e) {
			SystemLogger.error("关闭流异常：" + e.toString());
		}

		return bRet;

	}

	/**
	 * 写入tyte[]到对象
	 * 
	 * @param content
	 *            byte[]
	 * @return Object
	 */
	public static Object readObject(byte[] content) {

		ObjectInputStream is = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(content);

		try {
			is = new ObjectInputStream(bis);
		} catch (Exception e) {
			SystemLogger.error("创建流对象异常：" + e.toString());
		}
		Object retObj = null;

		try {
			retObj = is.readObject();
		} catch (Exception e) {
			SystemLogger.error("读取对象异常：" + e.toString());
			return null;
		}

		try {
			bis.close();
			is.close();
		} catch (Exception e) {
			SystemLogger.error("关闭流异常：" + e.toString());
		}

		return retObj;

	}

	/**
	 * 返回int
	 * 
	 * @param value
	 *            Integer
	 * @return int
	 */
	public static int intValue(Integer value) {
		return intValue(value, 0);
	}

	/**
	 * 返回int
	 * 
	 * @param value
	 *            Integer
	 * @param defaultValue
	 *            int
	 * @return int
	 */
	public static int intValue(Integer value, int defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		return value.intValue();
	}

	/**
	 * 返回float
	 * 
	 * @param value
	 *            Float
	 * @return float
	 */
	public static float floatValue(Float value) {
		return floatValue(value, 0);
	}

	/**
	 * 返回float值
	 * 
	 * @param value
	 *            Float
	 * @param defaultValue
	 *            float
	 * @return float
	 */
	public static float floatValue(Float value, float defaultValue) {

		// value is null
		if (value == null) {
			return defaultValue;
		}

		return value.floatValue();
	}

	/**
	 * 返回long
	 * 
	 * @param value
	 * @return
	 */
	public static long longValue(Long value) {
		return longValue(value, 0);
	}

	/**
	 * 返回long
	 * 
	 * @param value
	 *            Long
	 * @param defaultValue
	 *            long
	 * @return long
	 */
	public static long longValue(Long value, long defaultValue) {

		// value is null
		if (value == null) {
			return defaultValue;
		}

		return value.longValue();
	}

	/**
	 * 人民币金额大小写转换
	 * 
	 * @param num
	 *            String
	 * @return String
	 */
	public static String getRMB(String num) {
		String strOutput = "";
		String strUnit = "千百拾亿千百拾万千百拾元角分";
		String strSum = "零壹贰叁肆伍陆柒捌玖";
		num += "00";
		int intPos = num.indexOf('.');

		// intPos 大于零
		if (intPos >= 0) {
			num = num.substring(0, intPos)
					+ num.substring(intPos + 1, intPos + 3);
		}

		strUnit = strUnit.substring(strUnit.length() - num.length(), strUnit
				.length());

		// 循环
		for (int i = 0; i < num.length(); i++) {
			strOutput += strSum.substring(Integer.parseInt(num.substring(i,
					i + 1)), Integer.parseInt(num.substring(i, i + 1)) + 1)
					+ strUnit.substring(i, i + 1);
		}

		return strOutput;
	}

	public static String wrapRequestParams(java.util.Map map) {
		if (map == null || map.size() == 0) {
			return "";
		}

		Iterator iteKeys = map.keySet().iterator();
		String[] names = new String[map.size()];
		String[] values = new String[map.size()];
		int i = 0;
		while (iteKeys.hasNext()) {
			names[i] = iteKeys.next().toString();
			values[i] = map.get(names[i]).toString();
			i++;
		}
		return wrapRequestParams(names, values);
	}

	public static String wrapRequestParams(String[] names, Object[] values) {

		if (names == null || values == null || names.length != values.length) {
			SystemLogger.warn("为空，或者数组长度不一致");
			return "";
		}

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < names.length; i++) {
			try {
				buffer.append(names[i]).append("=").append(
						java.net.URLEncoder.encode(
								Tools.objToString(values[i]), "GBK")).append(
						"&");
			} catch (java.io.UnsupportedEncodingException ex) {
				SystemLogger.error(ex.toString());
				continue;
			}
		}
		if (buffer.length() != 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		return buffer.toString();
	}

	/**
	 * 对传入的字符数组，按升序排列
	 * 
	 * @param source
	 * @return
	 */
	public static String[] arraySortByAesc(String[] source) {

		int i, j;
		int length = source.length;
		String tmpStr;

		// 最外层循环
		for (i = 0; i < length - 1; i++) {
			for (j = i + 1; j < length; j++) {
				// SystemLogger.info("source["+i+"]:="+source[i]);
				// SystemLogger.info("source["+j+"]:="+source[j]);
				// 如果i里的length小于后面的就交换
				if (source[i].compareTo(source[j]) > 0) {
					tmpStr = source[i];
					source[i] = source[j];
					source[j] = tmpStr;
				}

			}
		}
		return source;
	}

	/***************************************************************************
	 * 对传入的map,根据其值升序把key放到一个数据中返回
	 * 
	 * @return
	 */
	public static String[] mapKeyToArrayByAsc(Map sourceMap) {

		/** 创建两个数组 */
		String[] keyArray = new String[sourceMap.size()];
		String[] valueArray = new String[sourceMap.size()];
		String[] tempArray = new String[sourceMap.size()];
		String[] returnArray = new String[sourceMap.size()];

		/** 取出key */
		Iterator keyItem = sourceMap.keySet().iterator();

		int inum = 0;

		/** 分别取出放入数据 */
		while (keyItem.hasNext()) {
			String keyValue = (String) keyItem.next();
			keyArray[inum] = keyValue;
			valueArray[inum] = (String) sourceMap.get(keyValue);
			inum++;
		}

		/** 按升序排列 */
		tempArray = arraySortByAesc(valueArray);

		/** 取出重新取出key,按升序放入returnArray */
		for (int i = 0; i < tempArray.length; i++) {
			for (int ii = 0; ii < keyArray.length; ii++) {
				String tempString = (String) sourceMap.get(keyArray[ii]);
				if (tempString.equals(tempArray[i])) {
					returnArray[i] = keyArray[ii];
				}
			}
		}

		/** 返回结果 */
		return returnArray;
	}

	//转换程HTML
	  public static String convert2HTML(String str) {
	        str = StringUtils.replace(str, "<", "&lt;");
	        str = StringUtils.replace(str, ">", "&gt;");
	        str = StringUtils.replace(str, "\r\n", "<br>");
	        str = StringUtils.replace(str, " ", "&nbsp;");

	        return str;
	    }

}