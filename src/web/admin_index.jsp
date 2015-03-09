<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<html>
<head>
<title><bean:message key="page.title.admin" /></title>
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

<table width=100%>
	<tr>
		<td width="30%"><%@ include file="admin_left.jsp"%></td>
		<td align="center"><img src="image/drategy.jpg" /></td>
	</tr>
</table>

<%@ include file="footer.jsp"%>
</body>
</html>
