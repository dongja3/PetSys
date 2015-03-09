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
</SCRIPT>
</head>
<body>

<br>
<table width="90%" border="1" cellspacing="0" cellpadding="0"
	bordercolordark="#ffffff" bordercolorlight="#000000" align="center">
	<tr>
		<td>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td colspan="4" class="tdtitle"><bean:message key="vaccine.title" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="vaccine.name" /></td>
				<td>&nbsp;<bean:write name="vaccine" property="name" /></td>
				<td class="bggray"><bean:message key="vaccine.supplier" /></td>
				<td>&nbsp;<bean:write name="vaccine" property="supplierName" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="vaccine.batch" /></td>
				<td>&nbsp;<bean:write name="vaccine" property="batchNo" /></td>
				<td class="bggray"><bean:message key="vaccine.time" /></td>
				<td>&nbsp;<bean:write name="vaccine" property="injectDate" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="vaccine.immunity" /></td>
				<td><logic:equal name="vaccine" property="actionMethod"
					value="muscle">
					<bean:message key="vaccine.immunity.muscle" />
				</logic:equal> <logic:equal name="vaccine" property="actionMethod"
					value="nose">
					<bean:message key="vaccine.immunity.nose" />
				</logic:equal> <logic:equal name="vaccine" property="actionMethod"
					value="body">
					<bean:message key="vaccine.immunity.body" />
				</logic:equal>: &nbsp;&nbsp; <bean:write name="vaccine"
					property="actionLocation" /></td>
				<td class="bggray"><bean:message key="vaccine.hospital" /></td>
				<td>&nbsp;<bean:write name="vaccine" property="hospitalName" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="vaccine.remark" /></td>
				<td colspan="3">&nbsp;<bean:write name="vaccine"
					property="description" /></td>
			</tr>

		</table>
		<br>
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
				</logic:equal> <logic:equal name="pet" property="sex" value="female">
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

<br>
<br>
<div align="center"><input type="button"
	onClick="window.opener = 'xxx';window.close();" class="graybox"
	value="<bean:message key="button.close" />"></div>
</body>
</html>
