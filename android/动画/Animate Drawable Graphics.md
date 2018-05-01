## Animate Drawable Graphics

### Use AnimationDrawable
使用步骤如下：
1. res/drawable中创建rocket_thrust.xml文件
```java
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true">
    <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust3" android:duration="200" />
</animation-list>
```
参数解释：
- oneshot:false，只循环一次，执行完毕停留在最后一帧；否则无限循环

2. 设置动画
```java
AnimationDrawable rocketAnimation;

public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);

  ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
  rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
  rocketAnimation = (AnimationDrawable) rocketImage.getBackground();

  rocketImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        rocketAnimation.start();
      }
  });
}
```
注意：
- 动画的start()方法不能在onCreate()方法中调用，因为此时
_the AnimationDrawable is not yet fully attached to the window_，你可以选择在onStart()方法中进行调用

3. 更多
- [原文](https://developer.android.com/guide/topics/graphics/drawable-animation.html#AnimationDrawable)
- [更多上述XML语法](https://developer.android.com/guide/topics/resources/animation-resource.html)

### Use AnimatedVectorDrawable
简介
The _AnimatedVectorDrawable_ class animates properties of a _VectorDrawable_ with animations defined using _ObjectAnimator_ or _AnimatorSet_.

其中涉及三个类：
- 矢量图：VectorDrawable
- 动画：ObjectAnimator或者AnimatorSet
- 将动画和矢量图结合在一起的类：AnimatedVectorDrawable

优点
- 由于是矢量图，不会失真
- 从API 25开始，AnimatedVectorDrawable运行在RenderThread(相对于之前API中的UI Thread)，这意味着，即使UI Thread失去响应，该动画仍能正常进行

使用步骤_定义三个XML
1. 在res/drawable定义矢量动画XML：
```java
<!-- res/drawable/vectordrawable.xml -->
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:height="64dp"
    android:width="64dp"
    android:viewportHeight="600"
    android:viewportWidth="600">
    <group
        android:name="rotationGroup"
        android:pivotX="300.0"
        android:pivotY="300.0"
        android:rotation="45.0" >
        <path
            android:name="v"
            android:fillColor="#000000"
            android:pathData="M300,70 l 0,-70 70,70 0,0 -70,70z" />
    </group>
</vector>
```

2. 在res/anim中定义需要使用到的动画
```java
<!-- res/anim/rotation.xml -->
<!-- 该动画等同于ObjectAnimator类 -->
<objectAnimator
    android:duration="6000"
    android:propertyName="rotation"
    android:valueFrom="0"
    android:valueTo="360" />
```
```java
<!-- res/anim/path_morph.xml -->
<!-- 该动画等同于AnimatorSet类 -->
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <objectAnimator
        android:duration="3000"
        android:propertyName="pathData"
        android:valueFrom="M300,70 l 0,-70 70,70 0,0   -70,70z"
        android:valueTo="M300,70 l 0,-70 70,0  0,140 -70,0 z"
        android:valueType="pathType" />
</set>
```
注意：they must have the same number of commands and the same number of parameters for each command

3. 在res/drawable中将动画和矢量图拼装在一起
```java
<!-- res/drawable/animvectordrawable.xml -->
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
  android:drawable="@drawable/vectordrawable" >
    <target
        android:name="rotationGroup"
        android:animation="@anim/rotation" />
    <target
        android:name="v"
        android:animation="@anim/path_morph" />
</animated-vector>
```
说明：其中name表示矢量图中的元素的名称，animation表示对name应用什么动画

使用步骤_定义一个XML
如下所示，该XML将上面3个XML合并到了一起
```java
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
                  xmlns:aapt="http://schemas.android.com/aapt" >
     <aapt:attr name="android:drawable">
         <vector
             android:height="64dp"
             android:width="64dp"
             android:viewportHeight="600"
             android:viewportWidth="600" >
             <group
                 android:name="rotationGroup"
                 android:pivotX="300.0"
                 android:pivotY="300.0"
                 android:rotation="45.0" >
                 <path
                     android:name="v"
                     android:fillColor="#000000"
                     android:pathData="M300,70 l 0,-70 70,70 0,0 -70,70z" />
             </group>
         </vector>
     </aapt:attr>

     <target android:name="rotationGroup"> *
         <aapt:attr name="android:animation">
             <objectAnimator
             android:duration="6000"
             android:propertyName="rotation"
             android:valueFrom="0"
             android:valueTo="360" />
         </aapt:attr>
     </target>

     <target android:name="v" >
         <aapt:attr name="android:animation">
             <set>
                 <objectAnimator
                     android:duration="3000"
                     android:propertyName="pathData"
                     android:valueFrom="M300,70 l 0,-70 70,70 0,0 -70,70z"
                     android:valueTo="M300,70 l 0,-70 70,0  0,140 -70,0 z"
                     android:valueType="pathType"/>
             </set>
         </aapt:attr>
      </target>
 </animated-vector>
```

4. 更多
- [原文](https://developer.android.com/guide/topics/graphics/drawable-animation.html#AnimVector)
- [AnimatedVectorDrawable](https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html)
- [AnimatedVectorDrawableCompat](https://developer.android.com/reference/android/support/graphics/drawable/AnimatedVectorDrawableCompat.html)