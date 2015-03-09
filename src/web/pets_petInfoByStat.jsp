<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<HTML>
<HEAD>
<title><bean:message key="page.title.pet" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/date.js"> </SCRIPT>
<link href="style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>

<br>

		<table width="90%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000" align="center">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="5" class="tdtitle"><bean:message key="pet.title" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.petName" /></td>
						<td><bean:write name="pet" property="petName" /></td>
						<td class="bggray"><bean:message key="pet.petNo" /></td>
						<td><bean:write name="pet" property="petNo" /></td>
						<td rowspan="5" align="center"><image
							src="imageShowServlet?imageId=<bean:write name="petImageId"/>"
							width="70" border="0" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.certificateUnit" /></td>
						<td><bean:write name="pet" property="certificateUnit" /></td>
						<td class="bggray"><bean:message key="pet.rfidNo" /></td>
						<td>&nbsp;<bean:write name="chipNo" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.variety" /></td>
						<td><bean:write name="varietyName" /></td>
						<td class="bggray"><bean:message key="pet.dateIssceed" /></td>
						<td><bean:write name="pet" property="dateIssceed" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.sex" /></td>
						<td><logic:equal name="pet" property="sex" value="male">
							<bean:message key="pet.sex.male" />
						</logic:equal> <logic:equal name="pet" property="sex"
							value="female">
							<bean:message key="pet.sex.female" />
						</logic:equal></td>
						<td class="bggray"><bean:message key="pet.age" /></td>
						<td><bean:write name="pet" property="age" /></td>
					</tr>
					<tr>
						<td class="bggray"><bean:message key="pet.character" /></td>
						<td><bean:write name="pet" property="character" /></td>
						<td class="bggray"><bean:message key="pet.color" /></td>
						<td><bean:write name="pet" property="hairColor" /></td>
					</tr>
					<tr>
						<td rowspan="2" class="bggray"><bean:message key="pet.family" /></td>
						<td><bean:message key="pet.father" /> <bean:write name="pet"
							property="father" /></td>
						<td class="bggray"><bean:message key="pet.usage" /></td>
						<td><bean:write name="pet" property="usage" /></td>
					</tr>
					<tr>
						<td><bean:message key="pet.mother" /> <bean:write name="pet"
							property="mother" /></td>
						<td class="bggray"><bean:message key="pet.importAddr" /></td>
						<td colspan="2"><bean:write name="pet" property="importAddr" /></td>
					</tr>
					<tr>
						<td colspan="5"></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>


<br>
<div align="center"><input type="button"
	onClick="window.opener = 'xxx';window.close();" class="graybox"
	value="<bean:message key="button.close" />"></div>
</BODY>
</HTML>

