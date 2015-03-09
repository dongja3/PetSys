<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<logic:equal name="method" value="add">
	<SCRIPT LANGUAGE="JavaScript">
<!--
if (confirm("<bean:message key="page.addsuccess"/>")) {
	window.location = "initVaccineAction.do?method=add&chipNo=<bean:write name="chipNo" />";
} else {
	window.location = 'vaccineListAction.do?petId=<bean:write name="petId" />';
}
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="edit">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.editsuccess"/>");
window.location = 'initVaccineAction.do?method=edit&vaccineId=<bean:write name="vaccineId"/>&chipNo=<bean:write name="chipNo" />';
//-->
</SCRIPT>
</logic:equal>
<logic:equal name="method" value="delete">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.deletesuccess"/>");
window.location = 'vaccineListAction.do?petId=<bean:write name="petId" />&chipNo=<bean:write name="chipNo" />';
//-->
</SCRIPT>
</logic:equal>
