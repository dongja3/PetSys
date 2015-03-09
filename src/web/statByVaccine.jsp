<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.drategy.pets.domain.*,java.util.*,com.drategy.pets.springservice.*,com.drategy.pets.context.*,com.drategy.pets.util.*,com.drategy.pets.bom.*,com.drategy.pets.form.*,com.drategy.pets.biz.*"%>

<%@ page import="org.jfree.chart.ChartFactory"%>
<%@ page import="org.jfree.chart.JFreeChart"%>
<%@ page import="org.jfree.chart.plot.PlotOrientation"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.data.category.*"%>

<%@ include file="base.jsp"%>
<%String con_moudleName = "查询报表";
			String con_childMoudleName = "查询报表";

			%>

<%Map resultMap = (Map) request.getAttribute("resultMap");
			Map titleMap = (Map) request.getAttribute("titleMap");
			Map firstMap = (Map) titleMap
					.get(VaccineStatSqlGenerator.FIRST_MAP);

			String[] titleArray = new String[firstMap.size()];
			titleArray = Tools.mapKeyToArrayByAsc(firstMap);
			String batchNo = (String) request.getAttribute("batchNo");

			%>


<html>
<head>
<title><bean:message key="header.menu.query" /></title>
</head>
<body onkeydown=return(!(event.keyCode==13))>

<%@ include file="header.jsp"%>
<table width="96%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="25%" valign="top">
		<div align="center"><br>
		<br>
		<img src="image/stat.jpg"></div>
		</td>
		<td width="75%" valign="top"><br>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="tdtitle" style="CURSOR: hand" onClick="viewnone(test)"><bean:message
							key="search.batch.title" /></td>
					</tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					id="test" style="display:none ">
					<form name="statVaForm" id="statVaForm" action="post">
					<tr class="bggray">
					    <td><bean:message key="stat.vaccine.title" /></td>
						<td><bean:message key="stat.vaccine" /></td>
						<td><INPUT TYPE="text" name="batchNo" size="20" /></td>
						<td><INPUT TYPE="button"
							value="<bean:message key="button.stat" />" class="graybox"
							onclick="query()"></td>
					</tr>
					<tr class="bggray">
					    <td><bean:message key="stat.variety"  /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><INPUT TYPE="button"
							value="<bean:message key="button.stat" />" class="graybox" onclick="queryByVariety()"></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<br>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolordark="#ffffff" bordercolorlight="#000000">
			<tr>
				<td class="tdtitle"><bean:message key="stat.vaccine.title" /></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="1" cellspacing="0" cellpadding="0"
					bordercolordark="#ffffff" bordercolorlight="#000000">
					<%out.print("<tr><td rowspan=2>&nbsp;<font color=red>第"+batchNo+"批次</font></td>");
			for (int i = 0; i < titleArray.length; i++) {
				if(i%2==0){
					out.println("<td colspan=2 class='bggray' align='center'>" + titleArray[i] + "</td>");
				}else{
					out.println("<td colspan=2 align='center'>" + titleArray[i] + "</td>");	
				}
				
			}

			out.print("</tr>");

			out.print("<tr>");
			for (int i = 0; i < titleArray.length; i++) {
				if(i%2==0){
				    out.print("<td class='bggray'>" + VaccineStatSqlGenerator.INJECT + "</td>");
				    out.print("<td class='bggray'>" + VaccineStatSqlGenerator.UN_INJECT + "</td>");
				}else{
					out.print("<td>" + VaccineStatSqlGenerator.INJECT + "</td>");
					out.print("<td>" + VaccineStatSqlGenerator.UN_INJECT + "</td>");
				}
			}

			out.print("</tr>");

			Set keySet = resultMap.keySet();
			Iterator keyItem = keySet.iterator();

			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			while (keyItem.hasNext()) {
				out.print("<tr><td>");
				String keyValue = (String) keyItem.next();
				out.print(keyValue + "</td>");
				Map dataMap = (Map) resultMap.get(keyValue);
				for (int ii = 0; ii < titleArray.length; ii++) {
				    String areaCode ="" ;
				    areaCode =(String)firstMap.get(titleArray[ii]);
				    String intData1 = (String)dataMap.get(titleArray[ii]+VaccineStatSqlGenerator.INJECT);
				    String intData2 = (String)dataMap.get(titleArray[ii]+VaccineStatSqlGenerator.UN_INJECT);
				    
				     if(ii%2==0){
					    	out.print("<td class='bggray'>");
					    }else{
					    	out.print("<td>");
					    }
				   if(intData1.equals("0")){
						out.print(dataMap.get(titleArray[ii]
								+ VaccineStatSqlGenerator.INJECT)
								+ "</td>");
				    }else{
				    	out.print("<a href='statResultShow.do?method=statByVaccine&areaCode="+areaCode+"&batchNo="+batchNo+"&inject="+VaccineStatSqlGenerator.INJECT+"'>");
						out.print(dataMap.get(titleArray[ii]
								+ VaccineStatSqlGenerator.INJECT)
								+ "</a></td>");
				    }	
				    
				   if(ii%2==0){
				    	out.print("<td class='bggray'>");
				    }else{
				    	out.print("<td>");
				    }
				    if(intData2.equals("0")){
						out.print(dataMap.get(titleArray[ii]
								+ VaccineStatSqlGenerator.UN_INJECT)
								+ "</td>");
                    }else{
                    	out.print("<a href='statResultShow.do?method=statByVaccine&areaCode="+areaCode+"&batchNo="+batchNo+"&inject="+VaccineStatSqlGenerator.UN_INJECT+"'>");
						out.print(dataMap.get(titleArray[ii]
								+ VaccineStatSqlGenerator.UN_INJECT)
								+ "</a></td>");
                    }
                    
					dataset.addValue(Integer.parseInt(dataMap.get(
							titleArray[ii] + VaccineStatSqlGenerator.INJECT)
							.toString()), "已注射", titleArray[ii]);

					dataset.addValue(Integer.parseInt(dataMap.get(
							titleArray[ii] + VaccineStatSqlGenerator.UN_INJECT)
							.toString()), "未注射", titleArray[ii]);

				}
				out.print("</tr>");
			}

			String title = "宠物按照疫苗批次统计图";
			JFreeChart chart = ChartFactory.createBarChart3D(title, "区域", "数量",
					dataset, PlotOrientation.VERTICAL, true, false, false);

			String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300,
					null, session);
			String graphURL = request.getContextPath()
					+ "/servletDisplayChart?filename=" + filename;

			%>
				</table>
				<br>
				<div ALIGN="CENTER"><img src="<%= graphURL %>" width=500 height=300
					border=0 usemap="#<%= filename %>"></div>
				<br>
				</td>
			</tr>
		</table>
		<br>
		<div align="center"><html:button property="button" onclick="OnBack()"
			styleClass="graybox">
			<bean:message key="button.statArea.return" />
		</html:button></div>
		</td>
	</tr>
