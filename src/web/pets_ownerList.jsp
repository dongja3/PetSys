<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "宠物及主人";
			String con_childMoudleName = "主人";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
<title><bean:message key="page.title.petsandowner" /></title>
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
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="tdtitle" style="CURSOR: hand" onClick="viewnone(test)"><bean:message
							key="ownerList.search" /></td>
					</tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					id="test" style="display:none ">
					<form name="searchOwnerForm" action="post">
					<tr class="bggray">
						<td><b><bean:message key="owner.title" /></b></td>
						<td><bean:message key="search.ownerName" /></td>
						<td><INPUT TYPE="text" name="name" size="20" /></td>
						<td><bean:message key="search.residentId" /></td>
						<td><INPUT TYPE="text" name="residentID" size="20" /></td>
						<td><INPUT TYPE="button"
							value="<bean:message key="button.search" />" class="graybox"
							onclick="query()"></td>
					</tr>
					</form>
					<tr>
						<td height="4"></td>
					</tr>
					<form name="searchPetForm" action="post">
					<tr class="bggray">
						<td><b><bean:message key="pet.pet" /></b></td>
						<td><bean:message key="search.petNo" /></td>
						<td><INPUT TYPE="text" name="petNo" size="20" /></td>
						<td><bean:message key="search.chipNo" /></td>
						<td><INPUT TYPE="text" name="rfidNo" size="20" /></td>
						<td><INPUT TYPE="button"
							value="<bean:message key="button.search" />" class="graybox"
							onclick="queryByPet()"></td>
					</tr>
					</form>
					<tr>
						<td height="4"></td>
					</tr>
					<form name="partnerForm" action="post">
					<tr class="bggray">
						<td><b><bean:message key="pet.partnership" /></b></td>
						<td colspan="4">
						<table width="100%">
							<tr>
								<td><bean:message key="owner.area" /></td>
								<td><select name="area">
									<option value="all"><bean:message key="area.all" /></option>
									<logic:iterate id="area" name="areaList">
										<option value="<bean:write name="area" property="areaCode" />"><bean:write
											name="area" property="name" /></option>
									</logic:iterate>
								</select></td>
								<td><bean:message key="pet.variety" /></td>
								<td><INPUT TYPE="text" name="variety" size="20"
									onclick="OnClick()" readonly /></td>
								<td><bean:message key="pet.sex" /></td>
								<td><select name="sex">
									<option value="male"><bean:message key="pet.sex.male" /></option>
									<option value="female"><bean:message key="pet.sex.female" /></option>
								</select></td>
							</tr>
						</table>
						</td>
						<td><INPUT TYPE="button"
							value="<bean:message key="button.search" />" class="graybox"
							onclick="partner()"></td>
					</tr>
					</form>
				</table>
				</td>
			</tr>
		</table>
		<br>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>

				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="5" class="tdtitle"><bean:message key="owner.title" /></td>
					</tr>
					<tr class="bggray">
						<td><b><bean:message key="owner.name" /></b></td>
						<td><b><bean:message key="owner.sex" /></b></td>
						<td><b><bean:message key="owner.area" /></b></td>
						<td><b><bean:message key="owner.residentID" /></b></td>
						<td><b><bean:message key="page.action" /></b></td>
					</tr>
					<logic:iterate id="owner" name="ownerList">
						<tr>
							<td>&nbsp;<bean:write property="name" name="owner" /></td>
							<td>&nbsp; <logic:equal property="sex" name="owner" value="male">
								<bean:message key="owner.sex.male" />
							</logic:equal> <logic:equal property="sex" name="owner"
								value="female">
								<bean:message key="owner.sex.female" />
							</logic:equal></td>
							<td>&nbsp;<bean:write property="areaName" name="owner" /></td>
							<td>&nbsp;<bean:write property="residentID" name="owner" /></td>
							<td>&nbsp; <a
								href="javascript:OnSeeAllPet('<bean:write property="id" name="owner" />')"><bean:message
								key="ownerList.action.petList" /></a> &nbsp;&nbsp; <%if (!loginUser.getSysRole().equals(conSysRole_admin)) {%>
							<a
								href="javascript:OnaddPet('<bean:write property="id" name="owner" />')"><bean:message
								key="ownerlist.action.addPet" /></a> <%}%> &nbsp;&nbsp; <%if (!loginUser.getSysRole().equals(conSysRole_admin)) {%>
							<a
								href="javascript:OnEditPetOwner('<bean:write property="id" name="owner" />')"><bean:message
								key="page.action.edit" /></a> <%}%> &nbsp;&nbsp; <a
								href="javascript:OnDeletePetOwner('<bean:write property="id" name="owner" />')"><bean:message
								key="page.action.delete" /></a></td>
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

		<br>
		<%if (!loginUser.getSysRole().equals(conSysRole_admin)) {%><html:button
			property="close" onclick="OnNewPetOwner()" styleClass="graybox">
			<bean:message key="ownerList.addOwner" />
		</html:button><%}

		%> <input type="hidden" id="pageURL" value="petOwnerListAction.do" />
		<input type="hidden" id="params"
			value='<bean:write name="params" filter="false"/>' /></td>
	</tr>
</table>


<br>
<br>
<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
function OnNewPetOwner() {
	window.location = "initPetOwnerAction.do?method=add";
}

function OnEditPetOwner(id) {
	window.location = "initPetOwnerAction.do?method=edit&petOwnerId="+id;
}

function OnDeletePetOwner(id) {
	if (confirm("<bean:message key="owner.delete.confirm" />")){	
	    window.location = "initPetOwnerAction.do?method=delete&petOwnerId="+id;
	}
}

function OnaddPet(id){
	window.location ="initPetAction.do?method=add&petOwnerId="+id;
}

function OnSeeAllPet(id){
	window.location ="petListAction.do?petOwnerId="+id;
}


function viewnone(e){
	e.style.display=(e.style.display=="none")?"":"none";
}

function query(){
	var name = document.searchOwnerForm.name.value;
	var residentID = document.searchOwnerForm.residentID.value;
	var url;
	if (name==""&&residentID==""){
	    alert("<bean:message key="page.info.search"/>");
		return;
	}else{
	    url = "petOwnerListAction.do?name="+name+"&residentID="+residentID;	
	}
	window.location = url;
}

function queryByPet(){
	var petNo = document.searchPetForm.petNo.value;
	var rfidNo = document.searchPetForm.rfidNo.value;
	var url;
	if (rfidNo==""&&petNo==""){
	    alert("<bean:message key="page.info.search"/>");
		return;
	}else{
	    url = "searchPetAction.do?petNo="+petNo+"&rfidNo="+rfidNo;	
	}
	window.location = url;

}

function partner(){
	var area = document.partnerForm.area.value;
	var variety = document.partnerForm.variety.value;
	if(variety==""){
	    variety="all";
	}
	var sex = document.partnerForm.sex.value
	var url = "partnerShipAction.do?area="+area+"&variety="+variety+"&sex="+sex;	

	window.location = url;

}

function OnClick(){	
    var url  ;
	var backValue ;
	var varietyName
	backValue="";
	  url ="varietyListAction.do?method=partnership" ;	 
	  var openOption = "dialogHeight:800px; dialogWidth:500px;";
	  backValue = window.showModalDialog(url,null,openOption);	
	  if (backValue != null) {
	      document.partnerForm.variety.value=backValue;	 
	  }	
	  }


//-->
</script>
