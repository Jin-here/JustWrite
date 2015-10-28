<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		
<title>删除套餐</title>

		<link type="text/css" rel="stylesheet" href="public(1).css">
		<link type="text/css" rel="stylesheet" href="style(1).css">

		<script language="javascript" src="jsutil.js"></script>
		<script src="../js/jquery-2.1.3.js"> </script>
		
		<script language="javascript">
		function checkform(){
			var id=document.getElementById("selcon").value;
			window.location.href = "chaxuncon?cid="+id;
		}
		function shanchu1(){
			var id=document.getElementById("selcon").value;
			window.location.href = "deletecon1?cid="+id;
		}
		function shanchu(){
			document.getElementById("f").submit();
		}
		
</script>

	</head>



	<body style="background:none">
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
            <div class="pe_r_t"><a href="shanchuUser.jsp">注销用户</a>|<a href="selallcondelete?">删除套餐</a></div>
<div class="pe_r_title">删除套餐</div>
   	<div class="pe_r_text">
    
   	<div class="select">选择要查询的套餐
	<select name="select" id="selcon" style= "width:230px;">
   	
   	<s:iterator var="con" value="allconlist">
   	<option value="<s:property value='cid'/>"><s:property value='conname'/></option>
   	</s:iterator>
   	</select>


	</div>
    <div class="chaxun"><img src="chaxun.gif" name="Submit" width="47" style="cursor: hand; height: 30px;" onClick="checkform()" value="查询"></div>
    <div class="chaxun"><img src="shanchu.gif" name="Submit" width="47" style="cursor: hand; height: 30px;" onClick="shanchu()" value="删除"></div>
    <div class="chaxuntips">
    <font color='#FF0000'><s:property value="#session.chaxuncontip"/></font>
    <%session.setAttribute("chaxuncontip",null); %>
    
    </div>
    </div>
    <div class="pe_r_tab">
<s:if test="%{con!=null}">
<form name="f" id="f" method="post" action="deletecon">
<table width="745" border="0">
  <tbody><tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">套餐名称：</div></td>
  	<td width="350"><input value="<s:property value='con.conname'/>" style= "background-color:transparent;border:0;width:200px;" type="text" id="op" name="con.conname" size="24" maxlength="40">
  </tr>
  <tr>
  <td width="353" height="37">
    <div align="right" class="perco">期限：</div></td>
  	<td width="350"><input type="text" value="<s:property value='con.contime'/>" style= "background-color:transparent;border:0;width:80px;" id="np" name="con.contime" size="24" maxlength="40">
    
  </tr>
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">带宽：</div></td>
  	<td width="350"><input type="text" value="<s:property value='con.bandwidth'/>" id="np1" style= "background-color:transparent;border:0;width:80px;"name="con.bandwidth" size="24" maxlength="40">
    
  </tr>
  <tr>
  <td width="353" height="37">
    <div align="right" class="perco">价格：</div></td>
  	<td width="350"><input type="text" value="<s:property value='con.conprice'/>元"  id="np" style= "background-color:transparent;border:0;width:80px;"name="conprice" size="24" maxlength="40">
    
  </tr>
    <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">服务供应商：</div></td>
  	<td width="350"><input type="text" value="<s:property value='con.operater'/>" id="np1" style= "background-color:transparent;border:0;width:200px;" name="con.operater" size="24" maxlength="40">
    
  </tr>
  <tr>
  	<td colspan="2" align="center">
    <div>
  	<img name="Submit" style="cursor: hand" src="shanchu.gif" value="修改" onClick="shanchu()"></div>
  
  </td>	
  </tr>

</tbody></table>
</form>
</s:if>
<center><font color='#00EC00'><s:property value="#session.shanchucontip"/></font></center>
    <%session.setAttribute("shanchucontip",null); %>


</div>




</body></html>