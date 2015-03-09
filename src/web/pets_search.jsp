<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title><bean:message key="page.title.search" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
<html:base />
</head>
<body onload="focus()" >

<br>
<br>
<br>
<br>
<table width="60%" border="0" align="center">
	<tr>
		<td><logic:messagesPresent>
			<bean:message key="errors.header" />
			<ul>
				<html:messages id="error">
					<li><bean:write name="error" /></li>
				</html:messages>
			</ul>
		</logic:messagesPresent> <html:form action="lostandFoundAction2"
			method="post">
			<table width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolordark="#ffffff" bordercolorlight="#000000" align="center">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="3" class="tdtitle"><bean:message key="search.chipNo" /></td>
						</tr>
						<tr>
							<td><bean:message key="search.chipNo" /></td>
							<td><html:text property="chipNo" /></td>
							<td><html:submit property="submit" styleClass="graybox">
								<bean:message key="button.search" />
							</html:submit></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
		</html:form></td>
	</tr>
</table>
</body>
</html>
<script language="JavaScript">
<!--
function focus(){this.document.forms[0].chipNo.focus();}
//-->
</script>
