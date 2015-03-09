<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title><bean:message key="admin.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
</SCRIPT>
<html:base />
</head>
<body>
<br>
<table width="100%">
	<logic:iterate id="news" name="newsList">
		<tr>
			<td><a href="javascript:OnClick('<bean:write property="id" name="news" />')">
			<bean:write property="title" name="news" /></a></td>
			<td width="80"><bean:write property="author" name="news" /></b></td>
			<td width="80">[<bean:write property="date" name="news" />]</b></td>
		</tr>
	</logic:iterate>
</table>
</body>
</html>
<script language="JavaScript">

 //当单击是否弹跳出模式窗口
function OnClick(id){	
    var url  ;
	  url ="newsInfoAction.do?id="+id; 
	  var openOption = "dialogHeight:600px; dialogWidth:600px;";
	  window.showModalDialog(url,null,openOption);
	  }
 
</script>