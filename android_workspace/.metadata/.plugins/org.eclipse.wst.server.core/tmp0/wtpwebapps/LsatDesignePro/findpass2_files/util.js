/************************************************************************************/
/*
*Common
*BEGIN
*/

/*
*Ajax全局设置
*/
$.ajaxSetup({timeout:5000});


/*获取元素用$$('id')*/
var $$ = function (id) {
	return "string" == typeof id ? document.getElementById(id) : id;
};

/*在元素后面插入一个元素(doc中只提供了在元素前面插入一个元素)*/
function insertAfter(newEl, targetEl){
	var parentEl = targetEl.parentNode;
	if(parentEl.lastChild == targetEl){
		parentEl.appendChild(newEl);
	}else{
		parentEl.insertBefore(newEl,targetEl.nextSibling);
	} 
}

/*校验EMAIL，正确返回true,错误返回false*/
var CheckEmail = function(mail){
    var result = false;
    var regxEmail = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
    var vEmail = $.trim($$(mail).value); 
    if ((vEmail != '') && (!regxEmail.test(vEmail))){
        result = false; 
    }
    else{
        result = true;
    } 
    return result; 
}


function checkIDcard(idcard)
{
    //var Errors = new Array("验证通过!","身份证号码位数不对!","身份证号码出生日期超出范围或含有非法字符!","身份证号码校验错误!","身份证地区非法!");
    var Errors = new Array("","身份证号码位数不对!","身份证号码出生日期超出范围!","身份证号码校验错误!","身份证地区非法!");
    var Area = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};

    // 15位
    // 测试闰年出生日期的合法性
    var regExStr15A = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
    // 测试平年出生日期的合法性
    var regExStr15B = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;

    // 18位
    // 测试闰年出生日期的合法性
    var regExStr18A = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;
    // 测试平年出生日期的合法性
    var regExStr18B = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;

    var regExStr = "";  //正则表达式变量
    
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    if(Area[parseInt(idcard.substr(0,2))]==null)
    {
         return Errors[4];
    }

    switch(idcard.length)
    {
        case 15:
            if ((parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
			    regExStr = regExStr15A
		    }
		    else{
                regExStr = regExStr15B
		    }
    		
            if(regExStr.test(idcard))
            {
                return Errors[0];
		    }
		    else
		    {
		        return Errors[2];
		    }
		    break;
    				
        case 18:
            if ((parseInt(idcard.substr(6,4))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,4))+1900) % 100 == 0 && (parseInt(idcard.substr(6,4))+1900) % 4 == 0 )){
			    regExStr = regExStr18A
		    }
		    else{
                regExStr = regExStr18B
		    }
    		
            if(regExStr.test(idcard))
            {
		        return Errors[0];
		    }
		    else
		    {
		        return Errors[2];
		    }
		    break;
    		
        default:
            return Errors[1];
		    break;
    }
}


var isIE = (document.all) ? true : false;
var isIE6 = isIE && ([/MSIE (\d+)\.0/i.exec(navigator.userAgent)][0][1] == 6);
var Class = {
	create: function() {
		return function() { this.initialize.apply(this, arguments); }
	}
}
var Extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
}
var Bind = function(object, fun) {
	return function() {
		return fun.apply(object, arguments);
	}
}
var Each = function(list, fun){
	for (var i = 0, len = list.length; i < len; i++) { fun(list[i], i); }
};

var Contains = function(a, b){
	return a.contains ? a != b && a.contains(b) : !!(a.compareDocumentPosition(b) & 16);
}

/*
*共用方法
*调用示例：common.InitInputStyle();
* by stone at 2011/03/04
*/
var Common = Class.create();
Common.prototype = {
    /*初始化信息*/
    initialize:function(options){
        
    },
    //初始化所有输入框
    InitInputStyle:function(){
        //初始化所有输入框事件样式
        $('input[type="password"]').bind({
            blur:function(e){
                this.className = 'ipt';
            },
           focus:function(e){
                this.className = 'ipt alive';
           }
        }); 
       $('input[type="text"]').bind({
            blur:function(e){
                this.className = 'ipt';
            },
           focus:function(e){
                this.className = 'ipt alive';
           }
        });  
    },
    //初始化验证码输入框事件,参数传入元素id
    InitVCode:function(vcode_id){
//        var cid = '#'+vcode_id;
//        $(cid).bind({
//            blur:function(e){
//                if ($.trim(this.value) == ''){
//                    this.value = '输入验证码';
//                    this.style.color = '#A5A5A5'; 
//                }
//            },
//           focus:function(e){
//                if (this.value == '输入验证码'){
//                    this.value = '';
//                    this.style.color = '#000';  
//                }
//           }
//       }); 
//       //初始化验证码输入框样式
//       var valCode = $.trim($(cid).val());
//       if (valCode == '' || valCode == '输入验证码'){
//            $(cid).val('输入验证码');
//            $(cid).css('color','#A5A5A5');
//       } 
    }
}
var common = new Common();

var OverLay = Class.create();
OverLay.prototype = {
  initialize: function(options) {

	this.SetOptions(options);
	
	this.Lay = $$(this.options.Lay) || document.body.insertBefore(document.createElement("div"), document.body.childNodes[0]);
	
	this.Color = this.options.Color;
	this.Opacity = parseInt(this.options.Opacity);
	this.zIndex = parseInt(this.options.zIndex);
	
	this.Lay.style.display = "none";
	this.Lay.style.zIndex = this.zIndex;
	this.Lay.style.left = 0;
	this.Lay.style.top = 0;
	this.Lay.style.position = "fixed";
	this.Lay.style.width = "100%";
	this.Lay.style.height = "100%";
	
	//with(this.Lay.style){ display = "none"; zIndex = this.zIndex; left = top = 0; position = "fixed"; width = height = "100%"; }
	
	if(isIE6){
		this.Lay.style.position = "absolute";
		//ie6设置覆盖层大小程序
		this._resize = Bind(this, function(){
			this.Lay.style.width = Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth) + "px";
			this.Lay.style.height = Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight) + "px";
		});
		//遮盖select
		this.Lay.innerHTML = '<iframe style="position:absolute;top:0;left:0;width:100%;height:100%;filter:alpha(opacity=0);"></iframe>'
	}
  },
  //设置默认属性
  SetOptions: function(options) {
    this.options = {//默认值
		Lay:		null,//覆盖层对象
		Color:		"#000",//背景色
		Opacity:	50,//透明度(0-100)
		zIndex:		1000//层叠顺序
    };
    Extend(this.options, options || {});
  },
  //显示
  Show: function() {
	//兼容ie6
	if(isIE6){ this._resize(); window.attachEvent("onresize", this._resize); }
	//设置样式

	isIE ? this.Lay.style.filter = "alpha(opacity:" + this.Opacity + ")" : this.Lay.style.opacity = this.Opacity / 100;
	this.Lay.style.backgroundColor = this.Color;
	this.Lay.style.display = "block";
	
//	with(this.Lay.style){
//		//设置透明度
//		isIE ? filter = "alpha(opacity:" + this.Opacity + ")" : opacity = this.Opacity / 100;
//		backgroundColor = this.Color; display = "block";
//	}
  },
  //关闭
  Close: function() {
	this.Lay.style.display = "none";
	if(isIE6){ window.detachEvent("onresize", this._resize); }
  }
};



var LightBox = Class.create();
LightBox.prototype = {
  initialize: function(box, options) {
	
	this.Box = $$(box);//显示层
	
	this.OverLay = new OverLay(options);//覆盖层
	
	this.SetOptions(options);
	
	this.Fixed = !!this.options.Fixed;
	this.Over = !!this.options.Over;
	this.Center = !!this.options.Center;
	this.onShow = this.options.onShow;
	
	this.Box.style.zIndex = this.OverLay.zIndex + 1;
	this.Box.style.display = "none";
	
	//兼容ie6用的属性
	if(isIE6){
		this._top = this._left = 0; this._select = [];
		this._fixed = Bind(this, function(){ this.Center ? this.SetCenter() : this.SetFixed(); });
	}
  },
  //设置默认属性
  SetOptions: function(options) {
    this.options = {//默认值
		Over:	true,//是否显示覆盖层
		Fixed:	false,//是否固定定位
		Center:	false,//是否居中
		onShow:	function(){}//显示时执行
	};
    Extend(this.options, options || {});
  },
  //兼容ie6的固定定位程序
  SetFixed: function(){
	this.Box.style.top = document.documentElement.scrollTop - this._top + this.Box.offsetTop + "px";
	this.Box.style.left = document.documentElement.scrollLeft - this._left + this.Box.offsetLeft + "px";
	
	this._top = document.documentElement.scrollTop; this._left = document.documentElement.scrollLeft;
  },
  //兼容ie6的居中定位程序
  SetCenter: function(){//alert(document.documentElement.scrollTop);
	this.Box.style.marginTop = document.documentElement.scrollTop - this.Box.offsetHeight / 2 + "px";
	this.Box.style.marginLeft = document.documentElement.scrollLeft - this.Box.offsetWidth / 2 + "px";
  },
  //显示
  Show: function(options) {
	//固定定位
	this.Box.style.position = this.Fixed && !isIE6 ? "fixed" : "absolute";

	//覆盖层
	this.Over && this.OverLay.Show();
	
	//this.Box.style.display = "block";
	$(this.Box).fadeIn('normal');
	
	//居中
	if(this.Center){
		this.Box.style.top = this.Box.style.left = "50%";
		//设置margin
		if(this.Fixed){
			this.Box.style.marginTop = - this.Box.offsetHeight / 2 + "px";
			this.Box.style.marginLeft = - this.Box.offsetWidth / 2 + "px";
		}else{
			this.SetCenter();
		}
	}
	
	//兼容ie6
	if(isIE6){
		if(!this.Over){
			//没有覆盖层ie6需要把不在Box上的select隐藏
			this._select.length = 0;
			Each(document.getElementsByTagName("select"), Bind(this, function(o){
				if(!Contains(this.Box, o)){ o.style.visibility = "hidden"; this._select.push(o); }
			}))
		}
		//设置显示位置
		this.Center ? this.SetCenter() : this.Fixed && this.SetFixed();
		//设置定位
		this.Fixed && window.attachEvent("onscroll", this._fixed);
	}
	
	this.onShow();
  },
  //关闭
  Close: function() {
	this.Box.style.display = "none";
	this.OverLay.Close();
	if(isIE6){
		window.detachEvent("onscroll", this._fixed);
		Each(this._select, function(o){ o.style.visibility = "visible"; });
	}
  }
};

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*帮助提示模块
*BEGIN
*说明：
* TipsList示例
* this.TipsList = [{ID:'btnModifyApps',Postion:'L',Content:'您可以点击“修改”来管理我的服务导航，<a>我知道了</a>',OneTips:true}];
* TipsList JSON数组中的数据说明
* ID：要显示提示的参照物元素ID
* Postion：位于参照物元素位置的方位，L左边、T上边、R右边、B下边
* Title：标题
* Content：提示内容
* GroupName：分组名称，显示方式按分组，一组一组显示
*/
var HelpTips = Class.create();
HelpTips.prototype = {
    initialize: function(options) {
        this.TipsList = [];
    },
	InitHelpTips:function(){
	    
	    var className = '',left = 0, top = 0;
	    var tipsCount = this.TipsList.length;
	    var index = 1;
	    for (var j=0; j<this.TipsList.length; j++){
	        var id = this.TipsList[j].ID;
	        //去除页面中ID不存在和不显示的元素
            if (!$$(id) || $$(id).style.display == 'none'){
                this.TipsList.splice(j,1);
                --j;
            }
	    }
	    tipsCount = this.TipsList.length;
	    
	    for(var i=0; i<this.TipsList.length; i++){	    
            var id = this.TipsList[i].ID;
            //创建一个tips容器
            var panelID = 'htips_'+id;
            var panel = $$(panelID) || document.body.insertBefore(document.createElement("div"), document.body.childNodes[0]);
            panel.setAttribute('id',panelID);
            panel.className = 'firsTtip';
            panel.style.display = 'none';
            panel.style.postion = 'absolute';
            panel.innerHTML = '<div class="firsTtip"><h1><span class="right">1/5</span>提示：标题</h1><h2>内容</h2><h3><a href="#" class="close">我知道了</a></h3></div>';
            
            //给tips赋值
            $('#'+panelID+' h2').html(this.TipsList[i].Content);
            
            
            //如果有多步，则显示第几步如1/3，如果只有一步则不显示
            var stepText = '';
            if ((index == 1) && (index == tipsCount)){
                stepText = "";
                $('#'+panelID+' .close').bind('click',new Function('helpTips.CloseTips("'+panelID+'","'+panelID+'","'+this.TipsList[i].GroupName+'")'));
            }
            else{
                stepText = index + '/' + tipsCount;
                if (index == tipsCount){
                    $('#'+panelID+' .close').bind('click',new Function('helpTips.CloseTips("'+panelID+'","'+panelID+'","'+this.TipsList[i].GroupName+'")'));
                }
                else{
                    var tmpID = 'htips_'+this.TipsList[i+1].ID;
                    $('#'+panelID+' .close').bind('click',new Function('helpTips.CloseTips("'+panelID+'","'+tmpID+'")'));

                }
            }
            //显示提示和第几步操作
            $('#'+panelID+' h1').html('<span class="right">'+stepText+'</span>'+this.TipsList[i].Title);
            
            if (index == 1){
                panel.style.display = 'block';
            }
            index ++;
            
            var panelWidth = panel.offsetWidth;
	        var panelHeight = panel.offsetHeight;
            
	        //jquery用ID，去取对象时格式为$('#id')
	        var _tmpID = '#'+id;
	        var refObj = $(_tmpID);
	        var refWidth = refObj[0].offsetWidth;
	        var refHeight = refObj[0].offsetHeight;
	        //默认设置提示位置
	        var postion = this.TipsList[i].Postion ? this.TipsList[i].Postion : 'B';
	        switch(this.TipsList[i].Postion){
	            case 'L': {
	                    className = 'firsTtip';
	                    left = refObj.offset().left - panelWidth - 2;
	                    top = refObj.offset().top;
	                }break;
	            case 'T': {
	                    className = 'firsTtip';
	                    left = refObj.offset().left;
	                    top = refObj.offset().top - panelHeight - 2;
                    }break;
	            case 'R': {
	                    className = 'firsTtip';
	                    left = refObj.offset().left + refWidth + 2;
	                    top = refObj.offset().top;
                    }break;
	            case 'B': {
	                    className = 'firsTtip';
	                    left = refObj.offset().left - 10;
	                    top = refObj.offset().top + refHeight + 2;
	                }break;
	            default:  {
	                    className = 'firsTtip';
	                    left = refObj.offset().left;
	                    top = refObj.offset().top + refHeight + 2;
	                }
	        }	 
	        panel.style.left = left + 'px';
	        panel.style.top = top + 'px';//alert(left+'|'+top);
	    }  
	},
	CloseTips:function(id,nextID,groupName){
        $('#'+id).hide();
        if (id == nextID){
            //更新数据库
            $.post(userHome.home_service_url,{m:'shelptips',q:groupName},
            function(r){
    			
			    /*用户已经过期，重新登录*/
			    if (r.Result == 'false'){
				    userHome.ReLogin();
				    return;
			    }
    		
                if (r == 'FALSE'){
                    alert("系统繁忙，请稍后再试");
                    return; 
                } 
            } );  
        }
        else{
            $('#'+nextID).show();
        }
	    
	}
}
var helpTips = new HelpTips();

