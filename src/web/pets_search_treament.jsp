<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<html>
<head>
<title><bean:message key="page.title.treatment" /></title>
<html:base />
</head>
<body onload="focus()" >

<%@ include file="header.jsp"%>

<table width="96%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="25%" valign="top">
		<div align="center"><br>
		<br>
		<img src="image/casses.jpg"></div>
		</td>
		<td width="75%"><br>
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
				<td><html:form action="initTreatmentRecordAction" method="post">

					<html:hidden property="method" value="add" />

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="3" class="tdtitle"><bean:message key="search.chipNo" /></td>
						</tr>
						<tr>
							<td><bean:message key="search.chipNo" /></td>
							<td><html:text property="chipNo" size="30" maxlength="100" /><font
								color="red">*</font></td>
							<td><html:submit property="submit" styleClass="graybox">
								<bean:message key="button.search" />
							</html:submit></td>
						</tr>
					</table>
					<bean:message key="page.info.required" />
				</html:form></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>
<br>

<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
function focus(){this.document.forms[0].chipNo.focus();}
//-->
</script>