<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title><bean:message key="changepassword.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<form name="passwordForm" action="post">
<table width="97%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td><bean:message key="logon.password1" /></td>
		<td><INPUT TYPE="password" name="password1" /></td>
	</tr>
	<tr>
		<td><bean:message key="logon.password2" /></td>
		<td><INPUT TYPE="password" name="password2" /></td>
	</tr>
	<tr>
		<td colspan="2"><INPUT TYPE="button"
			value="<bean:message key="button.changePassword" />" class="graybox"
			onclick="changePassword()"></td>
	</tr>
</table>
</form>

</body>
</html>
<script language="JavaScript">
<!--
function changePassword(){
    var password1 = document.passwordForm.password1.value;
	var password2 = document.passwordForm.password2.value;
	
	if(password1==""||password2==""){
	    alert("<bean:message key="page.password.notEmpty"/>");
        return;
	}
	
	if(password1!=password2){
	    alert("<bean:message key="page.password.notMatch"/>");
        return;
	}
	
	window.location = "initUserAction.do?method=updatePassword&password=" + password1;
}

//-->
</script>
