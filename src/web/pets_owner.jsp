<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "宠物及主人";
  String con_childMoudleName = "主人";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.owner" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
<SCRIPT LANGUAGE="JavaScript" src="js/date.js"> </SCRIPT>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>

<br>
<br>

<html:form action="petOwnerAction">

	<table width="96%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="25%" valign="top">
			<div align="center"><img src="image/petOwner.jpg"></div>
			</td>
			<td width="75%"><logic:messagesPresent>
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
					<td><html:hidden property="id" /> <html:hidden property="method" />

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4" class="tdtitle"><bean:message key="owner.title" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="owner.name" /></td>
							<td><html:text property="name" size="30" maxlength="10" /><font
								color="red">*</font></td>
							<td class="bggray"><bean:message key="owner.postcode" /></td>
							<td><html:text property="postCode" size="30" maxlength="6" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="owner.age" /></td>
							<td><html:text property="age" readonly="true"
								onclick="viewCalendar(this)" size="30" maxlength="10" /><font
								color="red">*</font></td>
							<td class="bggray"><bean:message key="owner.sex" /></td>
							<td><html:select property="sex">
								<html:option value="male">
									<bean:message key="owner.sex.male" />
								</html:option>
								<html:option value="female">
									<bean:message key="owner.sex.female" />
								</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="owner.familyphone" /></td>
							<td><html:text property="familyphone" size="30" maxlength="20" /></td>
							<td class="bggray"><bean:message key="owner.officephone" /></td>
							<td><html:text property="officephone" size="30" maxlength="20" /></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="owner.mobile" /></td>
							<td><html:text property="mobile" size="30" maxlength="20" /></td>
							<td class="bggray"><bean:message key="owner.residentID" /></td>
							<td><html:text property="residentID" size="30" maxlength="18" /><font
								color="red">*</font></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="owner.addr" /></td>
							<td><html:text property="addr" size="30" maxlength="100" /></td>
							<td class="bggray"><bean:message key="owner.area" /></td>
							<td>
							<html:select property="areaId">
								<html:options name="petOwnerForm" property="areaIdList"
									labelProperty="areaNameList" />
							</html:select>							
							<font color="red">*</font></td>
						</tr>
						<tr>
							<td class="bggray"><bean:message key="owner.sms" /></td>
							<td colspan="3">
							<html:hidden property="smsService" />
							 &nbsp;<input type="radio" name="smsOpen" value="Y" onclick="viewnone(timeControl)" />&nbsp;<bean:message key="owner.smsService" />&nbsp;<input type="radio"  name="smsOpen" value="N" checked onclick="viewnone1(timeControl)"/>&nbsp;<bean:message key="owner.noSmsService"  /> &nbsp;<div id="timeControl" style="display:none"><bean:message key="owner.sms.startTime" /><html:text
								property="startTime" onclick="viewCalendar(this)" size="20"
								maxlength="20" readonly="true" /> 
							&nbsp;&nbsp;&nbsp;&nbsp; <bean:message key="owner.sms.endTime" /><html:text
								property="endTime" onclick="viewCalendar(this)" size="20"
								maxlength="20" readonly="true" />  </div>
							</td>
						</tr>
						<tr>
							<td colspan="4"><bean:message key="page.info.required" /></td>
						</tr>
					</table>

					</td>
				</tr>
			</table>
			<br>
			<%if (!loginUser.getSysRole().equals(conSysRole_admin)) {%><html:submit
				property="submit" styleClass="graybox">
				<bean:message key="button.confirm" />
			</html:submit><%}

		%> &nbsp;&nbsp;&nbsp;&nbsp; <html:button property="button"
				onclick="OnBack()" styleClass="graybox">
				<bean:message key="button.petOwner.return" />
			</html:button> </html:form></td>
		</tr>
	</table>

	<br>
	<br>
	<%@ include file="footer.jsp"%>
</body>
</html>
<script language="JavaScript">
<!--

  OnOpenSms();
  
  function OnOpenSms(){
     if(document.petOwnerForm.smsService.value==""){
		 document.all("timeControl").style.display="none";
		 document.petOwnerForm.smsOpen[1].checked = true ;
		 document.petOwnerForm.smsService.value=="N";
     
     }else if(document.petOwnerForm.smsService.value=="N"){
		  document.all("timeControl").style.display="none";
		  document.petOwnerForm.smsOpen[1].checked = true ;    
     }else{
		  document.all("timeControl").style.display="block";
		  document.petOwnerForm.smsOpen[0].checked = true ;
     }
  }  
  
  function OnBack(){
  	window.location="petOwnerListAction.do";
  }
  
  function viewnone(e){
	document.petOwnerForm.smsService.value="Y";
	e.style.display="block";
  }
  
  function viewnone1(e){
	 document.petOwnerForm.smsService.value="N";
	e.style.display="none";
  }
//-->
</script>