/*END*/
/************************************************************************************/



/************************************************************************************/
/*
*用户门户登录首页
*BEGIN
*/
var UserHome = Class.create();
UserHome.prototype = {
    initialize: function(options) {
        this.home_service_url = ''; 
		this.PUInfo = '';
		this.pay_list_url = '';
        this.AppsPanel = '';
    },
    /*初始化我的服务*/
    InitUserApps:function(){
        $.ajax({
            url:this.home_service_url,
            type:'GET',
            dataType:'json', 
            data:{m:'ucapps',random:Math.random},
            beforeSend:function(){
                if ($('#loading_uapp')){
                    $('#loading_uapp').show();
                }
            },
            success:function(r){
                if ($('#loading_uapp')){
                    $('#loading_uapp').hide();
                }
				/*用户已经过期，重新登录*/
				if (r.Result == 'false'){
					userHome.ReLogin();
					return;
				}
				if (r.length > 0){
                    var apps = '';
                    $("#divApp").html();  
					var include189 = false;
                    for (var i=0; i<r.length; i++){
						if ((r[i].AppName.indexOf('189') != -1) && (r[i].AppName.indexOf('邮箱') != -1)){
							include189 = true;
							apps += '<div class="App" id="'+r[i].ID+'"><a href="'+r[i].AppURL+'" target="_blank" title="'+r[i].Remark+'"><span>'+r[i].AppName+'<label id="189Unread"></label></span><img src="'+r[i].AppLogoURL+'" width="32" height="32" /></a></div>';
						}else{
							apps += '<div class="App" id="'+r[i].ID+'"><a href="'+r[i].AppURL+'" target="_blank" title="'+r[i].Remark+'"><span>'+r[i].AppName+'</span><img src="'+r[i].AppLogoURL+'" width="32" height="32" /></a></div>';
						}
                    }
                    $("#divApp").html(apps); 
                    //设置未读邮件
				    userHome.InitUnreadMail();
                }
            },
			error:function(XMLHttpRequest,textStatus,errorThrown){
				$("#divApp").html('&nbsp;加载失败，请<a href="javascript:void(0);" onclick="userHome.InitUserApps();">重新载入</a>');
				$('#loading_uapp').hide();
			}
        });
    },
    /*
    *设置未读邮件
    *采用了循环调用规则，目的是为了解决189邮箱接口不稳定，经常提取不到数据
    *date: 2011/03/28
    */
    InitUnreadMail:function(){
	    if (($('#189Unread').length > 0) && ($('#189Unread').html() == '')){
	        $.ajax({
	            url:this.home_service_url,
	            type:'GET',
	            dataType:'json',
	            data:{m:'unreadmail',random:Math.random},
	            success:function(r){
			        if (r.Result == 'true'){
				        $('#189Unread').html('('+r.UnreadNum+')');
			        }
			        else{
			            setTimeout('userHome.InitUnreadMail()',5000);
			        }
	            },
	            error:function(XMLHttpRequest,textStatus,errorThrown){
	                setTimeout('userHome.InitUnreadMail()',5000);
	            }
	        });
	    }
    },
    /*打开修改服务面板*/
    OpenAppsPanel:function(){
		this.InitCTAppsPanel();
        //this.AppsPanel.Show();
    },
    /*关闭修改服务面板*/
    CloseAppsPanel:function(){
        this.SaveAppsList(); 
        this.AppsPanel.Close();
    },
    /*保存服务修改*/
    SaveAppsList:function(){
        var upList = $$('userapps_list').options;
        var upstr = "";
        for (var i=0; i<upList.length; i++){
            upstr+= upList[i].value + '|';
        } 
        upstr = upstr.substring(0,upstr.lastIndexOf('|')); 
        $.post(this.home_service_url,{m:'sapp',q:upstr},
        function(r){
			
			/*用户已经过期，重新登录*/
			if (r.Result == 'false'){
				userHome.ReLogin();
				return;
			}
		
            if (r == 'FALSE'){
                alert("系统繁忙，请稍后再试");
                return; 
            } 
            userHome.InitUserApps();
        } );  
    },
    /*关闭提醒*/
    CloseTips:function(id){
        $.get(this.home_service_url,
        {m:'tips',tid:id},
        function(r){
            $('#divTips').hide();
        }); 
    },
    /*初始化修改服务的面板*/
    InitCTAppsPanel:function(){ 
        this.AppsPanel = new LightBox('divSCA',{Over:true,Opacity:65,Center:true,Fixed:true});
        
        $.getJSON(this.home_service_url,{m:'apppop',random:Math.random},
        function(r){
			
			/*用户已经过期，重新登录*/
			if (r.Result == 'false'){
				userHome.ReLogin();
				return;
			}
		
            $('#userapps_list').html('');
            for (var i=0; i<r.userapps.length; i++){
                $('#userapps_list').append('<option value="'+r.userapps[i].ID+'" fixed="'+r.userapps[i].Fixed+'">'+r.userapps[i].AppName+'</option>');
            }
            $('#ctapps_list').html('');
            for (var i=0; i<r.ctapps.length; i++){
                $('#ctapps_list').append('<option value="'+r.ctapps[i].ID+'" fixed="'+r.ctapps[i].Fixed+'">'+r.ctapps[i].AppName+'</option>');
            }
			userHome.AppsPanel.Show();
        } );  
    },
	ReLogin:function(){
		alert('已经过期，请重新登录');
		window.location.href = '../../Logout.aspx';
	},
    /*更新修改服务的页面操作*/
    UpdateAppsList:function(active,passive){
        var _active = $$(active);
        var _passive = $$(passive);
        for(var i=0; i<_active.options.length; i++){
            if (_active.options[i].selected){
                var el = _active.options[i];
                if (el.getAttribute('fixed') == '0' && active=='userapps_list'){
                    var opt =  new Option(el.text,el.value);
                    opt.setAttribute('fixed',el.getAttribute('fixed'));
                    _passive.options.add(opt); 
                    _active.remove(i);
                    i = i-1; 
                }
                else if (active=='ctapps_list'){
                    if (passive=='userapps_list' && $$('userapps_list').options.length>=10){
                        alert('最多支持10个应用'); 
                        return;
                    }
                    var opt =  new Option(el.text,el.value);
                    opt.setAttribute('fixed',el.getAttribute('fixed'));
                    _passive.options.add(opt); 
                    _active.remove(i);
                    i = i-1; 
                }
               else{
                    alert(el.text+' 为必选应用，不可从菜单中移除:-)');
               }  
            }
        }
    },
    /*移动我的服务上下位置*/
    MoveApps:function(node1,node2){
        var nextSibling = node1.nextSibling;
        var parentNode = node1.parentNode;
        node2.parentNode.replaceChild(node1,node2);
        parentNode.insertBefore(node2,nextSibling);   
    },
    /*我的服务位置上移*/
    MoveUp:function(){
        var vInactive = $$("userapps_list");
        var vIndex = vInactive.selectedIndex;
        if (vIndex < 0){
            alert("请选择要移动的对象"); 
            return;
        } 
        if (vIndex == 0){
            //alert("已经是最顶端"); 
            return;
        } 
        this.MoveApps(vInactive.options[vIndex],vInactive.options[vIndex-1]);
    },
    /*我的服务位置下移*/
    MoveDown:function(){
        var vInactive = $$("userapps_list");
        var vIndex = vInactive.selectedIndex;
        if (vIndex < 0){
            alert("请选择要移动的对象"); 
            return;
        } 
        if (vIndex == vInactive.options.length-1){
            //alert("已经是最底端"); 
            return;
        } 
        this.MoveApps(vInactive.options[vIndex+1],vInactive.options[vIndex]);
    },
    ShowCustomToolTip:function(){
        //有待开发
    },
	/*打开关闭支付交易记录*/
	OpenClosePayPanel:function(){
		if ($('#pay_box').css('display') == 'block'){
			$('#pay_box').css('display','none');
		}
		else if ($('#pay_box').css('display') == 'none'){
			
			this.LoadPayList(1);
		}
	},
	/*获取交易总数*/
	GetPayCount:function(){
		var pageindex = 1;
		var pay_url = this.pay_list_url+'?page='+pageindex+'&pagesize=5&PUserInfo='+this.PUInfo+'&callback=?';
		//var pay_url = 'http://www.allokay.cn/1.aspx?callback=?';
		$.getJSON(pay_url,
			//{page:1,pagesize:5,PUserInfo:this.PUInfo},
			function(r){
				if (r.Result == 'true'){				
					$('#lblPayTotal').html('('+r.TotalNum+')');
				}
				else{
				    $('#lblPayTotal').html('(0)');
				}
			}
		);	
/*
		$.ajax({
			url:pay_url,
			dataType:'jsonp',
			timeout:2000,
			jsonnp:'callback',
			success:function(r){alert(r);
				if (r.Result == 'true'){
					$('#lblPayTotal').html('('+r.TotalNum+')');
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			}
		});*/
	},
	/*生成交易记录列表*/
	LoadPayList:function(pageindex){
		var pay_url = this.pay_list_url+'?page='+pageindex+'&pagesize=5&PUserInfo='+this.PUInfo+'&callback=?';
		$.getJSON(pay_url,
		//{page:pageindex,pagesize:5,PUserInfo:this.PUInfo},
		function(r){
			if (r.Result == 'true' && r.DataList.length > 0 && r.TotalNum > 0){
				$('#pay_box').css('display','block');
				
				$('#lblPayTotal').html('('+r.TotalNum+')');
				var strList = '';
				strList += '<tr>';
				strList += '<td width="25%" align="center" bgcolor="#E6EFF4"><strong>交易时间</strong></td>';
				strList += '<td width="60%" align="center" bgcolor="#E6EFF4"><strong>名称</strong></td>';
				strList += '<td width="15%" align="center" bgcolor="#E6EFF4"><strong>金额</strong></td>';
				strList += '</tr>';
				for(var i=0; i<r.DataList.length; i++){
					strList += '<tr>';
					strList += '<td align="center" bgcolor="#F9f9f9">'+r.DataList[i].DateTime+'</td>';
					strList += '<td align="center" bgcolor="#F9f9f9">'+r.DataList[i].Name+'</td>';
					strList += '<td align="center" bgcolor="#F9f9f9">'+r.DataList[i].Money+'</td>';
					strList += '</tr>';
				}
				$('#pay_list').html(strList);
				
				var strPager = '';
				var pageIndex = parseInt(r.PageIndex);
				var pageCount = parseInt(r.PageCount);
				var prePage = pageIndex-1;
				var nextPage = pageIndex+1;
				if (pageIndex == 1){
					if (pageCount == 1){
						strPager += '上一页 <strong>1/1</strong>页 下一页';
					}
					else{
						strPager += '上一页 <strong>1/'+pageCount+'</strong>页 <a href="javascript:void(0);" onclick="userHome.LoadPayList('+nextPage+');">下一页</a>';
					}
				}
				else if (pageIndex > 1 && pageIndex < pageCount){
					strPager += '<a href="javascript:void(0);" onclick="userHome.LoadPayList('+prePage+');">上一页</a> <strong>'+pageIndex+'/'+pageCount+'</strong>页 <a href="javascript:void(0);" onclick="userHome.LoadPayList('+nextPage+');">下一页</a>';
				}
				else if (pageIndex == pageCount && pageCount >1) {
					strPager += '<a href="javascript:void(0);" onclick="userHome.LoadPayList('+prePage+');">上一页</a> <strong>'+pageIndex+'/'+pageCount+'</strong>页 下一页';
				}
				$('#pay_pager').html(strPager);
			}
			else{
			    $('#pay_box').css('display','block');
			    $('#pay_list').html('<tr><td>您暂时还没有交易记录</td></tr>');
			    $('#pay_pager').html('');
				$('#lblPayTotal').html('(0)');
			}
		});
	}
}
/*new一个对象*/
var userHome = new UserHome();

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*宽带用户注册	selfs/reg/ADSL.aspx
*Author: stone
*Data: 2011/03/01
*BEGIN
*/

