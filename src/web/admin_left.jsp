<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<html:base />
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<br>
<br>
<table width="90%" border="0">
	<tr>
		<td colspan="2" valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td width="8%" align="left" class="titlecontent"><img
					src="image/title_head.gif" alt="No" width="28" height="30" /></td>
				<td width="88%" class="titlecontent"><br><bean:message key="adminLeft.title"/></td>
				<td width="4%" class="titlecontent" align="right"><img
					src="image/title_foot.gif" alt="No" width="8" height="30" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td valign="top" class="graybox" width="33%">
		<div align="center">
		<table width="100%" border="0">
			<tr>
				<td height="56" background="image/makeAdonate.gif">&nbsp;</td>
			</tr>
		</table>
		</div>
		<div class="comment" align="center"><br>
		<br>
		<br>
		<br>
		<a href="areaListAction.do"><bean:message key="adminLeft.area" /></a>
		<br>
		<br>
		<br>
		<br>
		<a href="employeeListAction.do"><bean:message key="adminLeft.employee" /></a>
		<br>
		<br>
		<br>
		<br>
		<a href="newsListAction.do"><bean:message key="adminLeft.news" /></a>
		<br>
		<br>
		<br>
		<br>
		<a href="smsListAction.do"><bean:message key="adminLeft.sms" /></a>
		<br>
		<br>
		</div>
		</td>
	</tr>
</table>


</body>
</html>
