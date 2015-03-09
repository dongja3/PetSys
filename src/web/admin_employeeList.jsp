<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "管理";
			String con_childMoudleName = "员工";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<title><bean:message key="page.title.employeeList" /></title>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

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
			<hr>
		</logic:messagesPresent>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>

				<table border="0" width="100%">
					<tr>
						<td colspan="3" class="tdtitle"><bean:message
							key="employee.title.list" /></td>
					</tr>
					<tr class="bggray">
						<td><b><bean:message key="employee.name" /></b></td>
						<td><b><bean:message key="employee.sex" /></b></td>
						<td><b><bean:message key="page.action" /></b></td>
					</tr>
					<logic:iterate id="employee" name="employeeList">
						<tr>
							<td><bean:write property="name" name="employee" /></td>
							<td><logic:equal property="sex" name="employee" value="male">
								<bean:message key="owner.sex.male" />
							</logic:equal> <logic:equal property="sex" name="employee"
								value="female">
								<bean:message key="owner.sex.female" />
							</logic:equal></td>
							<logic:equal name="employee" property="id" value="admin">
								<td><a
									href="javascript:OnAddAccount('<bean:write property="id" name="employee" />')"><bean:message
									key="employee.action.addAccount" /></a> <a
									href="javascript:OnListAccount('<bean:write property="id" name="employee" />')"><bean:message
									key="employee.action.AccountList" /></a></td>
							</logic:equal>
							<logic:notEqual name="employee" property="id" value="admin">
								<td><a
									href="javascript:OnEditEmployee('<bean:write property="id" name="employee" />')"><bean:message
									key="page.action.edit" /></a>&nbsp;&nbsp;<a
									href="javascript:OnDeleteEmployee('<bean:write property="id" name="employee" />')"><bean:message
									key="page.action.delete" /></a>&nbsp;&nbsp;<a
									href="javascript:OnAddAccount('<bean:write property="id" name="employee" />')"><bean:message
									key="employee.action.addAccount" /></a> <a
									href="javascript:OnListAccount('<bean:write property="id" name="employee" />')"><bean:message
									key="employee.action.AccountList" /></a></td>
							</logic:notEqual>
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
		<html:button property="button" onclick="OnAddEmployee()"
			styleClass="graybox">
			<bean:message key="button.add" />
		</html:button></td>
	</tr>
</table>

<input type="hidden" id="pageURL" value="employeeListAction.do" />
<input type="hidden" id="params"
	value='<bean:write name="params" filter="false"/>' />
<br>
<br>
<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
<!--
function OnAddEmployee() {
	window.location = "initEmployeeAction.do?method=add";
}

function OnListAccount(id){
    window.location="userListAction.do?employeeId="+id;
}

function OnEditEmployee(id) {
	window.location = "initEmployeeAction.do?method=edit&employeeId="+id;
}

function OnDeleteEmployee(id) {
	if (!confirm("<bean:message key="page.confirmDelete" />"))
		return;
	window.location = "initEmployeeAction.do?method=delete&employeeId="+id;
}

function OnAddAccount(id) {
	window.location = "initUserAction.do?method=add&employeeId="+id;
}

//-->
</script>
