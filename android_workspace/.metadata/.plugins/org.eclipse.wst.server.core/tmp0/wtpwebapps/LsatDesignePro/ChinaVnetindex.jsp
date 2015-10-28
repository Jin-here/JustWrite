<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ChinaVnet 互联星空-首页</title>
<link href="./indexfiles/css.css" rel="stylesheet" type="text/css">
  <meta http-equiv="content-type" content="text/html;charset=GBK">
<script type="text/javascript" async="" src="./indexfiles/ga.js"></script><script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<script language="javascript">
function SouSuo(){
	var getWB=document.getElementById("SouSuoWenBen").value;
	window.open("http://www.netitv.com/netitv/i_public/search.shtml?k="+getWB,"dreamduwin");
}
</script>
</head>
<!--body onload="window.open('http://a1.gd.vnet.cn/index/tishi1223.htm','','width=350,height=250')"-->
<body onload="MM_preloadImages(&#39;images/friend_kyt_k.gif&#39;)">



<!--head start-->

<!--head end-->
<%if((String)session.getAttribute("loginname")==null){
%>
<center>
<div><iframe width="1000" height="174" src="guideAndlogin.htm" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe></div>
</center>
<%} else{
out.print("<center>");
out.print("<div><iframe width='1000' height='174' src='guideAndlogin2.htm' scrolling='no' marginheight='0' marginwidth='0' frameborder='0'></iframe></div>");
out.print("</center>");%>
<%} %>
<div id="main">
 <div class="main">
 <div class="div_w964">
  <div class="main_1">
    <div class="main_2">
      <div class="div_h12"></div>
	  <!--left part-->