var ADSLReg = Class.create();
ADSLReg.prototype = {
    initialize:function(options){
        this.service_url = '';
        this.ProvincePanel = null;
        this.MaxTime = 61;
    },
    ShowProvincePanel:function(){
        this.ProvincePanel = new LightBox('divSCA',{Over:true,Opacity:65,Center:true,Fixed:true});
        this.ProvincePanel.Show(); 
    },
    CloseProvincePanel:function(){
        this.ProvincePanel.Close();
    },
    //选择省份后设置参数
    SetProvince:function(obj){
        $('#hdProvinceID').val($(obj.parentNode).attr('pid'));
        $('#hdProvincecode').val($(obj.parentNode).attr('pcode')); 
        $('#txtProvinceName').val($(obj.parentNode).attr('pname'));  
        this.ProvincePanel.Close();         
    },
    //初始化页面元素事件
	V_Account:function(tag){
		if ($.trim($('#txtAccount').val()) == ''){
			if (tag == 'f'){
				$('#alt_account span:only-child').attr('class','').text('请输入宽带(ADSL)账号'); 
			}
			else{
				$('#alt_account span:only-child').attr('class','redfont').text('请输入宽带(ADSL)账号'); 
			}
			return false;
		}
		else{
			$('#alt_account span:only-child').attr('class','').text(''); 
			return true;
		}
	},
	V_Password:function(tag){
		if ($.trim($('#txtAccountPwd').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwd span:only-child').attr('class','').text('请输入宽带(ADSL)密码'); 
			}
			else{
				$('#alt_pwd span:only-child').attr('class','redfont').text('请输入宽带(ADSL)密码'); 
			}
			return false;
		}
		else{
			$('#alt_pwd span:only-child').attr('class','').text(''); 
			return true;
		}
	},
	V_Code:function(tag){
		var code = $.trim($('#txtValidateCode').val()); 
		if (code == '输入验证码' || code == ''){
			if (tag == 'f'){
				$('#alt_VCode span:only-child').attr('class','').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else if (tag == 'v'){
				$('#alt_VCode span:only-child').attr('class','redfont').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else{
			    $('#alt_VCode span:only-child').attr('class','redfont');
			}
			return false;
		}
		else{
			$('#alt_VCode span:only-child').attr('class',''); 
			if ($('#alt_VCode span:only-child').html() == ''){
			    $('#alt_VCode span:only-child').html('看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>');
			}
			return true;
		}
	},
    InitADSL1Event:function(){        
        $('#txtAccount').bind({
            blur:function(){
                adslReg.V_Account('b');
            },
			focus:function(){
				adslReg.V_Account('f');
			}
        }); 
        $('#txtAccountPwd').bind({
            blur:function(){
                adslReg.V_Password('b');
            },
			focus:function(){
				adslReg.V_Password('f');
			}
        });
        $('#txtValidateCode').bind({
            blur:function(){
                adslReg.V_Code('b');
            },
			focus:function(){
				adslReg.V_Code('f');
			}
        }); 
		$('#adslForm').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnNext').click();
				}
			}
		});
    },
	RegGuideCheck:function(){
	    var provinceID = $('#hdProvinceID').val();
	    var accountNum = $('#txtAccount').val();
	    var result = false;
	    
	    //cellphone test
	    var cpRegEx = /^1[35]3\d{8}|18[901]\d{8}$/;
	    //telephone test
	    var tpRegEx = /^0\d{2,3}[1-9]\d{6,7}$/;
	    
	    if(cpRegEx.test(accountNum) || tpRegEx.test(accountNum)){
            result = true;
        }else{
            return false;
        }
        
        $.ajax({
            type: 'POST',
            url: this.service_url,
            data: {m:'regguide', pid:provinceID, num:accountNum},
            success: function(r){
                if(r.Result == 'TRUE'){
                    if(r.GuideFlag == '1'){
                        //alert('1')
                        $('#errDemo').css('display','block');
                        $('#lbl_Msg').html(r.Msg);
                    }else if(r.GuideFlag == '2'){
                        //alert('2')
                        $('#errDemo').css('display','block');
                        $('#lbl_Msg').html(r.Msg);
                    }
                    $('#txtAccountPwd').val('');
                    result = true;
                }else{
                    result = false;
                }
            },
            dataType: "json",
            async: false
        });
        
        return result;
	},
    //ADSL1页面提交以前的页面安全校验
    ValidateADSL1:function(){
        var res_account = true,res_pwd = true, res_vcode = true;
        res_account = this.V_Account('v');
		res_pwd = this.V_Password('v');
		res_vcode = this.V_Code('v');
        if (res_account == true && res_pwd == true && res_vcode == true){
			if (this.RegGuideCheck() == true) {
				return false;
			}
			return true;
        } 
        else {
            return false;
        } 
    },
    InitADSL1:function(){
        common.InitInputStyle();
        common.InitVCode('txtValidateCode'); 
        this.InitADSL1Event();
    },
	/* selfs/reg/ADSL2.aspx */
    //ADSL2页面提交以前的页面安全校验
    ValidateADSL2:function(){
        var r_pwd = true,r_pwdCfm=true, r_mail=true;
        r_pwd = adslReg.CheckAdsl2PWD(); 
		r_pwdCfm = adslReg.V_PasswordCfm_2();
        r_mail = adslReg.V_Email_2();
        if (r_pwd==true && r_pwdCfm==true && r_mail==true){
			return true;
		}
		else{
			return false;
		}
    },
    //校验密码是否正确 
    CheckAdsl2PWD:function(tag){
        var result = false;
        var pwd =  $('#txtPassword').val();
        if (pwd == ''){
			if (tag == 'f'){
				$('#alt_password span:only-child').attr('class','').text('请设置密码，6-16个字符，字母区分大小写。');				
			}
			else{
				$('#alt_password span:only-child').attr('class','redfont').text('请设置密码，6-16个字符，字母区分大小写。');	
				$('#alt_ico_pwd').attr('class','onBox onError');  
			}
            result = false; 
        }
        else if (pwd != '' && (pwd.length < 6 || pwd.length > 16)){
            $('#alt_ico_pwd').attr('class','onBox onError');   
            $('#alt_password span:only-child').attr('class','redfont').text('密码长度6-16位，请重新输入！');
            result = false;  
        } 
        else{
            $('#alt_ico_pwd').attr('class','onBox onCorrect');   
            $('#alt_password span:only-child').text('');
            result = true;  
        }
        return result; 
    },
    V_PasswordCfm_2:function(tag){
		if ($.trim($('#txtPasswordAgain').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwdagain span:only-child').attr('class','').text('再次输入密码'); 
			}
			else{
				$('#alt_ico_pwdagain').attr('class','onBox onError'); 
				$('#alt_pwdagain span:only-child').attr('class','redfont').text('再次输入密码'); 
			}
			return false;
		}
		else if ($('#txtPassword').val() != $('#txtPasswordAgain').val()){
			$('#alt_ico_pwdagain').attr('class','onBox onError'); 
			$('#alt_pwdagain span:only-child').attr('class','redfont').text('两次新密码输入不相同，请重新输入！'); 
			return false;
		}
		else{
			$('#alt_ico_pwdagain').attr('class','onBox onCorrect'); 
			$('#alt_pwdagain span:only-child').text(''); 
			return true;
		}
	},
	V_Email_2:function(tag){
		if ($.trim($('#txtEmail').val()) == ''){
			if (tag == 'f'){
				$('#alt_Email span:only-child').attr('class','').text('请输入邮箱，可凭此邮箱取回密码（选填）'); 
			}
			else{
				$('#alt_Email span:only-child').text(''); 
				$('#alt_ico_email').attr('class',''); 
			}
			return true;
		}
		else if (CheckEmail('txtEmail') == true){
			$('#alt_Email span:only-child').text('');
			if ($.trim($('#txtEmail').val()) != ''){ 
				$('#alt_ico_email').attr('class','onBox onCorrect'); 
			}
			else{
				 $('#alt_ico_email').attr('class',''); 
			}  
			return true;
		}
		else{
			$('#alt_Email span:only-child').attr('class','redfont').text('密码找回邮箱格式错误，请重新确认！');  
			$('#alt_ico_email').attr('class','onBox onError'); 
			return false;
		}
	},
    InitADSL2Event:function(){
        $('#txtPassword').bind({
            focus:function(){
                adslReg.CheckAdsl2PWD('f');
            },
            blur:function(){
                adslReg.CheckAdsl2PWD('b');
            } 
        });
        
        $('#txtPasswordAgain').bind({
            blur:function(){
                adslReg.V_PasswordCfm_2('b');
            },
			focus:function(){
				adslReg.V_PasswordCfm_2('f');
			}
        }); 
       
        $('#txtEmail').bind({
            blur:function(){
				adslReg.V_Email_2('b');
            },
			focus:function(){
				adslReg.V_Email_2('f');
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSave').click();
				}
			}
		});
    },
    //初始化ADSL2页面
    InitADSL2:function(){
        common.InitInputStyle();
        this.InitADSL2Event();
    },
    
    //宽带绑定手机 /selfs/reg/ADSL1.aspx
    V_BP_Phone:function(tag){
		if ($.trim($('#txtBoundPhone').val()) == ''){
			if (tag == 'f'){
				$('#alt_phone span:only-child').attr('class','').text('请输入绑定手机号码'); 
			}
			else{
				$('#alt_phone span:only-child').attr('class','redfont').text('请输入绑定手机号码'); 
			}
			return false;
		}
		else{
			$('#alt_phone span:only-child').attr('class','').text(''); 
			return true;
		}
	},
	V_BP_ValCode:function(tag){
		if ($.trim($('#txtMsgValCode').val()) == ''){
			if (tag == 'f'){
				$('#alt_ValCode span:only-child').attr('class','').text('请输入短信验证码'); 
			}
			else{
				$('#alt_ValCode span:only-child').attr('class','redfont').text('请输入短信验证码'); 
			}
			return false;
		}
		else{
			$('#alt_ValCode span:only-child').attr('class','').text(''); 
			return true;
		}
	},
    Init_BP_Event:function(){        
        $('#txtBoundPhone').bind({
            blur:function(){
                adslReg.V_BP_Phone('b');
            },
			focus:function(){
				adslReg.V_BP_Phone('f');
			}
        }); 
        $('#txtMsgValCode').bind({
            blur:function(){
                adslReg.V_BP_ValCode('b');
            },
			focus:function(){
				adslReg.V_BP_ValCode('f');
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnNext').click();
				}
			}
		});
		$('#btnSendPhoneVCode').bind({
		    click:function(){
		        adslReg.SendPhoneCode();
		    }
		});
    },
    Validate_BP:function(){
        var res_phone = true,res_vcode = true;
        res_phone = this.V_BP_Phone('v');
		res_vcode = this.V_BP_ValCode('v');
        if (res_phone == true && res_vcode == true){
            $('#txtBoundPhone').removeAttr('disabled');
            return true;
        } 
        else {
            return false;
        } 
    },
    Init_BP:function(){
        common.InitInputStyle();
        this.Init_BP_Event();
    },
    SendPhoneCode:function(){
        this.MaxTime = 61;
        var phone = $('#txtBoundPhone').val();
        $('#btnSendPhoneVCode').attr('disabled','disabled');
        $.post(this.service_url,{m:'svcode',q:phone},
        function(r){
            if (r.Result == 'TRUE'){
                $('#errDemo').css('display','block');
                $('#errDemo').attr('class','Correct');
                $('#lbl_Msg').html(r.Msg);
                
                $('#btnNext').removeAttr('disabled');
                $('#txtBoundPhone').attr('disabled','disabled');
                $('#btnSendPhoneVCode').attr('disabled','disabled');
                adslReg.ShowSendBtnLater();
                setInterval('adslReg.ShowSendBtnLater()',1000);
            }
            else{
                $('#errDemo').css('display','block');
                $('#errDemo').attr('class','error');
                $('#lbl_Msg').html(r.Msg);
                $('#btnNext').attr('disabled','disabled');
                $('#btnSendPhoneVCode').removeAttr('disabled');
            }
        },'json');
    },
    ShowSendBtnLater:function(){
        if (this.MaxTime > 1){
            this.MaxTime = this.MaxTime - 1;
            
            var showPadChar = '';
            if (this.MaxTime < 10){
                showPadChar = '0';
            }
            
            $('#btnSendPhoneVCode').val(showPadChar+this.MaxTime+'秒后可以重新发送');
        }
        else{
            $('#btnSendPhoneVCode').val('点此免费获取短信验证码');
            $('#btnSendPhoneVCode').removeAttr('disabled');
            $('#txtBoundPhone').removeAttr('disabled');
        }
    }
    
}
var adslReg = new ADSLReg();

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*固话注册	selfs/reg/Telephone.aspx
*Author: stone
*Data: 2011/03/04
*BEGIN
*/

var TelephoneReg = Class.create();
TelephoneReg.prototype = {
    initialize:function(options){
        
    },
	V_AreaID:function(tag){
		if ($.trim($('#txtAreaID').val()) == ''){
			if (tag == 'f'){
				$('#alt_phone span:only-child').text('请输入区号').attr('class','');
			}
			else{
				$('#alt_phone span:only-child').text('请输入区号').attr('class','redfont');
			}
			return false;
		}
		else{
			var _regxArea = /^\d{3,4}$/;
			if (_regxArea.test($.trim($('#txtAreaID').val()))){
				$('#alt_phone span:only-child').text('').attr('class','');
				return true;
			}
			else{
				$('#alt_phone span:only-child').attr('class','redfont').text('区号必须是3或4位的数字，请确认。');
				return false;
			}
		}
	},
	V_Phone:function(tag){
		if ($.trim($('#txtPhoneNum').val()) == '' ){
			if (tag == 'f'){
				$('#alt_phone span:only-child').attr('class','').text('请输入固话号码');
			}
			else{
				$('#alt_phone span:only-child').attr('class','redfont').text('请输入固话号码');
			}
			return false;
		}
		else{
			var _regx = /^\d{7,8}$/;
			if (_regx.test($.trim($('#txtPhoneNum').val()))){
				$('#alt_phone span:only-child').attr('class','').text('');
				return true;
			}
			else{
				$('#alt_phone span:only-child').attr('class','redfont').text('固话号码必须是7或8位的数字，请确认。');
				return false;
			}
		}
	},
	V_Code:function(tag){
		if ($.trim($('#ValidateCode').val()) == '' || $.trim($('#ValidateCode').val())=='输入验证码'){
			if (tag == 'f'){
				$('#alt_vcode span:only-child').attr('class','').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>');
			}
			else if (tag == 'v'){
				$('#alt_vcode span:only-child').attr('class','redfont').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>');
			}
			else{
			    $('#alt_vcode span:only-child').attr('class','redfont');
			}
			return false;
		}
		else{
			$('#alt_vcode span:only-child').attr('class','');
			if ($('#alt_vcode span:only-child').html() == ''){
			    $('#alt_vcode span:only-child').html('看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>');
			}
			return true;
		}
	},
    InitEvent1:function(){
        $('#txtAreaID').bind({
            blur:function(){
				objTelephone.V_AreaID('b');
            },
			focus:function(){
				objTelephone.V_AreaID('f');	
			}
        });
        $('#txtPhoneNum').bind({
            blur:function(){
				objTelephone.V_Phone('b');
            },
			focus:function(){
				objTelephone.V_Phone('f');
			}
        }); 
        $('#ValidateCode').bind({
            blur:function(){
                objTelephone.V_Code('b');
            },
			focus:function(){
                objTelephone.V_Code('f');
			}
        });  
		$('#telephoneForm').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnNext').click();
				}
			}
		});
    },
    Init1:function(){
        common.InitInputStyle();
        common.InitVCode('ValidateCode'); 
        this.InitEvent1();
         
    },
    Validate1:function(){
        var res_area=true,res_phone=true,res_code=true;	
		res_area = this.V_AreaID('v');
		res_phone = this.V_Phone('v');
		if ($.trim($('#txtPhoneNum').val()) == '' && $.trim($('#txtAreaID').val()) == ''){
            $('#alt_phone span:only-child').attr('class','redfont').text('请填写固定电话区号和号码');
            res_phone = false; 
        }
		res_code = this.V_Code('v');
        if (res_area == true && res_phone == true && res_code == true){
            return true;
        } 
        else {
            return false;
        } 
    },
	/* selfs/reg/Telephone2.aspx */
	V_ValCode_2:function(tag){
		if ($.trim($('#txtValCode').val()) == ''){
			if (tag == 'f'){
				$('#alt_vcode span:only-child').attr('class','').text('请输入固话收听到的验证码');
			}
			else{
				$('#alt_vcode span:only-child').attr('class','redfont').text('请输入固话收听到的验证码');
			}
			return false;
		}
		else{
			$('#alt_vcode span:only-child').attr('class','').text('');
			return true;
		}
	},
	V_Password_2:function(tag){
		var pwd =  $.trim($('#txtPassword').val());
		if (pwd == ''){
			if (tag == 'f'){
				$('#alt_pwd span:only-child').attr('class','').text('请设置密码，6-16个字符，字母区分大小写。');
			}
			else{
				$('#alt_pwd span:only-child').attr('class','redfont').text('请设置密码，6-16个字符，字母区分大小写。');
			}
			return false;
		}
		else if (pwd != '' && (pwd.length <6 || pwd.length >16)){
			$('#alt_pwd span:only-child').attr('class','redfont').text('密码长度6-16位，请重新输入！');
			return false;
		}
		else{
			$('#alt_pwd span:only-child').attr('class','').text('');
			return true;
		}
	},
	V_PasswordCfm_2:function(tag){
		if ($.trim($('#txtPasswordAgain').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwdagain span:only-child').attr('class','').text('再次输入密码');
			}
			else{
				$('#alt_pwdagain span:only-child').attr('class','redfont').text('再次输入密码');
			}
			return false;
		}
		else if ($.trim($('#txtPassword').val()) != $.trim($('#txtPasswordAgain').val())){
			$('#alt_pwdagain span:only-child').attr('class','redfont').text('两次新密码输入不相同，请重新输入！');
			return false;
		}
		else{
			$('#alt_pwdagain span:only-child').attr('class','');
			$('#alt_pwdagain span:only-child').text('');
			return true;
		}
	},
	InitEvent2:function(){
		$('#txtValCode').bind({
			blur:function(){
				objTelephone.V_ValCode_2('b');
			},
			focus:function(){
				objTelephone.V_ValCode_2('f');
			} 
		});
		$('#txtPassword').bind({
			blur:function(){
				objTelephone.V_Password_2('b');
			},
			focus:function(){
				objTelephone.V_Password_2('f');
			}  
		}); 
		$('#txtPasswordAgain').bind({
			blur:function(){
				objTelephone.V_PasswordCfm_2('b');
			},
			focus:function(){
				objTelephone.V_PasswordCfm_2('f');
			}  
		}); 
		$('#telephone2Form').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSave').click();
					return false;
				}
				else{
				    return true;
				}
			}
		});		
		
	},
	Validate2:function(){
		var res_vcode=true,res_pwd=true,res_pwdagain=true;
		
		res_vcode = this.V_ValCode_2('v');
		res_pwd = this.V_Password_2('v');
		res_pwdagain = this.V_PasswordCfm_2('v');

		if (res_vcode == true && res_pwd == true && res_pwdagain == true){
			return true;
		} 
		else {
			return false;
		} 
	},
	Init2:function(){
		common.InitInputStyle();
		this.InitEvent2();
	}
    
    
}
var objTelephone = new TelephoneReg();

