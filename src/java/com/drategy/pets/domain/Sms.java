//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/Sms.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $

package com.drategy.pets.domain;

/**
 * 短信类
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.6 $
 */
public class Sms {

	// 所有用户
	public static final String User_ALL = "User_ALL";

	// 正在使用服务的用户
	public static final String User_InService = "User_InService";

	// 未使用服务的用户
	public static final String User_NotInService = "User_NotInService";

	// 服务过期用户
	public static final String User_expired = "User_expired";

	// 服务即将过期用户
	public static final String User_willExpired = "User_willExpired";
	
	public static final String SendNow_Yes="Y";
	public static final String SendNow_No="N";
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 行政编号
	 */
	private String areaCode;

	/**
	 * 用户类型
	 */
	private String ownerType;

	/**
	 * 短信内容
	 */
	private String content;
	

	/**
	 * 短信发布时间
	 */
	private String sendTime;
	
	private String send;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

}
