// JScript 文件

//初始化“每天都在进步”区域
function InitAdvancement(){
    try
    {
        jQuery.getJSON('http://passport.189.cn/SelfS/Service/HomeAjaxHandler.ashx?m=ulog&callback=?',{random:Math.random},
        function(r){
            var inerHtml = "<h1>我们每天都在进步着：</h1>";
            for(var i=0; i<r.length; i++){
               inerHtml = inerHtml + "<p><span>" + r[i].title + "</span>" + r[i].time + "</p>";
            }
            jQuery("#updateTxt").html(inerHtml);
        } );  
    }
    catch(err)
    {
        jQuery("#updateTxt").html('');
    }
}


//初始化“每天都在进步”区域（新）
function InitAdvancementNew(){
    try
    {
        jQuery.getJSON('http://passport.189.cn/SelfS/Service/HomeAjaxHandler.ashx?m=ulog&callback=?',{random:Math.random},
        function(r){
            var inerHtml = "";
            for(var i=0; i<r.length; i++){
               inerHtml = inerHtml + "<tr><th>" + r[i].time + "</th><td>" + r[i].title + "</td></tr>";
            }
            jQuery("#updateTxt").html(inerHtml);
        } );  
    }
    catch(err)
    {
        jQuery("#updateTxt").html('');
    }
}

//初始化“每天都在进步”区域（营销）
function InitAdvancementYX(){
    try
    {
        jQuery.getJSON('http://passport.189.cn/SelfS/Service/HomeAjaxHandler.ashx?m=ulog&callback=?',{random:Math.random},
        function(r){
            var inerHtml = "<ul>";
            for(var i=0; i<r.length; i++){
               inerHtml = inerHtml + "<li class=uili ><a href=# title=" + r[i].title + ">" + r[i].time + "	" + r[i].title + "</a></li>";
            }
            inerHtml = inerHtml + "</ul>";
            jQuery("#botBG_login").append(inerHtml);
        } );  
    }
    catch(err)
    {
        jQuery("#updateTxt").html('');
    }
}

//初始化广告区域
function InitAdPart(){
    try
    {
        jQuery.getJSON('http://passport.189.cn/SelfS/Service/HomeAjaxHandler.ashx?m=lslides&callback=?',{random:Math.random},
        function(r){
            var spInerHtml ="<ul class='Slides'>";
            for(var i=0; i<r.length; i++){
               spInerHtml = spInerHtml + "<li><a target='_blank' href='" + r[i].url + "'><img src='"+ r[i].imageurl + "'></a></li>";
            }
            spInerHtml = spInerHtml +"</ul>";
            jQuery("#SlidePlayer").html(spInerHtml);           
            TB.widget.SimpleSlide.decoration('SlidePlayer', {eventType:'mouse', effect:'scroll'});
            picrun_ini();    
        } );  
    }
    catch(err)
    {
        jQuery("#SlidePlayer").html('');
    }
}

//初始合作站点区域
function InitFamilySite(){
    try
    {
        jQuery.getJSON('http://passport.189.cn/SelfS/Service/HomeAjaxHandler.ashx?m=reapp&callback=?',{random:Math.random},
        function(r){
                var PLstInerHtml='';
            for(var i=0; i<r.length; i++){
               PLstInerHtml = PLstInerHtml + "<A class=pl href=" + r[i].url + " target=_blank><IMG src=" + r[i].imageurl + " width='100' height='40' /></A>";
            }
            jQuery("#List1_1").html(PLstInerHtml); 
            picrun_ini();           	        
        } ); 
    }
    catch(err)
    {
        jQuery("#List1_1").html('');
    }
}

function loadgoogle(){
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-29209020-1']);
  _gaq.push(['_trackPageview']);
 
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www/') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
}

//初始化PassportLogin页面
function InitForPLogin(){
    InitAdvancement();
    InitFamilySite(); 
}

//初始化home页面
function InitForHome(){
    InitAdPart();
    InitAdvancement();
    InitFamilySite();   
}

//初始化home页面(新)
function InitForHomeNew(){
    InitAdvancementNew();
    InitFamilySite();   
}

//初始化home页面(YX)
function InitForHomeYX(){
    InitAdvancementYX();
 
}