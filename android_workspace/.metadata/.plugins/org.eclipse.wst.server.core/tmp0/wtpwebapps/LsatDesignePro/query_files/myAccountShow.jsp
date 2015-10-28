<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>我的信息</title>

<link type="text/css" rel="stylesheet" href="public(1).css">

<link type="text/css" rel="stylesheet" href="style(1).css">

</head>

<body style="background:none">



        	<div class="pe_r_img"><img src="pebg2.gif"></div>

            <div class="pe_r_t"><a href="myAccountShow.jsp">用户信息</a>|<a href="searchMX.jsp">明细账查询</a>|<a href="selectord?">订购查询</a></div>

           	<div class="pe_r_title">用户信息</div>

            <div class="pe_r_text">您的本月消费额为&nbsp;<span>0</span>&nbsp;元，目前账户余额是&nbsp;<span>0</span>&nbsp;元。 </div>

            <div class="pe_r_text">用户名：<s:property value="#session.loginname"/> </div>

            <div class="pe_r_text">归属地：南京江浦 </div>

            <div class="pe_r_text">用户状态：正常 </div>

            <div class="pe_r_text">本月已消费金额（元）：0 </div>

          <!-- <div class="pe_r_text">账户余额有效期：2013-08-30 </div>  -->

            <div class="pe_r_title">礼金券信息</div>           

<div class="pe_r_text">你没有礼金券</div><div class="pe_r_title">绑定账户信息</div><table width="745" border="0">

  <tbody><tr bgcolor="#E9E3D7">

    <td height="28" align="center">账户类型</td>

    <td align="center">账户限额（元）</td>

    <td align="center">账户状态</td>

  </tr>

  <tr>

    <td height="28" align="center">
宽带账户

	</td>

    <td align="center">360</td>

    <td align="center">

正常
	</td>

  </tr>

  <tr>

    <td height="28" align="center">

手机账户
	</td>

    <td align="center">60</td>

    <td align="center">

正常
	</td>

  </tr>

</tbody></table></body></html></tbody></table></body></html>