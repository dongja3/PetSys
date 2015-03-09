<%@ page contentType="text/html; charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<logic:equal name="method" value="delete">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.deletesuccess"/>");
window.location = 'smsListAction.do';
//-->
</SCRIPT>
</logic:equal>


