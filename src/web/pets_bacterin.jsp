<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<html>
<head>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<logic:messagesPresent>
	<bean:message key="errors.header" />
	<ul>
		<html:messages id="error">
			<li><bean:write name="error" /></li>
		</html:messages>
	</ul>
</logic:messagesPresent>

<html:form action="bactInj">

	<html:hidden property="method" />
	<html:hidden property="id" />

	<table width="80%" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="6"><bean:message key="injection.title" /></td>
		</tr>
		<tr>
			<td><bean:message key="injection.name" /></td>
			<td><html:text property="name" size="30" maxlength="30" /></td>
			<td><bean:message key="injection.supplier" /></td>
			<td><html:text property="supplier" size="30" maxlength="30" /></td>
			<td><bean:message key="injection.batch" /></td>
			<td><html:text property="batch" size="30" maxlength="30" /></td>
		</tr>
		<tr>
			<td><bean:message key="injection.time" /></td>
			<td><html:text property="time" size="30" maxlength="30" /></td>
			<td><bean:message key="injection.immunity" /></td>
			<td><html:select property="actionMethod">
				<html:option value="muscle">
					<bean:message key="injection.immunity.muscle" />
				</html:option>
				<html:option value="body">
					<bean:message key="injection.immunity.body" />
				</html:option>
			</html:select> &nbsp;&nbsp;&nbsp;&nbsp; <html:text
				property="actionLocation" /></td>
			<td><bean:message key="injection.hospital" /></td>
			<td><html:text property="hospital" size="30" maxlength="30" /></td>
		</tr>
		<tr>
			<td><bean:message key="injection.remark" /></td>
			<td colspan="5"><html:textarea property="remark" /></td>
		</tr>
	</table>

	<html:submit property="submit">
		<bean:message key="button.confirm" />
	</html:submit> &nbsp;&nbsp;&nbsp;&nbsp; <html:cancel property="cancel">
		<bean:message key="button.return" />
	</html:cancel>

</html:form>

<%@ include file="footer.jsp"%>
</body>
</html>
