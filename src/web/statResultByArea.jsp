<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "查询报表";
			String con_childMoudleName = "查询报表";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<title><bean:message key="pet.statResultByArea" /></title>
</head>
<body>
<%@ include file="header.jsp"%>
<br>
<br>
<table width="96%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="25%" valign="top">
		<div align="center"><img src="image/petOwner.jpg"></div>
		</td>
		<td width="75%">

		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>

				<table width="100%" border="1" cellspacing="0" cellpadding="0"
					bordercolordark="#ffffff" bordercolorlight="#000000">
					<tr>
						<td>

						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="100%" colspan="4" class="tdtitle"><bean:message
									key="pet.statResultByArea" /></td>
							</tr>
							<tr class="bggray">
								<td width="30%"><b><bean:message key="pet.petName" /></b></td>
								<td width="10%"><b><bean:message key="pet.sex" /></b></td>
								<td width="35%"><b><bean:message key="pet.petNo" /></b></td>
								<td width="25%"><b><bean:message key="page.action" /></b></td>
							</tr>
							<logic:iterate name="petList" id="pet">
								<tr>
									<td>&nbsp;<bean:write name="pet" property="petName" /></td>
									<td>&nbsp; <logic:equal name="pet" property="sex" value="male">
										<bean:message key="pet.sex.male" />
									</logic:equal> <logic:equal name="pet" property="sex"
										value="female">
										<bean:message key="pet.sex.female" />
									</logic:equal></td>
									<td>&nbsp;<bean:write name="pet" property="petNo" /></td>
									<td>&nbsp;<a
										href="javascript:OnClick('<bean:write name="pet" property="id" />')"><bean:message
										key="pet.petList.petDatail" /></a>&nbsp;&nbsp;</td>
								</tr>
							</logic:iterate>
							<tr>
								<td colspan="7">

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

				</td>
			</tr>
		</table>
		<br>
		<input type="hidden" id="pageURL"
			value="statResultShow.do?method=statByArea" /> <input type="hidden"
			id="params" value='<bean:write name="params" filter="false"/>' /> <html:button
			property="button" onclick="history.back()" styleClass="graybox">
			<bean:message key="button.return" />
		</html:button></td>
	</tr>
</table>

<br>
<br>
<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--

//当单击是否弹跳出模式窗口
function OnClick(id){	
    var url  ;
	  url ="initPetAction.do?method=showPetInfo&petId="+id; 
	  var openOption = "dialogHeight:450px; dialogWidth:600px;";
	  window.showModalDialog(url,null,openOption);
	  }

//-->

</script>
