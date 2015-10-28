<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link type="text/css" rel="stylesheet" href="./query_files/public.css">
<link type="text/css" rel="stylesheet" href="./query_files/style.css">	
		<title>我的星空－江苏互联星空</title>

	</head>
	
 	<%if(session.getAttribute("loginname")==null){
 		response.sendRedirect("passport/zx.passport.189.cn/Logon/UDBCommon/L/LoginIndex.jsp");}
	%>

	<body style="background:none">
<div><iframe src="guideAndlogin2.htm" width="100%" height="174" scrolling="no" frameborder="0"></iframe></div>
<table width="964" border="0" align="center" cellpadding="0" cellspacing="0" >
<tbody><tr>
		<td align="center">
			<iframe src="./query_files/myvnet.htm" width="960" height="730" scrolling="no" frameborder="0"></iframe>
		</td>
	</tr>
	<tr>
		<td align="center" width="964" height="202">
			<div width="964" height="202"><iframe src="endbut.htm" width="100%" height="200" scrolling="no" frameborder="0"></iframe></div>
	  </td>
	</tr>
</tbody></table>
</body>

</html>