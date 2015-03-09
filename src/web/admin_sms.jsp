<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "¹ÜÀí";
			String con_childMoudleName = "¶ÌÐÅ";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.sms" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/date.js"> </SCRIPT>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<html:form action="smsAction.do">
	<table width="96%" align="center" border="0">
		<tr>
			<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
			<td valign="top"><br>
			<br>
			<logic:messagesPresent>
				<bean:message key="errors.header" />
				<ul>
					<html:messages id="error">
						<li><bean:write name="error" /></li>
					</html:messages>
				</ul>
			</logic:messagesPresent>
			<table width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolordark="#ffffff" bordercolorlight="#000000">
				<tr>
					<td><html:hidden property="method" /> <html:hidden property="id" />

					<table width="100%" border="0" cellspacing="0" cellpadding="0">

						<tr>
							<td colspan="2" class="tdtitle"><bean:message key="sms.title" /></td>
						</tr>
						<tr>
							<td><bean:message key="sms.areaCode" /></td>
							<td><html:select property="areaCode">
								<logic:iterate id="area" name="areaList">
									<bean:define id="areaId">
										<bean:write property="id" name="area" />
									</bean:define>
									<html:option value="<%=areaId%>">
										<bean:write name="area" property="name" />
									</html:option>
								</logic:iterate>
							</html:select></td>
						</tr>
						<tr>
							<td><bean:message key="sms.ownerType" /></td>
							<td><html:select property="ownerType">
								<html:option value="User_InService">
									<bean:message key="sms.user.User_InService" />
								</html:option>
								<html:option value="User_NotInService">
									<bean:message key="sms.user.User_NotInService" />
								</html:option>
								<html:option value="User_expired">
									<bean:message key="sms.user.User_expired" />
								</html:option>
								<html:option value="User_willExpired">
									<bean:message key="sms.user.User_willExpired" />
								</html:option>
								<html:option value="User_ALL">
									<bean:message key="sms.user.User_ALL" />
								</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td><bean:message key="sms.content" /></td>
							<td><html:textarea property="content" cols="40" rows="6" onkeydown="javaScript:textareaValidate(this)" onkeyup="javaScript:textareaValidate(this)"></html:textarea><font
								color="red">*</font></td>
						</tr>
						<tr>
							<td><bean:message key="sms.time" /></td>
							<td><html:text property="sendTime" onclick="viewCalendar(this)"
								size="20" maxlength="20" readonly="true" /><bean:message
								key="sms.time.mask" /></td>
						</tr>

					</table>

					</td>
				</tr>
			</table>
			<html:submit property="submit" styleClass="graybox">
				<bean:message key="button.confirm" />
			</html:submit></td>
		</tr>
	</table>
</html:form>

<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
<!--
function viewon(){
    document.smsForm.sendTime.disabled="false"
}
function viewoff(){
	document.smsForm.sendTime.disabled="true"
}

function textareaValidate(obj){

	if(obj.value.length >70) {
		obj.value = obj.value.substring(0,70);
		alert("<bean:message key="page.info.txtMaxLength.70" />");		
	}	

}
//-->
</script>