<div class="main_left"><iframe width="654" height="245" src="./indexfiles/demo.html" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe>
	    <!--blog-->
        <div id="blog" class="mt18"><iframe width="654" height="56" src="./indexfiles/blog.htm" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe></div>
		<!--blog end-->
		<div><img src="./indexfiles/index_line1.gif"></div>
        <div class="div_tysx1">
          <div class="logo_tysx"><span class="ss1_left"><a href="http://www.tv189.com/" target="_blank"><img src="./indexfiles/logo_tysx.gif" width="132" height="56" border="0"></a></span></div>
          <div class="div_ss1">
            <form action="javascript:SouSuo();" method="get"><div class="ss1_left">
            <input id="SouSuoWenBen" type="text" style="width:100px;">
             </div>
            <div class="ss1_right"><img src="./indexfiles/btn_ss1.gif" onclick="javascript:SouSuo();"></div>
            </form>

            <script language="javascript">
             function SouSuo(){
	         var getWB=document.getElementById("SouSuoWenBen").value;
	         window.open("http://so.tv189.com/s/"+getWB+".htm","dreamduwin");
             }
            </script>
          </div>
		  <div class="logo_imusic"><a href="http://www.118100.cn/" target="_blank"><img src="./indexfiles/logo_imusic.gif" width="132" height="56" border="0"></a><a href="file:///C:/Documents%20and%20Settings/Administrator/%E6%A1%8C%E9%9D%A2/%E6%AF%8F%E5%A4%A9%E6%9B%B4%E6%96%B0/data.xml"></a></div>
        </div>
        <div class="div_tysx2">
          <div class="div_tysx2_l">
                <div><a href="http://yx.tv189.com/m/379564.htm" target="_blank"><img src="./indexfiles/index_mov_141119.jpg" width="320" height="94" border="0"></a><a href="http://yx.tv189.com/v/368911.htm" target="_blank"></a></div>
			    <table class="mt11" width="320" border="0" cellspacing="0" cellpadding="0">
                  <tbody><tr>
                    <td height="30" colspan="2" align="left"><span class="font_ff6600"><a href="http://www.tv189.com/" onclick="javascript:pageTracker._trackPageview(&#39;/tyyx/dianbo&#39;);" target="_blank">[最新活动]</a></span><span class="font_4d4d4d_14"> <a href="http://taste.tv189.com/2014/xyhsy/index.html" target="_blank"><strong>飞Young中国梦 校园好声音全国总决赛</strong></a><a href="http://www.tv189.com/" target="_blank"></a></span></td>
                  </tr>
                  <tr>
                    <td width="12%" height="30" align="left" class="font_ff6600"><a href="http://yx.tv189.com/" target="_blank" onclick="javascript:pageTracker._trackPageview(&#39;/tytv/dianbo&#39;);">[影院]</a></td>
                    <td width="88%" align="left" class="font_4d4d4d_14"><a href="http://yx.tv189.com/v/379370.htm" target="_blank">麦兜我和我妈妈 - 饱含童真的治愈系故事 </a></td>
                  </tr>
                  <tr>
                    <td height="30" align="left" class="font_ff6600"><a href="http://yx.tv189.com/" target="_blank" onclick="javascript:pageTracker._trackPageview(&#39;/tytv/dianbo&#39;);">[影院]</a></td>
                    <td align="left" class="font_4d4d4d_14"><a href="http://yx.tv189.com/v/379368.htm" target="_blank">我的青春蜜友  - 一段爱恨纠缠啼笑皆非闺蜜情</a><a href="http://yx.tv189.com/v/377373.htm" target="_blank"></a></td>
                  </tr>
                  <tr>
                    <td height="30" align="left"><span class="font_ff6600"><a href="http://yx.tv189.com/" target="_blank" onclick="javascript:pageTracker._trackPageview(&#39;/tytv/dianbo&#39;);">[影院]</a></span></td>
                    <td align="left" class="font_4d4d4d_14"><a href="http://yx.tv189.com/v/379403.htm" target="_blank">死亡派对 - 隐藏黑暗中的真相 杀人游戏开始</a><a href="http://yx.tv189.com/m/378712.htm" target="_blank"></a></td>
                  </tr>
                  <tr>
                    <td height="30" align="left"><span class="font_ff6600"><a href="http://yx.tv189.com/" target="_blank" onclick="javascript:pageTracker._trackPageview(&#39;/tyyx/dianbo&#39;);">[影院]</a></span></td>
                    <td align="left" class="font_4d4d4d_14"><a href="http://yx.tv189.com/v/379662.htm" target="_blank">早安冬日海- 邂逅爱情 一段说走就走的旅行</a></td>
                  </tr>
                  <tr>
                    <td height="30" align="left" class="font_ff6600"><a href="http://yx.tv189.com/" target="_blank" onclick="javascript:pageTracker._trackPageview(&#39;/tytv/dianbo&#39;);">[影院]</a></td>
                    <td align="left" class="font_4d4d4d_14"><a href="http://yx.tv189.com/v/379564.htm" target="_blank">亲爱的 - 寻子背后，一场亲情风暴正席卷而至</a><a href="http://yx.tv189.com/m/377374.htm" target="_blank"></a></td>
                  </tr>
                </tbody></table>
          </div>
		  <div class="div_tysx2_r">
		        <div><a href="http://www.118100.cn/v5/984660---------/--/--/topic/2013FastModel/index/index.html" target="_blank"><img src="./indexfiles/index_mus_141119.jpg" width="320" height="94" border="0"></a></div>
			<table class="mt11" width="320" border="0" cellspacing="0" cellpadding="0">
			  <tbody><tr>
			    <td height="30" align="left" class="font_ff6600"><a href="http://www.118100.cn/v6/quku/index.html" target="_blank">[新歌]</a></td>
			    <td align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/984660---------/--/--/topic/2013FastModel/index/index.html" target="_blank">中国正在听 - 巨墙挑战 最炫酷的视听盛宴</a></td>
			    </tr>
			  <tr>
			    <td width="12%" height="30" align="left" class="font_ff6600"><a href="http://www.118100.cn/v6/quku/index.html" target="_blank">[新歌]</a></td>
			    <td width="88%" align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/action/secweborder/v3/weborder3.do?cid=3399084&pt=4&ca=5282&pid=461847" target="_blank">老公赚钱老婆花 - 大庆小告诉你好男人标准</a></td>
			    </tr>
			  <tr>
			    <td height="30" align="left"><span class="font_ff6600"><a href="http://www.118100.cn/v6/quku/index.html" target="_blank">[新歌]</a></span></td>
			    <td align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/984491---------/--/--/topic/2013FastModel/index/index.html" target="_blank">王菲 - 匆匆那年 记忆中爱情和友情纯真自白</a></td>
			    </tr>
			  <tr>
			    <td height="30" align="left" class="font_ff6600"><a href="http://www.118100.cn/v6/quku/index.html" target="_blank">[新歌]</a></td>
			    <td align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/action/secweborder/v3/weborder3.do?cid=3398888&pt=4&ca=5282&pid=461847" target="_blank">张碧晨- 殇情献唱一吻之间（青年医生插曲</a><a href="http://www.118100.cn/v5/action/secweborder/v3/weborder3.do?cid=3368736&pt=4&ca=5282&pid=461847" target="_blank"></a>）</td>
			    </tr>
			  <tr>
			    <td height="30" align="left" class="font_ff6600"><a href="http://www.118100.cn/v6/quku/index.html" target="_blank">[新歌]</a></td>
			    <td align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/action/secweborder/v3/weborder3.do?cid=3391639&pt=4&ca=5282&pid=461847" target="_blank">Be here - 张靓颖婉转深情嗓音助力“希式爱情”</a><a href="http://www.118100.cn/v5/action/secweborder/v3/weborder3.do?cid=3323199&pt=4&ca=1238&pid=461847" target="_blank"></a></td>
			    </tr>
			  <tr>
			    <td height="30" align="left" class="font_ff6600"><a href="http://www.118100.cn/v6/quku/index.html" target="_blank">[新歌]</a></td>
			    <td align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/action/secweborder/v3/weborder3.do?cid=3397931&pt=4&ca=5282&pid=461847" target="_blank">杨丞琳-
			      点水 轻轻唱出蜻蜓点水般的爱情路</a></td>
			    </tr>
			</tbody></table>
            
		  </div>
          
        </div>
        <div><img src="./indexfiles/index_line1.gif"></div>
        <div class="div_xk_zt">
          <div class="div_xk">
		    <div><img src="./indexfiles/logo_xkyy.gif"></div>
            <div><a href="http://cloud.189.cn/" target="_blank"><img src="./indexfiles/index_ad3_1127.jpg" border="0"></a></div>
			<table class="mt11" width="320" border="0" cellspacing="0" cellpadding="0">
			  <tbody><tr>
				<td height="30" align="left"><span class="font_ff6600">[<a href="http://webmail26.189.cn/webmail/">189 邮箱</a>]</span> <a href="http://webmail26.189.cn/webmail/" target="_blank"><span class="font_4d4d4d_14">随时随地轻松处理您的邮件</span></a></td>
				</tr>
			  <tr>
			   <td height="30" align="left"><span class="font_ff6600"><a href="http://game.189.cn/" target="_blank">[爱 游 戏]</a></span> <a href="http://game.189.cn/" target="_blank"><span class="font_4d4d4d_14">更多劲爆游戏，总有一款适合您</span></a></td>
				</tr>
			  <tr>
			    <td height="30" align="left"><span class="font_ff6600"><a href="http://dm.189.cn/index.php" target="_blank">[爱 动 漫]</a></span> <a href="http://dm.189.cn/index.php" target="_blank"><span class="font_4d4d4d_14">让您天天看最新最酷最炫的动漫</span></a></td>
				</tr>
			  <tr>
			    <td height="30" align="left"><span class="font_ff6600"><a href="http://www.189store.com/" target="_blank">[天翼空间]</a></span> <a href="http://www.189store.com/" target="_blank"><span class="font_4d4d4d_14">好玩好用，手机应用程序宝库</span></a></td> 
				</tr>
			</tbody></table>
          </div>
		  <div class="div_zt">
		    <div><!--a href="http://a1.gd.vnet.cn/monthly/zthz/" target="_blank"--><img src="./indexfiles/logo_zt.gif" border="0"></div>
		    <div><a href="http://www.118100.cn/v5/978446---------/--/--/topic/2013FastModel/index/index.html"><img width="320" height="94" src="./indexfiles/index_mus_141111.jpg" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></img></a></div>
			<table class="mt11" width="320" border="0" cellspacing="0" cellpadding="0">
			  <tbody><tr>
				<td width="12%" height="30" align="left"><span class="font_ff6600"><a href="http://yx.tv189.com/" onclick="javascript:pageTracker._trackPageview(&#39;/tyyx/dianbo&#39;);" target="_blank">[影院]</a></span></td>
				    <td width="88%" align="left" class="font_4d4d4d_14"><a href="http://yx.tv189.com/2014/ffl/" target="_blank">电影精选集：锋菲恋12年，你我别来无恙</a></td>
			  </tr>
			  <tr>
			    <td width="12%" height="30" align="left" class="font_ff6600"><a href="http://www.118100.cn/" onclick="javascript:pageTracker._trackPageview(&#39;/tyyx/dianbo&#39;);" target="_blank">[音乐]</a></td>
				<td width="88%" align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/978446---------/--/--/topic/2013FastModel/index/index.html" target="_blank">郭韩大战：电影杀手锏，谁说音乐不重要 </a></td>
			  </tr>
			  <tr>
			    <td width="12%" height="30" align="left"><span class="font_ff6600"><a href="http://qnk.tv189.com/l/tv/" onclick="javascript:pageTracker._trackPageview(&#39;/tytv/dianbo&#39;);" target="_blank">[影院]</a></span></td>
				    <td width="88%" align="left"><span class="font_4d4d4d_14"><a href="http://yx.tv189.com/2014/zhouxiuna/" target="_blank">大胸女神：那些年，我们一起追的周秀娜</a><a href="http://a1.gd.vnet.cn/xkmovie/zhuanti/young/" target="_blank"></a></span></td>
			  </tr>
			  <tr>
			    <td width="12%" height="30" align="left" class="font_ff6600"><a href="http://www.118100.cn/" onclick="javascript:pageTracker._trackPageview(&#39;/tyyx/dianbo&#39;);" target="_blank">[音乐]</a></td>
				<td width="88%" align="left" class="font_4d4d4d_14"><a href="http://www.118100.cn/v5/975550---------/--/--/topic/2013FastModel/index/index.html" target="_blank">神曲大乱斗：让广场节奏来得更丧心病狂</a>　<!--a href="http://a1.gd.vnet.cn/monthly/zthz/" target="_blank"><font color="#993300">>>更多</font></a--></td>
			  </tr>
			</tbody></table>
		  </div>
        </div>
		
        <div class="div_friend">
			<table width="654" border="0" cellspacing="0" cellpadding="0">
			  <tbody><tr>
				<td><a href="http://dm.189.cn/index.php" target="_blank" onmouseover="MM_swapImage(&#39;friend1&#39;,&#39;&#39;,&#39;images/friend_adm_k.gif&#39;,0)" onmouseout="MM_swapImgRestore()"><img src="./indexfiles/friend_adm.gif" alt="爱动漫" name="friend1" width="131" height="58" border="0" id="friend1"></a></td>
				<td><a href="http://www.189mv.cn/" target="_blank" onmouseover="MM_swapImage(&#39;friend2&#39;,&#39;&#39;,&#39;images/friend_yxt_k.gif&#39;,0)" onmouseout="MM_swapImgRestore()"><img src="./indexfiles/friend_yxt.gif" alt="院线通" name="friend2" width="131" height="58" border="0" id="friend2"></a></td>
				<td><a href="http://kyt.gd.vnet.cn/" target="_blank" onmouseover="MM_swapImage(&#39;friend3&#39;,&#39;&#39;,&#39;images/friend_kyt_k.gif&#39;,1)" onmouseout="MM_swapImgRestore()"><img src="./indexfiles/friend_kyt.gif" alt="客运通" name="friend3" width="130" height="58" border="0" id="friend3"></a></td>
				<td><a href="http://esurfingdownload.tykd.vnet.cn/EServices/download.html" target="_blank" onmouseover="MM_swapImage(&#39;friend4&#39;,&#39;&#39;,&#39;images/friend_tykd_k.gif&#39;,0)" onmouseout="MM_swapImgRestore()"><img src="./indexfiles/friend_tykd.gif" alt="天翼宽带" name="friend4" width="131" height="58" border="0" id="friend4"></a></td>
				<td><a href="http://www.118100.cn/" target="_blank" onmouseover="MM_swapImage(&#39;friend5&#39;,&#39;&#39;,&#39;images/friend_music_k.gif&#39;,0)" onmouseout="MM_swapImgRestore()"><img src="./indexfiles/friend_music.gif" alt="爱音乐" name="friend5" width="131" height="58" border="0" id="friend5"></a></td>
			  </tr>
			</tbody></table>
       </div>
      </div>
	  <!--left part end-->
	  
	  <!--right part-->
	  <div class="main_right">
	    <div id="hd">
	      <iframe width="258" height="246" src="./indexfiles/hd.htm" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe>
	    </div>
	    <div><img src="./indexfiles/line_right.gif"></div>
		<div id="pref"><iframe width="258" height="170" src="./indexfiles/pref.htm" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe></div>
	    <div><img src="./indexfiles/line_right.gif"></div>
	    <div id="phone"><iframe width="258" height="280" src="./indexfiles/phone.htm" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe></div>
	    <div><img src="./indexfiles/line_right.gif"></div>
	    <div id="mov"><iframe width="258" height="236" src="./indexfiles/mov.htm" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe></div>
		<div><img src="./indexfiles/line_right.gif"></div>
	  </div>
	  <!--right part end-->
    </div>
	
	<!--foot_nav start-->
	<div id="foot_nav" style="background-color:#00CCCC">
    
	<div style="float:left;"><iframe width="948" height="90" src="./indexfiles/end.htm" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe></div>
  </div>
    <!--foot_nav end-->
  </div>
 </div>
 </div>
</div>





</body></html>