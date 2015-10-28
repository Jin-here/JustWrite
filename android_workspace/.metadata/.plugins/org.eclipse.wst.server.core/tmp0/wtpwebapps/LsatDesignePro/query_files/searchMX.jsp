<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title>我的账户</title>

<link type="text/css" rel="stylesheet" href="public(1).css">

<link type="text/css" rel="stylesheet" href="style(1).css">

</head>

<body style="background:none">

<form name="form1" action="searchMX.htm" method="post">	



        	<div class="pe_r_img"><img src="pebg2.gif"></div>

            <div class="pe_r_t"><a href="myAccountShow.jsp">用户信息</a>|<a href="searchMX.jsp">明细账查询</a>|<a href="selectord?">订购查询</a></div>

           	<div class="pe_r_title">明细账查询</div>

            <div class="pe_r_text"><s:property value="#session.loginname" />,您的本月消费额为&nbsp;<span>0&nbsp;</span>元<br>请选择账单月份：<br>

            	

    <select name="checkinfo.month" >

          
        <option value="1">1月</option>
           
        <option value="2">2月</option>
          
        <option value="3">3月</option>
               
        <option value="4">4月</option>
           
        <option value="5" >5月</option>
       
        <option value="6" selected="selected">6月</option>

		<option value="7">7月</option>
          
        <option value="8">8月</option>
         
        <option value="9">9月</option>
          
        <option value="10">10月</option>
        
        <option value="11">11月</option>
         
        <option value="12">12月</option>
   
</select>

</div>

            <div class="pe_r_tab">

<div class="pe_r_text">当前这个月您还没有消费 :-)</div><table width="745" border="0">

  <tbody><tr bgcolor="#E9E3D7">

    <td height="28" align="center">订购类型</td>

    <td align="center">服务/产品</td>

    <td align="center">提供商</td>

    <td align="center">订购时间</td>

    <td align="center">费用</td>

  </tr>





	

  

</tbody></table>

            </div>

            

</form>





</body></html></body></html>