/*END*/
/************************************************************************************/



/************************************************************************************/
/*
*绑定邮箱	/SelfS/Home/Account/SecurityEmail.aspx
*Author: stone
*Data: 2011/03/07
*BEGIN
*/
var BindEmail = Class.create();
BindEmail.prototype = {
    initialize:function(options){
        this.ProvincePanel = null;
    },
    ShowProvincePanel:function(){
        this.ProvincePanel = new LightBox('divSCA',{Over:true,Opacity:65,Center:true,Fixed:true});
        this.ProvincePanel.Show(); 
    },
    CloseProvincePanel:function(){
        this.ProvincePanel.Close();
    },
    //选择省份后设置参数
    SetProvince:function(obj){
        $('#hdProvinceID').val($(obj.parentNode).attr('pid'));
        $('#hdProvincecode').val($(obj.parentNode).attr('pcode')); 
        $('#txtProvinceName').val($(obj.parentNode).attr('pname'));  
        this.ProvincePanel.Close();         
    }
}
var ObjBindEmail = new BindEmail();

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*省份弹出框
*Author: stone
*Data: 2011/03/08
*使用方法：
* 1、拷贝页面HTML到你所需要的页面；2、读取provinceid从hdProvinceID读取,Provincecode对应省份代码,txtProvinceName用于显示省份名称
*BEGIN
*/
var ProvincePopup = Class.create();
ProvincePopup.prototype = {
    initialize:function(options){
        this.ProvincePanel = null;
    },
    //参数是弹出框的ID
    ShowProvincePanel:function(panelID){
        this.ProvincePanel = new LightBox(panelID,{Over:true,Opacity:65,Center:true,Fixed:true});
        this.ProvincePanel.Show(); 
    },
    CloseProvincePanel:function(){
        this.ProvincePanel.Close();
    },
    //选择省份后设置参数,obj,provinceID,provinceName必传
    SetProvince:function(obj,provinceID,provinceName,provinceCode){
        provinceID = '#'+provinceID;
        provinceName = '#' + provinceName;
        provinceCode = '#' + provinceCode;
        if ($(provinceID).length > 0 && $(obj.parentNode).attr('pid')){
            $(provinceID).val($(obj.parentNode).attr('pid'));
        } 
        if ($(provinceName).length > 0 && $(obj.parentNode).attr('pname')){ 
            $(provinceName).val($(obj.parentNode).attr('pname'));  
        } 
        if ($(provinceCode).length > 0 && $(obj.parentNode).attr('pcode')){ 
            $(provinceCode).val($(obj.parentNode).attr('pcode'));  
        }  
        this.ProvincePanel.Close();         
    }
}
var objProvincePopup = new ProvincePopup();

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*找回用户名
*url: /selfs/find/GetName.aspx
*Author: stone
*Data: 2011/03/08
*BEGIN
*/
var FindUserName = Class.create();
FindUserName.prototype = {
    initialize:function(options){
        
    },
    //初始化页面元素事件	V_Account:function(tag){
		if ($.trim($('#txtUserAccount').val()) == ''){
			if (tag == 'f'){
				$('#alt_account span:only-child').attr('class','').text('请输入宽带(ADSL)账号'); 
			}
			else{
				$('#alt_account span:only-child').attr('class','redfont').text('请输入宽带(ADSL)账号'); 
			}
			return false;
		}
		else{
			$('#alt_account span:only-child').text(''); 
			return true;
		}
	},
	V_Password:function(tag){
		if ($.trim($('#txtPassword').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwd span:only-child').attr('class','').text('请输入宽带(ADSL)密码'); 
			}
			else{
				$('#alt_pwd span:only-child').attr('class','redfont').text('请输入宽带(ADSL)密码'); 
			}
			return false;
		}
		else{
			$('#alt_pwd span:only-child').text(''); 
			return true;
		}
	},
	V_Code:function(tag){
		var code = $.trim($('#txtValidateCode').val()); 
		if (code == '输入验证码' || code == ''){
			if (tag == 'f'){
				$('#alt_VCode span:only-child').attr('class','').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else if (tag == 'v'){
				$('#alt_VCode span:only-child').attr('class','redfont').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else{
			    $('#alt_VCode span:only-child').attr('class','redfont');
			}
			return false;
		}
		else{
			$('#alt_VCode span:only-child').attr('class','');
			if ($('#alt_VCode span:only-child').html() == ''){
			    $('#alt_VCode span:only-child').html('看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			return true;
		}
	},
    InitEvent:function(){        
        $('#txtUserAccount').bind({
            blur:function(){
                objFindName.V_Account('b');
            },
			focus:function(){
				objFindName.V_Account('f');
			}
        }); 
        $('#txtPassword').bind({
            blur:function(){
                objFindName.V_Password('b');
            },
			focus:function(){
				objFindName.V_Password('f');
			}
        });
        $('#txtValidateCode').bind({
            blur:function(){
                objFindName.V_Code('b');
            },
			focus:function(){
				objFindName.V_Code('f');
			}
        }); 
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnFindName').click();
				}
			}
		});
    },
    //页面提交以前的页面安全校验
    Validate:function(){
        var res_account = true,res_pwd = true, res_vcode = true;
        res_account = this.V_Account('v');
		res_pwd = this.V_Password('v');
		res_vcode = this.V_Code('v');
        if (res_account == true && res_pwd == true && res_vcode == true){
            return true;
        } 
        else {
            return false;
        } 
    },
    Init:function(){
        common.InitInputStyle();
        common.InitVCode('txtValidateCode'); 
        this.InitEvent();
    }
}
var objFindName = new FindUserName();

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*宽带用户找回密码
*url: /SelfS/Find/GetPassADSL.aspx , GetPassSet.aspx ,GetPassADSL2.aspx
*Author: stone
*Data: 2011/03/08
*BEGIN
*/
var ADSLFindPWD = Class.create();
ADSLFindPWD.prototype = {
    initialize:function(options){
        this.MaxTime = 61;
    },
    //初始化页面元素事件
	V_Account:function(tag){
		if ($.trim($('#txtUserAccount').val()) == ''){
			if (tag == 'f'){
				$('#alt_account span:only-child').attr('class','').text('请输入宽带(ADSL)账号'); 
			}
			else{
				$('#alt_account span:only-child').attr('class','redfont').text('请输入宽带(ADSL)账号'); 
			}
			return false;
		}
		else{
			$('#alt_account span:only-child').text(''); 
			return true;
		}
	},
	V_Password:function(tag){
		if ($.trim($('#txtPassword').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwd span:only-child').attr('class','').text('请输入宽带(ADSL)密码'); 
			}
			else{
				$('#alt_pwd span:only-child').attr('class','redfont').text('请输入宽带(ADSL)密码'); 
			}
			return false;
		}
		else{
			$('#alt_pwd span:only-child').text(''); 
			return true;
		}
	},
	V_Code:function(tag){
		var code = $.trim($('#txtValidateCode').val()); 
		if (code == '输入验证码' || code == ''){
			if (tag == 'f'){
				$('#alt_VCode span:only-child').attr('class','').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else if (tag == 'v'){
				$('#alt_VCode span:only-child').attr('class','redfont').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else{
			    $('#alt_VCode span:only-child').attr('class','');
			}
			return false;			
		}
		else{
		    $('#alt_VCode span:only-child').attr('class','');
		    if ($('#alt_VCode span:only-child').html() == ''){
		        $('#alt_VCode span:only-child').html('看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
		    }			
			return true;
		}
	},
    InitEvent:function(){        
        $('#txtUserAccount').bind({
            blur:function(){
                objADSLFindPWD.V_Account('b');
            },
			focus:function(){
				objADSLFindPWD.V_Account('f');
			}
        }); 
        $('#txtPassword').bind({
            blur:function(){
                objADSLFindPWD.V_Password('b');
            },
			focus:function(){
				objADSLFindPWD.V_Password('f');
			}
        });
        $('#txtValidateCode').bind({
            blur:function(){
				objADSLFindPWD.V_Code('b');
            },
			focus:function(){
				objADSLFindPWD.V_Code('f');
			}
        }); 
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnNext').click();
				}
			}
		});
    },
    //页面提交以前的页面安全校验
    Validate:function(){
        var res_account = true,res_pwd = true, res_vcode = true;
        res_account = this.V_Account();
		res_pwd = this.V_Password();
		res_vcode = this.V_Code();
        if (res_account == true && res_pwd == true && res_vcode == true){
            return true;
        } 
        else {
            return false;
        } 
    },
    Init:function(){
        common.InitInputStyle();
        common.InitVCode('txtValidateCode'); 
        this.InitEvent();
    },
	/* /SelfS/Find/GetPassSet.aspx */
    //2页面提交以前的页面安全校验
    Validate2:function(){
        var r_pwd = true,r_pwdCfm=true;
        r_pwd = objADSLFindPWD.CheckAdsl2PWD(); 
        r_pwdCfm = objADSLFindPWD.V_PasswordCfm_2(); 
		if (r_pwd==true && r_pwdCfm==true){
			return true;
		}
		else{
			return false;
		}
    },
    //校验密码是否正确 
    CheckAdsl2PWD:function(tag){
        var result = false;
        var pwd =  $('#txtNewPwd').val();
        if (pwd == ''){
            if (tag == 'f'){
				$('#alt_password span:only-child').attr('class','').text('请设置密码，6-16个字符，字母区分大小写。');
			}
			else{
				$('#alt_password span:only-child').attr('class','redfont').text('请设置密码，6-16个字符，字母区分大小写。');
			}
            result = false; 
        }
        else if (pwd != '' && (pwd.length < 6 || pwd.length > 16)){
            $('#alt_password span:only-child').attr('class','redfont').text('密码长度6-16位，请重新输入！');
            result = false;  
        } 
        else{
            $('#alt_password span:only-child').text('');
            result = true;  
        }
        return result; 
    },
	V_PasswordCfm_2:function(tag){
		if ($.trim($('#txtNewPwdCfm').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwdagain span:only-child').attr('class','').text('再次输入密码'); 
			}
			else{
				$('#alt_pwdagain span:only-child').attr('class','redfont').text('再次输入密码'); 
			}
			return false;
		}
		else if ($.trim($('#txtNewPwd').val()) != $.trim($('#txtNewPwdCfm').val())){
			$('#alt_pwdagain span:only-child').attr('class','redfont').text('输入密码不一致，请重新输入'); 
			return false;
		}
		else{
			$('#alt_pwdagain span:only-child').text(''); 
			return true;
		}
	},
    //初始化页面元素事件
    Init2Event:function(){
        $('#txtNewPwd').bind({
            focus:function(){
                objADSLFindPWD.CheckAdsl2PWD('f');
            },
            blur:function(){
                objADSLFindPWD.CheckAdsl2PWD('b');
            } 
        });
        $('#txtNewPwdCfm').bind({
            blur:function(){
				objADSLFindPWD.V_PasswordCfm_2('b');
            },
			focus:function(){
				objADSLFindPWD.V_PasswordCfm_2('f');
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnReset').click();
				}
			}
		});
    },
    //初始化2页面
    Init2:function(){
        common.InitInputStyle();
        this.Init2Event();
    },
	/* /SelfS/Find/GetPassADSL2.aspx */
	//初始化页面元素事件
	V_Name_Card:function(tag){
		if ($.trim($('#txtRealName').val()) == ''){
			if (tag == 'f'){
				$('#alt_name span:only-child').attr('class','').text('请输入真实姓名'); 
			}
			else{
				$('#alt_name span:only-child').attr('class','redfont').text('请输入真实姓名'); 
			}
			return false;
		}
		else{
			$('#alt_name span:only-child').text(''); 
			return true;
		}
	},
	V_ID_Card:function(tag){
		if ($.trim($('#txtIDCard').val()) == ''){
			if (tag == 'f'){
				$('#alt_id span:only-child').attr('class','').text('请输入身份证号'); 
			}
			else{
				$('#alt_id span:only-child').attr('class','redfont').text('请输入身份证号'); 
			}
			return false;
		}
		else{
			$('#alt_id span:only-child').text(''); 
			return true;
		}
	},
    InitEventIDCard:function(){        
        $('#txtRealName').bind({
            blur:function(){
				objADSLFindPWD.V_Name_Card('b');
            },
			focus:function(){
				objADSLFindPWD.V_Name_Card('f');
			}
        }); 
        $('#txtIDCard').bind({
            blur:function(){
				objADSLFindPWD.V_ID_Card('b');
            },
			focus:function(){
				objADSLFindPWD.V_ID_Card('f');
			}			
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnNext').click();
				}
			}
		});
    },
    //页面提交以前的页面安全校验
    ValidateIDCard:function(){
        var res_name = true,res_cid = true;
        res_name = this.V_Name_Card();
		res_cid = this.V_ID_Card();
        if (res_name == true && res_cid == true){
            return true;
        } 
        else {
            return false;
        } 
    },
    InitIDCard:function(){
        common.InitInputStyle();
        this.InitEventIDCard();
    },
    //宽带验证绑定手机 /SelfS/find/GetPassADSL1.aspx
	V_BP_ValCode:function(tag){
		if ($.trim($('#txtMsgValCode').val()) == ''){
			if (tag == 'f'){
				$('#alt_ValCode span:only-child').attr('class','').text('请输入短信验证码'); 
			}
			else{
				$('#alt_ValCode span:only-child').attr('class','redfont').text('请输入短信验证码'); 
			}
			return false;
		}
		else{
			$('#alt_ValCode span:only-child').attr('class','').text(''); 
			return true;
		}
	},
    Init_BP_Event:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
                adslReg.V_BP_ValCode('b');
            },
			focus:function(){
				adslReg.V_BP_ValCode('f');
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnNext').click();
				}
			}
		});
		$('#btnSendPhoneVCode').bind({
		    click:function(){
		        objADSLFindPWD.SendPhoneCode();
		    }
		});
    },
    Validate_BP:function(){
        var res_vcode = true;
		res_vcode = this.V_BP_ValCode('v');
        if (res_vcode == true){
            return true;
        } 
        else {
            return false;
        } 
    },
    Init_BP:function(){
        common.InitInputStyle();
        this.Init_BP_Event();
    },
    SendPhoneCode:function(){
        this.MaxTime = 61;
        $('#btnSendPhoneVCode').attr('disabled','disabled');
        $.ajax({
            url:this.service_url,
            type:'POST',
            dataType:'json',
            data:{m:'svcode_get',random:Math.random},
            success:function(r){
                if (r.Result == 'TRUE'){
                    $('#errDemo').css('display','block');
                    $('#errDemo').attr('class','Correct');
                    $('#lbl_Msg').html(r.Msg);
                    
                    $('#btnNext').removeAttr('disabled');
                    $('#btnSendPhoneVCode').attr('disabled','disabled');
                    objADSLFindPWD.ShowSendBtnLater();
                    setInterval('objADSLFindPWD.ShowSendBtnLater()',1000);
                }
                else{
                    $('#errDemo').css('display','block');
                    $('#errDemo').attr('class','error');
                    $('#lbl_Msg').html(r.Msg);
                    $('#btnNext').attr('disabled','disabled');
                    $('#btnSendPhoneVCode').removeAttr('disabled');
                }
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
               ///TODO 
               alert("系统忙，请稍后再试");
            }
        });
    },
    ShowSendBtnLater:function(){
        if (this.MaxTime > 1){
            this.MaxTime = this.MaxTime - 1;
            
            var showPadChar = '';
            if (this.MaxTime < 10){
                showPadChar = '0';
            }
            
            $('#btnSendPhoneVCode').val(showPadChar+this.MaxTime+'秒后可以重新发送');
        }
        else{
            $('#btnSendPhoneVCode').val('点此免费获取短信验证码');
            $('#btnSendPhoneVCode').removeAttr('disabled');
        }
    }
}
var objADSLFindPWD = new ADSLFindPWD();

