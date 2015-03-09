<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "query";
  String con_childMoudleName = "query";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="owner.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
<SCRIPT LANGUAGE="JavaScript" src="js/date.js"> </SCRIPT>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<br>
<br>

	<table width="96%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="25%" valign="top">
			<div align="center"><img src="image/stat.jpg"></div>
			</td>
			<td width="75%">
			<table width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolordark="#ffffff" bordercolorlight="#000000">
				<tr>
					<td>

					

					</td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>

	<br>
	<br>
	<%@ include file="footer.jsp"%>
</body>
</html>

