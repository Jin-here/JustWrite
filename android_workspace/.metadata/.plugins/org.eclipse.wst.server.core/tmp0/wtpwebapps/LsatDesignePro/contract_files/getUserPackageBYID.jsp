<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=GBK">

<title>星空套餐</title>
<link type="text/css" rel="stylesheet" href="public(1).css">
<link type="text/css" rel="stylesheet" href="style(1).css">
</head>
<body style="background:none">

<div class="pe_r_img"><img src="pebg2.gif"></div>
           	<div class="pe_r_title">星空套餐</div>
            <div class="pe_r_text"><div class="tips1">已订购的套餐</div><div class="tips2">
<font color='#00EC00'><s:property value="#session.selectcontip"/></font>
    <%session.setAttribute("selectcontip",null); %></div></div>
<table width="745" border="0">


 
<tbody>
	<tr bgcolor="#E9E3D7" class="firtr">
		<td height="28" align="center" >服务名称</td>
		<td align="center">服务提供商</td>
		<td align="center">开始日期</td>
		<td align="center">结束日期</td>
		<td align="center">状态</td>
		<td align="center">操作</td></tr>
			<s:iterator var="uc" value="conlist">
    <tr class="sectr">
     <td height="28" align="center"><s:property value="conname" /></td>
     <td align="center"><s:property value="operater" /></td>
     <td align="center"><s:property value="conStartdate" /></td>
     <td align="center"><s:property value="conenddate" /></td>
     <td align="center"><s:property value="status" /></td>
     <td align="center" class="endtd"><input type="button" value="<s:property value="dosome"/>" onclick="if(confirm('你确定要执行此操作吗 ?'))alert('您的申请已被受理，请耐心等待。')"/></td>
     </tr>
    </s:iterator>

</tbody></table>


</body></html>