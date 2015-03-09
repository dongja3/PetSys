<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.drategy.pets.domain.Pet,com.drategy.pets.bom.*,java.util.*"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "宠物及主人";
			String con_childMoudleName = "宠物";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title><bean:message key="page.title.petList" /></title>
</head>
<body>
<%@ include file="header.jsp"%>
<table width="96%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="25%" valign="top">
		<div align="center"><br>
		<br>
		<img src="image/pet.jpg"></div>
		</td>
		<td width="75%"><br>
		<br>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%" colspan="4" class="tdtitle"><bean:message
							key="pet.petList" /></td>
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
								key="pet.petList.petDatail" /></a>&nbsp;&nbsp; <%if (!loginUser.getSysRole().equals(conSysRole_admin)) {%><a
								href="javascript:OnEdit('<bean:write name="pet" property="id" />')"><bean:message
								key="page.action.edit" /></a> &nbsp;&nbsp;<%}%><a
								href="javascript:OnDelete('<bean:write name="pet" property="id" />')"><bean:message
								key="page.action.delete" /></a></td>
						</tr>
					</logic:iterate>

				</table>
				</td>
			</tr>
		</table>
		<br>
		<%if (!loginUser.getSysRole().equals(conSysRole_admin)) {%> <html:button
			property="button" onclick="AddPet()" styleClass="graybox">
			<bean:message key="button.petList.addPet" />
		</html:button> &nbsp;&nbsp;&nbsp;&nbsp;<%}%> <html:button
			property="return" onclick="OnBack()" styleClass="graybox">
			<bean:message key="button.petOwner.return" />
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
var petOwnerId = '<bean:write name="petOwnerId"/>';
function AddPet() {
	window.location = "initPetAction.do?method=add&petOwnerId="+petOwnerId;
}

function OnBack() {
	window.location = "petOwnerListAction.do"
}

function OnEdit(id){
	window.location = "initPetAction.do?method=edit&petId="+id;
}

function OnDelete(id){
    if (confirm("<bean:message key="page.confirmDelete" />")){
		window.location = "initPetAction.do?method=delete&petId="+id;
	}
}


<!--
function viewnone(e){
e.style.display=(e.style.display=="none")?"":"none";
}

//当单击是否弹跳出模式窗口
function OnClick(id){	
    var url  ;
	  url ="initPetAction.do?method=showInfo&petId="+id; 
	  var openOption = "dialogHeight:400px; dialogWidth:600px;";
	  window.showModalDialog(url,null,openOption);
	  }


//-->
</script>
