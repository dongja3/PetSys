<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title><bean:message key="mainFrame.title" /></title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="68%"><img src="image/logo.gif" alt="Logo" width="348"
			height="52" /></td>
		<td width="32%" valign="top">
		<table width="200" border="0" align="right" cellpadding="0"
			cellspacing="0" class="toplink">
			<tr>
				<td height="38" class="toptd"><img src="image/headdot.gif" /><br />
				<a href="pets_search.jsp" target="_blank"><bean:message
					key="header.search" /></a></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headline.gif"
					width="3" height="41" /></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headdot.gif" /><br />
				<a href="help.jsp" target="_blank"><bean:message key="header.help" /></a></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headline.gif"
					width="3" height="41" /></td>
				<td class="toptd" nowrap="nowrap"><img src="image/headdot.gif" /><br />
				<a href="initLogin.do?method=loginOff"><bean:message
					key="header.logout" /></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="wrapperborder">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="100" class="bannerbg">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="10%""><img src="image/banner-bg-left.gif" alt="no" /></td>
						<td width="47%" valign="bottom"><img src="image/front-pet.gif"
							alt="Two Cats" /></td>
						<td width="38%"><font class="bannerfonttitle"><bean:message
							key="header.autoid" /></font><br />
						<br />
						<br />
						</td>
						<td width="5%" align="right"><img src="image/banner-bg-right.gif"
							alt="no" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="menubar">
	<tr>
		<td width="1%" height="30" class="menutd"><img
			src="image/menu_head.jpg" width="11" height="30" /></td>
		<td width="97%" class="menutd" id="whitelink">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="menufont">
			<tr>
				<td width="9%" nowrap="nowrap" class="menuitem  sel"><a
					href="newsListAction.do?method=show"><font color="#000000"><bean:message
					key="header.menu.homepage" /><font></a></td>

				<%if (currUserSysRole.equals(conSysRole_admin)) {

			%>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="petOwnerListAction.do"><bean:message
					key="header.menu.petowner" /></a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="pets_search_vaccine.jsp"><bean:message
					key="header.menu.vaccine" /></a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="pets_search_treament.jsp"><bean:message
					key="header.menu.treatment" /></a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="statAction.do?method=statByArea"> <bean:message
					key="header.menu.query" /></a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="admin_index.jsp"><bean:message key="header.menu.admin" /></a></td>
				<td nowrap="nowrap" width="100%"><img src="image/menu_split.gif"
					alt="fda" width="3" height="30" /></td>

				<%}%>

				<%if (currUserSysRole.equals(conSysRole_sysInputor)) {

			%>

				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="petOwnerListAction.do"><bean:message
					key="header.menu.petowner" /> </a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="pets_search_vaccine.jsp"><bean:message
					key="header.menu.vaccine" /></a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="statAction.do?method=statByArea"><bean:message
					key="header.menu.query" /></a></td>
				<td nowrap="nowrap" width="100%"><img src="image/menu_split.gif"
					alt="fda" width="3" height="30" /></td>

				<%}%>

				<%if (currUserSysRole.equals(conSysRole_sysDoctor)) {

			%>

				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="pets_search_treament.jsp"><bean:message
					key="header.menu.treatment" /></a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="statAction.do?method=statByArea"><bean:message
					key="header.menu.query" /></a></td>
				<td width="100%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<%}%>
				<%if (currUserSysRole.equals(conSysRole_sysQuery)) {

			%>

				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="statAction.do?method=statByArea"><bean:message
					key="header.menu.query" /></a></td>
				<td width="100%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<%}%>

				<%if (currUserSysRole.equals(conSysRole_sysVaccine)) {
			%>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="pets_search_vaccine.jsp"><bean:message
					key="header.menu.vaccine" /></a></td>
				<td width="0%" nowrap="nowrap"><img src="image/menu_split.gif"
					width="3" height="30" /></td>
				<td width="4%" nowrap="nowrap" class="menuitem"><a
					href="statAction.do?method=statByArea"><bean:message
					key="header.menu.query" /></a></td>
				<td nowrap="nowrap" width="100%"><img src="image/menu_split.gif"
					alt="fda" width="3" height="30" /></td>
				<%}%>
			</tr>
		</table>
		</td>
		<td width="2%" align="right" class="menutd"><img
			src="image/menu_foot.jpg" width="10" height="30" /></td>
	</tr>
</table>
</body>
</html>
