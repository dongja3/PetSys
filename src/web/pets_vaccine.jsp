<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "า฿ร็";
			String con_childMoudleName = "า฿ร็";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.vaccine" /></title>
<html:base />
<link href="style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="js/date.js"> </SCRIPT>
</head>
<body>

<%@ include file="header.jsp"%>

<table width="96%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="25%" valign="top">
		<div align="center"><br>
		<br>
		<img src="image/vaccine.jpg"></div>
		</td>
		<td width="75%"><br>
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
				<td><html:form action="vaccineAction">

					<html:hidden property="method" />
					<html:hidden property="id" />
					<html:hidden property="petId" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4" class="tdtitle"><bean:message key="vaccine.title" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="vaccine.name" /></td>
							<td><html:text property="name" size="20" maxlength="20" /><font
								color="red">*</font></td>
							<td class="bggray"><bean:message key="vaccine.supplier" /></td>
							<td><html:text property="supplierName" size="30" maxlength="80" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="vaccine.batch" /></td>
							<td><html:text property="batchNo" size="30" maxlength="20" /><font
								color="red">*</font></td>
							<td class="bggray"><bean:message key="vaccine.time" /></td>
							<td><html:text property="injectDate" onclick="viewCalendar(this)"
								size="20" maxlength="20" readonly="true" /><font color="red">*</font></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="vaccine.immunity" /></td>
							<td><html:select property="actionMethod">
								<html:option value="muscle">
									<bean:message key="vaccine.immunity.muscle" />
								</html:option>
								<html:option value="body">
									<bean:message key="vaccine.immunity.body" />
								</html:option>
								<html:option value="nose">
									<bean:message key="vaccine.immunity.nose" />
								</html:option>
							</html:select> <html:text property="actionLocation" size="10"
								maxlength="10" /></td>
							<td class="bggray"><bean:message key="vaccine.hospital" /></td>
							<td><html:text property="hospitalName" size="30" maxlength="50" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="vaccine.remark" /></td>
							<td colspan="3"><html:textarea property="description" cols="80"
								rows="5" onkeydown="javaScript:textareaValidate(this)"
								onkeyup="javaScript:textareaValidate(this)" /></td>
						</tr>
						<tr>
							<td colspan="2"><bean:message key="page.info.required" /></td>
						</tr>
					</table>
					<br>
					<html:submit property="submit" styleClass="graybox">
						<bean:message key="button.confirm" />
					</html:submit>
					<html:button property="button" onclick="javascript:OnBack()"
						styleClass="graybox">
						<bean:message key="button.vaccineList.return" />
					</html:button>

				</html:form>
				<hr hight="1" noshade width="100%">

				<table width="100%" border="1" cellspacing="0" cellpadding="0"
					bordercolor="#333333">
					<tr>
						<td colspan="5" class="tdtitle"><bean:message key="pet.title" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.petName" /></td>
						<td>&nbsp;<bean:write name="pet" property="petName" /></td>
						<td class="bggray"><bean:message key="pet.petNo" /></td>
						<td>&nbsp;<bean:write name="pet" property="petNo" /></td>
						<td rowspan="4" align="center"><image
							src="imageShowServlet?imageId=<bean:write name="petImageId"/>"
							width="70" border="0" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.certificateUnit" /></td>
						<td>&nbsp;<bean:write name="pet" property="certificateUnit" /></td>
						<td class="bggray"><bean:message key="pet.rfidNo" /></td>
						<td>&nbsp;<bean:write name="chipNo" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.variety" /></td>
						<td>&nbsp;<bean:write name="variety" /></td>
						<td class="bggray"><bean:message key="pet.dateIssceed" /></td>
						<td>&nbsp;<bean:write name="pet" property="dateIssceed" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.sex" /></td>
						<td>&nbsp;<logic:equal name="pet" property="sex" value="male">
							<bean:message key="pet.sex.male" />
						</logic:equal> <logic:equal name="pet" property="sex"
							value="female">
							<bean:message key="pet.sex.female" />
						</logic:equal></td>
						<td class="bggray"><bean:message key="pet.age" /></td>
						<td>&nbsp;<bean:write name="pet" property="age" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.character" /></td>
						<td>&nbsp;<bean:write name="pet" property="character" /></td>
						<td class="bggray"><bean:message key="pet.color" /></td>
						<td>&nbsp;<bean:write name="pet" property="hairColor" /></td>
						<td rowspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td rowspan="2" class="bggray"><bean:message key="pet.family" /></td>
						<td><b><bean:message key="pet.father" />:</b> <bean:write
							name="pet" property="father" /></td>
						<td class="bggray"><bean:message key="pet.usage" /></td>
						<td>&nbsp;<bean:write name="pet" property="usage" /></td>
					</tr>
					<tr>
						<td><b><bean:message key="pet.mother" />:</b> <bean:write
							name="pet" property="mother" /></td>
						<td class="bggray"><bean:message key="pet.importAddr" /></td>
						<td>&nbsp;<bean:write name="pet" property="importAddr" /></td>
					</tr>
				</table>
				<br>
				<br>

				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>



<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
var petId = '<bean:write name="vaccineRecordForm" property="petId"/>';
function OnBack() {
	window.location = "vaccineListAction.do?petId="+petId;
}

function textareaValidate(obj){

	if(obj.value.length >100) {
		obj.value = obj.value.substring(0,100);
		alert("<bean:message key="page.info.txtMaxLength.200" />");		
	}	

}
//-->
</script>
