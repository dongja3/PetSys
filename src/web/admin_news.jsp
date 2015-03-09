<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "管理";
			String con_childMoudleName = "新闻";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.news" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/date.js"> </SCRIPT>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<html:form action="newsAction.do">
	<table width="96%" align="center" border="0">
		<tr>
			<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
			<td valign="top"><br>
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
					<td><html:hidden property="method" /> <html:hidden property="id" />

					<table border="0" cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td colspan="2" class="tdtitle"><bean:message
								key="news.pageTitle" /></td>
						</tr>
						<TR>
							<TD><bean:message key="news.title" /></TD>
							<TD><html:text property="title" size="40" maxlength="100"/> <font color="red">*</font>
							</TD>
						</tr>
						<tr>
							<TD><bean:message key="news.content" /></TD>
							<TD><html:textarea property="content" rows="10" cols="60"  />
								<font color="red">*</font></TD>
						</TR>
						<tr>
							<TD><bean:message key="news.author" /></TD>
							<TD colspan="3"><html:text property="author" size="20" maxlength="10" /></TD>
						</TR>
						<tr>
							<TD><bean:message key="news.date" /></TD>
							<TD colspan="3"><html:text property="date" size="20"
								readonly="true" onclick="viewCalendar(this)" /><font color="red">*</font></TD>
						</TR>
						<tr>
							<td colspan="2"><bean:message key="page.info.required" /></td>
					</TABLE>

					</td>
				</tr>
			</table>
			<html:submit property="submit" styleClass="graybox">
				<bean:message key="button.confirm" />
			</html:submit> &nbsp;&nbsp;&nbsp;&nbsp; <html:button
				property="button" onclick="OnBack()" styleClass="graybox">
				<bean:message key="button.newsList.return" />
			</html:button></td>
		</tr>
	</table>
</html:form>

<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
<!--
  function OnBack(){
  	window.location="newsListAction.do";
  }
  
 
//-->
</script>
