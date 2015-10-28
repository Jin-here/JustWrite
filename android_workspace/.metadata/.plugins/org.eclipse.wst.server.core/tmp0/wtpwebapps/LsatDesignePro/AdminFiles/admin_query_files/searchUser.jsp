<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>查询用户信息</title>

<link type="text/css" rel="stylesheet" href="public(1).css">

<link type="text/css" rel="stylesheet" href="style(1).css">

</head>

<body style="background:none">



        	<div class="pe_r_img"><img src="pebg2.gif"></div>

            <div class="pe_r_t"><a href="selalluser?">查询用户信息</a>|<a href="selallord?">查询订单信息</a>|<a href="selallcon?">查询套餐信息</a>|<a href="selallucon?">查询用户套餐</a></div>
            <div class="pe_r_title">查询用户信息</div>
            <div class="pe_r_text"><div class="tips1">已注册的用户</div><div class="tips2">
<font color='#00EC00'><s:property value="#session.searchusertip"/></font>
    <%session.setAttribute("searchusertip",null); %></div></div>


            <!-- <div class="pe_r_text">账户余额有效期：2013-08-30 </div>  -->
            <table width="745" border="0">

  <tbody><tr bgcolor="#E9E3D7" class="firtr">

    <td height="28" align="center">用户名</td>
    <td align="center">用户姓名</td>
    <td align="center">账号密码</td>
    <td align="center">性别</td>
	<td align="center">证件号码</td>
    <td align="center">联系电话</td>
    <td align="center">电子邮件</td>
    <td align="center" >状态</td>

  </tr>
<s:iterator var="user" value="alluserlist">
<tr class="sectr">
	<td align="center" height="20"><s:property value="userid"/></td>
    <td align="center"><s:property value="username"/></td>
    <td align="center"><s:property value="password"/></td>
    <td align="center"><s:property value="sex"/></td>
	<td align="center"><s:property value="useridcard"/></td>
    <td align="center"><s:property value="usertel"/></td>
    <td align="center"><s:property value="useremail"/></td>
    <td align="center" class="endtd"><s:property value="status"/></td>
</tr>
</s:iterator>
 

</tbody></table></body></html>