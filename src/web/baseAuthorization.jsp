<%if (!loginUser.getId().equals("admin")) {
				String con_userId = loginUser.getId();
				Authorization currAuthorization = structService
						.findAuthorizationBymoudleName(con_moudleName,
								con_childMoudleName, con_userId);
				if (currAuthorization == null) {
					out.print("<script language=\"javascript\">");
					out.print("window.location='failer.jsp';");
					out.print("</script>");
				} else {
					actAdd = currAuthorization.getActAdd();
					actEdit = currAuthorization.getActUpdate();
					actDelete = currAuthorization.getActDelete();
				}
			} else {
				actAdd = "yes";
				actEdit = "yes";
				actDelete = "yes";
			}
		%>
