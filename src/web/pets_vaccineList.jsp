<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.drategy.pets.domain.Pet,com.drategy.pets.bom.*,java.util.*"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "疫苗";
			String con_childMoudleName = "疫苗";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.vaccineList" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
</head>
<body topmargin="10" leftmargin="20">
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
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="100%" colspan="4" class="tdtitle"><bean:message
							key="vaccineList.title" /></td>
					</tr>
					<tr class="bggray">
						<td width="30%">&nbsp;<bean:message key="vaccine.name" /></td>
						<td width="10%">&nbsp;<bean:message key="vaccine.batch" /></td>
						<td width="35%">&nbsp;<bean:message key="vaccine.time" /></td>
						<td width="25%">&nbsp;<bean:message key="page.action" /></td>
					</tr>
					<logic:iterate name="vaccineList" id="vaccine">
						<tr>
							<td height="23">&nbsp;<bean:write name="vaccine" property="name" /></td>
							<td>&nbsp;<bean:write name="vaccine" property="batchNo" /></td>
							<td>&nbsp;<bean:write name="vaccine" property="injectDate" /></td>
							<td>&nbsp;<a
								href="javascript:OnClick('<bean:write name="vaccine" property="id" />')"><bean:message
								key="news.view.action" /></a>&nbsp;&nbsp;<a
								href="javascript:OnEdit('<bean:write name="vaccine" property="id" />')"><bean:message
								key="page.action.edit" /></a>&nbsp;&nbsp;<a
								href="javascript:OnDelete('<bean:write name="vaccine" property="id" />')"><bean:message
								key="page.action.delete" /></a></td>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="7" class="bggray">
						<table cellpadding="0" cellspacing="0" align="center" border="0"
							width="100%">
							<tr>
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
				<input type="hidden" id="pageURL" value="vaccineListAction.do" /> <input
					type="hidden" id="params"
					value='<bean:write name="params" filter="false"/>' /> <br>
			</tr>
		</table>
		<input type="hidden" id="pageURL" value="treatmentRecordListAction.do" />
		<input type="hidden" id="params"
			value='<bean:write name="params" filter="false"/>' /> <br>
		<html:button property="button" onclick="AddVaccine()"
			styleClass="graybox">
			<bean:message key="button.vaccineList.addPet" />
		</html:button></td>
	</tr>
</table>

<br>
<br>

<%@ include file="footer.jsp"%>

<script language="JavaScript">
<!--
var chipNo = '<bean:write name="chipNo"/>';
function AddVaccine() {
	window.location = "initVaccineAction.do?method=add&chipNo="+chipNo;
}


function OnEdit(id){
	window.location = "initVaccineAction.do?method=edit&vaccineId="+id+"&chipNo="+chipNo;
}

function OnDelete(id){
    if(confirm('<bean:message key="page.confirmDelete" />')){
    window.location = "initVaccineAction.do?method=delete&vaccineId="+id+"&chipNo="+chipNo;
    }
	
}

//当单击是否弹跳出模式窗口
function OnClick(id){	
    var url  ;
	  url ="initVaccineAction.do?method=show&vaccineId="+id+"&chipNo="+chipNo;
	  var openOption = "dialogHeight:600px; dialogWidth:600px;";
	  window.showModalDialog(url,null,openOption);
	  }


//-->
</script>