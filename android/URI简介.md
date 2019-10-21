 
# URI简介
就Android平台而言，URI主要分三个部分：scheme, authority and path。其中authority又分为host和port。格式如下：scheme://host:port/path
举个实际的例子：
```java
content://com.example.project:200/folder/subfolder/etc
\------/ \------------------/\---/\------------------/
scheme          host          port        path
         \-----------------------/
                 authority
```
现在大家应该知道data flag中那些属性的含义了吧，看下data flag
```java
<data android:host="string"
    android:mimeType="string"
    android:path="string"
    android:pathPattern="string"
    android:pathPrefix="string"
    android:port="string"
    android:scheme="string" />
```
但是我们在程序中一般是不直接用URI来标识CP的，是的，正如我们通常见到的用定义的常量来标识。例如standard CP中的Contacts，我们就用Contacts.People.CONTENT_URI来标识Contacts CP中People这个表。那么要标识某个具体的人怎么办呢？ 这就用到了ContentUris.withAppendedId() 和 Uri.withAppendedPath()。例如我们要表示content://contacts/people/20，那么我们就可以用如下语句：
```java
Uri uri = ContentUris.withAppendedId(People.CONTENT_URI, 20); 
```
或者
```java
Uri uri = Uri.withAppendedPath(People.CONTENT_URI, "20");
```

举些例子，如：
1. 所有联系人的Uri： content://contacts/people
2. 某个联系人的Uri: content://contacts/people/5
3. 所有图片Uri: content://media/external
4. 某个图片的Uri：content://media/external/images/media/4
下面是一些常用的Uri
1. 显示网页:
```java
Uri uri = Uri.parse("http://www.google.com");
Intent it = new Intent(Intent.ACTION_VIEW,uri);
startActivity(it);
```
2. 显示地图:
```java
Uri uri = Uri.parse("geo:38.899533,-77.036476");
Intent it = new Intent(Intent.Action_VIEW,uri);
startActivity(it);
```
3. 路径规划:
```java
Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
Intent it = new Intent(Intent.ACTION_VIEW,URI);
startActivity(it);
```
4. 拨打电话:
```java
Uri uri = Uri.parse("tel:xxxxxx");
Intent it = new Intent(Intent.ACTION_DIAL, uri);
startActivity(it);
```
5. 调用拨号程序
```java
Uri uri = Uri.parse("tel.xxxxxx");
Intent it = new Intent(Intent.ACTION_CALL,uri);
```
要使用这个必须在配置文件中加入<uses-permission id="Android.permission.CALL_PHONE" />
6. 调用发送短信的程序
```java
Intent it = new Intent(Intent.ACTION_VIEW);
it.putExtra("sms_body", "The SMS text");
it.setType("vnd.android-dir/mms-sms");
startActivity(it);
```
7. 发送短信
```java
Uri uri = Uri.parse("smsto:0800000123");
Intent it = new Intent(Intent.ACTION_SENDTO, uri);
it.putExtra("sms_body", "The SMS text");
startActivity(it);
```
8. 发送彩信
```java
Uri uri = Uri.parse("content://media/external/images/media/23");
Intent it = new Intent(Intent.ACTION_SEND);
it.putExtra("sms_body", "some text");
it.putExtra(Intent.EXTRA_STREAM, uri);
it.setType("image/png");
startActivity(it);
```
9. 发送Email
```java
Uri uri = Uri.parse("mailto:xxx@abc.com");
Intent it = new Intent(Intent.ACTION_SENDTO, uri);
startActivity(it);
```
```java
Intent it = new Intent(Intent.ACTION_SEND);
it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
it.putExtra(Intent.EXTRA_TEXT, "The email body text");
it.setType("text/plain");
startActivity(Intent.createChooser(it, "Choose Email Client"));
```
```java
Intent it=new Intent(Intent.ACTION_SEND);
String[] tos={"me@abc.com"};
String[] ccs={"you@abc.com"};
it.putExtra(Intent.EXTRA_EMAIL, tos);
it.putExtra(Intent.EXTRA_CC, ccs);
it.putExtra(Intent.EXTRA_TEXT, "The email body text");
it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
it.setType("message/rfc822");
startActivity(Intent.createChooser(it, "Choose Email Client"));
```
10. 添加附件
```java
Intent it = new Intent(Intent.ACTION_SEND);
it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
it.putExtra(Intent.EXTRA_STREAM, "[url=]file:///sdcard/mysong.mp3[/url]");
it.setType("audio/mp3");
startActivity(Intent.createChooser(it, "Choose Email Client"));
```
11. 播放多媒体
```java
Intent it = new Intent(Intent.ACTION_VIEW);
Uri uri = Uri.parse("[url=]file:///sdcard/song.mp3[/url]");
it.setDataAndType(uri, "audio/mp3");
startActivity(it);
```
```java
Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
Intent it = new Intent(Intent.ACTION_VIEW, uri);
startActivity(it);
```
12. Uninstall 程序
```java
Uri uri = Uri.fromParts("package", strPackageName, null);
Intent it = new Intent(Intent.ACTION_DELETE, uri);
startActivity(it);
```
13. 调用相册
```java
public static final String MIME_TYPE_IMAGE_JPEG = "image/*";
public static final int ACTIVITY_GET_IMAGE = 0;
Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
getImage.addCategory(Intent.CATEGORY_OPENABLE);
getImage.setType(MIME_TYPE_IMAGE_JPEG);
startActivityForResult(getImage, ACTIVITY_GET_IMAGE);
//调用系统相机应用程序，并存储拍下来的照片
Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
time = Calendar.getInstance().getTimeInMillis();
intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/tucue", time + ".jpg")));startActivityForResult(intent, ACTIVITY_GET_CAMERA_IMAGE);
```

14. install apk
```java
Uri installUri = Uri.fromParts("package", "xxx", null);
Intent it = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
```
15. play audio
```java
Uri playUri = Uri.parse("[url=]file:///sdcard/download/everything.mp3[/url]");
Intent it = new Intent(Intent.ACTION_VIEW, playUri);
```
16. 搜索应用
```java
// pkg_name is the full package path for an application
Uri uri = Uri.parse("market://search?q=pname:pkg_name");
Intent it = new Intent(Intent.ACTION_VIEW, uri);
startActivity(it);
```
17. 进入联系人页面
```java
Intent intent = new Intent();
intent.setAction(Intent.ACTION_VIEW);
intent.setData(People.CONTENT_URI);
startActivity(intent);
```
18. 查看指定联系人
```java
//info.id联系人ID
Uri personUri = ContentUris.withAppendedId(People.CONTENT_URI, info.id);
Intent intent = new Intent();
intent.setAction(Intent.ACTION_VIEW);
intent.setData(personUri);
startActivity(intent);
```

