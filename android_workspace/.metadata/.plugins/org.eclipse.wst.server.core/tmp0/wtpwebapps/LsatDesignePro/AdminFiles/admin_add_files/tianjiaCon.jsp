<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		
<title>添加套餐</title>

		<link type="text/css" rel="stylesheet" href="public(1).css">
		<link type="text/css" rel="stylesheet" href="style(1).css">

		<script language="javascript" src="jsutil.js"></script>
		<script src="../js/jquery-2.1.3.js"> </script>
		
		<script language="javascript">
		

		function check1(){
			if(document.getElementById("cname").value==""){	
				document.getElementById("tips").innerHTML="<font color='#FF0000'>套餐名称不能为空！</font>";
				return false;
			}else if(document.getElementById("newprice").value==""){
				document.getElementById("tips").innerHTML="<font color='#FF0000'>套餐价格不能为空！</font>";
				return false;
			}else if(document.getElementById("contime").value=="0"){
				document.getElementById("tips").innerHTML="<font color='#FF0000'>套餐期限不能为空！</font>";
				return false;
			}else if(document.getElementById("operater").value==""){
				document.getElementById("tips").innerHTML="<font color='#FF0000'>套餐供应商不能为空！</font>";
				return false;
			}
				return true;
		}
		function IsNum(num){ 
			var reNum=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/; 
			if(reNum.test(num)) { 
				return true; 
				} else { 
					if(num < 0) { 
						document.getElementById("tips").innerHTML="<font color='#FF0000'>套餐价格不能为负！</font>";
						} else { 
							document.getElementById("tips").innerHTML="<font color='#FF0000'>套餐价格只能是规则数字！</font>";
 
							} return false; 
							}
			} 
		function checkform(){
			if(check1()&&IsNum(document.getElementById("newprice").value)){
				document.getElementById("f").submit();
			}
				
		}

</script>

	</head>



	<body style="background:none">
        	<div class="pe_r_img"><img src="pebg2.gif"></div>
            <div class="pe_r_t"><a href="tianjiaufir?">添加用户</a>|<a href="tianjiaCon.jsp">添加套餐</a>|<a href="tianjiaHdong.htm"> 添加活动</a></div>
<div class="pe_r_title">添加套餐</div>
   	 <div class="pe_r_text">
   	<div class="tips1">添加套餐</div><div class="tips2" id="tips">
   	<font color='#FF0000'><s:property value="#session.addcontips"/></font>
    <%session.setAttribute("addcontips",null); %>
   	</div> 
</div>
    <div class="pe_r_tab">
<center><s:property value="#session.tips"/></center>
<form name="f" id="f" method="post" action="tianjiacon">
<table width="745" border="0">
  <tbody>
  <s:if test="%{con==null}">
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">套餐名称：</div></td>
  	<td width="350"><input type="text" id="cname" name="con.conname" size="24" maxlength="40">
  </tr>
  <tr>
  <td width="353" height="37">
    <div align="right" class="perco">期限：</div></td>
  	<td width="350">
<select name="con.contime" id="contime">
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
  	<td width="350">
    <select name="con.bandwidth" id="bandwidth">
    <option value="无"></option>
    <option value="2M">2M</option>
    <option value="4M">4M</option>
    <option value="8M">8M</option>
    <option value="10M">10M</option>
    <option value="20M">20M</option>
    </select>
  </tr>
  <tr>
  <tr>
  <td width="353" height="37">
    <div align="right" class="perco">价格：</div></td>
  	<td width="350"><input type="text"  id="newprice" name="newprice" size="24" maxlength="40">
    
  </tr>
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">服务供应商：</div></td>
  	<td width="350"><input type="text" id="operater" name="con.operater" size="24" maxlength="40">
  </tr>
    <tr>
  	<td colspan="2" align="center">
    <div>
  	<img name="Submit" style="cursor: hand" src="tianjia.gif" value="修改" onClick="checkform()">
	<img name="Submit" style="cursor: hand" src="reset.gif" value="取消" onClick="reset()">
  </div>
  
  </td>	
  </tr>
  </s:if>
  
  
    <s:if test="%{con!=null}">
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">套餐名称：</div></td>
  	<td width="350">
  	<input type="text" readonly="readonly" style= "background-color:transparent;border:0;"  id="cname" name="con.conname" size="24" maxlength="40" value="<s:property value='con.conname'/>">
  </tr>
  <tr>
  <td width="353" height="37">
    <div align="right" class="perco">期限：</div></td>
  	<td width="350">
  	<input type="text" readonly="readonly" style= "background-color:transparent;border:0;"  id="contime" name="con.contime" size="24" maxlength="40" value="<s:property value='con.contime'/>">

  </tr>
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">带宽：</div></td>
  	<td width="350">
   	<input type="text" readonly="readonly" style= "background-color:transparent;border:0;"  id="bandwidth" name="con.bandwidth" size="24" maxlength="40" value="<s:property value='con.bandwidth'/>">
 
  </tr>
  <tr>
  <tr>
  <td width="353" height="37">
    <div align="right" class="perco">价格：</div></td>
  	<td width="350"><input type="text" readonly="readonly" style= "background-color:transparent;border:0;"  id="newprice" name="newprice" size="24" maxlength="40" value="<s:property value='con.conprice'/>">元
    
  </tr>
  <tr bgcolor="#E9E3D7">
  <td width="353" height="37">
    <div align="right" class="perco">服务供应商：</div></td>
  	<td width="350"><input type="text" readonly="readonly" style= "background-color:transparent;border:0;" id="operater" name="con.operater" size="24" maxlength="40" value="<s:property value='con.operater'/>">
  </tr>
    <tr>
  	<td colspan="2" align="center">
    <div>
  	<img name="Submit" style="cursor: hand" src="wancheng.gif" value="修改" onClick="location='tianjiaCon.jsp'">
	<img name="Submit" style="cursor: hand" src="reset.gif" value="取消" onClick="reset()">
  </div>
  
  </td>	
  </tr>
  </s:if>

</tbody></table>
</form>
</div>




</body></html>