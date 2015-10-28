// ��ȡ�ַ����Ŀ�ͷ�ͽ�β�Ŀո�
function trim(str) {
  return str.replace(/^\s*|\s*$/g,"");
}

// У���ı��Ƿ�Ϊ��
function textIsNull(checkValue) {
  // ���ı�����trim����
  checkValue = trim(checkValue);
  if(checkValue=="")
    return true;
    
  return false;
}

// ���ַ���ת��������,���ڸ�ʽΪYYYY-MM-DD
function str2Date(dateStr) {
  return new Date(dateStr.replace(/-/g, "\/"));
}

// ת�����ڣ�ת����ĸ�ʽΪYYYY-MM-DD
function date2Str(theDate) {
  return theDate.getYear()+"-"+(theDate.getMonth()+1)+"-"+theDate.getDate();;
}

// �Ƚ����ڵĴ�С,-2:���ڸ�ʽ����;-1:��һ��С�ڵڶ���;0:�������;1:��һ�����ڵڶ�������
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

// email��ַ���
function checkEmail(mailaddr) { 
  var emailPat='^[a-zA-Z0-9]{1}[\.a-zA-Z0-9_-]*[a-zA-Z0-9]{1}@[a-zA-Z0-9]+[-]{0,1}[a-zA-Z0-9]+[\.]{1}[a-zA-Z]+[\.]{0,1}[a-zA-Z]+$'; 
  var matchArray=mailaddr.match(emailPat); 
  if (matchArray == null) {
    return false;
  }
  
  return true;
} 

// У��IP
function checkIP(ipAddr) {
  var ipPart = ipAddr.split(".");
  if(ipPart.length!=4) return false;
  for(num in ipPart) {
    if(isNaN(ipPart[num])) return false;
    if(ipPart[num].valueOf()<=0 || ipPart[num].valueOf() > 255) return false;
  }
  
  return true;
}

// У����IP��ַ,����IP�Զ��ŷָ�
function checkIPList(ipList) {
  var ipAddr_a = ipList.split(",");
  
  for(ipAddr in ipAddr_a) {
    if(!checkIP(ipAddr_a[ipAddr]))
      return false;
  }
  return true;
}

// �绰����У��
function PhoneCheck(phoneNum) {
  var reg=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/;
  if(!reg.test(phoneNum))
    return false;
  return true;
}