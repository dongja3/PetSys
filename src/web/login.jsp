<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title><bean:message key="loginForm.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
<html:base />
</head>

<body>
					
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="68%"><img src="image/logo.gif" width="348"
			height="52" /></td>
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
				<td nowrap="nowrap" width="100%">&nbsp;</td>
			</tr>
		</table>
		</td>
		<td width="2%" align="right" class="menutd"><img
			src="image/menu_foot.jpg" width="10" height="30" /></td>
	</tr>
</table>
<table width="100%" border="0">
	<tr>
		<td width="32%" class="newstd" valign="top">
		<table width="100%" border="0">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="3" align="left"><logic:messagesPresent>
							<bean:message key="errors.header" />
							<ul>
								<html:messages id="error">
									<li><bean:write name="error" /></li>
								</html:messages>
							</ul>
						</logic:messagesPresent></td>
					</tr>
					<tr>
						<td width="8%" align="left" class="titlecontent"><img
							src="image/title_head.gif" alt="No" width="28" height="30" /></td>
						<td width="88%" class="titlecontent"><bean:message key="loginForm.userLogin" /></td>
						<td width="4%" class="titlecontent" align="right"><img
							src="image/title_foot.gif" alt="No" width="8" height="30" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td width="0%" valign="top"><img class="imagestyle"
					src="image/news-capture-image.gif" alt="Car" width="118"
					height="102" /></td>
				<td width="100%" valign="top">
				
				<html:form action="login">
					<table border="0" width="100%">
						<html:hidden property="action" />
						<tr class="comment">
							<td align="left"><bean:message key="loginForm.userName" /></td>
							<td align="left"><html:text property="userName" size="15"
								maxlength="30" /></td>
						</tr>
						<tr class="comment">
							<td align="left"><bean:message key="loginForm.password" /></td>
							<td align="left"><html:password property="password" size="15"
								maxlength="30" /></td>
						</tr>
						<tr class="comment">
							<td>&nbsp;</td>
							<td><html:submit property="submit" styleClass="graybox">
								<bean:message key="button.login" />
							</html:submit></td>
						</tr>
					</table>
				</html:form>
				</td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="8%" align="left" class="titlecontent"><img
									src="image/title_head.gif" alt="No" width="28" height="30" /></td>
								<td width="88%" class="titlecontent"><bean:message key="loginForm.lostFound" /></td>
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
						<html:form action="lostandFoundAction" method="post">

							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								
								<tr>
									<td><bean:message key="search.chipNo" /></td>
									<td><html:text property="chipNo" /></td>
									<td><html:submit property="submit" styleClass="graybox">
										<bean:message key="button.search" />
									</html:submit></td>
								</tr>
							</table>

						</html:form></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
		<td width="68%" valign="top">
		<table width="100%" border="0">
			<tr>
				<td width="0%" height="340" valign="top"><br>
				<img src="image/fund-capture-image.gif" alt="Welcome to our fund"
					width="140" height="139" /></td>
				<td width="100%" valign="top">
				<div id="textbox">
				<iframe height="100%" width="98%" frameBorder="0" src="newsTopListAction.do"></iframe>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<%@ include file="footer.jsp"%>
</body>
</html>
