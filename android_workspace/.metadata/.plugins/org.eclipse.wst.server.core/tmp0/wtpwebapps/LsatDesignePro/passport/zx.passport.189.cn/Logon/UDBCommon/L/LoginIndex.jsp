<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼帐号欢迎您</title>
    <meta name="Description" content="天翼帐号是中国电信为用户提供的业务统一登录帐号,使用天翼帐号可畅行中国电信提供的众多互联网服务。" />
    <meta name="Keywords" content="中国电信天翼帐号,电信天翼帐号,189通行证,天翼帐号,189邮箱,天翼宽带,189邮箱注册,天翼宽带注册,189,189.cn" />
    <link rel="icon" href="../Skin/default/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="../Skin/default/images/favicon.ico" type="image/x-icon" />

<script type="text/javascript" src="../JSFrame/jquery.js" ></script>
<script src="../JS/jquery-2.1.3.js"> </script>
<script type="text/javascript" src="../JSFrame/PageInit.js" ></script>
<script type="text/javascript" src="../JS/udbPwdCheck.js-t=20130328" ></script>
<script type="text/javascript" src="../JS/esurfinglogin.js-t=20120611" ></script>
<script type="text/javascript" src="../../../../service.passport.189.cn/ViJiami/common_sync_js_B.aspx" ></script>
<link href="../css/login_YX.css-t=20121206.css"  rel="stylesheet" type="text/css" />
<style type="text/css">
.uili {text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px;width:280px}
</style>
<script type="text/javascript">

/*
$(document).ready(function(){	
	$("#loginbut").click(function(){
		var userid=$("#txtUserID").val();
		alert(userid);
		var userpas=$("#txtPwd").val();
		alert(userpas);
		 $.ajax({ url: "loginjuge", 
			 type:"post",
		      data:{"userid":userid,"userpas":userpas}, 
		      dataType: "text",
		      timeout: 10000, 
		      success: function(data,textStatus){
		    	  alert(data);
		    	  if(data=="0"){
		    		  $("#lbl_Msg").html('<font color="#00EC00">原密码正确</font>');
		    	  }
		    	  
	            }  
	 
	 } ); 
	});
});*/









</script>
</head>
<body onload="javascript:InitForHomeYX()">
  <div id="header">
  <img src="../images/index_01.gif" tppabs="http://zx.passport.189.cn/Logon/UDBCommon/images/index_01.gif" />
    <div id="aboutAndHelp"><a href="http://passport.189.cn/SelfS/About/About.aspx">关于天翼帐号</a> | <a href="http://passport.189.cn/SelfS/About/FAQ.aspx" tppabs="http://passport.189.cn/SelfS/About/FAQ.aspx" >帮助中心</a></div>
    <div class="clear"></div>
  </div>
  <div id="content">
  <form name="Form1" method="post" action="loginjuge" id="Form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKMjA4Nzc2MjEyMA9kFgJmD2QWBgINDw8WAh4EVGV4dAWEAmh0dHA6Ly9wYXNzcG9ydC4xODkuY24vU2VsZlMvUmVkaXJlY3RSZXNwb25zZS5hc3B4P1JldHVyblVSTD1odHRwJTNhJTJmJTJmcGFzc3BvcnQuMTg5LmNuJTJmU2VsZlMlMmZIb21lJTJmZGVmYXVsdC5hc3B4JTNmUmV0dXJuVXJsJTNkaHR0cCUzYSUyZiUyZnNlcnZpY2Uudm5ldC5jbiUyZnp4JTJmVk5ldEludGVyZmFjZSUyZlZOZXRGb3JTUCUyZlZORVRTU08uYXNweCUzZlJldHVyblVybCUzZGh0dHAlMjUzYSUyNTJmJTI1MmZ3d3cudm5ldC5jbiUyNTJmZGQCDg8PFgIfAAUQMzUwMDAwMDAwMDAwMDEwMWRkAg8PDxYCHwAFDjU4LjIxMi4xMDEuMTM2ZGQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgIFC2NiX1NhdmVOYW1lBQZSZWFkZWT33RrIjns52Yzd/HaYFoBTFAIzXw==" />
</div>

<div>

	<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWFAK/kKniAQLT8dy8BQKd+7qdDgLRmZCFBgK1yJy1AQKM3NqTDQK/8ZbBBQKbgt3aDwLs9or/DwLbgOTgCALb2YGkAQLk2KvYDwLFvdPFDgKh/9zICgKnqZGuBgLynrTDDQL7urmaBALpqfPRDwK/s4scAo6J737d3O4ZvlB55ADRmViVkVGfzrTn8w==" />
