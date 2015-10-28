<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function doBindemail(id){
		window.location.href = "manage?uid="+id;//加以修改,方法名+参数
	}
function clean(){
	document.getElementById("email").value="";
}
</script>
</head>
<body>
<s:form name="userinforform"  theme="simple">
绑定邮箱
<input type="text" value="输入邮箱地址" id="email" onFocus="clean()"/>
<input type="button" value="绑定" onClick="doBindemail(<s:property value="uid" />)" />
</s:form>
</body>
</html>