<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>

<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<title><bean:message key="mainFrame.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
<html:base />
</head>

<body>

<%@ include file="header.jsp"%>
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
						<td width="88%" class="titlecontent"><bean:message
							key="loginForm.userLogin" /></td>
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
				<td width="100%" valign="top"><%if (userObj == null) {

			%> <html:form action="login">
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
				</html:form> <%} else {
				String userName = loginUser.getUserName();%>
				<table border="0" width="100%">
					<tr class="comment">
						<td align="left">&nbsp;</td>
						<td align="left"><b><%=userName%></b></td>
					</tr>
					<tr class="comment">
						<td align="left">&nbsp;</td>
						<td align="left"><bean:message key="loginForm.welcome" /></td>
					</tr>
					<tr class="comment">
						<td colspan="2"><INPUT TYPE="button"
							value="<bean:message key="button.changePassword" />"
							class="graybox" onclick="changePassword()"></td>
					</tr>
				</table>

				<%}

		%></td>
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
								<td width="88%" class="titlecontent"><bean:message
									key="loginForm.lostFound" /></td>
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
						<html:form action="lostandFoundAction3" method="post">

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
		<td width="68%" valign="top"><br>
		<br>
		<table width="96%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000" align="center">
			<tr>
				<td colspan="4">
				<table width="100%" align="center">
					<tr>
						<td colspan="4" class="tdtitle"><bean:message key="news.list" /></td>
					</tr>
					<tr class="bggray">
						<td><b><bean:message key="news.title" /></b></td>
						<td><b><bean:message key="news.author" /></b></td>
						<td><b><bean:message key="news.date" /></b></td>
						<td><b><bean:message key="news.view.detail" /></b></td>
					</tr>
					<logic:iterate id="news" name="newsList">
						<tr>
							<td>&nbsp;<bean:write property="title" name="news" /></td>
							<td width="80">&nbsp;<bean:write property="author" name="news" /></td>
							<td width="80">&nbsp;<bean:write property="date" name="news" /></td>
							<td width="80">&nbsp; <a
								href="javascript:OnClick('<bean:write property="id" name="news" />')"><bean:message
								key="news.view.action" /></a></td>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="4" class="bggray">
						<table cellpadding="0" cellspacing="0" align="center" border="0"
							width="100%">
							<tr class="graybox">
								<td width="50%">
								<div align="left">&nbsp;<bean:write name="hPage"
									property="pageInfo" filter="false" /></div>
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
		<input type="hidden" id="pageURL"
			value="newsListAction.do?method=show" /> <input type="hidden"
			id="params" value='<bean:write name="params" filter="false"/>' /></td>
	</tr>
</table>
<br>
<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
function changePassword()
 {
   window.open("changePassword.jsp",'', 'height=140, width=200, top=280, left=350, toolbar=no, menubar=no,scrollbars=no, resizable=no,location=no, status=no');
 }
 //当单击是否弹跳出模式窗口
function OnClick(id){	
    var url  ;
	  url ="newsInfoAction.do?id="+id; 
	  var openOption = "dialogHeight:600px; dialogWidth:600px;";
	  window.showModalDialog(url,null,openOption);
	  }
 
</script>