</div>
    <div id="con_content">
    <div id="login">
        <div id="topBG_login">
        <div id="botBG_login">
      <p id="title_login">登录我的天翼帐号</p>
      <s:if test="%{#session.logintip==null}">
      <div id="errDemo" class="error" style="visibility:hidden">
      </s:if>
      <s:else>
      <div id="errDemo" class="error" >
      </s:else>
            <div class="bg">
                <div class="text">
                    <span id="lbl_Msg"><s:property value="#session.logintip"/></span></div>
            <%session.setAttribute("logintip", null); %>
            </div>
        </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr id="trInputAccount">
          <th>帐号：</th>
          <td><input name="txtUserID" type="text" id="txtUserID" class="textInput" onmouseover="if (value =='手机号/固话号/别名'){value =''}" onfocus="if (value =='手机号/固话号/别名'){value =''}" onmouseout="if (value ==''){value='手机号/固话号/别名';this.style.color='#999'}" onblur="if (value ==''){value='手机号/固话号/别名';this.style.color='#999'}" onkeydown="this.style.color='#000000'" value="手机号/固话号/别名" /></td>
        </tr>
        <tr id="trInputPassword">
          <th>密码：</th>
          <td><input name="txtPwd" type="password" maxlength="16" id="txtPwd" class="textInput" />&nbsp;<a href="../../../../../findpass.htm">找回密码?</a></td>
        </tr>
        
       
        
        <tr>
          <th>&nbsp;</th>
          <td><span id="spanSaveName" ><span class="chooseInput"><input id="cb_SaveName" type="checkbox" name="cb_SaveName" /></span><label for="remUser">记住帐号</label></span><span class="chooseInput"><input id="Readed" type="checkbox" name="Readed" checked="checked" /></span><label for="readsign">已阅读<a href="http://passport.189.cn/SelfS/About/Agreement.aspx" target="_blank">服务条款</a>和<a href="http://passport.189.cn/SelfS/About/Privacy.aspx" target="_blank">隐私声明</a></label></td>
        </tr>
        
        <tr id="trLogin">
          <th>&nbsp;</th>
          <td>
          <input type="submit" name="ibtn_Login" id="loginbut" value=" " onclick="if(ValidatePwd_AddReadCheck()){return compile('txtPwd');}else{return false;};" id="ibtn_Login" class="ibtn_Login" />&nbsp;
          </td>
        </tr>
        
      </table>
      <h2>体验天翼帐号，畅享一号通行：</h2>
      	</div>
      </div>
    </div>
    <div class="clear"></div>
    
        <input name="hid_ql_flag" type="hidden" id="hid_ql_flag" value="Y" />
        <input name="hid_ql_js_account" type="hidden" id="hid_ql_js_account" />
        <input name="hid_ql_js_udbtoken" type="hidden" id="hid_ql_js_udbtoken" />
        <input name="hid_ql_js_state" type="hidden" id="hid_ql_js_state" />
        <input name="pageguid" type="hidden" id="pageguid" value="ce02ba1b5bdd4d88b99446f5b2c50c2b" />
       
    
    
    
    
    
    <input name="HiddenErrMsg" type="hidden" id="HiddenErrMsg" />
    <input name="TimeMsg" type="hidden" id="TimeMsg" />
    <input name="fucengFlag" type="hidden" id="fucengFlag" value="0" />
    <input name="EncryptPageUrlSave" type="hidden" id="EncryptPageUrlSave" value="https://service.passport.189.cn/ViJiami/common_sync_js_B.aspx" />
    <input name="EncryptPassWord" type="hidden" id="EncryptPassWord" />
    <input name="EncryptVersionNo" type="hidden" id="EncryptVersionNo" value="1" />
    <input name="EncryptRemark" type="hidden" id="EncryptRemark" value="usable" />
  </div>
  </form>
  </div>
  <div id="footer">
    <div id="leftCopyright"> <a href="http://www.chinatelecom.com.cn/" target="_blank">中国电信集团</a> | 客服热线:10000<br />
      Copyright &copy; passport.189.cn </div>
    <div id="rightCopyright"> <a href="http://www.vnet.cn/about/culture02.htm" target="_blank">增值电信业务经营许可证A2.B1.B2-20090001</a><br />
      <a href="http://www.vnet.cn/about/culture.htm" target="_blank">文网文[2008]053号</a> | <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备09031924号</a></div>
    <div class="clear"></div>
  </div>
<script type="text/javascript" src="../../../../www.vnet.cn/tres/Vnet/passport/selfsLogin.js" tppabs="http://www.vnet.cn/tres/Vnet/passport/selfsLogin.js"></script>
<script type="text/javascript">
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','../../../../www.google-analytics.com/analytics.js'/*tpa=http://www.google-analytics.com/analytics.js*/,'ga');


  ga('create', 'UA-29209020-1', 'http://zx.passport.189.cn/Logon/UDBCommon/L/189.cn');
  ga('send', 'pageview');
</script>
</body>

</html>
