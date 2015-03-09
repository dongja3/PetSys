<%@ page contentType="text/html; charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<logic:equal name="method" value="add">
	<SCRIPT LANGUAGE="JavaScript">
<!--
if (confirm("<bean:message key="page.addsuccess"/>")) {
	window.location = 'initUserAction.do?method=add&employeeId=<bean:write name="employeeId"/>';
} else {
	window.location = 'userListAction.do?employeeId=<bean:write name="employeeId"/>';
}
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="edit">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.editsuccess"/>");
window.location = 'userListAction.do?employeeId=<bean:write name="employeeId"/>';
//-->
</SCRIPT>
</logic:equal>
<logic:equal name="method" value="delete">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.deletesuccess"/>");
window.location = 'userListAction.do?employeeId=<bean:write name="employeeId"/>';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="updatePassword">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.changePasswordSuccess"/>");
window.close();
//-->
</SCRIPT>
</logic:equal>
