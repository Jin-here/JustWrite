<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		

		<title>注销用户</title>

		<link type="text/css" rel="stylesheet" href="public(3).css">

		<link type="text/css" rel="stylesheet" href="style(1).css">

	<script language="javascript" src="jsutil.js"></script>
	<script language="javascript">
    
	function submitform1(){
		if(document.getElementById("uname").value==""&&document.getElementById("idcard").value=="")
			document.getElementById("tip").innerHTML="<font color='#FF0000'>姓名证件不能都为空！</font>";
		else if(document.getElementById("uname").value!=""&&document.getElementById("idcard").value!="")
			document.getElementById("tip").innerHTML="<font color='#FF0000'>姓名证件不能都填！</font>";
			else if(document.getElementById("uname").value!=""&&document.getElementById("idcard").value=="")
			{var id=document.getElementById("uname").value;
		window.location.href = "shanchuuser1?userid="+id;}
			else if(document.getElementById("uname").value==""&&document.getElementById("idcard").value!="")
			{var id=document.getElementById("idcard").value;
			window.location.href = "shanchuuser1?userid="+id;	}
	}
	function submitform(){
		document.getElementById("f").submit();
	}
	function checkform(){
		if(document.getElementById("uname").value==""&&document.getElementById("idcard").value=="")
			document.getElementById("tip").innerHTML="<font color='#FF0000'>姓名证件不能都为空！</font>";
		else if(document.getElementById("uname").value!=""&&document.getElementById("idcard").value!="")
			document.getElementById("tip").innerHTML="<font color='#FF0000'>姓名证件不能都填！</font>";
			else if(document.getElementById("uname").value!=""&&document.getElementById("idcard").value=="")
			{var id=document.getElementById("uname").value;
		window.location.href = "chaxunuser?userid="+id;}
			else if(document.getElementById("uname").value==""&&document.getElementById("idcard").value!="")
			{var id=document.getElementById("idcard").value;
			window.location.href = "chaxunuser?userid="+id;	}
	}
	



</script></head>

	<body style="background:none">

<form  id="f"  method="post" action="shanchuuser" >
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
        	<div class="pe_r_t"><a href="shanchuUser.jsp">注销用户</a>|<a href="selallcondelete?">删除套餐</a></div>
<div class="pe_r_title">注销用户</div>
            <div class="pe_r_text">
    
   	<div class="select">输入要查询的账号<input type="text" class="uid" style="width:110px;" id="uname">或证件号码<input type="text" id="idcard" class="uname" style="width:150px;"></div>
    <div class="chaxun"><img src="chaxun.gif" name="Submit" width="47" style="cursor: hand; height: 30px;" onClick="checkform()" value="查询"></div>
    <div class="chaxun"><img name="Submit" style="cursor: hand" src="shanchu.gif" value="修改" onClick="submitform1()"></div>
    
    <div class="chaxuntips" id="tip">
    
    <font color='#FF0000'><s:property value="#session.chaxuntip"/></font>
    <%session.setAttribute("chaxuntip",null); %>
    </div>
</div>
            
            
    
            
            
            <div class="pe_r_tab">
            <s:if test="%{userinfor!=null}"> 
<table width="745" border="0">
  <tbody><tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">用户名：</div>
    </td>
    <td width="369">
    	
    	<input type="text" style= "background-color:transparent;border:0;" value="<s:property value='userinfor.userid'/>" name="userinfor.userid" size="24" maxlength="40">  
    </td>
  </tr>
  <tr>
  	<td width="366" height="37"><div align="right" class="perco">用户全称：</div></td>
  	<td width="369"><input type="text" style= "background-color:transparent;border:0;" value="<s:property value='userinfor.username'/>" name="userinfor.username" size="24" maxlength="40"> 
    </td>
  </tr>
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">身份证号码：</div>
    </td>
    <td width="369">
    <input type="text" style= "background-color:transparent;border:0;" value="<s:property value='userinfor.useridcard'/>" name="userinfor.useridcard" size="24" maxlength="40"> 
    	
    </td>
  </tr>

  
  <tr>
  	<td colspan="2" align="center">
    <div>
    <img name="Submit" style="cursor: hand" src="shanchu.gif" value="修改" onClick="submitform()"></div>
    </td>
  </tr>
</tbody></table>
</s:if>
<center> <font color='#00EC00'><s:property value="#session.shanchuutip"/></font>
    <%session.setAttribute("shanchuutip",null); %></center>
</div>


</form>
</body></html>