// 截取字符串的开头和结尾的空格
function trim(str) {
  return str.replace(/^\s*|\s*$/g,"");
}

// 校验文本是否为空
function textIsNull(checkValue) {
  // 将文本进行trim处理
  checkValue = trim(checkValue);
  if(checkValue=="")
    return true;
    
  return false;
}

// 将字符串转化成日期,日期格式为YYYY-MM-DD
function str2Date(dateStr) {
  return new Date(dateStr.replace(/-/g, "\/"));
}

// 转换日期，转换后的格式为YYYY-MM-DD
function date2Str(theDate) {
  return theDate.getYear()+"-"+(theDate.getMonth()+1)+"-"+theDate.getDate();;
}

// 比较日期的大小,-2:日期格式错误;-1:第一个小于第二个;0:俩个相等;1:第一个大于第二个日期
function compareDate(oneDate,twoDate) {
  if( (typeof(oneDate)!="object" && oneDate.constructor != Date) ||
    (typeof(twoDate)!="object" && twoDate.constructor != Date))
  {
    return -2;
  }
  
  var millSec = oneDate.getTime() - twoDate.getTime();
  if(millSec>0)
  {
      return 1;
  }
  else if(millSec==0) 
  {
      return 0;
  }
  else
  {
      return -1;
  }
}

// email地址检查
function checkEmail(mailaddr) { 
  var emailPat='^[a-zA-Z0-9]{1}[\.a-zA-Z0-9_-]*[a-zA-Z0-9]{1}@[a-zA-Z0-9]+[-]{0,1}[a-zA-Z0-9]+[\.]{1}[a-zA-Z]+[\.]{0,1}[a-zA-Z]+$'; 
  var matchArray=mailaddr.match(emailPat); 
  if (matchArray == null) {
    return false;
  }
  
  return true;
} 

// 校验IP
function checkIP(ipAddr) {
  var ipPart = ipAddr.split(".");
  if(ipPart.length!=4) return false;
  for(num in ipPart) {
    if(isNaN(ipPart[num])) return false;
    if(ipPart[num].valueOf()<=0 || ipPart[num].valueOf() > 255) return false;
  }
  
  return true;
}

// 校验多个IP地址,各个IP以逗号分隔
function checkIPList(ipList) {
  var ipAddr_a = ipList.split(",");
  
  for(ipAddr in ipAddr_a) {
    if(!checkIP(ipAddr_a[ipAddr]))
      return false;
  }
  return true;
}

// 电话号码校验
function PhoneCheck(phoneNum) {
  var reg=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/;
  if(!reg.test(phoneNum))
    return false;
  return true;
}