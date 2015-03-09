<%@ page language="java" contentType="text/html;charset=gbK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title><bean:message key="variety.list.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<br>
<%int i = 0;%>
<form name="form1">
<table width="90%" border="0" align="center">
	<tr>
		<logic:iterate name="varietyList" id="variety">
			<%i++;
			if (i % 4 == 1) {%>
			<tr>
				<%}

			%>

				<bean:define id="varietyName">
					<bean:write property="name" name="variety" />
				</bean:define>
				<td><input type="radio" name="variety" value="<%=varietyName %>"
					onclick="OnSubmit(this)"> <%=varietyName%></td>
				<%if (i % 4 == 0) {%>
			</tr>
			<%}

		%>
		</logic:iterate>
	</tr>
</table>
<hr height="1" noshade />
<div align="center"><font color="red"><bean:message key="variety.others" /></font>
<input type="text" name="otherVariety"> <input type="button"
	name="submit" value="<bean:message key="button.submit" />"
	onclick="Submit()"></div>
</body>
</form>

</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--

function OnSubmit(obj){
	var i=0
	var backValue ;
	backValue=obj.value;	
	window.returnValue = backValue;
	window.close();
}	
	function Submit(){
	var backValue ;
	var variety = document.form1.otherVariety.value;
	if(variety==""){
	    alert('<bean:message key="vairety.info"/>');
	}else{
	window.returnValue =variety;
	window.close();
	}
}	

//-->
</SCRIPT>
