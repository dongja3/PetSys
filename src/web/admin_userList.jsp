<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "¹ÜÀí";
			String con_childMoudleName = "µÇÂ½ÕÊºÅ";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<title><bean:message key="page.title.userList" /></title>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<table width="96%" align="center" border="0">
	<tr>
		<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
		<td><br>
		<br>
		<logic:messagesPresent>
			<bean:message key="errors.header" />
			<ul>
				<html:messages id="error">
					<li><bean:write name="error" /></li>
				</html:messages>
			</ul>
			<hr>
		</logic:messagesPresent>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>
				<table border="0" width="100%">
					<tr>
						<td colspan="3" class="tdtitle"><bean:message key="user.title" /></td>
					</tr>
					<tr>
						<td class="bggray"><b><bean:message key="user.userName" /></b></td>
						<td class="bggray"><b><bean:message key="user.systemRole" /></b></td>
						<td class="bggray"><b><bean:message key="page.action" /></b></td>
					</tr>
					<logic:iterate id="user" name="userList">
						<tr>
							<td><bean:write property="userName" name="user" /></td>
							<td><logic:equal name="user" property="sysRole" value="admin">
								<bean:message key="user.role.admin" />
							</logic:equal> <logic:equal name="user" property="sysRole"
								value="sysInputor">
								<bean:message key="user.role.sysInputor" />
							</logic:equal> <logic:equal name="user" property="sysRole"
								value="sysQuery">
								<bean:message key="user.role.sysQuery" />
							</logic:equal> <logic:equal name="user" property="sysRole"
								value="sysVaccine">
								<bean:message key="user.role.sysVaccine" />
							</logic:equal> <logic:equal name="user" property="sysRole"
								value="sysDoctor">
								<bean:message key="user.role.sysDoctor" />
							</logic:equal></td>
							<td><logic:equal name="user" property="id" value="admin">
								<a
									href="javascript:OnEditUser('<bean:write property="id"
									name="user" />')"><bean:message
									key="page.action.edit" /></a>
							</logic:equal> <logic:notEqual name="user" property="id"
								value="admin">
								<a
									href="javascript:OnEditUser('<bean:write property="id" name="user" />')"><bean:message
									key="page.action.edit" /></a>&nbsp;&nbsp;<a
									href="javascript:OnDeleteUser('<bean:write property="id" name="user" />')"><bean:message
									key="page.action.delete" /></a>&nbsp;<a
									href="javascript:OnAuthorizationConfig('<bean:write property="id" name="user" />')"><bean:message
									key="user.action.authorizationConfig" /></a>
							</logic:notEqual></td>
						</tr>
					</logic:iterate>

					<tr>
						<td colspan="3" class="bggray">
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
		<html:button property="button" onclick="OnAddUser()"
			styleClass="graybox">
			<bean:message key="button.add" />
		</html:button> <html:button property="button" onclick="OnReturn()"
			styleClass="graybox">
			<bean:message key="button.return" />
		</html:button></td>
	</tr>
</table>

<input type="hidden" id="pageURL" value="userListAction.do" />
<input type="hidden" id="params"
	value='<bean:write name="params" filter="false"/>' />
<br>
<br>
<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
<!--
var employeeId = '<bean:write name="employeeId" />'
function OnAddUser() {
	window.location = "initUserAction.do?method=add&employeeId="+employeeId;
}

function OnReturn(employeeId) {
	window.location = "employeeListAction.do";
}

function OnEditUser(id) {
	window.location = "initUserAction.do?method=edit&userId="+id;
}

function OnDeleteUser(id) {
	if (!confirm("<bean:message key="page.confirmDelete" />"))
		return;
	window.location = "initUserAction.do?method=delete&userId="+id;
}

function OnAuthorizationConfig(userId){
	window.location = "initAuthorizationConfigAction.do?userId="+userId;
}
//-->
</script>
