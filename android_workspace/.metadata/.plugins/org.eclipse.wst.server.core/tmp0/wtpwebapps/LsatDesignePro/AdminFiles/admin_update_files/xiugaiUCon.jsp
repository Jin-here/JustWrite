<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		

		<title>修改用户套餐</title>

		<link type="text/css" rel="stylesheet" href="public(4).css">
		<link type="text/css" rel="stylesheet" href="public(3).css">
		<link type="text/css" rel="stylesheet" href="style(1).css">

	<script language="javascript" src="jsutil.js"></script>
	<script language="javascript">
    

	
	function check1(){
		if(document.getElementById("uname").value==""){
			document.getElementById("tips").innerHTML="<font color='#FF0000'>用户名不能为空！</font>";
		}
		else
			return 1;
	}
	
	function check6(){
		var str=document.getElementsByName("shutdownconlist");
		var len=str.length;
		for (i=0;i<len;i++){
			if(str[i].checked == true)
			  {
			   return 1;
			  }
		}
		document.getElementById("tips2").innerHTML="<font color='#FF0000'>至少必须选择一个套餐！</font>";

			  return 0;		  
	}
	function check7(){
		var str=document.getElementsByName("newconlist");
		var len=str.length;
		for (i=0;i<len;i++){
			if(str[i].checked == true)
			  {
			   return 1;
			  }
		}
		document.getElementById("tips2").innerHTML="<font color='#FF0000'>至少必须选择一个套餐！</font>";

			  return 0;		  
	}
	function submitform(){
		if(check7()==1){
			document.getElementById("f").submit();

		}
		
	}
function checkform(){
	if(check1()==1){
	var id=document.getElementById("uname").value;
	window.location.href = "xiugaiusercon?userid="+id;
	}
}
function shutdown(){
	if(check6()==1)
	document.getElementById("form").submit();
}
	function reset(){
		document.getElementById("f").reset();
	}

</script></head>

	<body style="background:none">


        	<div class="pe_r_img"><img src="pebg2.gif"></div>
        	<div class="pe_r_t"><a href="xiugaiUser.jsp">用户信息修改</a>|<a href="selallconsec?">套餐信息修改</a>|<a href="xiugaiUCon.jsp">用户套餐修改</a></div>
			<div class="pe_r_title">用户套餐修改</div>

             <div class="pe_r_text">
             
             
             <div class="select1">输入要查询的账号<input type="text" class="uid" style="width:180px;" id="uname"></div>
			    <div class="chaxun"><img src="chaxun.gif" name="Submit" width="47" style="cursor: hand; height: 30px;" onClick="checkform()" value="查询"></div>
			    <div class="chaxuntips" id="tips">
			    <font color='#FF0000'><s:property value="#session.chaxuntip"/></font>
   								 <%session.setAttribute("chaxuntip",null); %></div>
			    </div>
<s:if test="%{haveallconlist!=null}">
<form id="form" method="post" action="zhongzhiusercon">   
   <div class="pe_r_text">
   	  <div class="select">选择要终止的套餐： 已有套餐：</div>
   	  	<div class="check">
   	  	<input type="hidden" value="<s:property value='userinfor.userid'/>" name="userid">
	   	  <s:iterator var="havecon" value="haveallconlist">
	      <input type="checkbox" name="shutdownconlist" value="<s:property value='conname'/>"><s:property value="conname"/><br>
	      </s:iterator>
    	</div>
    <div class="chaxun"><img src="zhongzhi.gif" name="Submit" width="47" style="cursor: hand; height: 30px;" onClick="shutdown()" value="终止"></div>
    <div class="chaxuntips" id="tips2">
     <font color='#FF0000'><s:property value="#session.zhongzhitip"/></font>
   			 <%session.setAttribute("zhongzhitip",null); %></div>
	
    </div>
</form>
  			<div class="pe_r_text"><div class="tips1">添加用户</div><div class="tips2" id="tips3">
				<font color='#FF0000'><s:property value="#session.adductips"/></font>
   				 <%session.setAttribute("adductips",null); %></div></div>
 <form  id="f"  method="post" action="xiugaiuserconsec" >        
            <div class="pe_r_tab">
<table width="745" border="0">
  <tbody>
      
  
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">用户名：</div>
    </td>
    <td width="369">
    	
    	<input type="text" readonly="readonly" style= "background-color:transparent;border:0;" value="<s:property value='userinfor.userid'/>" name="userinfor.userid"  id="uid" size="24" maxlength="40">  
    </td>
  </tr>
  <tr>
  	<td width="366" height="37"><div align="right" class="perco">用户全称：</div></td>
  	<td width="369"><input type="text" readonly="readonly" style= "background-color:transparent;border:0;" value="<s:property value='userinfor.username'/>" name="userinfor.username" size="24" maxlength="40"> 
    </td>
  </tr>
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">身份证号码：</div>
    </td>
    <td width="369">
    <input type="text" readonly="readonly" style= "background-color:transparent;border:0;" value="<s:property value='userinfor.useridcard'/>" name="userinfor.useridcard" size="24" maxlength="40"> 
    	
    </td>
  </tr>

 
  <tr >
  	<td width="366" height="37">
    <div align="right" class="perco">请选择套餐：</div></td>
    <td width="369" > 
    <s:iterator var="con" value="allconlist" >
   	<lable><input type="checkbox" name="newconlist" value="<s:property value='conname'/>"><s:property value='conname'/></lable><br>
   	</s:iterator>
    </td>
  </tr>

  
  

  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">已办理的套餐：</div></td>
    <td width="369" > 
    <s:iterator var="con" value="haveallconlist">
   	<s:property value='conname'/><br>
   	</s:iterator>
    </td>
  </tr>
  
  

  <tr>
  	<td colspan="2" align="center">
    <div>
    <img name="Submit" style="cursor: hand" src="tianjia.gif" value="修改" onClick="submitform()">
		<img name="Submit" style="cursor: hand" src="reset.gif" value="取消" onClick="reset()">
    </div>
    </td>
  </tr>

 <!--  <s:if test="haveallconlist!=null">
  <tr>
  	<td colspan="2" align="center">
    <div>
    <img name="Submit" style="cursor: hand" src="wancheng.gif" value="完成" onClick="location='tianjiaufir?'">
		<img name="Submit" style="cursor: hand" src="dayin.gif" value="打印" onClick="alert('未检测到打印设备')">
    </div>
    </td>
  </tr>
  </s:if>-->
</tbody></table>

			</div>
	
</form>
</s:if>
</body></html>