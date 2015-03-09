<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<logic:equal name="method" value="add">
	<SCRIPT LANGUAGE="JavaScript">
<!--
if (confirm("<bean:message key="page.addsuccess"/>")) {
	window.location = "initEmployeeAction.do?method=add";
} else {
	window.location = 'employeeListAction.do';
}
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="edit">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.editsuccess"/>");
window.location = 'employeeListAction.do';
//-->
</SCRIPT>
</logic:equal>
<logic:equal name="method" value="delete">
    <logic:equal name="action" value="success">
	<SCRIPT LANGUAGE="JavaScript">
	<!--
	alert("<bean:message key="page.deletesuccess"/>");
	window.location = 'employeeListAction.do';
	//-->
	</SCRIPT>
	</logic:equal>
	<logic:equal name="action" value="failed">
	<SCRIPT LANGUAGE="JavaScript">
	<!--
	alert("<bean:message key="page.employee.deleteFail"/>");
	window.location = 'employeeListAction.do';
	//-->
	</SCRIPT>
	</logic:equal>
</logic:equal>