</table>
<br>
</body>
</html>

<script language="JavaScript">
<!--
function viewnone(e){
e.style.display=(e.style.display=="none")?"":"none";
}

function query(){
	var batchNo =trim(document.statVaForm.batchNo.value);
	var url;
	if (batchNo==""){
	     document.statVaForm.batchNo.value="";
	     alert('<bean:message key="page.stat.warnInfo" />');
		 return;
	}else{
	url = "statAction.do?method=statByVaccine&batchNo="+batchNo;
	   
	}
	window.location = url;
}

function queryByVariety(){
	url = "statAction.do?method=statByVariety";
	window.location = url;

}

function trim(s)
{
    if (s == null)
        return s;

    var i;
    var beginIndex = 0;
    var endIndex = s.length - 1;

    for (i=0; i<s.length; i++)
    {
        if (s.charAt(i) == ' ' || s.charAt(i) == '　')
        {
            beginIndex++;
        }
        else
        {
            break;
        }
    }

    for (i = s.length - 1; i >= 0; i--)
    {
        if (s.charAt(i) == ' ' || s.charAt(i) == '　')
        {
            endIndex--;
        }
        else
        {
            break;
        }
    }

    if (endIndex < beginIndex)
    {
        return "";
    }

    return s.substring(beginIndex, endIndex + 1);
}

function OnBack(){
  	window.location="statAction.do?method=statByArea";
  }

//-->
</script>

