<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>我的订购</title>
<link type="text/css" rel="stylesheet" href="public(1).css">
<link type="text/css" rel="stylesheet" href="style(1).css">
<script>

</script></head>


<body style="background:none">
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
            <div class="pe_r_t"><a href="myAccountShow.jsp">用户信息</a>|<a href="searchMX.jsp">明细账查询</a>|<a href="selectord?">订购查询</a></div>
           	<div class="pe_r_title">订购查询</div>

	
	
		
			<div class="pe_r_text"><div class="tips1">已订购的服务</div><div class="tips2">
<font color='#00EC00'><s:property value="#session.selectordtip"/></font>
    <%session.setAttribute("selectordtip",null); %></div></div>
				<table width="745" border="0">
				  <tbody><tr bgcolor="#E9E3D7" class="firtr">
				 	<td width=20%" align="center">订单编号</td>
				    <td width="28%" height="28" align="center">服务名称</td>
				    <td width="14%" align="center">服务提供商</td>
				    <td width="14%" align="center">下单日期</td>
				    <td width="10%" align="center">状态</td>
				    <td width="10%" align="center">操作</td>
				  </tr>
					
				  <!--              本省服务       -->
				  
	<s:iterator var="ord" value="ordlist"><!--   此功能修改       -->
    <s:iterator var="con" value="contract">
    <tr class="sectr">
     <td height="28" align="center"><s:property value="orderid" /></td>  
     <td align="center"><s:property value="conname" /></td>
     <td align="center"><s:property value="operater" /></td> 
     <td align="center"><s:property value="orderdate" /></td>
     <td align="center"><s:property value="status" /></td>
     <td align="center" class="endtd"><input type="button" value="<s:property value="dosome"/>" onclick="window.location.href='deleteord?oid=<s:property value="orderid"/>'"/></td>
     </tr>
     </s:iterator>
    </s:iterator>
   
		</tbody></table>

</body></html>