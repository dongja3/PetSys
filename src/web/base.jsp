<%@ page
	import="com.drategy.pets.domain.*,java.util.*,com.drategy.pets.springservice.*,com.drategy.pets.context.*,com.drategy.pets.util.*,com.drategy.pets.bom.*,com.drategy.pets.form.*,com.drategy.pets.biz.*"%>
<%Object userObj = session.getAttribute(Configuration.getBaseConfig()
					.findConfigs("baseConfig").find("sessionUserKey"));

			if (userObj == null) {
				out.print("<script language=\"javascript\">");
				out.print("window.location='initLogin.do?method=login';");
				out.print("</script>");
				return;
			}

			User loginUser = (User) userObj;

			StructService structService = (StructService) Global.getInstance()
					.getService("structService");

			String actAdd = Authorization.ACT_PRRMIT_NO;
			String actEdit = Authorization.ACT_PRRMIT_NO;
			String actDelete = Authorization.ACT_PRRMIT_NO;

			String currUserSysRole = loginUser.getSysRole();

			String conSysRole_admin = "admin";
			String conSysRole_sysInputor = "sysInputor";
			String conSysRole_sysDoctor = "sysDoctor";
			String conSysRole_sysQuery = "sysQuery";
			String conSysRole_sysVaccine = "sysVaccine";			
			

		%>
