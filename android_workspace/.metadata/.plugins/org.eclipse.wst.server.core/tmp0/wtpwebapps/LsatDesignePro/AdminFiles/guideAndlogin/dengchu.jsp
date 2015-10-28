<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title></title>
    <style type="text/css">
        <!--
		
		body{
			margin:0;
			padding:0;
			font-size:12px;
			line-height:22px;
			}
		.login1{
	float:left;
	width:964px;
	height:41px;
	background-image:url(./loginkuang/index_login_bg.gif);
	background-repeat:no-repeat;
}
		.font_99ff00{ color:#99ff00;}
        -->
    </style>
    <link href="font.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        <!--
        .style1 {
            COLOR: #64bbd9;
            line-height: 18px;
            font-size: 12px;
        }

        .style4 {
            LINE-HEIGHT: 21px;
            FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
            color: #ff5400;
            font-size: 14px;
        }

        -->
    </style>
    <script language="javascript">
        function greetings() {
            var welcomeStr = new String();
            var hour = (new Date()).getHours();
            if (hour >= 5 && hour < 10)
            {
                welcomeStr = "早上好!";
            }
            else if (hour >= 10 && hour < 12)
            {
                welcomeStr = "上午好!";
            }
            else if (hour >= 12 && hour < 19)
            {
                welcomeStr = "下午好!";
            }
            else
            {
                welcomeStr = "晚上好!";
            }
            document.write(welcomeStr);
        }
    </script>
</head>

<body marginwidth="0" marginheight="0">

		<div class="login1">
		  <div style=" float:left; margin-top:8px; margin-left:27px;">
		<a href="../admin_login.jsp" target="_top"><img src="logout_btn1.gif" width="60" height="23" border="0"></a></div>
		<div style=" width:auto; float:left; margin-top:8px; margin-left:10px; color:#ffffff;">
		<%=session.getAttribute("adminloginname")%>@管理员&nbsp;&nbsp;<%=session.getAttribute("adminloginname2")%>&nbsp;&nbsp;<script language="javascript">greetings();</script></div>
		<div style=" width:auto; float:right; margin-top:8px; margin-right:10px; color:#ffffff;">欢迎登录管理系统</div>
		</div>
    	    

    	
    	
</body></html>