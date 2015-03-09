<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
<html:base />
<link href="style.css" rel="stylesheet" type="text/css">
<title><bean:message key="page.title.pet" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/date.js"> </SCRIPT>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
</head>
<body>
<%@ include file="header.jsp"%>
<br>
<br>
<html:form action="petAction" enctype="multipart/form-data"
	method="POST">
	<table width="96%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="25%" valign="top">
			<div align="center"><br>
			<br>
			<img src="image/pet.jpg"></div>
			</td>
			<td width="75%"><logic:messagesPresent>
				<bean:message key="errors.header" />
				<ul>
					<html:messages id="error">
						<li><bean:write name="error" /></li>
					</html:messages>
				</ul>
			</logic:messagesPresent> <html:hidden property="id" /> <html:hidden
				property="method" /> <html:hidden property="petOwnerId" /> <html:hidden
				property="petImageId" /> <html:hidden property="petCertificateId" />

			<table width="100%" border="1" cellspacing="1" cellpadding="1"
				bordercolordark="#ffffff" bordercolorlight="#000000">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="5" class="tdtitle"><bean:message key="pet.editPet" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="pet.petName" /></td>
							<td><html:text property="petName" size="30" maxlength="20" /><font
								color="red">*</font></td>
							<td class="bggray"><bean:message key="pet.petNo" /></td>
							<td><html:text property="petNo" size="30" maxlength="20" /><font
								color="red">*</font></td>
							<td rowspan="5"><image
								src="imageShowServlet?imageId=<bean:write name="petForm" property="petImageId"/>"
								width="70" border="0" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="pet.certificateUnit" /></td>
							<td><html:text property="certificateUnit" size="30"
								maxlength="50" /></td>
							<td class="bggray"><bean:message key="pet.dateIssceed" /></td>
							<td><html:text property="dateIssceed" size="30" maxlength="30"
								onclick="viewCalendar(this)" readonly="true" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="pet.variety" /></td>
							<td><html:text property="varietyName" size="30" maxlength="30"
								onclick="OnClick()" readonly="true"/><font color="red">*</font></td>
							<td class="bggray"><bean:message key="pet.age" /></td>
							<td><html:text property="age" size="30" maxlength="4" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="pet.sex" /></td>
							<td><html:select property="sex">
								<html:option value="male">
									<bean:message key="pet.sex.male" />
								</html:option>
								<html:option value="female">
									<bean:message key="pet.sex.female" />
								</html:option>
							</html:select></td>
							<td class="bggray"><bean:message key="pet.color" /></td>
							<td><html:text property="hairColor" size="30" maxlength="10" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="pet.character" /></td>
							<td><html:text property="character" size="30" maxlength="20" /></td>
							<td class="bggray"><bean:message key="pet.usage" /></td>
							<td><html:text property="usage" size="30" maxlength="30" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="pet.rfidNo" /></td>
							<logic:equal name="petForm" property="method" value="edit">
								<td><html:text property="rfidNo" size="30" maxlength="64"
									readonly="true" /> <bean:message key="page.info.noedit" /></td>
							</logic:equal>
							<logic:equal name="petForm" property="method" value="add">
								<td><html:text property="rfidNo" size="30" maxlength="64" /><font
									color="red">*</font></td>
							</logic:equal>
							<td class="bggray"><bean:message key="pet.image" /></td>
							<td colspan="2"><html:file property="petImageUpFile"
								styleClass="graybox" accept="image/jpeg" /></td>
						</tr>
						<tr>
							<td rowspan="2" class="bggray"><bean:message key="pet.family" /></td>
							<td><bean:message key="pet.father" /> <html:text
								property="father" size="30" maxlength="10" /></td>
							<td class="bggray"><bean:message key="pet.importAddr" /></td>
							<td colspan="2"><html:text property="importAddr" size="40"
								maxlength="80" /></td>
						</tr>
						<tr>
							<td><bean:message key="pet.mother" /> <html:text
								property="mother" size="30" maxlength="10" /></td>
							<td class="bggray"><bean:message key="pet.certificate" /></td>
							<td colspan="2"><html:file property="petCertificate"
								styleClass="graybox" disabled="true"/></td>
						</tr>
						<tr>
							<td colspan="5"><bean:message key="page.info.required" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<%if (!loginUser.getSysRole().equals(conSysRole_admin)) {%> <html:submit
				property="submit" styleClass="graybox">
				<bean:message key="button.confirm" />
			</html:submit> &nbsp;&nbsp;&nbsp;&nbsp; <%}%><html:button
				property="return" onclick="OnBack()" styleClass="graybox">
				<bean:message key="button.pet.return" />
			</html:button></td>
		</tr>
	</table>

</html:form>
<br>
<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
  function OnBack(){
  	window.location="petListAction.do?petOwnerId=<bean:write name="petForm" property="petOwnerId" />";
  }
  
  //当单击是否弹跳出模式窗口

	function OnClick(){	
	    var url  ;
		var backValue ;
	   // var areaName;
		//var areaCode ;
		var varietyName
		backValue="";
		  url ="varietyListAction.do?method=list" ;	 
		  var openOption = "dialogHeight:800px; dialogWidth:500px;";
		  backValue = window.showModalDialog(url,null,openOption);	
		  if (backValue != null) {
		      document.petForm.varietyName.value=backValue;
	         // areaName = backValue.substring(0,backValue.indexOf("$")); 
			  //areaCode = backValue.substring(backValue.indexOf("$")+1,backValue.length);
			  //document.areaForm.name.value=areaName;
			 // document.areaForm.areaCode.value=areaCode;		 
		  }	
	  }
  
    //屏蔽回车键
    document.onkeydown = function() {
	if(event.keyCode==13) {
		event.keyCode=0;
		event.returnValue=false;
	}
    }
//-->
</script>
