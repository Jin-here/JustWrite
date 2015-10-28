<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>查询订单信息</title>
<link type="text/css" rel="stylesheet" href="public(1).css">
<link type="text/css" rel="stylesheet" href="style(1).css">
<script>

</script></head>


<body style="background:none">
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
            <div class="pe_r_t"><a href="selalluser?">查询用户信息</a>|<a href="selallord?">查询订单信息</a>|<a href="selallcon?">查询套餐信息</a>|<a href="selallucon?">查询用户套餐</a></div>
           	<div class="pe_r_title">查询订单信息</div>

	
	
		
			<div class="pe_r_text"><div class="tips1">已注册的用户</div><div class="tips2">
<font color='#00EC00'><s:property value="#session.searchordtip"/></font>
    <%session.setAttribute("searchordtip",null); %></div></div>
            
				<table width="745" border="0">
				  <tbody><tr bgcolor="#E9E3D7" class="firtr">
				    <td width="" height="28" align="center">订单编号</td>
				    <td width="" align="center">用户名</td>
				    <td width="" align="center">服务名称</td>
				    <td width="" align="center">服务供应商</td>
				    <td width="" align="center">下单日期</td>
				    <td width="" align="center">状态</td>
				  </tr>
			<s:iterator var="ord" value="allordlist">
			<s:iterator var="con" value="contract">
				<tr class="sectr">
					<td align="center" height="20"><s:property value="orderid"/></td>
				    <td align="center"><s:property value="userid"/></td>
				    <td align="center"><s:property value="conname"/></td>
				    <td align="center"><s:property value="operater"/></td>
				    <td align="center"><s:property value="orderdate"/></td>	
				    <td align="center" class="endtd"><s:property value="status" /></td>
				</tr>
			</s:iterator>
			</s:iterator>

  
		</tbody></table>

</body></html>