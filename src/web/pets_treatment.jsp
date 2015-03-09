<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "²¡Àý";
			String con_childMoudleName = "²¡Àý";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.treatment" /></title>
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
		<img src="image/casses.jpg"></div>
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
				<td><html:form action="treatmentRecordAction">

					<html:hidden property="method" />
					<html:hidden property="id" />
					<html:hidden property="petId" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4" class="tdtitle"><bean:message
								key="treatment.title" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="treatment.date" /></td>
							<td><html:text property="date" onclick="viewCalendar(this)"
								readonly="true" /><font color="red">*</font></td>
							<td class="bggray"><bean:message key="treatment.visitDoctorTime" /></td>
							<td><html:select property="visitDoctorTime">
								<html:option value="1">
									<bean:message key="treatment.visitDoctorTime.first" />
								</html:option>
								<html:option value="0">
									<bean:message key="treatment.visitDoctorTime.notFirst" />
								</html:option>
							</html:select> <html:text property="diseaseState" size="30" maxlength="30" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="treatment.description" /></td>
							<td><html:textarea property="description" cols="30" rows="5" onkeydown="javaScript:textareaValidate(this)" onkeyup="javaScript:textareaValidate(this)"></html:textarea></td>
							<td class="bggray"><bean:message key="treatment.prescrition" /></td>
							<td><html:textarea property="prescrition" cols="30" rows="5" onkeydown="javaScript:textareaValidate(this)" onkeyup="javaScript:textareaValidate(this)"></html:textarea></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="treatment.diseaseHistory" /></td>
							<td colspan="3"><html:textarea property="diseaseHistory" cols="80" rows="5" onkeydown="javaScript:textareaValidate(this)" onkeyup="javaScript:textareaValidate(this)"></html:textarea></td>
						</tr>
						<tr>
							<td colspan="2"><bean:message key="page.info.required" /></td>
						</tr>
					</table>
					<br>
					<html:submit property="submit" styleClass="graybox">
						<bean:message key="button.confirm" />
					</html:submit>
					<html:button property="cancel" onclick="javascript:OnBack()"
						styleClass="graybox">
						<bean:message key="button.treamentList.return" />
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
						<td><bean:write name="pet" property="petName" /></td>
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
						<td><b><bean:message key="pet.father" />:</b> <bean:write name="pet"
							property="father" /></td>
						<td class="bggray"><bean:message key="pet.usage" /></td>
						<td>&nbsp;<bean:write name="pet" property="usage" /></td>
					</tr>
					<tr>
						<td><b><bean:message key="pet.mother" />:</b> <bean:write name="pet"
							property="mother" /></td>
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
<br>
<br>

<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--
var petId = '<bean:write name="treatmentRecordForm" property="petId"/>';

function OnBack() {
	window.location = "treatmentRecordListAction.do?petId="+petId;
}

function textareaValidate(obj){

	if(obj.value.length >200) {
		obj.value = obj.value.substring(0,200);
		alert("<bean:message key="page.info.txtMaxLength.200" />");		
	}	

}

//-->
</script>
