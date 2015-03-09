<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<logic:equal name="method" value="add">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.news.addsuccess"/>");
	window.location = 'newsListAction.do';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="edit">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.editsuccess"/>");
window.location = 'newsListAction.do';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="result" value="delete">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.deletesuccess"/>");
window.location = 'newsListAction.do';
//-->
</SCRIPT>
</logic:equal>

