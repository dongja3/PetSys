<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<style type="text/css">
<!--
.style2 {
	font-size: 36px;
	font-family: Times New Roman, Times, serif;
	color: #000066;
}

body {
	background-color: #FFFFFF;
}
-->
</style>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><span class="style2">Dratey Pets System </span></td>
	</tr>
	<tr>
		<td>
		<%if(currUserSysRole.equals(conSysRole_admin)){%>
		<a href="initPetOwnerAction.do?method=add">Pets&Owner</a>|<a
			href="pets_search_vaccine.jsp">Vaccine</a>|<a
			href="pets_search_treament.jsp">Case</a>|Query|<a
			href="admin_index.jsp">Admin</a>				
		<%}%>
		
		<%if(currUserSysRole.equals(conSysRole_sysInputor)){%>
		<a href="initPetOwnerAction.do?method=add">Pets&Owner</a>|<a
			href="pets_search_vaccine.jsp">Vaccine</a>|Query|		
		<%}%>
		
		<%if(currUserSysRole.equals(conSysRole_sysDoctor)){%>
		 <a	href="pets_search_vaccine.jsp">Vaccine</a>|<a
			href="pets_search_treament.jsp">Case</a>|Query
		<%}%>
		
		<%if(currUserSysRole.equals(conSysRole_sysQuery)){%>
		Query			
		<%}%>
		
		<%if(currUserSysRole.equals(conSysRole_sysLeader)){%>
		Query			
		<%}%>
		</td>
	</tr>
</table>
<hr align="center" size="2" noshade>
</body>
</html>
