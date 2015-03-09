<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title><bean:message key="page.title.news" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
<html:base />
</head>
<body>
<a href="#1"></a>
<br>
<br>

<table width="90%" border="1" cellspacing="0" cellpadding="0"
	bordercolordark="#ffffff" bordercolorlight="#000000" align="center">
	<tr>
		<td>

		<table border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000" width="100%">
			<TR>
				<TD class="tdtitle" colspan="2">
				<div align="center"><bean:write name="news" property="title" /></div>
				</TD>
			</tr>
			<tr>
				<TD colspan="2"><br>
				<bean:write name="news" property="content" filter="false" /><br><br>
				</TD>
			</TR>
			<tr class="bggray">
				<TD><bean:message key="news.author" /></TD>
				<TD>&nbsp;<bean:write name="news" property="author" /></TD>
			</TR>
			<tr class="bggray">
				<TD><bean:message key="news.date" /></TD>
				<TD>&nbsp;<bean:write name="news" property="date" /></TD>
			</TR>
			<tr>
		</TABLE>

		</td>
	</tr>
</table>

<br>
<br>
<div align="center"><input type="button"
	onClick="window.opener = 'xxx';window.close();" class="graybox"
	value="<bean:message key="button.close" />"></div>

</body>
</html>

