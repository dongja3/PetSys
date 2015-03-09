<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "¹ÜÀí";
			String con_childMoudleName = "¶ÌÐÅ";
			%>
<html>
<head>
<title><bean:message key="page.title.sms" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<table width="96%" align="center" border="0">
	<tr>
		<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
		<td td valign="top"><br>
		<br>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td><logic:messagesPresent>
					<bean:message key="errors.header" />
					<ul>
						<html:messages id="error">
							<li><bean:write name="error" /></li>
						</html:messages>
					</ul>
					<hr>
				</logic:messagesPresent>
				<table width="100%">
					<tr>
						<td colspan="4" class="tdtitle"><bean:message key="sms.smsList" /></td>
					</tr>
					<tr class="bggray">
						<td><b><bean:message key="area.name" /></b></td>
						<td><b><bean:message key="sms.ownerType" /></b></td>
						<td><b><bean:message key="sms.time" /></b></td>
						<td><b><bean:message key="page.action" /></b></td>
					</tr>
					<logic:iterate id="sms" name="smsList">
						<tr>
							<td>&nbsp;<logic:iterate id="area" name="areaList">
								<bean:define id="areaId">
									<bean:write property="id" name="area" />
								</bean:define>
								<logic:equal property="areaCode" name="sms" value="<%=areaId%>">
									<bean:write property="name" name="area" />
								</logic:equal>
							</logic:iterate></td>
							<td>&nbsp; <logic:equal property="ownerType"
								name="sms" value="User_ALL">
								<bean:message key="sms.user.User_ALL" />
							</logic:equal> <logic:equal property="ownerType" name="sms"
								value="User_InService">
								<bean:message key="sms.user.User_InService" />
							</logic:equal> <logic:equal property="ownerType" name="sms"
								value="User_NotInService">
								<bean:message key="sms.user.User_NotInService" />
							</logic:equal> <logic:equal property="ownerType" name="sms"
								value="User_expired">
								<bean:message key="sms.user.User_expired" />
							</logic:equal> <logic:equal property="ownerType" name="sms"
								value="User_willExpired">
								<bean:message key="sms.user.User_willExpired" />
							</logic:equal></td>
							<td width="80">&nbsp;<bean:write property="sendTime" name="sms" /></td>
							<td width="80">&nbsp;<a
								href="javascript:OnEditSms('<bean:write property="id" name="sms" />')"><bean:message
								key="page.action.edit" /></a>
								&nbsp;&nbsp;<a
								href="javascript:OnDelete('<bean:write property="id" name="sms" />')"><bean:message
								key="page.action.delete" /></a>
								</td>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="4" class="bggray">
						<table cellpadding="0" cellspacing="0" align="center" border="0"
							width="100%">
							<tr>
								<td width="50%">
								<div align="left"><bean:write name="hPage" property="pageInfo"
									filter="false" /></div>
								</td>
								<td width="50%">
								<div align="right"><bean:write name="hPage" property="goPages"
									filter="false" /></div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<html:button property="button" onclick="OnNewSms()"
			styleClass="graybox">
			<bean:message key="sms.button.addSms" />
		</html:button></td>
	</tr>
</table>

<input type="hidden" id="pageURL" value="smsListAction.do" />
<input type="hidden" id="params"
	value='<bean:write name="params" filter="false"/>' />
<br>
<br>

<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
function OnNewSms() {
	window.location = "initSmsAction.do?method=add";
}
function OnEditSms(id) {
	window.location = "initSmsAction.do?method=edit&id="+id;
}
function OnDelete(id){
    if (confirm("<bean:message key="page.confirmDelete" />")){
		window.location = "initSmsAction.do?method=delete&smsId="+id;
	}
}

//-->
</script>
