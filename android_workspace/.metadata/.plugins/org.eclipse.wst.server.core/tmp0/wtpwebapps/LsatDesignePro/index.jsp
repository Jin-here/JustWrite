<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function check1(){
	if(document.getElementById("userid").value==""){
		alert("账号不能为空！");
	}
	else
		return 1;
}
function check2(){
	if(document.getElementById("username").value==""){
		alert("用户真名不能为空！");
	}else
		return 1;
}
function check3(){
	if(document.getElementById("password").value==""){
		alert("密码不能为空！");
	}else
		return 1;
}
function check4(){
	if(document.getElementById("password").value!=document.getElementById("password1").value){
		alert("两次密码不一样！");
	}else
		return 1;
}
function check5(){
	if(document.getElementById("useridcard").value==""){
		alert("身份证号码不能为空！");
	}else
		return 1;
}
function check(){
	if(check1()==1&&check2()==1&&check3()==1&&check4()==1&&check5()==1){
		document.adduser.submit();
	}
}

</script>
</head>



<body>
<form name="adduser" action="addinuser" method="post" id="form1">

<table>
<tr><td>注册账号</td><td><input name="ui.userid" type="text" id="userid"></td></tr>
<tr><td>用户真名</td><td><input name="ui.username" type="text" id="username"></td></tr>
<tr><td>账户密码</td><td><input name="ui.password" type="password" id="password"></td></tr>
<tr><td>确认密码</td><td><input name="password1" type="password" id="password1"></td></tr>
<tr><td>身份证号码</td><td><input name="ui.useridcard" type="text" id="useridcard"></td></tr>
<tr><td>手机号码</td><td><input name="ui.usertel" type="text" id="usertel"></td></tr>
<tr><td colspan="2" align="center"><input type="button" value="添加" onClick="check()"></td></tr>
</table>
</form>
<s:property value="#request.tips"/>

</body>
</html>