/*END*/
/************************************************************************************/



/************************************************************************************/
/*
*手机注册
*url: /selfs/reg/Cellphone.aspx
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var CellphoneReg = Class.create();
CellphoneReg.prototype = {
    initialize:function(options){
        
    },
    Validate:function(){
        var res_phone=true,res_vcode=true;
        if ($.trim($('#txtPhoneNum').val()) == ''){
            $('#alt_phone span:only-child').text('请输入中国电信手机号码').attr('class','redfont'); 
            res_phone = false;
        }
        if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
            $('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class','redfont'); 
            res_vcode = false;
        }
        if (res_phone==true && res_vcode==true){
            return true;
        }
        else{
            return false;
        }
    },
    InitEvent:function(){
        $('#txtPhoneNum').bind({
            blur:function(){
                if ($.trim($('#txtPhoneNum').val()) == ''){
                    $('#alt_phone span:only-child').text('请输入中国电信手机号码').attr('class','redfont'); 
                }
                else{
                    $('#alt_phone span:only-child').text(''); 
                }
            },
			focus:function(){
				if ($.trim($('#txtPhoneNum').val()) == ''){
					$('#alt_phone span:only-child').text('请输入中国电信手机号码').attr('class',''); 
				}
			}
        });
        $('#txtValidateCode').bind({
            blur:function(){
                if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
                    //$('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class','redfont'); 
                    $('#alt_vcode span:only-child').attr('class','redfont'); 
                }
                else{
                    $('#alt_vcode span:only-child').html(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
                    $('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class',''); 
                }
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSubmit').click();
				}
			}
		});
        
    },
    Init:function(){
        common.InitInputStyle();
        common.InitVCode('txtValidateCode'); 
        this.InitEvent();
    },
	/* selfs/reg/Cellphone2.aspx */
    Validate2:function(){
        var res_mcode=true,res_pwd=true;res_pwdagain=true;
		var _pwd = $.trim($('#txtPassword').val());
        if ($.trim($('#txtMsgValCode').val()) == ''){
            $('#alt_mcode span:only-child').text('请输入手机获取到的验证码'); 
            res_phone = false;
        }
        if (_pwd == ''){
            $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。'); 
            res_pwd = false;
        }
		if (_pwd.length<6 || _pwd.length>16){
            $('#alt_pwd span:only-child').text('密码为6-16个字符，字母区分大小写。'); 
            res_pwd = false;
		}
        if ($.trim($('#txtPasswordCfm').val()) != $.trim($('#txtPassword').val())){
            $('#alt_pwdagain span:only-child').text('两次新密码输入不相同，请重新输入！'); 
            res_pwdagain = false;
        }
        if (res_mcode==true && res_pwd==true && res_pwdagain==true){
            return true;
        }
        else{
            return false;
        }
    },
    InitEvent2:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
                if ($.trim($('#txtMsgValCode').val()) == ''){
                    $('#alt_mcode span:only-child').text('请输入手机获取到的验证码').attr('class','redfont'); 
                }
                else{
                    $('#alt_mcode span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtMsgValCode').val()) == ''){
                    $('#alt_mcode span:only-child').text('请输入手机获取到的验证码').attr('class',''); 
                }
			}
        });
        $('#txtPassword').bind({
            blur:function(){
				var _pwd = $.trim($('#txtPassword').val());
                if (_pwd == ''){
                    $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。').attr('class','redfont'); 
                }
				else if (_pwd.length<6 || _pwd.length>16){
					$('#alt_pwd span:only-child').text('密码为6-16个字符，字母区分大小写。').attr('class','redfont'); 
				}
                else{
                    $('#alt_pwd span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtPassword').val()) == ''){
                    $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。').attr('class',''); 
                }
			}
        });
        $('#txtPasswordCfm').bind({
            blur:function(){
                if ($.trim($('#txtPasswordCfm').val()) != $.trim($('#txtPassword').val())){
                    $('#alt_pwdagain span:only-child').text('两次新密码输入不相同，请重新输入！').attr('class','redfont'); 
                }
                else{
                    $('#alt_pwdagain span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtPasswordCfm').val()) == ''){
                    $('#alt_pwdagain span:only-child').text('再次输入密码').attr('class',''); 
                }
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSumbit').click();
				}
			}
		});
    },
    Init2:function(){
        common.InitInputStyle();
        this.InitEvent2();
    }
    
}
var objCellphoneReg = new CellphoneReg();

/*END*/
/************************************************************************************/



/************************************************************************************/
/*
*非电信手机注册
*url: /selfs/reg/OCellphone.aspx
*Author: stone,xulong
*Data: 2011/03/09,2011-07-01
*BEGIN
*/
var OCellphoneReg = Class.create();
OCellphoneReg.prototype = {
    initialize:function(options){
        
    },
    Validate:function(){
        var res_phone=true,res_vcode=true;
        if ($.trim($('#txtPhoneNum').val()) == ''){
            $('#alt_phone span:only-child').text('请输入（中国移动或中国联通）手机号码').attr('class','redfont'); 
            res_phone = false;
        }
        if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
            $('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class','redfont'); 
            res_vcode = false;
        }
        if (res_phone==true && res_vcode==true){
            return true;
        }
        else{
            return false;
        }
    },
    InitEvent:function(){
        $('#txtPhoneNum').bind({
            blur:function(){
                if ($.trim($('#txtPhoneNum').val()) == ''){
                    $('#alt_phone span:only-child').text('请输入（中国移动或中国联通）手机号码').attr('class','redfont'); 
                }
                else{
                    $('#alt_phone span:only-child').text(''); 
                }
            },
			focus:function(){
				if ($.trim($('#txtPhoneNum').val()) == ''){
					$('#alt_phone span:only-child').text('请输入（中国移动或中国联通）手机号码').attr('class',''); 
				}
			}
        });
        $('#txtValidateCode').bind({
            blur:function(){
                if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
                    //$('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class','redfont'); 
                    $('#alt_vcode span:only-child').attr('class','redfont'); 
                }
                else{
                    $('#alt_vcode span:only-child').html(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
                    $('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class',''); 
                }
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSubmit').click();
				}
			}
		});
        
    },
    Init:function(){
        common.InitInputStyle();
        common.InitVCode('txtValidateCode'); 
        this.InitEvent();
    },
	/* selfs/reg/OCellphone2.aspx */
    Validate2:function(){
        var res_mcode=true,res_pwd=true;res_pwdagain=true;
		var _pwd = $.trim($('#txtPassword').val());
        if ($.trim($('#txtMsgValCode').val()) == ''){
            $('#alt_mcode span:only-child').text('请输入手机获取到的验证码'); 
            res_phone = false;
        }
        if (_pwd == ''){
            $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。'); 
            res_pwd = false;
        }
		if (_pwd.length<6 || _pwd.length>16){
            $('#alt_pwd span:only-child').text('密码为6-16个字符，字母区分大小写。'); 
            res_pwd = false;
		}
        if ($.trim($('#txtPasswordCfm').val()) != $.trim($('#txtPassword').val())){
            $('#alt_pwdagain span:only-child').text('两次新密码输入不相同，请重新输入！'); 
            res_pwdagain = false;
        }
        if (res_mcode==true && res_pwd==true && res_pwdagain==true){
            return true;
        }
        else{
            return false;
        }
    },
    InitEvent2:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
                if ($.trim($('#txtMsgValCode').val()) == ''){
                    $('#alt_mcode span:only-child').text('请输入手机获取到的验证码').attr('class','redfont'); 
                }
                else{
                    $('#alt_mcode span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtMsgValCode').val()) == ''){
                    $('#alt_mcode span:only-child').text('请输入手机获取到的验证码').attr('class',''); 
                }
			}
        });
        $('#txtPassword').bind({
            blur:function(){
				var _pwd = $.trim($('#txtPassword').val());
                if (_pwd == ''){
                    $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。').attr('class','redfont'); 
                }
				else if (_pwd.length<6 || _pwd.length>16){
					$('#alt_pwd span:only-child').text('密码为6-16个字符，字母区分大小写。').attr('class','redfont'); 
				}
                else{
                    $('#alt_pwd span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtPassword').val()) == ''){
                    $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。').attr('class',''); 
                }
			}
        });
        $('#txtPasswordCfm').bind({
            blur:function(){
                if ($.trim($('#txtPasswordCfm').val()) != $.trim($('#txtPassword').val())){
                    $('#alt_pwdagain span:only-child').text('两次新密码输入不相同，请重新输入！').attr('class','redfont'); 
                }
                else{
                    $('#alt_pwdagain span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtPasswordCfm').val()) == ''){
                    $('#alt_pwdagain span:only-child').text('再次输入密码').attr('class',''); 
                }
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSumbit').click();
				}
			}
		});
    },
    Init2:function(){
        common.InitInputStyle();
        this.InitEvent2();
    }
    
}
var objOCellphoneReg = new OCellphoneReg();

/*END*/
/************************************************************************************/



