<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<logic:equal name="method" value="add">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.area.addsuccess"/>");
	window.location = 'areaListAction.do';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="edit">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.editsuccess"/>");
window.location = 'areaListAction.do';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="result" value="delete">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.deletesuccess"/>");
window.location = 'areaListAction.do';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="result" value="childNotEmpty">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.area.childNotEmpty"/>");
window.location = 'areaListAction.do';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="result" value="areaAssigned">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.area.areaAssigned"/>");
window.location = 'areaListAction.do';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="result" value="noFather">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.area.noFather"/>");
window.location = 'areaListAction.do';
//-->
</SCRIPT>
</logic:equal>

