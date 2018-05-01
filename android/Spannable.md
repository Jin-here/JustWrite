## 基本代码
```java
SpannableString spannableString = new SpannableString();
spannableString.setSpan();
tv.setText(spannableString);
```
setSpan()参数介绍
- 第一个参数为具体的span，见下文“span分类”
- 第二个参数为样式应用的开始位置
- 第三个参数为样式应用的结束为止
- 第四个参数为flags，见下文“Spanned flag常用值”

## Spanned flag常用值
- Spanned.SPAN_INCLUSIVE_INCLUSIVE：在指定范围_前后_插入新字符，都会应用新样式
- Spanned.SPAN_EXCLUSIVE_EXCLUSIVE：在指定范围_前后_插入新字符，样式_无变化_
- Spanned.SPAN_INCLUSIVE_EXCLUSIVE：在指定范围_前_插入新字符，应用新样式
- Spanned.SPAN_EXCLUSIVE_INCLUSIVE：在指定范围_后_插入新字符，应用新样式

更多其他值的介绍，[GO](https://www.jianshu.com/p/1956e15c9a27)

## span分类

### 设置字体样式(TypefaceSpan)
构造函数参数说明：
1. family，可选值为
   - Typeface.DEFAULT：默认，正常形态
   - Typeface.DEFAULT_BOLD：粗体；Note: this may be not actually be
     bold, depending on what fonts are installed. Call getStyle() to know
     for sure.
   - Typeface.SANS_SERIF：无衬线字体
   - Typeface.SERIF：衬线字体
   - Typeface.MONOSPACE：等宽字体

### 设置字体的粗体，斜体，粗斜体(StyleSpan)
构造函数参数说明：
1. style，可选值为
   - Typeface.NORMAL：正常
   - Typeface.BOLD：加粗
   - Typeface.ITALIC：倾斜
   - Typeface.BOLD_ITALIC：加粗倾斜

### 设置字体的绝对大小(AbsoluteSizeSpan)
构造函数参数说明：
1. size：字体的大小
2. dip：size单位是否为dip

### 设置字体的相对大小(RelativeSizeSpan)
构造函数参数说明：
1. proportion：表示为默认字体大小的多少倍

### 设置前景色(ForegroundColorSpan)
构造函数参数说明：
1. color：颜色

### 设置前景色(BackgroundColorSpan)
构造函数参数说明：
1. color：颜色

### 设置超链接(URLSpan)
构造函数参数说明：
1. url，可以为
   - 电话：tel:12345678
   - 邮件：mailto:android@google.com
   - 短信：sms（或者smsto）:12345678
   - 彩信：mms（或者mmsto）:12345678
   - 地图：geo:38.899533,-77.036476

注意：TextView需要设置setMovementMethod()以监听超链接事件

## 设置拉伸字体(ScaleXSpan)
构造函数参数说明：
1. proportion：相较于默认字体的拉伸比例

### 其他无参构造函数span
- 下换线：UnderlineSpan
- 上划线：StrikethroughSpan
- 下标：SubscriptSpan
- 上标：SuperscriptSpan