/************************************************************************************/
/*
*海外手机注册
*url: /selfs/reg/overseas/Cellphone.aspx
*Author: xulong
*Data: 2012-10-10
*BEGIN
*/
var OSCellphoneReg = Class.create();
OSCellphoneReg.prototype = {
    initialize:function(options){
        
    },
    Select:function(){
        //英国用户需在手机号码前加国际字冠和英国国家码0044
        var ddl_text = '',ddl_value = '';
        var ddl_value_arr = new Array();
        ddl_text = $.trim($('#ddlDeviceNo option:selected').text());
        ddl_value = $.trim($('#ddlDeviceNo').val());
        //DeviceNo|PrefixCode|CountryCode|PhoneRegular
        ddl_value_arr = ddl_value.split("|");
        
        return ddl_text + "用户需在手机号码前加国际字冠和" + ddl_text + "国家码" + ddl_value_arr[1]+ddl_value_arr[2];
    },
    Validate:function(){
        var res_phone=true,res_vcode=true;
        if ($.trim($('#txtMDN').val()) == ''){
            $('#alt_mdn span:only-child').text(objOSCellphoneReg.Select()).attr('class','redfont'); 
            res_phone = false;
        }
        if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
            $('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class','redfont'); 
            res_vcode = false;
        }
        if (res_phone==true && res_vcode==true){
            return true;
        }
        else{
            return false;
        }
    },
    InitEvent:function(){
        $('#txtMDN').bind({
            blur:function(){
                if ($.trim($('#txtMDN').val()) == ''){
                    $('#alt_mdn span:only-child').text(objOSCellphoneReg.Select()).attr('class','redfont'); 
                }
                else{
                    $('#alt_mdn span:only-child').text(''); 
                }
            },
			focus:function(){
				if ($.trim($('#txtMDN').val()) == ''){
					$('#alt_mdn span:only-child').text(objOSCellphoneReg.Select()).attr('class',''); 
				}
			}
        });
        $('#txtValidateCode').bind({
            blur:function(){
                if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
                    //$('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class','redfont'); 
                    $('#alt_vcode span:only-child').attr('class','redfont'); 
                }
                else{
                    $('#alt_vcode span:only-child').html(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
                    $('#alt_vcode span:only-child').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>').attr('class',''); 
                }
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSubmit').click();
				}
			}
		});
        
    },
    Init:function(){
        common.InitInputStyle();
        common.InitVCode('txtValidateCode'); 
        this.InitEvent();
    },
	/* selfs/reg/overseas/Cellphone2.aspx */
    Validate2:function(){
        var res_mcode=true,res_pwd=true;res_pwdagain=true;
		var _pwd = $.trim($('#txtPassword').val());
        if ($.trim($('#txtMsgValCode').val()) == ''){
            $('#alt_mcode span:only-child').text('请输入手机获取到的验证码'); 
            res_phone = false;
        }
        if (_pwd == ''){
            $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。'); 
            res_pwd = false;
        }
		if (_pwd.length<6 || _pwd.length>16){
            $('#alt_pwd span:only-child').text('密码为6-16个字符，字母区分大小写。'); 
            res_pwd = false;
		}
        if ($.trim($('#txtPasswordCfm').val()) != $.trim($('#txtPassword').val())){
            $('#alt_pwdagain span:only-child').text('两次新密码输入不相同，请重新输入！'); 
            res_pwdagain = false;
        }
        if (res_mcode==true && res_pwd==true && res_pwdagain==true){
            return true;
        }
        else{
            return false;
        }
    },
    InitEvent2:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
                if ($.trim($('#txtMsgValCode').val()) == ''){
                    $('#alt_mcode span:only-child').text('请输入手机获取到的验证码').attr('class','redfont'); 
                }
                else{
                    $('#alt_mcode span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtMsgValCode').val()) == ''){
                    $('#alt_mcode span:only-child').text('请输入手机获取到的验证码').attr('class',''); 
                }
			}
        });
        $('#txtPassword').bind({
            blur:function(){
				var _pwd = $.trim($('#txtPassword').val());
                if (_pwd == ''){
                    $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。').attr('class','redfont'); 
                }
				else if (_pwd.length<6 || _pwd.length>16){
					$('#alt_pwd span:only-child').text('密码为6-16个字符，字母区分大小写。').attr('class','redfont'); 
				}
                else{
                    $('#alt_pwd span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtPassword').val()) == ''){
                    $('#alt_pwd span:only-child').text('请设置密码，6-16个字符，字母区分大小写。').attr('class',''); 
                }
			}
        });
        $('#txtPasswordCfm').bind({
            blur:function(){
                if ($.trim($('#txtPasswordCfm').val()) != $.trim($('#txtPassword').val())){
                    $('#alt_pwdagain span:only-child').text('两次新密码输入不相同，请重新输入！').attr('class','redfont'); 
                }
                else{
                    $('#alt_pwdagain span:only-child').text(''); 
                }
            },
			focus:function(){
                if ($.trim($('#txtPasswordCfm').val()) == ''){
                    $('#alt_pwdagain span:only-child').text('再次输入密码').attr('class',''); 
                }
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnSumbit').click();
				}
			}
		});
    },
    Init2:function(){
        common.InitInputStyle();
        this.InitEvent2();
    }
    
}
var objOSCellphoneReg = new OSCellphoneReg();

/*END*/
/************************************************************************************/



/************************************************************************************/
/*
*找回密码
*url: /selfs/find/
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var FindPassword = Class.create();
FindPassword.prototype = {
    initialize:function(options){
        
    },
	/*账号找回密码 /selfs/find/default.aspx */
	Validate:function(){
        var res_uid=true,res_vcode=true;
        res_uid = this.V_UserID('v');
		res_vcode = this.V_Code('v');
        if (res_uid==true && res_vcode==true){
            return true;
        }
        else{
            return false;
        }
    },
	V_UserID:function(tag){
		var _uid = $.trim($('#txtUserID').val());
		if (_uid == ''){
			if (tag == 'f'){
				$('#alt_uid span:only-child').attr('class','').text('请输入用户名'); 
			}
			else{
				$('#alt_uid span:only-child').attr('class','redfont').text('用户名不能为空！'); 
			}
			return false;			
		}
		else{
		//取消用户名长度限制
//			if (_uid.length < 5 || _uid.length > 15){
//				$('#alt_uid span:only-child').attr('class','redfont').text('用户名是5位到15位');
//				return false;
//			}
//			else{
				$('#alt_uid span:only-child').attr('class','').text('');
				return true;
//			}
		}
	},
	V_Code:function(tag){
		if ($.trim($('#txtValidateCode').val()) == '' || $.trim($('#txtValidateCode').val()) == '输入验证码'){
			if (tag == 'f'){
				$('#alt_vcode span:only-child').attr('class','').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else if (tag == 'v'){
				$('#alt_vcode span:only-child').attr('class','redfont').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			else{
			    $('#alt_vcode span:only-child').attr('class','redfont');
			}
			return false;
		}
		else{
			$('#alt_vcode span:only-child').attr('class',''); 
			if ($('#alt_vcode span:only-child').html() == ''){
			    $('#alt_vcode span:only-child').html('看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>'); 
			}
			return true;
		}
	},
    InitEvent:function(){
        $('#txtUserID').bind({
            blur:function(){
                objFindPassword.V_UserID('b');
            },
			focus:function(){
				objFindPassword.V_UserID('f');
			}
        });
        $('#txtValidateCode').bind({
            blur:function(){
                objFindPassword.V_Code('b');
            },
			focus:function(){
				objFindPassword.V_Code('f');
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnNext').click();
				}
			}
		});
        
    },
    Init:function(){
        common.InitInputStyle();
        common.InitVCode('txtValidateCode'); 
        this.InitEvent();
    },
	/*selfs/find/GetPassCellphone.aspx*/
	ValidateCellPhone:function(){
        var res_mcode=true,res_pwd=true;res_pwdagain=true;
        res_mcode = this.V_ValCode_CellPhone();
		res_pwd = this.V_Password_CellPhone();
		res_pwdagain = this.V_PasswordCfm_CellPhone();
        if (res_mcode==true && res_pwd==true && res_pwdagain==true){
            return true;
        }
        else{
            return false;
        }
    },
	V_ValCode_CellPhone:function(tag){
		if ($.trim($('#txtMsgValCode').val()) == ''){
			if (tag == 'f'){
				$('#alt_mcode span:only-child').attr('class','').text('请输入手机获取到的验证码'); 
			}
			else{
				$('#alt_mcode span:only-child').attr('class','redfont').text('请输入手机获取到的验证码'); 
			}
			return false;
		}
		else{
			$('#alt_mcode span:only-child').text(''); 
			return true;
		}
	},
	V_Password_CellPhone:function(tag){
		var _pwd = $.trim($('#txtNewPwd').val());
		if (_pwd == ''){
			if (tag == 'f'){
				$('#alt_pwd span:only-child').attr('class','').text('请设置密码，6-16个字符，字母区分大小写。'); 
			}
			else{
				$('#alt_pwd span:only-child').attr('class','redfont').text('请设置密码，6-16个字符，字母区分大小写。'); 
			}
			$('#txtNewPwd').val('');
			return false;
		}
		else{
			if (_pwd.length<6 || _pwd.length>16){
				$('#alt_pwd span:only-child').attr('class','redfont').text('密码长度6-16位，请重新输入！'); 
				return false;
			}
			else
			{
				$('#alt_pwd span:only-child').text(''); 
				return true;
			}
		}
	},
	V_PasswordCfm_CellPhone:function(tag){
		if ($.trim($('#txtNewPwdCfm').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwdagain span:only-child').attr('class','').text('再次输入密码');
			}
			else{
				$('#alt_pwdagain span:only-child').attr('class','redfont').text('再次输入密码');
			}
			return false;
		}
		else if ($.trim($('#txtNewPwdCfm').val()) != $.trim($('#txtNewPwd').val())){
			$('#alt_pwdagain span:only-child').attr('class','redfont').text('两次密码输入不一致'); 
			return false;
		}
		else{
			$('#alt_pwdagain span:only-child').text(''); 
			return true;
		}
	},
    InitEventCellPhone:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
                objFindPassword.V_ValCode_CellPhone('b');
            },
			focus:function(){
				objFindPassword.V_ValCode_CellPhone('f');
			}
        });
        $('#txtNewPwd').bind({
            blur:function(){
                objFindPassword.V_Password_CellPhone('b');
            },
			focus:function(){
				objFindPassword.V_Password_CellPhone('f');
			}
        });
        $('#txtNewPwdCfm').bind({
            blur:function(){
				objFindPassword.V_PasswordCfm_CellPhone('b');
            },
			focus:function(){
				objFindPassword.V_PasswordCfm_CellPhone('f');
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnReset').click();
				}
			}
		});
    },
    InitCellPhone:function(){
        common.InitInputStyle();
        this.InitEventCellPhone();
    },
	/*固话找回密码 selfs/find/GetPassTelephone.aspx*/
	ValidateTelephone:function(){
        var res_mcode=true,res_pwd=true;res_pwdagain=true;
        res_mcode = this.V_ValCode_2('v');
		res_pwd = this.V_Password_2('v');
		res_pwdagain = this.V_PasswordCfm_2('v');
        if (res_mcode==true && res_pwd==true && res_pwdagain==true){
            return true;
        }
        else{
            return false;
        }
    },
	V_ValCode_2:function(tag){
		if ($.trim($('#txtMsgValCode').val()) == ''){
			if (tag == 'f'){
				$('#alt_mcode span:only-child').attr('class','').text('请输入固话收听到的验证码'); 
			}
			else{
				$('#alt_mcode span:only-child').attr('class','redfont').text('请输入固话收听到的验证码'); 
			}
			return false;
		}
		else{
			$('#alt_mcode span:only-child').text(''); 
			return true;
		}
	},
	V_Password_2:function(tag){
		var _pwd = $.trim($('#txtNewPwd').val());
		if (_pwd == ''){
			if (tag == 'f'){
				$('#alt_pwd span:only-child').attr('class','').text('请设置密码，6-16个字符，字母区分大小写。'); 
			}
			else{
				$('#alt_pwd span:only-child').attr('class','redfont').text('请设置密码，6-16个字符，字母区分大小写。'); 
			}
			return false;			
		}
		else{
			if (_pwd.length<6 || _pwd.length>16){
				$('#alt_pwd span:only-child').attr('class','redfont').text('密码长度6-16位，请重新输入！'); 
				return false;
			}
			$('#alt_pwd span:only-child').text(''); 
			return true;
		}
	},
	V_PasswordCfm_2:function(tag){
		if ($.trim($('#txtNewPwdCfm').val()) == ''){
			if (tag == 'f'){
				$('#alt_pwdagain span:only-child').attr('class','').text('再次输入密码'); 
			}
			else{
				$('#alt_pwdagain span:only-child').attr('class','redfont').text('再次输入密码'); 
			}
			return false;
		}
		else if ($.trim($('#txtNewPwdCfm').val()) != $.trim($('#txtNewPwd').val())){
			$('#alt_pwdagain span:only-child').attr('class','redfont').text('两次密码输入不一致'); 
			return false;
		}
		else{
			$('#alt_pwdagain span:only-child').text(''); 
			return true;
		}
	},
    InitEventTelephone:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
				objFindPassword.V_ValCode_2('b');
            },
			focus:function(){
				objFindPassword.V_ValCode_2('f');
			}
        });
        $('#txtNewPwd').bind({
            blur:function(){
                objFindPassword.V_Password_2('b');
            },
			focus:function(){
				objFindPassword.V_Password_2('f');
			}
        });
        $('#txtNewPwdCfm').bind({
            blur:function(){
                objFindPassword.V_PasswordCfm_2('b');
            },
			focus:function(){
				objFindPassword.V_PasswordCfm_2('f');
			}
        });
		$('#form1').bind({
			keydown:function(e){
				if (e.keyCode == 13){
					$('#btnReset').click();
				}
			}
		});
    },
    InitTelephone:function(){
        common.InitInputStyle();
        this.InitEventTelephone();
    }
}
var objFindPassword = new FindPassword();

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*找回密码
*url: /selfs/find/
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var PassSet = Class.create();
PassSet.prototype = {
    initialize:function(options){
        this.MaxTime = 61;
        this.service_url = '';
    },
    V_ErrorMsg:function(){
        if ($('#errDemo').attr('class') != 'error' && $('#errDemo').css('visibility') == 'visible'){
            $('#errDemo').css('visibility','hidden');
        }
    },
	OldPWD:function(tag){
	    this.V_ErrorMsg();
	    
		var _oldPWD = $.trim($('#txtCurPwd').val());
		if (_oldPWD == ''){
			if (tag && tag=='f'){
				$('#alt_txtCurPwd').attr('class','onBox onShow');
				$('#alt_txtCurPwd').html('请输入您的当前密码！');
			}
			else{
				$('#alt_txtCurPwd').attr('class','onBox onError');
				$('#alt_txtCurPwd').html('请输入您的当前密码！');
			}
			return false;
		}
		else if (_oldPWD.length < 6 || _oldPWD.length > 16){
			$('#alt_txtCurPwd').attr('class','onBox onError');
			$('#alt_txtCurPwd').html('当前密码输入有误，请重新输入！');
			return false;
		}
		else{
			$('#alt_txtCurPwd').attr('class','onBox onCorrect');
			$('#alt_txtCurPwd').html('');
			return true;
		}
	},
	NewPWD:function(tag){
        this.V_ErrorMsg();

		var _newPWD = $.trim($('#txtNewPwd').val());
		if (_newPWD == ''){
			if (tag && tag=='f'){
				$('#alt_txtNewPwd').attr('class','onBox onShow');
				$('#alt_txtNewPwd').html('请设置密码，6-16个字符，字母区分大小写。');
			}
			else{
				$('#alt_txtNewPwd').attr('class','onBox onError');
				$('#alt_txtNewPwd').html('请设置密码，6-16个字符，字母区分大小写。');			
			}
			return false;
		}
		else if (_newPWD.length < 6 || _newPWD.length > 16){
			$('#alt_txtNewPwd').attr('class','onBox onError');
			$('#alt_txtNewPwd').html('密码长度6-16位，请重新输入！');
			return false;
		}
		else{
			$('#alt_txtNewPwd').attr('class','onBox onCorrect');
			$('#alt_txtNewPwd').html('');
			return true;
		}
	},
	NewPWDCfm:function(tag){
	    this.V_ErrorMsg();
	
		var _newPWDCfm = $.trim($('#txtNewPwdCfm').val());
		var _newPWD = $.trim($('#txtNewPwd').val());
		if (_newPWDCfm == ''){
			if (tag && tag=='f'){
				$('#alt_txtNewPwdCfm').attr('class','onBox onShow');
				$('#alt_txtNewPwdCfm').html('再次输入密码');
			}
			else{
				$('#alt_txtNewPwdCfm').attr('class','onBox onError');
				$('#alt_txtNewPwdCfm').html('再次输入密码');
			}
			return false;
		}
		else if (_newPWDCfm != _newPWD){
			$('#alt_txtNewPwdCfm').attr('class','onBox onError');
			$('#alt_txtNewPwdCfm').html('两次新密码输入不相同，请重新输入！');
			return false;
		}
		else{
			$('#alt_txtNewPwdCfm').attr('class','onBox onCorrect');
			$('#alt_txtNewPwdCfm').html('');
			return true;
		}
	},
	InitEvent:function(){
		$('#txtCurPwd').bind({
			blur:function(){
				objPassSet.OldPWD();
			},
			focus:function(){
				objPassSet.OldPWD('f');
			}
		});	
		$('#txtNewPwd').bind({
			blur:function(){
				objPassSet.NewPWD();
			},
			focus:function(){
				objPassSet.NewPWD('f');
			}
		});
		$('#txtNewPwdCfm').bind({
			blur:function(){
				objPassSet.NewPWDCfm();
			},
			focus:function(){
				objPassSet.NewPWDCfm('f');
			}
		});
	},
	Validate:function(){
        var res_old=true,res_pwd=true;res_pwdagain=true;
        res_old = this.OldPWD('v');
		res_pwd = this.NewPWD('v');
		res_pwdagain = this.NewPWDCfm('v');
        if (res_old==true && res_pwd==true && res_pwdagain==true){
            return true;
        }
        else{
            return false;
        }
    },
	Init:function(){
		this.InitEvent();
	},
    //宽带账号找回密码手机验证 /SelfS/Home/Account/PassSetADSL1.aspx
	V_BP_ValCode:function(tag){
		if ($.trim($('#txtMsgValCode').val()) == ''){
			if (tag == 'f'){
				$('#alt_txtMsgValCode').attr('class','onBox onShow').html('请输入短信验证码'); 
			}
			else{
				$('#alt_txtMsgValCode').attr('class','onBox onError').html('请输入短信验证码'); 
			}
			return false;
		}
		else{
			$('#alt_txtMsgValCode').attr('class','onBox onCorrect').html(''); 
			return true;
		}
	},
    Init_BP_Event:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
                adslReg.V_BP_ValCode('b');
            },
			focus:function(){
				adslReg.V_BP_ValCode('f');
			}
        });
		$('#btnSendPhoneVCode').bind({
		    click:function(){
		        objPassSet.SendPhoneCode();
		    }
		});
    },
    Validate_BP:function(){
        var res_vcode = true;
		res_vcode = this.V_BP_ValCode('v');
        if (res_vcode == true){
            return true;
        } 
        else {
            return false;
        } 
    },
    Init_BP:function(){
        this.Init_BP_Event();
    },
    SendPhoneCode:function(){
        this.MaxTime = 61;
        $('#btnSendPhoneVCode').attr('disabled','disabled');
        $.ajax({
            url:this.service_url,
            type:'POST',
            dataType:'json',
            data:{m:'svcode_mp',random:Math.random},
            success:function(r){
                if (r.Result == 'TRUE'){
                    $('#errDemo').css('visibility','visible');
                    $('#errDemo').attr('class','Correct');
                    $('#errDemo').html(r.Msg);
                    
                    $('#btnSubmit').removeAttr('disabled');
                    $('#btnSendPhoneVCode').attr('disabled','disabled');
                    objPassSet.ShowSendBtnLater();
                    setInterval('objPassSet.ShowSendBtnLater()',1000);
                }
                else{
                    $('#errDemo').css('visibility','visible');
                    $('#errDemo').attr('class','error');
                    $('#errDemo').html(r.Msg);
                    $('#btnSubmit').attr('disabled','disabled');
                    $('#btnSendPhoneVCode').removeAttr('disabled');
                }
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
               ///TODO 
               alert("系统忙，请稍后再试");
            }
        });
    },
    ShowSendBtnLater:function(){
        if (this.MaxTime > 1){
            this.MaxTime = this.MaxTime - 1;
            
            var showPadChar = '';
            if (this.MaxTime < 10){
                showPadChar = '0';
            }
            
            $('#btnSendPhoneVCode').val(showPadChar+this.MaxTime+'秒后可以重新发送');
        }
        else{
            $('#btnSendPhoneVCode').val('点此免费获取短信验证码');
            $('#btnSendPhoneVCode').removeAttr('disabled');
        }
    }
}
var objPassSet = new PassSet();


