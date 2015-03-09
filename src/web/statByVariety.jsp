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
    Map  rowMap = (Map) request.getAttribute("rowMap");
			Map titleMap = (Map) request.getAttribute("titleMap");
			Map firstMap = (Map) titleMap
					.get(VaccineStatSqlGenerator.FIRST_MAP);
			String[] titleArray = new String[firstMap.size()];
			titleArray = Tools.mapKeyToArrayByAsc(firstMap);		
            
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
				<td class="tdtitle"><bean:message key="stat.variety.title" /></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="1" cellspacing="0" cellpadding="0"
					bordercolordark="#ffffff" bordercolorlight="#000000">
					<%out.print("<tr><td >&nbsp;</td>");
			for (int i = 0; i < titleArray.length; i++) {
				if(i%2==0){
					out.println("<td  >" + titleArray[i] + "</td>");
				}else{
					out.println("<td  align='center'>" + titleArray[i] + "</td>");	
				}
				
			}

			out.print("</tr>");
	

			Set keySet = resultMap.keySet();
			Iterator keyItem = keySet.iterator();
            
            int iii = 0 ;
            
			while (keyItem.hasNext()) {
			    if(iii%2==0){
			     	out.print("<tr class='bggray'><td>");
			     }else{
			     	out.print("<tr><td>");
			     }
				iii++;
				String keyValue = (String) keyItem.next();
				String varietyId = (String)rowMap.get(keyValue);
			
				out.print(keyValue + "</td>");
				Map dataMap = (Map) resultMap.get(keyValue);
				for (int ii = 0; ii < titleArray.length; ii++) {
				    String areaCode ="" ;
				    areaCode =(String)firstMap.get(titleArray[ii]);
				    String intData1 = (String)dataMap.get(titleArray[ii]);				  
				    	 
			    	out.print("<td>");
					
				   if(intData1.equals("0")){
						out.print(intData1+"</td>");
				    }else{
				    	out.print("<a href='statResultShow.do?method=statByVariety&areaCode="+areaCode+"&varietyId="+varietyId+"'>");
						out.print(dataMap.get(titleArray[ii])+ "</a></td>");
				    }	

				}
				out.print("</tr>");
			}
			%>
				</table>				
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

