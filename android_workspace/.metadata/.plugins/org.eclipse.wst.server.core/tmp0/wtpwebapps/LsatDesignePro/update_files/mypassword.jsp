<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		
<title>密码修改</title>

		<link type="text/css" rel="stylesheet" href="public(1).css">
		<link type="text/css" rel="stylesheet" href="style(1).css">

		<script language="javascript" src="jsutil.js"></script>
		<script src="../js/jquery-2.1.3.js"> </script>
		
		<script language="javascript">
		function checkform(){
			if(check()&&document.getElementById("tips1").innerHTML=='<font color="#00ec00">原密码正确</font>'){
				document.getElementById("f").submit();
			}
		}
		
		function check(){
			if(document.getElementById("op").value==""){
				document.getElementById("tips1").innerHTML="<font color='#FF0000'>原密码不能为空</font>";
				return false;
			}if(document.getElementById("np").value==""){
				document.getElementById("tips2").innerHTML="<font color='#FF0000'>新密码不能为空</font>";
				return false;
			}if(document.getElementById("np").value.length<6){
				document.getElementById("tips2").innerHTML="<font color='#FF0000'>新密码不能少于6位</font>";
				return false;
			}if(document.getElementById("np1").value==""){
				document.getElementById("tips3").innerHTML="<font color='#FF0000'>确认新密码不能为空</font>";
				return false;
			}if(document.getElementById("np").value!=document.getElementById("np1").value){
				document.getElementById("tips3").innerHTML="<font color='#FF0000'>两次密码不能为空</font>";
				return false;
			}
				return true;
		}
		
		$(document).ready(function(){	
			$("#op").blur(function(){
				var opass=$("#op").val();
				var userid=$("#hideUid").val();
				 $.ajax({ url: "updateMM", 
					 type:"post",
				      data:{"op":opass,"uid":userid}, 
				      dataType: "text",
				      timeout: 10000, 
				      success: function(data,textStatus){
				    	  if(data=="1"){
				    		  $("#tips1").html('<font color="#00EC00">原密码正确</font>');
				    	  }
				    	  else{
				    		  $("#tips1").html("<font color='#FF0000'>原密码不正确</font>")
				    	  }
			            }  
			 
			 } ); 
			});
		});
</script>

	</head>



	<body style="background:none">
	<input id="hideUid" type="hidden" value="<s:property value='#session.loginname'/>">
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
            <div class="pe_r_t"><a href="selectuser?">信息修改</a>|<a href="mypassword.jsp">密码修改</a>|<a href="prepaypass.htm">支付密码申请</a>|<a href="checkIndividuation.htm">安全设置</a></div>
           	<div class="pe_r_title">密码修改</div>
           	<div class="pe_r_text">注意：登录密码中不能包含空格，并且密码长度不能小于6。</div>
            <div class="pe_r_tab">
<center><s:property value="#session.tips"/></center>
<form name="f" id="f" method="post" action="updatepass">
<table width="745" border="0">
  <tbody><tr bgcolor="#E9E3D7">
  <td width="366" height="37">
    <div align="right" class="perco">原密码：</div></td>
  	<td width="150"><input type="password" id="op" name="oldpass" size="24" maxlength="40">
    <td width="200"><div id="tips1"><font color="#6C6C6C">输入原密码</font></div></td></td>
  </tr>
  <tr>
  <td width="366" height="37">
    <div align="right" class="perco">新密码：</div></td>
  	<td width="150"><input type="password"  id="np" name="newpass" size="24" maxlength="40">
    <td width="200"><div id="tips2"></div></td>
  </td>
  </tr>
  <tr bgcolor="#E9E3D7">
  <td width="366" height="37">
    <div align="right" class="perco">再次输入：</div></td>
  	<td width="150"><input type="password" id="np1" name="newpass1" size="24" maxlength="40">
    <td width="200"><div id="tips3"></div></td>
  </td>
  </tr>
  <tr>
  	<td colspan="3" align="center">
    <div>
  	<img name="Submit" style="cursor: hand" src="modify.gif" value="修改" onClick="checkform()">
	<img name="Submit" style="cursor: hand" src="reset.gif" value="取消" onClick="reset()">
  </div>
  
  </td>	
  </tr>
</tbody></table>
</form>
</div>




</body></html>