<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "管理";
  String con_childMoudleName = "权限";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title></title>
</head>
<body topmargin="5" leftmargin="20">
<%@ include file="header.jsp"%>
<table width="96%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
		<td width="70%"><br>
		<br>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>
				<FORM METHOD="POST" ACTION="authorizationConfigAction.do"
					name="authorizationConfigForm">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="6" class="tdtitle"><bean:message
							key="authorization.title" /></td>
					</tr>
					<tr class="bggray">
						<td width="10%">&nbsp;</td>
						<td width="25%"><bean:message key="authorization.moudleName" /></td>
						<td width="25%"><bean:message key="authorization.childMoudleName" /></td>
						<td width="40%" style="display:none"><bean:message key="page.action" /></td>
					</tr>
					<%User user = (User)request.getAttribute("user");
			int isum = 0;
			String moudleName = "";
			String childMoudleName = "";
			String sysRoleName = user.getSysRole();
			SystemLogger.debug("sysRoleName:"+sysRoleName);
			String[] moudleNames = Authorization.getMoudleNamesValue(sysRoleName);
			Set authorizationSet = (Set) request
					.getAttribute("authorizationSet");
			for (int i = 0; i < moudleNames.length; i++) {
				String[] childMoudleNames = Authorization
						.getChildMoudleNamesValue(moudleNames[i]);
				moudleName = moudleNames[i];
				for (int ii = 0; ii < childMoudleNames.length; ii++) {
					childMoudleName = childMoudleNames[ii];
					Authorization authorization = structService
							.findAuthorizationBymoudleName(moudleName,
									childMoudleName, user.getId());
					if (authorizationSet.contains(authorization)) {

						%>
					<tr>
						<td class="tinttd"><INPUT TYPE="checkbox"
							NAME="authorization<%=isum%>" value="authorization<%=isum%>"
							checked onclick="OnClick(this)"></td>
						<td class="tinttd"><input type="hidden" name="moudleName<%=isum%>"
							value="<%=moudleName%>"><%=moudleName%></td>
						<td class="tinttd"><input type="hidden"
							name="childMoudleName<%=isum%>" value="<%=childMoudleName%>"><%=childMoudleName%>
						</td>
					
						<td class="tinttd" style="display:none"><%if (authorization.getActAdd().equals(
								Authorization.ACT_PRRMIT_YES)) {

							%> <INPUT TYPE="checkbox" NAME="add<%=isum%>" value="yes" checked>&nbsp;<bean:message
							key="page.action.add" /> <%} else {

							%> <INPUT TYPE="checkbox" NAME="add<%=isum%>" value="yes">&nbsp;<bean:message
							key="page.action.add" /> <%}
						if (authorization.getActUpdate().equals(
								Authorization.ACT_PRRMIT_YES)) {

							%> <INPUT TYPE="checkbox" NAME="modify<%=isum%>" value="yes"
							checked>&nbsp;<bean:message key="page.action.edit" /> <%} else {

							%> <INPUT TYPE="checkbox" NAME="modify<%=isum%>" value="yes">&nbsp;<bean:message
							key="page.action.edit" /> <%}
						if (authorization.getActDelete().equals(
								Authorization.ACT_PRRMIT_YES)) {

							%> <INPUT TYPE="checkbox" NAME="delete<%=isum%>" value="yes"
							checked>&nbsp;<bean:message key="page.action.delete" /> <%} else {

							%> <INPUT TYPE="checkbox" NAME="delete<%=isum%>" value="yes">&nbsp;<bean:message
							key="page.action.delete" /> <%}

					%></td>					 
					</tr>
					<%} else {

						%>
					<tr>
						<td><INPUT TYPE="checkbox" NAME="authorization<%=isum%>"
							value="authorization<%=isum%>" onclick="OnClick(this)"></td>
						<td><input type="hidden" name="moudleName<%=isum%>"
							value="<%=moudleName%>"><%=moudleName%></td>
						<td><input type="hidden" name="childMoudleName<%=isum%>"
							value="<%=childMoudleName%>"> <%=childMoudleName%></td>
						
						<td style="display:none"><INPUT TYPE="checkbox" NAME="add<%=isum%>" value="yes">&nbsp;<bean:message
							key="page.action.add" /><INPUT TYPE="checkbox"
							NAME="modify<%=isum%>" value="yes">&nbsp;<bean:message
							key="page.action.edit" /><INPUT TYPE="checkbox"
							NAME="delete<%=isum%>" value="yes"><bean:message
							key="page.action.delete" /></td>
						
					</tr>
					<%}
					isum++;
				}
			}

			%>
					<tr>
						<td colspan="7"></td>
					</tr>
				</table>
				<input type="hidden" name="userId" value="<%=user.getId()%>" /> <input
					type="hidden" name="authorizationSum" value="<%=isum%>" /> <br>
				</td>
			</tr>
		</table>
		<html:submit property="submit" styleClass="graybox">
			<bean:message key="button.confirm" />
		</html:submit> &nbsp;&nbsp;&nbsp;&nbsp; <html:button property="button"
			onclick="OnBack()" styleClass="graybox">
			<bean:message key="button.return" />
		</html:button></td>
	</tr>
</table>

<br>
<br>

<%@ include file="footer.jsp"%>
</FORM>
<br>
&nbsp;&nbsp;
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
var employeeId = '<bean:write name="employeeId" />' ;
function OnBack(){		
	window.location= "userListAction.do?employeeId="+employeeId;
}

function OnClick(obj){
    var nameString = obj.name;
    var objname= "document.authorizationConfigForm.add"+nameString.substring(13,nameString.length);	
	var objname1= "document.authorizationConfigForm.modify"+nameString.substring(13,nameString.length);	
	var objname2= "document.authorizationConfigForm.delete"+nameString.substring(13,nameString.length);	
    var tempObj = eval(objname);
	var tempObj1 = eval(objname1);
	var tempObj2 = eval(objname2);
	if(obj.checked){
		tempObj.checked = true;
		tempObj1.checked = true;
		tempObj2.checked = true;
	}else{
		tempObj.checked = false;
		tempObj1.checked = false;
		tempObj2.checked = false;
	}
}
//-->
</SCRIPT>
