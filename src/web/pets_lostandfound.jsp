<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title><bean:message key="page.title.lostandfound" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
<html:base />
</head>
<body>
<br>
<br>
<table width="60%" border="1" cellspacing="0" cellpadding="0"
	bordercolordark="#ffffff" bordercolorlight="#000000" align="center">
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="4" class="tdtitle"><bean:message key="owner.title" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="owner.name" /></td>
				<td><bean:write name="petOwner" property="name" /></td>
				<td class="bggray"><bean:message key="owner.postcode" /></td>
				<td><bean:write name="petOwner" property="postCode" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="owner.age" /></td>
				<td><bean:write name="petOwner" property="age" /></td>
				<td class="bggray"><bean:message key="owner.sex" /></td>
				<td ><logic:equal name="petOwner" property="sex"
					value="male">
					<bean:message key="owner.sex.male" />
				</logic:equal> <logic:equal name="petOwner" property="sex"
					value="female">
					<bean:message key="owner.sex.female" />
				</logic:equal></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="owner.familyphone" /></td>
				<td><bean:write name="phone" property="homeTelephone" /></td>
				<td class="bggray"><bean:message key="owner.officephone" /></td>
				<td><bean:write name="phone" property="companyTelephone" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="owner.mobile" /></td>
				<td><bean:write name="phone" property="mobileTelephone" /></td>
				<td class="bggray"><bean:message key="owner.residentID" /></td>
				<td><bean:write name="petOwner" property="residentID" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="owner.addr" /></td>
				<td><bean:write name="address" property="detailAddress" /></td>
				<td class="bggray"><bean:message key="owner.area" /></td>
				<td><bean:write name="areaName" /></td>
			</tr>
		</table>
		<br>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="5" class="tdtitle"><bean:message key="pet.title" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="pet.petName" /></td>
				<td><bean:write name="pet" property="petName" /></td>
				<td class="bggray"><bean:message key="pet.petNo" /></td>
				<td><bean:write name="pet" property="petNo" /></td>
				<td rowspan="5"><image
					src="imageShowServlet?imageId=<bean:write name="petImageId"/>"
					width="70" border="0" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="pet.certificateUnit" /></td>
				<td><bean:write name="pet" property="certificateUnit" /></td>
				<td class="bggray"><bean:message key="pet.dateIssceed" /></td>
				<td><bean:write name="pet" property="dateIssceed" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="pet.variety" /></td>
				<td><bean:write name="varietyName" /></td>
				<td class="bggray"><bean:message key="pet.age" /></td>
				<td><bean:write name="pet" property="age" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="pet.sex" /></td>
				<td><logic:equal name="pet" property="sex" value="male">
					<bean:message key="pet.sex.male" />
				</logic:equal> <logic:equal name="pet" property="sex" value="female">
					<bean:message key="pet.sex.female" />
				</logic:equal></td>
				<td class="bggray"><bean:message key="pet.color" /></td>
				<td><bean:write name="pet" property="hairColor" /></td>
			</tr>
			<tr>
				<td class="bggray"><bean:message key="pet.character" /></td>
				<td><bean:write name="pet" property="character" /></td>
				<td class="bggray"><bean:message key="pet.usage" /></td>
				<td><bean:write name="pet" property="usage" /></td>
			</tr>
			<tr>
				<td rowspan="2" class="bggray"><bean:message key="pet.family" /></td>
				<td><bean:message key="pet.father" /> <bean:write name="pet"
					property="father" /></td>
				<td class="bggray"><bean:message key="pet.importAddr" /></td>
				<td colspan="2"><bean:write name="pet" property="importAddr" /></td>
			</tr>
			<tr>
				<td><bean:message key="pet.mother" /> <bean:write name="pet"
					property="mother" /></td>
				<td class="bggray"><bean:message key="pet.certificate" /></td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td colspan="5"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>
<br>
<div align="center"><html:button property="button" onclick="OnBack()"
	styleClass="graybox">
	<bean:message key="button.return" />
</html:button>&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
	onClick="window.opener = 'xxx';window.close();" class="graybox"
	value="<bean:message key="button.close" />"></div>

</body>
</html>

<script language="JavaScript">
<!--
  function OnBack(){
  	window.location="newsListAction.do?method=show";
  }
//-->
</script>
