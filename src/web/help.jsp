<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title>Help</title>
<link rel="stylesheet" href="style.css" type="text/css" />
<html:base />
</head>
<body>
<p>&nbsp;</p>
<table width="70%" border="1" align="center"  bordercolorlight="#FFFFFF" lign="center" ellpadding="0"
	cellspacing="0  bordercolordark=" #000000>
	<tr><td>
<table width="100%" border="1" align="center"  cellpadding="2" cellspacing="2" bordercolordark="#000000" bordercolorlight="#000000">
	<tr>
		<td colspan="2><div align="center" class="tdtitle">
		  <div align="center">宠物自动识别管理系统帮助
	      </div>		  </div></td>
	</tr>
	<tr>
		<td colspan="2">宠物自动识别管理系统是基于对宠物自动识别的网络管理信息化综合平台，
		能够为宠物管理部门提供科学、有效的管理模式。 宠物在注册的同时由相关人
		员为其注射一个带有唯一ID的芯片，该芯片将作为宠物的电子身份证伴随宠物一生， 以后宠物的防疫、看病以及丢失查找都能够通过宠物管理系统实现。</td>
	</tr>
	<tr>
	  <td colspan="2">&nbsp;</td>
	  </tr>
	<tr>
		<td rowspan="4"  class="tdtitle">
		<div align="center">宠物登记</div>
		</td>
		<td width=76% class="bggray"><b>添加宠物主人</b></td>
	</tr>
	<tr>
	<td>单击首页上的“宠物及主人”， <br>
	  填入宠物主人的相关的真实资料， <br>
	  点击“确认”，注册成功。 <br>
	  同时，你也可以对宠物主人进行编辑、删除、添加宠物等一系列的操作</td>
	</tr>
	<tr>
	<td class="graybox"><b>添加宠物</b></td>
	</tr>
	<tr>
	<td>在宠物主人页面点击“添加宠物”， <br>
	  填入宠物名字、编号等相关信息， <br>
	  点击确认，添加成功。</td>
	</tr>
	<tr>
	  <td colspan="2" 4>&nbsp;</td>
	  </tr>
	<tr>
		<td class="tdtitle">
		<div align="center">疫苗的录入</div>
		</td>
		<td>在首页点击疫苗，输入已录入的芯片号码，点击“搜索”，就可以对芯片对应的宠物录入疫苗状况。</td>
	</tr>
	<tr>
	  <td colspan="2">&nbsp;</td>
	  </tr>
	<tr>
		<td class="tdtitle">
		<div align="center">病例的录入</div>
		</td>
		<td>在首页点击病例，输入已录入的芯片号码，点击“确认”，就可以对芯片对应的宠物录入病情案例。</td>
	</tr>
	<tr>
	  <td colspan="2">&nbsp;</td>
	  </tr>
	<tr>
		<td rowspan="4" class="tdtitle">
		<div align="center">查询</div>
		</td>
		<td class="bggray"><b>查询报表</b></td>
	</tr>
	<tr>
		<td>你可以查询宠物的数量在各个管理辖区的分布情况，还可以根据疫苗批号或者品种对宠物进行统计，</td>
	</tr>
	<tr>
		<td class="bggray"><b>搜索</b></td>
	</tr>
	<tr>
		<td>输入芯片号码，点击搜索，就可以查询到芯片对应的宠物及其主人的相关信息</td>
	</tr>
	<tr>
	  <td colspan="2">&nbsp;</td>
	  </tr>
	<tr>
		<td rowspan="8" class="tdtitle">
		<div align="center">管理</div>
		</td>
		<td class="bggray"><b>区域管理</b></td>
	</tr>
	<tr>
		<td>你可以对管理区域进行编辑、添加、删除的操作</td>
	</tr>
	<tr>
		<td class="bggray"><b>员工管理</b></td>
	</tr>
	<tr>
		<td>你可以对区域管理员进行添加、删除、编辑的操作，同时，你也可以对区域管理员进行权限的配置</td>
	</tr>
	<tr>
		<td class="bggray"><b>新闻管理</b></td>
	</tr>
	<tr>
		<td>你可以在操添加、编辑、删除系统首页的新闻，也可以在页面底部点击“添加新闻”处添加新闻</td>
	</tr>
	<tr>
		<td class="bggray"><b>短信管理</b></td>
	</tr>
	<tr>
		<td>你可以为注册短信服务的宠物主人发送相关的短信息</td>
	</tr>
</table>
<br>
<div align="center"><input type="button"
	onClick="window.opener = 'xxx';window.close();" class="graybox"
	value="<bean:message key="button.close" />"></div>
	</td></tr>
	</table>
</body>
</html>