/*END*/
/************************************************************************************/

/************************************************************************************/
/*
*找回密码
*url: /selfs/find/
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var MailPassSet = Class.create();
MailPassSet.prototype = {
    initialize:function(options){
        this.MaxTime = 61;
        this.service_url = '';
    },
	V_BP_ValCode:function(tag){
		if ($.trim($('#txtMsgValCode').val()) == ''){
			if (tag == 'f'){
				$('#lblUpdateMsg').css('visibility','visible').html('请输入短信验证码'); 
			}
			else{
				$('#lblUpdateMsg').css('visibility','visible').html('请输入短信验证码'); 
			}
			return false;
		}
		else{
			$('#lblUpdateMsg').html(''); 
			return true;
		}
	},
    //邮箱找回密码手机验证 /SelfS/S/Mail/PwdCommonSetADSL1.aspx
    Init_BP_Event:function(){
        $('#txtMsgValCode').bind({
            blur:function(){
                adslReg.V_BP_ValCode('b');
            },
			focus:function(){
				adslReg.V_BP_ValCode('f');
			}
        });
		$('#btnSendPhoneVCode').bind({
		    click:function(){
		        objMailPassSet.SendPhoneCode();
		    }
		});
    },
    Validate_BP:function(){
        var res_vcode = true;
		res_vcode = this.V_BP_ValCode('v');
        if (res_vcode == true){
            return true;
        } 
        else {
            return false;
        } 
    },
    Init_BP:function(){
        this.Init_BP_Event();
    },
    SendPhoneCode:function(){
        this.MaxTime = 61;
        $('#btnSendPhoneVCode').attr('disabled','disabled');
        $.ajax({
            url:this.service_url,
            type:'POST',
            dataType:'json',
            data:{m:'svcode_mailp',random:Math.random},
            success:function(r){
                if (r.Result == 'TRUE'){
                    $('#lblUpdateMsg').css('visibility','visible').html(r.Msg);
                    
                    $('#btnSubmit').removeAttr('disabled');
                    $('#btnSendPhoneVCode').attr('disabled','disabled');
                    objMailPassSet.ShowSendBtnLater();
                    setInterval('objMailPassSet.ShowSendBtnLater()',1000);
                }
                else{
                    $('#lblUpdateMsg').css('visibility','visible').html(r.Msg);
                    $('#btnSubmit').attr('disabled','disabled');
                    $('#btnSendPhoneVCode').removeAttr('disabled');
                }
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
               ///TODO 
               alert("系统忙，请稍后再试");
            }
        });
    },
    ShowSendBtnLater:function(){
        if (this.MaxTime > 1){
            this.MaxTime = this.MaxTime - 1;
            
            var showPadChar = '';
            if (this.MaxTime < 10){
                showPadChar = '0';
            }
            
            $('#btnSendPhoneVCode').val(showPadChar+this.MaxTime+'秒后可以重新发送');
        }
        else{
            $('#btnSendPhoneVCode').val('点此免费获取短信验证码');
            $('#btnSendPhoneVCode').removeAttr('disabled');
        }
    }

}
var objMailPassSet = new MailPassSet();
/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*找回密码
*url: /selfs/find/
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var InfoSet = Class.create();
InfoSet.prototype = {
    initialize:function(options){
    },
	CardID:function(){

	},
	BirthDate:function(tag){
		var _birthDate = $.trim($('#txtBirthDay').val());
		_regxDate = /(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)/;
		if (_birthDate != '' && _regxDate.test(_birthDate)==false){
			$('#alt_txtBirthDay').attr('class','onBox onError');
			$('#alt_txtBirthDay').html('请输入正确的日期(1988-03-18)');
			return false;
		}
		else if (_birthDate == ''){
		    if (tag && tag=='f'){
		        $('#alt_txtBirthDay').attr('class','onBox onShow');
			    $('#alt_txtBirthDay').html('请输入正确的日期(如1988-03-18)');    
		    }
		    else{
			    $('#alt_txtBirthDay').attr('class','onBox');
			    $('#alt_txtBirthDay').html('');
			}
			return true;
		}
		else{
		    if (!tag){
		        $('#alt_txtBirthDay').attr('class','onBox onCorrect');
		        $('#alt_txtBirthDay').html('');
			}
			return true;
		}
	},
	PostCode:function(tag){
		var code = $.trim($('#txtPostCode').val());
		var regxCode = /\d{6}/;
		if (code != '' && code.length != 6){
			$('#alt_txtPostCode').attr('class','onBox onError');
			$('#alt_txtPostCode').html('请输入正确的邮编');
			return false;
		}
		else if (code.length == 6){
		    if (!tag && tag!='f'){
			    $('#alt_txtPostCode').attr('class','onBox onCorrect');
			    $('#alt_txtPostCode').html('');
			}
			return true;
		}
		else{
		    if (tag && tag=='f'){
		        $('#alt_txtPostCode').attr('class','onBox onShow').html('请输入邮编');
		    }
		    else{
		        $('#alt_txtPostCode').attr('class','onBox');
		        $('#alt_txtPostCode').html('');
		    }
			return true;
		}
	},
	V_IDCard:function(tag){
	    var _val = $.trim($('#txtCertCode').val());
	    var _disabled = $('#txtCertCode').attr('disabled');
	    if ((_val=='****' && _disabled==true) || _val == ''){
	        if (tag && tag=='f'){
	            $('#alt_txtCertCode').attr('class','onBox onShow').html('请输入身份证号，输入后不可修改');
	        }
	        else
	        {
	            $('#alt_txtCertCode').attr('class','onBox').html('');
	        }
	        return true;
	    }
	    var _result = checkIDcard(_val);
	    if (_result == ''){
			$('#alt_txtCertCode').attr('class','onBox onCorrect').html('');
	        return true;
	    }
	    else{
	        $('#alt_txtCertCode').attr('class','onBox onError').html(_result);
	        return false;
	    }
	},
	V_RealName:function(tag){
		if ($.trim($('#txtRealName').val()) == ''){
			if (tag && tag=='f'){
				$('#alt_txtRealName').attr('class','onBox onShow').html('请输入真实姓名，输入后不可修改');
			}
			else{
				$('#alt_txtRealName').attr('class','onBox').html('');
			}
		}
		else{
		    $('#alt_txtRealName').attr('class','onBox').html('');
		}
	},
	V_Phone:function(tag){
		if ($.trim($('#txtContactTel').val()) == ''){
			if (tag && tag=='f'){
				$('#alt_txtContactTel').attr('class','onBox onShow').html('请输入联系电话');
			}
			else{
				$('#alt_txtContactTel').attr('class','onBox').html('');
			}
		}
		else{
		    $('#alt_txtContactTel').attr('class','onBox').html('');
		}
	},
	V_Address:function(tag){
		if ($.trim($('#txtAddress').val()) == ''){
			if (tag && tag=='f'){
				$('#alt_txtAddress').attr('class','onBox onShow').html('请输入通信地址');
			}
			else{
				$('#alt_txtAddress').attr('class','onBox').html('');
			}
		}
		else{
		    $('#alt_txtAddress').attr('class','onBox').html('');
		}
	},
	InitEvent:function(){
		$('#txtBirthDay').bind({
			blur:function(){
				objInfoSet.BirthDate();
			},
			focus:function(){
				objInfoSet.BirthDate('f');
			}
		});	
		$('#txtPostCode').bind({
			blur:function(){
				objInfoSet.PostCode();
			},
			focus:function(){
				objInfoSet.PostCode('f');
			}
		});	
		$('#txtCertCode').bind({
			blur:function(){
				objInfoSet.V_IDCard();
			},
			focus:function(){
				objInfoSet.V_IDCard('f');
			}
		});	
		$('#txtRealName').bind({
			blur:function(){
				objInfoSet.V_RealName();
			},
			focus:function(){
				objInfoSet.V_RealName('f');
			}
		});	
		$('#txtContactTel').bind({
			blur:function(){
				objInfoSet.V_Phone();
			},
			focus:function(){
				objInfoSet.V_Phone('f');
			}
		});	
		$('#txtAddress').bind({
			blur:function(){
				objInfoSet.V_Address();
			},
			focus:function(){
				objInfoSet.V_Address('f');
			}
		});	
		$('#btnSubmit').bind({
			click:function(){
				return objInfoSet.Validate();
			}
		});
	},
	Validate:function(){
        var res_birthDate=true,res_postcode=true,res_cardID=true;
        res_birthDate = this.BirthDate('v');
		res_postcode = this.PostCode('v');
		res_cardID = this.V_IDCard('v');
        if (res_birthDate==true && res_postcode==true && res_cardID==true){
            return true;
        }
        else{
            return false;
        }
    },
	Init:function(){
		this.InitEvent();
	}
}
var objInfoSet = new InfoSet();


/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*别名设置
*url: /SelfS/Home/Account/AliasSet.aspx
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var AliasSet = Class.create();
AliasSet.prototype = {
    initialize:function(options){
    },
	V_Alias:function(){
		if ($.trim($('#txtNewAlias').val()) == ''){
			$('#errDemo').attr('class','error').css('visibility','visible');
			$('#errDemo').html('别名不能为空！');
			return false;
		}
		else{
			$('#errDemo').attr('class','Correct').css('visibility','hidden');
			$('#errDemo').html('');
			return true;
		}
	},
	InitEvent:function(){
		$('#txtNewAlias').bind({
			focus:function(){
				//objAliasSet.V_Alias();
			},
			blur:function(){
				objAliasSet.V_Alias();
			}
		});
		$('#btnSumbit').bind({
			click:function(){
				return objAliasSet.Validate();
			}
		});
		$('#btnCheckRepeat').bind({
			click:function(){
				return objAliasSet.Validate();
			}
		});
	},
	Validate:function(){
        var res_alias=true;
        res_alias = this.V_Alias();
        if (res_alias==true){
            return true;
        }
        else{
            return false;
        }
    },
	Init:function(){
		this.InitEvent();
	}
}
var objAliasSet = new AliasSet();
	
	
/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*设置密码
*url: /SelfS/Home/Account/PassSetPhone.aspx
*Author: stone
*Data: 2011/03/14
*BEGIN
*/
var PassSetPhone = Class.create();
PassSetPhone.prototype = {
    initialize:function(options){
    },
	OldPWD:function(tag){
		var _oldPWD = $.trim($('#txtCurPwd').val());
		if (_oldPWD == ''){
			$('#errDemo').css('visibility','visible');
			$('#errDemo').html('请输入您的当前密码！');
			return false;
		}
		else if (_oldPWD.length < 6 || _oldPWD.length > 16){
			$('#errDemo').css('visibility','visible');
			$('#errDemo').html('当前密码输入有误，请重新输入！');
			return false;
		}
		else{
			$('#errDemo').css('visibility','hidden');
			$('#errDemo').html('');
			return true;
		}
	},
	VCode:function(tag){
		var _code = $.trim($('#txtMsgValCode').val());
		if (_code == ''){
			if (tag && tag=='f'){
				$('#alt_txtMsgValCode').attr('class','onBox onShow');
				$('#alt_txtMsgValCode').html('请输入短信验证码！');
			}
			else{
				$('#alt_txtMsgValCode').attr('class','onBox onError');
				$('#alt_txtMsgValCode').html('请输入短信验证码！');

			}
			return false;
		}
		else{
			$('#alt_txtMsgValCode').attr('class','onBox onCorrect');
			$('#alt_txtMsgValCode').html('');
			return true;
		}
	},
	NewPWD:function(tag){
		//TODO 需要增加安全强度校验
		var _newPWD = $.trim($('#txtNewPwd').val());
		if (_newPWD == ''){
			if (tag && tag=='f'){
				$('#alt_txtNewPwd').attr('class','onBox onShow');
				$('#alt_txtNewPwd').html('请设置密码，6-16个字符，字母区分大小写。');
			}
			else{
				$('#alt_txtNewPwd').attr('class','onBox onError');
				$('#alt_txtNewPwd').html('请设置密码，6-16个字符，字母区分大小写。');

			}
			return false;
		}
		else if (_newPWD.length < 6 || _newPWD.length > 16){
			$('#alt_txtNewPwd').attr('class','onBox onError');
			$('#alt_txtNewPwd').html('密码长度6-16位，请重新输入！');
			return false;
		}
		else{
			$('#alt_txtNewPwd').attr('class','onBox onCorrect');
			$('#alt_txtNewPwd').html('');
			return true;
		}
	},
	NewPWDCfm:function(tag){
		var _newPWDCfm = $.trim($('#txtNewPwdCfm').val());
		var _newPWD = $.trim($('#txtNewPwd').val());
		if (_newPWDCfm == ''){
			if (tag && tag=='f'){
				$('#alt_txtNewPwdCfm').attr('class','onBox onShow');
				$('#alt_txtNewPwdCfm').html('再次输入密码');
			}
			else{
				$('#alt_txtNewPwdCfm').attr('class','onBox onError');
				$('#alt_txtNewPwdCfm').html('再次输入密码');

			}
			return false;
		}
		else if (_newPWDCfm != _newPWD){
			$('#alt_txtNewPwdCfm').attr('class','onBox onError');
			$('#alt_txtNewPwdCfm').html('两次新密码输入不相同，请重新输入！');
			return false;
		}
		else{
			$('#alt_txtNewPwdCfm').attr('class','onBox onCorrect');
			$('#alt_txtNewPwdCfm').html('');
			return true;
		}
	},
	InitEvent:function(){
		$('#txtCurPwd').bind({
			blur:function(){
				objPassSetPhone.OldPWD();
			},
			focus:function(){
				objPassSetPhone.OldPWD('f');
			}
		});	
		$('#txtNewPwd').bind({
			blur:function(){
				objPassSetPhone.NewPWD();
			},
			focus:function(){
				objPassSetPhone.NewPWD('f');
			}
		});
		$('#txtMsgValCode').bind({
			blur:function(){
				objPassSetPhone.VCode();
			},
			focus:function(){
				objPassSetPhone.VCode('f');
			}
		});
		$('#txtNewPwdCfm').bind({
			blur:function(){
				objPassSetPhone.NewPWDCfm();
			},
			focus:function(){
				objPassSetPhone.NewPWDCfm('f');
			}
		});
		$('#btnSubmit').bind({
			click:function(){
				//return objPassSetPhone.Validate();
			    if (objPassSetPhone.Validate()){
			        return compileForReset('txtNewPwd,txtNewPwdCfm');
			    }
			    else{
			        return false;
			    }
			    return false;
			}
		});
	},
	Validate:function(){
        var res_old=true,res_pwd=true;res_pwdagain=true,res_code=true;
       // res_old = this.OldPWD();
		res_code = this.VCode();
		res_pwd = this.NewPWD();
		res_pwdagain = this.NewPWDCfm();
        if (res_old==true && res_pwd==true && res_pwdagain==true && res_code==true){
            return true;
        }
        else{
            return false;
        }
    },
	Init:function(){
		this.InitEvent();
	}
}
var objPassSetPhone = new PassSetPhone();

