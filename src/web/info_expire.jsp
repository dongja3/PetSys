<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<title><bean:message key="error.page.expire.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="68%"><img src="image/logo.gif" width="348" height="52" /></td>
		<td width="32%" valign="top">
		<table width="200" border="0" align="right" cellpadding="0"
			cellspacing="0" class="toplink">
			<tr>
				<td height="38" class="toptd"><img src="image/headdot.gif" /><br />
				<a href="pets_search.jsp" target="_blank"><bean:message key="header.search" /></a></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headline.gif"
					width="3" height="41" /></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headdot.gif" /><br />
				<a href="help.jsp" target="_blank"><bean:message key="header.help" /></a></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headline.gif"
					width="3" height="41" /></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headdot.gif" /><br />
				<bean:message key="header.logout" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="wrapperborder">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="100" class="bannerbg">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="10%""><img src="image/banner-bg-left.gif" alt="no" /></td>
						<td width="47%" valign="bottom"><img src="image/front-pet.gif"
							alt="Two Cats" /></td>
						<td width="38%"><font class="bannerfonttitle"><bean:message
							key="header.autoid" /></font><br />
						<br />
						<br />
						</td>
						<td width="5%" align="right"><img src="image/banner-bg-right.gif"
							alt="no" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="menubar">
	<tr>
		<td width="1%" height="30" class="menutd"><img
			src="image/menu_head.jpg" width="11" height="30" /></td>
		<td width="97%" class="menutd" id="whitelink">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="menufont">
			<tr>
				<td width="9%" nowrap="nowrap" class="menuitem  sel"><a
					href="newsListAction.do?method=show"><font color="#000000"><bean:message
					key="header.menu.homepage" /><font></a></td>
				<td nowrap="nowrap" width="100%">&nbsp;</td>
			</tr>
		</table>
		</td>
		<td width="2%" align="right" class="menutd"><img
			src="image/menu_foot.jpg" width="10" height="30" /></td>
	</tr>
</table>
<br>
<br>
<table width="96%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="25%" valign="top">
		<div align="center"><img src="image/info.jpg"></div>
		</td>
		<td width="75%">

		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td class="tdtitle"><bean:message key="error.page.expire.title" /></td>
			</tr>
			<tr>
				<td><br>
				<br>
				<br>
				<br>
				<bean:message key="error.page.expire" /><br>
				<br>
				<br>
				<br>
				</td>
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
