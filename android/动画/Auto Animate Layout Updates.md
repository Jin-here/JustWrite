## Auto Animate Layout Updates
使用步骤
1. 在ViewGroup中设置android:animateLayoutChanges为true
```java
<LinearLayout android:id="@+id/container"
    android:animateLayoutChanges="true"
    ...
/>
```
设置完成后，当在ViewGroup中增加，删除，更新等时，将会使用系统默认提供的动画

2. 自定义动画
如果想自定义动画以替换默认动画，创建LayoutTransition，然后通过setLayoutTransition()应用

3. 更多
   - [原文](https://developer.android.com/training/animation/layout.html)