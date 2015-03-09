<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.drategy.pets.domain.Pet,com.drategy.pets.bom.*,java.util.*"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "病例";
			String con_childMoudleName = "病例";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.treatmentList" /></title>
<SCRIPT LANGUAGE="JavaScript" src="js/list.js"> </SCRIPT>
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
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="100%" colspan="4" class="tdtitle"><bean:message
							key="treamentList.title" /></td>
					</tr>
					<tr class="bggray">
						<td>&nbsp;<bean:message key="treatment.date" /></td>
						<td>&nbsp;<bean:message key="treatment.diseaseState" /></td>
						<td>&nbsp;<bean:message key="page.action" /></td>
					</tr>
					<logic:iterate name="treatmentRecordList" id="treatmentRecord">
						<tr>
							<td height="23">&nbsp;<bean:write name="treatmentRecord"
								property="date" /></td>
							<td>&nbsp;<bean:write name="treatmentRecord"
								property="diseaseState" /></td>
							<td>&nbsp;<a
								href="javascript:OnClick('<bean:write name="treatmentRecord" property="id" />')"><bean:message
								key="news.view.action" /></a>&nbsp;&nbsp;<a
								href="javascript:OnEdit('<bean:write name="treatmentRecord" property="id" />')"><bean:message
								key="page.action.edit" /></a>&nbsp;&nbsp;<a
								href="javascript:OnDelete('<bean:write name="treatmentRecord" property="id" />')"><bean:message
								key="page.action.delete" /></a></td>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="7">
						<table cellpadding="0" cellspacing="0" align="center" border="0"
							width="100%">
							<tr class="bggray">
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
		<input type="hidden" id="pageURL" value="treatmentRecordListAction.do" />
		<input type="hidden" id="params"
			value='<bean:write name="params" filter="false"/>' /> <br>
		<html:button property="button" onclick="AddTreatmentRecord()"
			styleClass="graybox">
			<bean:message key="button.treamentList.addPet" />
		</html:button></td>
	</tr>
</table>

<br>
<br>

<%@ include file="footer.jsp"%>


</body>
</html>

<br>
<br>
<script language="JavaScript">
<!--

var chipNo = '<bean:write name="chipNo"/>';

function AddTreatmentRecord() {
	window.location = "initTreatmentRecordAction.do?method=add&chipNo="+chipNo;
}

function OnEdit(id){
	window.location = "initTreatmentRecordAction.do?method=edit&treatmentRecordId="+id+"&chipNo="+chipNo;
}

function OnDelete(id){
    if(confirm('<bean:message key="page.confirmDelete" />')){
    window.location = "initTreatmentRecordAction.do?method=delete&treatmentRecordId="+id+"&chipNo="+chipNo;
    }
	
}

//当单击是否弹跳出模式窗口
function OnClick(id){	
    var url  ;
	  url ="initTreatmentRecordAction.do?method=show&treatmentRecordId="+id+"&chipNo="+chipNo;
	  var openOption = "dialogHeight:600px; dialogWidth:600px;";
	  window.showModalDialog(url,null,openOption);
	  }

//-->
</script>
