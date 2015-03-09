<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "管理";
			String con_childMoudleName = "新闻";
		%>
<html>
<head>
<title><bean:message key="page.title.newsList" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<table width="96%" align="center" border="0">
	<tr>
		<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
		<td td valign="top"><br>
		<br>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td><logic:messagesPresent>
					<bean:message key="errors.header" />
					<ul>
						<html:messages id="error">
							<li><bean:write name="error" /></li>
						</html:messages>
					</ul>
					<hr>
				</logic:messagesPresent>
				<table width="100%">
					<tr>
						<td colspan="4" class="tdtitle"><bean:message key="news.list" /></td>
					</tr>
					<tr class="bggray">
						<td><b><bean:message key="news.title" /></b></td>
						<td><b><bean:message key="news.author" /></b></td>
						<td><b><bean:message key="news.date" /></b></td>
						<td><b><bean:message key="page.action" /></b></td>
					</tr>
					<logic:iterate id="news" name="newsList">
						<tr>
							<td>&nbsp;<bean:write property="title" name="news" /></td>
							<td width="80">&nbsp;<bean:write property="author" name="news" /></td>
							<td width="80">&nbsp;<bean:write property="date" name="news" /></td>
							<td width="80">&nbsp;<a
								href="javascript:OnEditNews('<bean:write property="id" name="news" />')"><bean:message
								key="page.action.edit" /></a>&nbsp;&nbsp;<a
								href="javascript:OnDeleteNews('<bean:write property="id" name="news" />')"><bean:message
								key="page.action.delete" /></a></td>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="4" class="bggray">
						<table cellpadding="0" cellspacing="0" align="center" border="0"
							width="100%">
							<tr>
								<td width="50%">
								<div align="left"><bean:write name="hPage" property="pageInfo"
									filter="false" /></div>
								</td>
								<td width="50%">
								<div align="right"><bean:write name="hPage" property="goPages"
									filter="false" /></div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<html:button property="button" onclick="OnNewNews()"
			styleClass="graybox">
			<bean:message key="news.buttion.addNews" />
		</html:button></td>
	</tr>
</table>

<input type="hidden" id="pageURL" value="newsListAction.do" />
<input type="hidden" id="params"
	value='<bean:write name="params" filter="false"/>' />
<br>
<br>

<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
function OnNewNews() {
	window.location = "initNewsAction.do?method=add";
}

function OnEditNews(id) {
	window.location = "initNewsAction.do?method=edit&id="+id;
}

function OnDeleteNews(id) {
	if (!confirm("<bean:message key="page.confirmDelete" />"))
		return;
	window.location = "initNewsAction.do?method=delete&id="+id;
}

//-->
</script>
