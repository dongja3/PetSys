<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="base.jsp"%>
<%String con_moudleName = "管理";
			String con_childMoudleName = "区域";
%>
<%@ include file="baseAuthorization.jsp"%>
<html>
<head>
<title><bean:message key="page.title.admin.area" /></title>
<html:base />
</head>
<body>

<%@ include file="header.jsp"%>


<html:form action="areaAction.do">

	<table width="96%" align="center" border="0">
		<tr>
			<td width="30%"><%@ include file="admin_left.jsp"%></td>
			<td valign="top"><br>
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
					<td><html:hidden property="method" /> <html:hidden property="id" />

					<table border="0" cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td colspan="4" class="tdtitle"><bean:message key="area.title" /></td>
						</tr>
						<TR>
							<TD><bean:message key="area.name" /></TD>
							<TD><html:text property="name" size="20"  onclick="OnClick()" /> <font color="red">*</font>
							</TD>


							<TD><bean:message key="area.father" /></TD>
							<TD><html:text property="fatherAreaName" size="20"
								readonly="true" /><bean:message key="page.info.noedit" /> <html:hidden
								property="fatherId" /></TD>
						</tr>
						<tr>
							<TD><bean:message key="area.areaCode" /></TD>
							<TD><html:text property="areaCode" size="20" onclick="OnClick()" /><font color="red">*</font></TD>
							<TD colspan="2"><p   onclick="OnClick()"><bean:message key="page.areacode" /></p></TD>
						</TR>
						<tr>
							<TD><bean:message key="area.note" /></TD>
							<TD colspan="3"><html:textarea property="note" cols="40" rows="5" onkeydown="javaScript:textareaValidate(this)" onkeyup="javaScript:textareaValidate(this)" ></html:textarea></TD>
						</TR>
						<tr>
							<td colspan="4"><bean:message key="page.info.required" /></td>
					</TABLE>

					</td>
				</tr>
			</table>
			<html:submit property="submit" styleClass="graybox">
				<bean:message key="button.confirm" />
			</html:submit> &nbsp;&nbsp;&nbsp;&nbsp; <html:button
				property="button" onclick="OnBack()" styleClass="graybox">
				<bean:message key="button.areaList.return" />
			</html:button></td>
		</tr>
	</table>
</html:form>

<%@ include file="footer.jsp"%>
</body>
</html>

<script language="JavaScript">
<!--
  function OnBack(){
  	window.location="areaListAction.do";
  }

  //当单击是否弹跳出模式窗口
function OnClick(){	
    var url  ;
	var backValue ;
    var areaName;
	var areaCode ;
	backValue="";
	  url ="zjac.jsp" ;	 
	  var openOption = "dialogHeight:800px; dialogWidth:500px;";
	  backValue = window.showModalDialog(url,null,openOption);	
	  if (backValue != null) {
          areaName = backValue.substring(0,backValue.indexOf("$")); 
		  areaCode = backValue.substring(backValue.indexOf("$")+1,backValue.length);
		  document.areaForm.name.value=areaName;
		  document.areaForm.areaCode.value=areaCode;		 
	  }	
}

function textareaValidate(obj){

	if(obj.value.length >200) {
		obj.value = obj.value.substring(0,200);
		alert("<bean:message key="page.info.txtMaxLength.200" />");		
	}	

}
//-->
</script>
