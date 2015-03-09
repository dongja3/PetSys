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
<title><bean:message key="page.title.employee" /></title>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<html:form method="post" action="employeeAction.do">
	<table width="96%" align="center" border="0">
		<tr>
			<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
			<td><logic:messagesPresent>
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
							<td colspan="2" class="tdtitle"><bean:message
								key="employee.title" /></td>
						</tr>
						<tr>
							<td><bean:message key="employee.name" /></td>
							<td><logic:equal name="employeeForm" property="method"
								value="edit">
								<html:text property="name" readonly="true" />
								<bean:message key="page.info.noedit" />
							</logic:equal> <logic:equal name="employeeForm" property="method"
								value="add">
								<html:text property="name" maxlength="10" />
								<font color="red">*</font>
							</logic:equal></td>
						</tr>
						<tr>
							<td><bean:message key="employee.sex" /></td>
							<td><html:select property="sex">
								<html:option value="male">
									<bean:message key="owner.sex.male" />
								</html:option>
								<html:option value="female">
									<bean:message key="owner.sex.female" />
								</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td><bean:message key="employee.age" /></td>
							<td><html:text property="age" maxlength="4"/></td>
						</tr>
						<tr>
							<td><bean:message key="employee.postCode" /></td>
							<td><html:text property="postCode" maxlength="6" /></td>
						</tr>
						<tr>
							<td><bean:message key="employee.residentID" /></td>
							<td><html:text property="residentID" maxlength="18"/><font color="red">*</font></td>
						</tr>
						<tr>
							<td colspan="2"><bean:message key="page.info.required"/></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<html:submit property="submit" styleClass="graybox">
				<bean:message key="button.confirm" />
			</html:submit> &nbsp;&nbsp;&nbsp;&nbsp; <html:button
				property="button" onclick="OnBack()" styleClass="graybox">
				<bean:message key="button.employeeList.return" />
			</html:button></td>
		</tr>
	</table>
</html:form>
<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
<!--
  function OnBack(){
  	window.location="employeeListAction.do";
  }
//-->
</script>