/*END*/
/************************************************************************************/


/************************************************************************************/
/*
*设置安全邮箱
*url: /SelfS/Home/Account/SecurityEmail.aspx
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var SecurityEmail = Class.create();
SecurityEmail.prototype = {
    initialize:function(options){
    },
	V_Account:function(tag){
		var _account = $.trim($('#txt_Account').val());
		if (_account == ''){
			if (tag && tag=='f'){
				$('#alt_txt_Account').attr('class','onBox onShow');
				$('#alt_txt_Account').html('请输入宽带(ADSL)账号');
			}
			else{
				$('#alt_txt_Account').attr('class','onBox onError');
				$('#alt_txt_Account').html('请输入宽带(ADSL)账号');
			}
			return false;
		}
		else if (_account.length > 48){
			$('#alt_txt_Account').attr('class','onBox onError');
			$('#alt_txt_Account').html('宽带账号出错，请重新输入！');
			return false;
		}
		else{
			$('#alt_txt_Account').attr('class','onBox onCorrect');
			$('#alt_txt_Account').html('');
			return true;
		}
	},
	V_AccountPWD:function(tag){
		var _accountpwd = $.trim($('#txt_AccPwd').val());
		if (_accountpwd == ''){
			if (tag && tag=='f'){
				$('#alt_txt_AccPwd').attr('class','onBox onShow');
				$('#alt_txt_AccPwd').html('请输入宽带(ADSL)密码');
			}
			else{
				$('#alt_txt_AccPwd').attr('class','onBox onError');
				$('#alt_txt_AccPwd').html('请输入宽带(ADSL)密码');

			}
			return false;
		}
		else{
			$('#alt_txt_AccPwd').attr('class','onBox onCorrect');
			$('#alt_txt_AccPwd').html('');
			return true;
		}
	},
	V_Code:function(tag){
		var _vcode = $.trim($('#ValidateCode').val());
		if (_vcode == ''){
			if (tag=='f'){
				$('#alt_ValidateCode').attr('class','onBox onShow');
				$('#alt_ValidateCode').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>');
			}
			else if (tag == 'v'){
				$('#alt_ValidateCode').attr('class','onBox onError');
				$('#alt_ValidateCode').html('请输入验证码，看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>');
			}
			else{
			    $('#alt_ValidateCode').attr('class','onBox onError');
			}
			return false;
		}
		else{
			$('#alt_ValidateCode').attr('class','onBox onShow');
			if ($.trim($('#alt_ValidateCode').html()) == ''){
			    $('#alt_ValidateCode').html('看不清？<a href="javascript:void(0);" onclick="refreshCode()">换一张</a>');
			}
			return true;
		}
	},
	InitEvent:function(){
		$('#txt_Account').bind({
			focus:function(){
				objSecurityEmail.V_Account('f');
			},
			blur:function(){
				objSecurityEmail.V_Account('b');
			}
		});
		$('#txt_AccPwd').bind({
			focus:function(){
				objSecurityEmail.V_AccountPWD('f');
			},
			blur:function(){
				objSecurityEmail.V_AccountPWD('b');
			}
		});
		$('#ValidateCode').bind({
			focus:function(){
				objSecurityEmail.V_Code('f');
			},
			blur:function(){
				objSecurityEmail.V_Code('b');
			}
		});

	},
	Validate:function(){
        var r_account=true,r_pwd=true,r_code=true;
        r_account = this.V_Account('v');
		r_pwd = this.V_AccountPWD('v');
		r_code = this.V_Code('v');
        if (r_account==true && r_pwd==true && r_code==true){
            return true;
        }
        else{
            return false;
        }
    },
	Init:function(){
		this.InitEvent();
	},
	V_SMail_3:function(){
		var _mail = $.trim($('#txt_SEmail').val());
		if (_mail == ''){
			$('#errDemo').css('visibility','visible');
			$('#errDemo').html('请输入安全邮箱');
			return false;
		}
		else if (CheckEmail('txt_SEmail') == true){
			$('#errDemo').css('visibility','hidden');
			$('#errDemo').html('');
			return true;
		}
		else{
			$('#errDemo').css('visibility','visible');
			$('#errDemo').html('请输入正确的邮箱格式');
			return false;
		}
	},
	InitEvent3:function(){
		$('#txt_SEmail').bind({
			blur:function(){
				objSecurityEmail.V_SMail_3();
			},
			focus:function(){
				objSecurityEmail.V_SMail_3();
			}
		});
	},
	Validate3:function(){
		var r_mail=true;
		r_mail = objSecurityEmail.V_SMail_3();
		if (r_mail == true){
			return true;
		}
		else{
			return false;
		}
	},
	Init3:function(){
		this.InitEvent3();
	},
	V_2_RealName:function(tag){
		if($.trim($('#txt_RealName').val()) == ''){
			if (tag && tag=='f'){
				$('#alt_txt_RealName').attr('class','onBox onShow');
				$('#alt_txt_RealName').html('请输入真实姓名');
			}
			else{
				$('#alt_txt_RealName').attr('class','onBox onError');
				$('#alt_txt_RealName').html('请输入真实姓名');
			}
			return false;
		}
		else{
			$('#alt_txt_RealName').attr('class','onBox');
			$('#alt_txt_RealName').html('');
			return true;
		}
	},
	V_2_CardID:function(tag){
		if($.trim($('#txt_IDCard').val()) == ''){
			if (tag && tag=='f'){
				$('#alt_txt_IDCard').attr('class','onBox onShow');
				$('#alt_txt_IDCard').html('请输入身份证号');
			}
			else{
				$('#alt_txt_IDCard').attr('class','onBox onError');
				$('#alt_txt_IDCard').html('请输入身份证号');

			}
			return false;
		}
		else{
			$('#alt_txt_IDCard').attr('class','onBox');
			$('#alt_txt_IDCard').html('');
			return true;
		}
	},
	InitEvent2:function(){
		$('#txt_RealName').bind({
			blur:function(){
				objSecurityEmail.V_2_RealName();
			},
			focus:function(){
				objSecurityEmail.V_2_RealName('f');
			}
		});
		$('#txt_IDCard').bind({
			blur:function(){
				objSecurityEmail.V_2_CardID();
			},
			focus:function(){
				objSecurityEmail.V_2_CardID('f');
			}
		});
	},
	Validate2:function(){
		var r_name=true,r_card=true;
		r_name = objSecurityEmail.V_2_RealName();
		r_card = objSecurityEmail.V_2_CardID();
		if (r_name == true && r_card == true){
			return true;
		}
		else{
			return false;
		}
	},
	Init2:function(){
		objSecurityEmail.InitEvent2();
	}
}
var objSecurityEmail = new SecurityEmail();
	
	
/*END*/
/************************************************************************************/



/************************************************************************************/
/*
*身份认证
*url: /SelfS/Home/Account/IdInspection.aspx
*Author: stone
*Data: 2011/03/09
*BEGIN
*/
var IdInspection = Class.create();
IdInspection.prototype = {
    initialize:function(options){
    },
	V_CardID:function(tag){
		var _val = $.trim($('#txt_IDCard').val());
		if (_val == ''){
		    if (tag && tag=='f'){
			    $('#alt_txt_IDCard').attr('class','onBox onShow');
			}
			else{
			    $('#alt_txt_IDCard').attr('class','onBox onError');
			}
			$('#alt_txt_IDCard').html('请输入身份证号');
			return false;
		}
		else{
			var res = checkIDcard(_val);
			if (res == ''){
				$('#alt_txt_IDCard').attr('class','onBox onCorrect');
				$('#alt_txt_IDCard').html('');
				return true;
			}
			else{
				$('#alt_txt_IDCard').attr('class','onBox onError');
				$('#alt_txt_IDCard').html(res);
				return false;
			}
		}
	},
	V_RealName:function(tag){
		if ($.trim($('#txt_RealName').val()) == ''){
		    if (tag && tag=='f'){
			    $('#alt_txt_RealName').attr('class','onBox onShow');
			}
			else{
			    $('#alt_txt_RealName').attr('class','onBox onError');
			}
			$('#alt_txt_RealName').html('请输入真实姓名');
			return false;
		}
		else{
			$('#alt_txt_RealName').attr('class','onBox onCorrect');
			$('#alt_txt_RealName').html('');
			return true;
		}
	},
	V_CellPhone:function(tag){
		if ($.trim($('#txt_CellPhone').val()) == ''){
		    if (tag && tag=='f'){
		        $('#alt_txt_CellPhone').attr('class','onBox onShow');
		    }
		    else{
			    $('#alt_txt_CellPhone').attr('class','onBox onError');
			}
			$('#alt_txt_CellPhone').html('请输入手机号码');
			return false;
		}
		else{
			var regxPhone = /^1[3584]\d{9}$/;
			if (regxPhone.test($.trim($('#txt_CellPhone').val()))){
				$('#alt_txt_CellPhone').attr('class','onBox onCorrect');
				$('#alt_txt_CellPhone').html('');
				return true;
			}
			else{
				$('#alt_txt_CellPhone').attr('class','onBox onError');
				$('#alt_txt_CellPhone').html('请输入正确的手机号码');
				return false;
			}
			
		}
	},
	InitEvent:function(){
		$('#txt_IDCard').bind({
			focus:function(){
				objIdInspection.V_CardID('f');
			},
			blur:function(){
				objIdInspection.V_CardID();
			}
		});
		$('#txt_RealName').bind({
			focus:function(){
				objIdInspection.V_RealName('f');
			},
			blur:function(){
				objIdInspection.V_RealName();
			}
		});
		$('#txt_CellPhone').bind({
			focus:function(){
				objIdInspection.V_CellPhone('f');
			},
			blur:function(){
				objIdInspection.V_CellPhone();
			}
		});
	},
	Validate:function(){
		var r_name=true,r_card=true,r_phone=true;
		r_name = objIdInspection.V_RealName('v');
		r_card = objIdInspection.V_CardID('v');
		r_phone = objIdInspection.V_CellPhone('v');
		if (r_name == true && r_card == true && r_phone == true){
			return true;
		}
		else{
			return false;
		}
	},
	Init:function(){
		this.InitEvent();
	}
	
}
var objIdInspection = new IdInspection();
/*END*/
/************************************************************************************/


