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
<title></title>
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
						<td width="25%"><b><bean:message key="pet.petNo" /></b></td>
						<td width="35%"><b><bean:message key="page.action" /></b></td>
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
								href="javascript:OnPetInfo('<bean:write name="pet" property="id" />')"><bean:message
								key="pet.petList.petDatail" /></a>&nbsp;&nbsp;<a
								href="javascript:OnEditPetOwner('<bean:write name="pet" property="id" />')"><bean:message
								key="page.action.petEdit" /></a>&nbsp;&nbsp;<a
								href="javascript:OnEdit('<bean:write name="pet" property="id" />')"><bean:message
								key="page.action.edit" /></a>&nbsp;&nbsp;<a
								href="javascript:OnDelete('<bean:write name="pet" property="id" />')"><bean:message
								key="page.action.delete" /></a></td>
						</tr>
					</logic:iterate>

				</table>
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
<script language="JavaScript">
<!--

function OnEdit(id){
	window.location = "initPetAction.do?method=edit&petId="+id;
}

function OnDelete(id){
    if (confirm("<bean:message key="page.confirmDelete" />")){
		window.location = "initPetAction.do?method=delete&petId="+id;
	}
}

function OnPetInfo(id){
	window.location = "initPetAction.do?method=showInfo&petId="+id;
}


function OnEditPetOwner(id) {
	window.location = "initPetOwnerAction.do?method=edit&petId="+id;
}

//-->
</script>
