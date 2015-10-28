<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		
<title>套餐信息修改</title>

		<link type="text/css" rel="stylesheet" href="public(1).css">
		<link type="text/css" rel="stylesheet" href="style(1).css">

		<script language="javascript" src="jsutil.js"></script>
		<script src="../js/jquery-2.1.3.js"> </script>
		
		<script language="javascript">

		function checkform(){
			var id=document.getElementById("selcon").value;
			window.location.href = "xiugaicon?cid="+id;
			
		}
		function check1(){
			if(document.getElementById("cname").value==""){	
				document.getElementById("chaxuntip").innerHTML="<font color='#FF0000'>套餐名称不能为空！</font>";
				return false;
			}else if(document.getElementById("conprice").value==""){
				document.getElementById("chaxuntip").innerHTML="<font color='#FF0000'>套餐价格不能为空！</font>";
			return false;
			}else
				return true;
		}
		
		function IsNum(num){ 
			var reNum=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/; 
			if(reNum.test(num)) { 
				return true; 
				} else { 
					if(num < 0) { 
						document.getElementById("chaxuntip").innerHTML="<font color='#FF0000'>套餐价格不能为负！</font>";
						} else { 
							document.getElementById("chaxuntip").innerHTML="<font color='#FF0000'>套餐价格只能是规则数字！</font>";
 
							} return false; 
							}
			} 
		function upcheck(){
			if(check1()&&IsNum(document.getElementById("conprice").value)){
				document.getElementById("f").submit();
			}
				
		}
</script>

	</head>



	<body style="background:none">
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
            <div class="pe_r_t"><a href="xiugaiUser.jsp">用户信息修改</a>|<a href="selallconsec?">套餐信息修改</a>|<a href="xiugaiUCon.jsp">用户套餐修改</a></div>
<div class="pe_r_title">套餐信息修改</div>
   	<div class="pe_r_text">
    
   	<div class="select">选择要查询的套餐<select name="select" id="selcon" style= "width:230px;">
   	
   	<s:iterator var="con" value="allconlist">
   	<option value="<s:property value='cid'/>"><s:property value='conname'/></option>
   	</s:iterator>
   	</select>
   	</div>
   	
    <div class="chaxun"><img src="chaxun.gif" name="Submit" width="47" style="cursor: hand; height: 30px;" onClick="checkform()" value="查询"></div>
    <div class="chaxuntips" id="chaxuntip">
    <font color='#FF0000'><s:property value="#session.chaxuncontip"/></font>
    <%session.setAttribute("chaxuncontip",null); %>
    
    </div>
    </div>
    <div class="pe_r_tab">
    <s:if test="%{con.conname!=null}">
<form name="f" id="f" method="post" action="xiugaiconsec">
<table width="745" border="0">
  <tbody><tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">套餐名称：</div></td>
  	<td width="350">
  <input type="text" id="cname" value="<s:property value='con.conname'/>" style="width:190px;" name="con.conname" size="24" maxlength="40">
  </tr>
  <tr>
  <td width="353" height="37">
    <div align="right" class="perco">期限：</div></td>
  	<td width="350"><input type="text"  style= "background-color:transparent;border:0;width:80px;" id="contime" value="<s:property value='con.contime'/>" name="con.contime" size="24" maxlength="40">
    更改为
    <select name="newcontime" id="newtime">
    <option value="0"></option>
    <option value="1年">1年</option>
    <option value="2年">2年</option>
    <option value="4年">4年</option>
    <option value="1个月">1个月</option>
    <option value="3个月">3个月</option>
    <option value="6个月">6个月</option>
    </select>
  </tr>
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">带宽：</div></td>
  	<td width="350"><input type="text" style= "background-color:transparent;border:0;width:80px;" id="conband" name="con.bandwidth" value="<s:property value='con.bandwidth'/>"size="24" maxlength="40">
    更改为
    <select name="newbandwidth">
    <option value="0"></option>
    <option value="2M">2M</option>
    <option value="4M">4M</option>
    <option value="8M">8M</option>
    <option value="10M">10M</option>
    <option value="20M">20M</option>
    </select>
  </tr>
  
  <tr>
  <input type="hidden" name="newcid" value='<s:property value="con.cid"/>'>
  <td width="353" height="37">
    <div align="right" class="perco">价格：</div></td>
  	<td width="350"><input type="text"  id="conprice" style="width:80px;"name="newprice" value="<s:property value='con.conprice'/>" size="24" maxlength="40">
    元
  </tr>
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">供应商：</div></td>
  	<td width="350"><input type="text" style= "background-color:transparent;border:0;width:80px;" id="conband" name="con.operater" value="<s:property value='con.operater'/>"size="24" maxlength="40">
  </tr>
  <tr>
  	<td colspan="2" align="center">
    <div>
  	<img name="Submit" style="cursor: hand" src="modify.gif" value="修改" onClick="upcheck()">
	<img name="Submit" style="cursor: hand" src="reset.gif" value="取消" onClick="reset()">
  </div>
  
  </td>	
  </tr>
</tbody></table>
</form>
</s:if>
</div>




</body></html>