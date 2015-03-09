<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<logic:equal name="method" value="add">
	<SCRIPT LANGUAGE="JavaScript">
<!--
if (confirm("<bean:message key="page.addsuccess"/>")) {
	window.location = "initPetAction.do?method=add&petOwnerId=<bean:write name="petOwnerId" />";
} else {
	window.location = 'petListAction.do?petOwnerId=<bean:write name="petOwnerId" />';
}
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="edit">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.editsuccess"/>");
window.location = 'initPetAction.do?method=edit&petId=<bean:write name="petId"/>';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="delete">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="page.deletesuccess"/>");
window.location = 'petListAction.do?petOwnerId=<bean:write name="petOwnerId" />';
//-->
</SCRIPT>
</logic:equal>

<logic:equal name="method" value="noOwner">
	<SCRIPT LANGUAGE="JavaScript">
<!--
alert("<bean:message key="pet.noOwner"/>");
window.location = 'petOwnerListAction.do';
//-->
</SCRIPT>
</logic:equal>
