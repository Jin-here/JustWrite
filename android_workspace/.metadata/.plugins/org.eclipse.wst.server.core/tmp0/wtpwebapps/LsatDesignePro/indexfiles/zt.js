var gCurrent=0, gSize=4, gframeDIV, gNumDIV, gAutoStart;
var gData=[
{href:"http://yx.tv189.com/2014/ffl/",img:"images_zt/index_mov_141110.jpg"},
{href:"http://www.118100.cn/v5/978446---------/--/--/topic/2013FastModel/index/index.html",img:"images_zt/index_mus_141111.jpg"}
];
var gFocus=1;	//最少取1,最大为图片总数

function $(id){return document.getElementById(id);}

function Init()
{
	gSize=gData.length;
    if (gFocus<1 || gFocus>gSize) gFocus=1;
	gFocus--;

	gframeDIV=$("ADMain");
	gNumDIV=$("ADNo");

	var str1=[], str2=[];
	for(i=0;i<gSize;i++)
	{
		if (i==gFocus)
		{
			str1.push('<div style="display:block"><a href="'+gData[i].href+'" target="_blank"><img class="adimg" src="'+gData[i].img+'"></a></div>');
			str2.push('<span class="adbbg1" id="t'+i+'" onmouseover="Start('+i+');ClearAuto();" onmouseout="SetAuto()">'+(i+1)+'</span>');
		}
		else
		{
			str1.push('<div style="display:none"><a href="'+gData[i].href+'" target="_blank"><img class="adimg" src="'+gData[i].img+'"></a></div>');
			str2.push('<span class="adbbg0" id="t'+i+'" onmouseover="Start('+i+');ClearAuto();" onmouseout="SetAuto()">'+(i+1)+'</span>');
		}	
	}
	gframeDIV.innerHTML=str1.join("");
	gNumDIV.innerHTML=str2.join("");
}

function Start(value)
{
	gCurrent=value;
	SetBg(value);
	Play(value);
}
function SetBg(value)
{
	for(var i=0;i<gSize;i++) $("t"+i+"").className="adbbg0";
	$("t"+value+"").className="adbbg1";
} 
function Play(value)
{
	var children=gframeDIV.getElementsByTagName("DIV");
	try
	{
		gframeDIV.filters[0].apply();
		for(i=0;i<gSize;i++) children[i].style.display=(i==value)?"block":"none"; 
		gframeDIV.filters[0].play();
	}
	catch(e)
	{
		for(i=0;i<gSize;i++) children[i].style.display=(i==value)?"block":"none";
	}
}

function ClearAuto() 
{
	clearInterval(gAutoStart)
}

function SetAuto()
{
	gAutoStart=setInterval(
		function()
		{
			gCurrent++;
			if(gCurrent>(gSize-1)) gCurrent=0;
			Start(gCurrent);
		}
		,3000
	);
}