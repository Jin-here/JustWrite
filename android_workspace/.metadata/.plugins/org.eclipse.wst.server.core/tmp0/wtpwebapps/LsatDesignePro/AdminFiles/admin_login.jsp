<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>管理员登录</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<script type="text/javascript">
function checkform(){
	if(document.getElementById("adminid").value==""||document.getElementById("adminpas")=="")
		document.getElementById("tip").innerHTML="<font color='#FF0000'>账号密码不能为空！</font>";
	else
		document.getElementById("form").submit();
	
}
function resetform(){
	document.getElementById("form").reset();
	
} 



</script>
</head>

<body>
<center>
<div style="width:1200px;height:640px;background-image:url(admin_login_files/admin_login.gif);">

<div style="width:400px; height:200px; padding-top: 350px; padding-left: 380px;">
  <form id="form" action="adminloginjuge">
  <table width="293" height="193">
    <tr>
      <td width="118" align="center">管理员账号</td>
      <td width="175"><input type="text" id="adminid" name="adminid" style="width:170px;height:30px;" /></td>
    </tr>
    <tr>
      <td align="center">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
      <td><input type="password" id="adminpas" name="adminpas" style="width:170px;height:30px;" /></td>
    </tr>
    
    <tr>
      <td colspan="2" align="center"><img name="Submit" style="cursor: hand" src="admin_login_files/denglu.gif" value="修改" onclick="checkform()" /> &nbsp;&nbsp; 
      <img name="Submit" style="cursor: hand" src="admin_login_files/quxiao.gif" value="修改" onclick="resetform()" /></td>
      
    </tr>
    <tr><td colspan="2" align="center">
    <s:if test="%{#session.adminlogintip==null} ">
    <sapn id="tip"></span>
 	</s:if>
    <s:else>
 	 <sapn id="tip"><font color='#FF0000'><s:property value="#session.adminlogintip"/></font></span>
  	<%session.setAttribute("adminlogintip", null); %>
    </s:else>
    </td></tr>
  </table>
  </form>
</div>
 </div>
</center>>
</body>
</html>