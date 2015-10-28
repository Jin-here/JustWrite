<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		

		<title>添加用户</title>

		<link type="text/css" rel="stylesheet" href="public(1).css">

		<link type="text/css" rel="stylesheet" href="style(1).css">

	<script language="javascript" src="jsutil.js"></script>
	<script language="javascript">
    

	
	function check1(){
		if(document.getElementById("userid").value==""){
			document.getElementById("tips").innerHTML="<font color='#FF0000'>用户名不能为空！</font>";
		}
		else
			return 1;
	}
	function check2(){
		if(document.getElementById("username").value==""){
			document.getElementById("tips").innerHTML="<font color='#FF0000'>用户姓名不能为空！</font>";
		}else
			return 1;
	}
	function check3(){
		if(document.getElementById("password").value==""){
			document.getElementById("tips").innerHTML="<font color='#FF0000'>用户密码不能为空！</font>";
		}else
			return 1;
	}
	function check4(){
		if(document.getElementById("password").value!=document.getElementById("password1").value){
			document.getElementById("tips").innerHTML="<font color='#FF0000'>两次密码不相同！</font>";
		}else
			return 1;
	}
	function check5(){
		if(document.getElementById("useridcard").value==""){
			document.getElementById("tips").innerHTML="<font color='#FF0000'>证件号码不能为空！</font>";
		}else
			return 1;
	}
	function check6(){
		var str=document.getElementsByName("newconlist");
		var len=str.length;
		for (i=0;i<len;i++){
			if(str[i].checked == true)
			  {
			   return 1;
			  }
		}
		document.getElementById("tips").innerHTML="<font color='#FF0000'>至少必须选择一个套餐！</font>";

			  return 0;
		  
	}
	function submitform(){
		if(check1()==1&&check2()==1&&check3()==1&&check4()==1&&check5()==1&&check6()==1){
			document.getElementById("f").submit();

		}
		
	}

	
	function reset(){
		document.getElementById("f").reset();
	}

</script></head>

	<body style="background:none">

<form  id="f"  method="post" action="tianjiausec?" >
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
        	<div class="pe_r_t"><a href="tianjiaufir?">添加用户</a>|<a href="tianjiaCon.jsp">添加套餐</a>|<a href="tianjiaHdong.htm">添加活动</a></div>
<div class="pe_r_title">添加用户</div>

             <div class="pe_r_text">
   	<div class="tips1">添加用户</div><div class="tips2" id="tips">
   	<font color='#FF0000'><s:property value="#session.addutips"/></font>
    <%session.setAttribute("addutips",null); %>
   	</div> 
</div>
            
            
            
            
            
            <div class="pe_r_tab">
<table width="745" border="0">
  <tbody>
  
    <s:if test="%{haveallconlist==null}">
  
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">用户名：</div>
    </td>
    <td width="369">
    	
    	<input type="text" id="userid" value="<s:property value='userinfor.userid'/>" name="userinfor.userid" size="24" maxlength="40">  
    </td>
  </tr>
  <tr>
  	<td width="366" height="37"><div align="right" class="perco">用户全称：</div></td>
  	<td width="369"><input type="text" id="username" value="<s:property value='userinfor.username'/>" name="userinfor.username" size="24" maxlength="40"> 
    </td>
  </tr>
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">身份证号码：</div>
    </td>
    <td width="369">
    <input type="text"  id="useridcard" value="<s:property value='userinfor.useridcard'/>" name="userinfor.useridcard" size="24" maxlength="40"> 
    	
    </td>
  </tr>
  </s:if>
      <s:if test="%{haveallconlist!=null}">
  
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">用户名：</div>
    </td>
    <td width="369">
    	
    	<input type="text" readonly="readonly" style= "background-color:transparent;border:0;" value="<s:property value='userinfor.userid'/>" name="userinfor.userid" size="24" maxlength="40">  
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
  </s:if>
  
 <s:if test="%{haveallconlist==null}"> 
  <tr>
  	<td width="366"><div align="right" class="perco">密码：</div></td>
  	<td width="369"><input type="password" id="password" name="userinfor.password" size="24" maxlength="40"> 
    </td>
  </tr>
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">确认密码：</div>
    </td>
    <td width="369">
    <input type="password" id="password1" name="surepas" size="24" maxlength="40"> 
    	
    </td>
  </tr>
</s:if>  
  
  
  
  
  
  
  
  
  <s:if test="%{allconlist!=null}">
  <tr >
  	<td width="366" height="37">
    <div align="right" class="perco">请选择套餐：</div></td>
    <td width="369" > 
    <s:iterator var="con" value="allconlist" >
   	<lable><input type="checkbox" name="newconlist" value="<s:property value='cid'/>"><s:property value='conname'/></lable><br>
   	</s:iterator>
    </td>
  </tr>
  </s:if>
  
  
   <s:if test="%{haveallconlist!=null}">
  <tr >
  	<td width="366" height="37">
    <div align="right" class="perco">已办理的套餐：</div></td>
    <td width="369" > 
    <s:iterator var="con" value="haveallconlist">
   	<s:property value='conname'/><br>
   	</s:iterator>
    </td>
  </tr>
  </s:if>
  
  <s:if test="%{haveallconlist==null}">
  <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">消费上限(元)：</div></td>
    <td width="369">
    <select name="userinfor.expend">
    <option>420元</option>
    <option>840元</option>
    
    </select>
    </td>
  </tr>
  </s:if>
    <s:if test="%{haveallconlist!=null}">
   <tr bgcolor="#E9E3D7">
  	<td width="366" height="37">
    <div align="right" class="perco">消费上限(元)：</div></td>
    <td width="369"><s:property value="userinfor.expend"/>
    </td>
  </tr>
  </s:if>
  
  
  
  
  <s:if test="haveallconlist==null">
  <tr>
  	<td colspan="2" align="center">
    <div>
    <img name="Submit" style="cursor: hand" src="tianjia.gif" value="修改" onClick="submitform()">
		<img name="Submit" style="cursor: hand" src="reset.gif" value="取消" onClick="reset()">
    </div>
    </td>
  </tr>
  </s:if>
    <s:if test="haveallconlist!=null">
  <tr>
  	<td colspan="2" align="center">
    <div>
    <img name="Submit" style="cursor: hand" src="wancheng.gif" value="完成" onClick="location='tianjiaufir?'">
		<img name="Submit" style="cursor: hand" src="dayin.gif" value="打印" onClick="alert('未检测到打印设备')">
    </div>
    </td>
  </tr>
  </s:if>
</tbody></table>
</div>
</form>
</body></html>