<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "¹ÜÀí";
			String con_childMoudleName = "ÇøÓò";

		%>
<html>
<head>
<title><bean:message key="page.title.admin.areaList" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<table width="96%" align="center" border="0">
	<tr>
		<td width="30%" valign="top"><%@ include file="admin_left.jsp"%></td>
		<td valign="top"><br>
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
						<td colspan="3" class="tdtitle"><bean:message key="area.title" /></td>
					</tr>
					<tr class="bggray">
						<td><b><bean:message key="area.name" /></b></td>
						<td><b><bean:message key="area.areaCode" /></b></td>
						<td><b><bean:message key="page.action" /></b></td>
					</tr>
					<logic:iterate id="area" name="areaList">
						<tr>
							<logic:notEqual name="area" property="id" value="rootArea">
								<td>&nbsp;<bean:write property="name" name="area" /></td>
								<td>&nbsp;<bean:write property="areaCode" name="area" /></td>
								<logic:equal name="area" property="areaCode" value="330000">
									<td><a
										href="javascript:OnNewArea('<bean:write property="id" name="area" />')"><bean:message
										key="area.action.addSubArea" /></td>
								</logic:equal>

								<logic:notEqual name="area" property="areaCode" value="330000">

									<td>&nbsp;<a
										href="javascript:OnEditArea('<bean:write property="id" name="area" />')"><bean:message
										key="page.action.edit" /></a>&nbsp;&nbsp;<a
										href="javascript:OnDeleteArea('<bean:write property="id" name="area" />')"><bean:message
										key="page.action.delete" /></a> &nbsp;&nbsp;<a
										href="javascript:OnNewArea('<bean:write property="id" name="area" />')"><bean:message
										key="area.action.addSubArea" /></td>
								</logic:notEqual>
							</logic:notEqual>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="3" class="bggray">
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

		</td>
	</tr>
</table>

<input type="hidden" id="pageURL" value="areaListAction.do" />
<input type="hidden" id="params"
	value='<bean:write name="params" filter="false"/>' />
<br>
<br>

<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
function OnNewArea(id) {
	window.location = "initAreaAction.do?method=add&fatherareaId="+id;
}

function OnEditArea(id) {
	window.location = "initAreaAction.do?method=edit&areaId="+id;
}

function OnDeleteArea(id) {
	if (!confirm("<bean:message key="page.confirmDelete" />"))
		return;
	window.location = "initAreaAction.do?method=delete&areaId="+id;
}

//-->
</script>
