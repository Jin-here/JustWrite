/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function submitFrom(){
    var account=document.getElementById("account");
    var errDes=document.getElementById("errDes");
    var errDemo=document.getElementById("errDemo");
    var passWordMinNum=document.getElementById("passWordMinNum");
    var passWordMaxNum=document.getElementById("passWordMaxNum");
   if(account.value==""){
        errDes.innerHTML="鐢ㄦ埛鍚嶄笉鑳戒负绌";
        errDemo.style.display="block";
        return false;
    }
    if(account.value.indexOf(" ")!=-1)
    {
        errDes.innerHTML="鐢ㄦ埛鍚嶄笉鑳藉寘鍚┖鏍";
        errDemo.style.display="block";
        return false;
    }
    var reg=/[u4E00-u9FA5]/g
    if (!reg.test(account.value)){
        errDes.innerHTML="鐢ㄦ埛鍚嶄笉鑳藉寘鍚腑鏂";
        errDemo.style.display="block";
        return false;
    }

    if(!isNaN(account.value))
    {
        if(account.value.length>20||account.value.length<5){
            errDes.innerHTML="缁熶竴甯愬彿浣嶆暟涓嶆纭";
            errDemo.style.display="block";
            return false;
        }
    }else{
        if(account.value.length>16||account.value.length<5){
            errDes.innerHTML="鍒悕鐨勪綅鏁颁负5鍒�5浣";
            errDemo.style.display="block";
            return false;
        }
    }
    
    var password=document.getElementById("password");
    if(password.value==""){
        errDes.innerHTML="瀵嗙爜涓嶈兘涓虹┖";
        errDemo.style.display="block";
        return false;
    }
    if(password.value.indexOf(" ")!=-1)
    {
        errDes.innerHTML="瀵嗙爜涓嶈兘鍖呭惈绌烘牸";
        errDemo.style.display="block";
        return false;
    }
    if(password.value.length<passWordMinNum.value||password.value.length>passWordMaxNum.value){
        errDes.innerHTML="瀵嗙爜浣嶆暟涓嶆纭";
        errDemo.style.display="block";
        return false;
    }
    return true;
}
function mm(e)   //璁╄鎺т欢澶卞幓鐒︾偣鐨勬椂鍊欒繘琛岄獙璇�{
    var   s=e.value;
    if   (s=="")   
    	   //鑻ュ�涓虹┖鍒欓�鍑洪獙璇佸嚱鏁�    if   (s.indexOf(" ")   !=   -1)
    {
        alert("瀛楃涓瞫tr涓惈鏈夌┖鏍");
        e.focus();   //閲嶆柊璁╄繖涓帶浠跺緱鍒扮劍鐐�    }
}   
function getPassword(){
    var account = document.forms[0].account.value;
    if(account == null || account == ""){
        alert("璇疯緭鍏ュぉ缈兼墜鏈哄彿");
        return false;
    }
    var passwordType=document.getElementById("passwordType");
    passwordType.value=1;
    var req;
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    }else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }

    req.open("GET","WsBusiness?userId=" + account + "&operation=getPassword", true);

    req.onreadystatechange = function (){
        if (req.readyState == 4) {
            if (req.status == 200) {
                var retTxt = req.responseText;
                alert(retTxt);
            }
        }
    };
    req.send(null);
    return true;
}
function submithuiyongFrom(){
    var account=document.getElementById("account");
    var errDes=document.getElementById("errDes");
    var errDemo=document.getElementById("errDemo");
    var passWordMinNum=document.getElementById("passWordMinNum");
    var passWordMaxNum=document.getElementById("passWordMaxNum");
   if(account.value==""){
        errDes.innerHTML="鐢ㄦ埛鍚嶄笉鑳戒负绌";
        errDemo.style.display="block";
        return false;
    }
    if(account.value.indexOf(" ")!=-1)
    {
        errDes.innerHTML="鐢ㄦ埛鍚嶄笉鑳藉寘鍚┖鏍";
        errDemo.style.display="block";
        return false;
    }
    var reg=/[u4E00-u9FA5]/g
    if (!reg.test(account.value)){
        errDes.innerHTML="鐢ㄦ埛鍚嶄笉鑳藉寘鍚腑鏂";
        errDemo.style.display="block";
        return false;
    }

    if(!isNaN(account.value))
    {
        if(account.value.length>20||account.value.length<5){
            errDes.innerHTML="缁熶竴甯愬彿浣嶆暟涓嶆纭";
            errDemo.style.display="block";
            return false;
        }
    }else{
        if(account.value.length>16||account.value.length<5){
            errDes.innerHTML="鍒悕鐨勪綅鏁颁负5鍒�5浣";
            errDemo.style.display="block";
            return false;
        }
    }

    var password=document.getElementById("password");
    if(password.value==""){
        errDes.innerHTML="瀵嗙爜涓嶈兘涓虹┖";
        errDemo.style.display="block";
        return false;
    }
    if(password.value.indexOf(" ")!=-1)
    {
        errDes.innerHTML="瀵嗙爜涓嶈兘鍖呭惈绌烘牸";
        errDemo.style.display="block";
        return false;
    }
    if(password.value.length<passWordMinNum.value||password.value.length>passWordMaxNum.value){
        errDes.innerHTML="瀵嗙爜浣嶆暟涓嶆纭";
        errDemo.style.display="block";
        return false;
    }
    document.getElementById("loginForm").submit();
    return true;
}