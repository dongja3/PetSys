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
<title><bean:message key="page.title.user" /></title>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<html:form action="userAction">
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
					<html:hidden property="employeeId" />

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="2" class="tdtitle"><bean:message key="user.title" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="user.userName" /></td>
							<td><logic:equal name="userForm" property="method" value="edit">
								<html:text property="userName" readonly="true" />
								<bean:message key="page.info.noedit" />
							</logic:equal> <logic:equal name="userForm" property="method"
								value="add">
								<html:text property="userName" maxlength="10" />
								<font color="red">*</font>
							</logic:equal></td>
						</tr>
						<tr>
							<logic:equal name="userForm" property="method" value="edit">
								<td class="bggray"><bean:message key="user.password" /></td>
								<td><html:password property="password" maxlength="20" /><font
									color="red">*</font></td>
							</logic:equal>
							<logic:equal name="userForm" property="method" value="add">
								<td class="bggray"><bean:message key="user.password" /></td>
								<td><html:hidden property="password" value="123456" /> <bean:message
									key="page.info.default.password" /></td>
							</logic:equal>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="user.systemRole" /></td>
							<td><html:select property="sysRole">
								<html:option value="admin">
									<bean:message key="user.role.admin" />
								</html:option>
								<html:option value="sysInputor">
									<bean:message key="user.role.sysInputor" />
								</html:option>
								<html:option value="sysDoctor">
									<bean:message key="user.role.sysDoctor" />
								</html:option>
								<html:option value="sysQuery">
									<bean:message key="user.role.sysQuery" />
								</html:option>
								<html:option value="sysVaccine">
									<bean:message key="user.role.sysVaccine" />
								</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="user.area" /></td>
							<td><html:select property="areaId">
								<html:options name="userForm" property="areaIdList"
									labelProperty="areaNameList" />
							</html:select></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="user.note" /></td>
							<td><html:text property="note" maxlength="100" /></td>
						</tr>
						<tr>
							<td colspan="2"><bean:message key="page.info.required" /></td>
					</TABLE>

					</td>
				</tr>
			</table>
			<html:submit property="submit" styleClass="graybox">
				<bean:message key="button.confirm" />
			</html:submit> &nbsp;&nbsp;&nbsp;&nbsp; <html:button
				property="button" onclick="OnBack()" styleClass="graybox">
				<bean:message key="button.accountList.return" />
			</html:button></td>
		</tr>
	</table>
</html:form>

<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
<!--
  var employeeId = '<bean:write name="userForm" property="employeeId" />'
  function OnBack(){
  	window.location="userListAction.do?employeeId="+employeeId;
  }
//-->
</script>
