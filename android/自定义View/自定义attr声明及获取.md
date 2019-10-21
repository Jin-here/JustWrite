# 自定义attr声明及获取
1. 声明
```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="MyView">
        <attr name="textColor" format="color" />
        <attr name="textSize" format="dimension" />
    </declare-styleable>
</resources>
```


2. 自定义属性格式详解
   1. reference：
      1. 属性定义：<attr name="background" format="reference" />
      2. 属性使用：android:background="@drawable/图片ID"
   2. color
      1. 属性定义：<attr name="textColor" format="color" />
      2. 属性使用：android:textColor="#00FF00"
   3. boolean
      1. 属性定义：<attr name="focusable" format="boolean" />
      2. 属性使用：android:focusable="true"
   4. dimension
      1. 属性定义：<attr name="layout_width" format="dimension" />
      2. 属性使用：android:layout_width="42dip"
   5. float
      1. 属性定义：<attr name="fromAlpha" format="float" />
      2. 属性使用：android:fromAlpha="1.0"
   6. integer
      1. 属性定义：<attr name="frameDuration" format="integer" />
      2. 属性使用：android:frameDuration="100"
   7. string
      1. 属性定义：<attr name="apiKey" format="string" />
      2. 属性使用：android:apiKey="0jOkQ80oD1JL9C6HAja99uGXCRiS2CGjKO_bc_g"
   8. float
      1. 属性定义：<attr name="fromDegrees" format="float" />
      2. 属性使用：android:fromDegrees="0"
   9. fraction
      1. 属性定义：<attr name="fromDegrees" format="fraction" />
      2. 属性使用：android:fromDegrees="50%"
   10. enum
      1. 属性定义：
         <attr name="orientation">
             <enum name="horizontal" value="0" />
             <enum name="vertical" value="1" />
         </attr>
      2. 属性使用：android:orientation="vertical"
   11. flag(位或运算)
      1. 属性定义：
         <attr name="windowSoftInputMode">
             <flag name="stateUnspecified" value="0" />
             <flag name="stateUnchanged" value="1" />
             <flag name="stateHidden" value="2" />
             <flag name="stateAlwaysHidden" value="3" />
             <flag name="stateVisible" value="4" />
             <flag name="stateAlwaysVisible" value="5" />
             <flag name="adjustUnspecified" value="0x00" />
             <flag name="adjustResize" value="0x10" />
             <flag name="adjustPan" value="0x20" />
             <flag name="adjustNothing" value="0x30" />
         </attr>
      2. 属性使用：android:windowSoftInputMode="stateUnspecified | stateUnchanged　|　stateHidden"
   12. 注意，属性定义时可以指定多种类型值，如：
      1. 属性定义：<attr name="background" format="reference|color" />
      2. 属性使用：android:background="@drawable/图片ID|#00FF00"


3. 获取
```java
TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);

int textColor = a.getColor(R.styleable.MyView_textColor, 0xFFFFFFFF);
float textSize = a.getDimension(R.styleable.MyView_textSize, 36);

a.recycle();
```