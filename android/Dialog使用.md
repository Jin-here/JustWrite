# Dialog使用
1. AlertDialog
```java
AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
               //.setTitle("你好")
               //.setMessage("你好吗？")
                .setPositiveButton("不好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "不好", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "好", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("fuck", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "fuck", Toast.LENGTH_SHORT).show();
                    }
                })
                /*// 单选框，点击选项对话框消失
                .setItems(R.array.city, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.city)[which], Toast.LENGTH_SHORT).show();
                }
                })*/
                /*// 多选框，点击选项对话框不消失
                .setMultiChoiceItems(R.array.city, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.city)[which] + (isChecked ? "yes" : "no"), Toast.LENGTH_SHORT).show();
                }
                })*/
                /*// 单选框，点击选项对话框不消失
                .setSingleChoiceItems(R.array.city, 1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.city)[which], Toast.LENGTH_SHORT).show();
                }
                })*/
                .setView(getLayoutInflater().inflate(R.layout.dialog_signin, null))
                // 取消时执行，比如点击cancel，点击对话框以外的部分，点击物理返回键，调用cancel()方法
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                // 对话框消失时执行，执行时不一定会调用oncancellistener，必须要满足上述条件
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "dismissed", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        // 去掉标题，如果放在dialogfragment中，还可以使用setStyle(DialogFragment.STYLE_NO_TITLE, 0)方法
        // 不过该方法需放在oncreateView()方法前调用，可以放在oncreate()中
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();
```

2. DialogFragment
使用DialogFragment原因：
Using DialogFragment to manage the dialog ensures that it correctly handles lifecycle events such as when the user presses the Back button or rotates the screen. The DialogFragment class also allows you to reuse the dialog’s UI as an embeddable component in a larger UI, just like a traditional Fragment(such as when you want the dialog UI to appear differently on large and small screens).

3. 例子1——使用默认的dialog
```java
public class FireMissilesDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.dialog_fire_missiles)
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
```


4. 例子2——自定义布局
```java
@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_signin, null))
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
```

5. 将dialogfragment当作fragment使用（可以用fragmenttraction的add(),replace()等方法）
```java
@Nullable
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.dialog_signin, container, false);
}

@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    view.findViewById(R.id.btn_fuck).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {}
    });
}
```
注意：需像fragment一样实例化，不然调用fragmenttraction的方法没用。

应用：
```java
public void showDialog() {
FragmentManager fragmentManager = getSupportFragmentManager();
CustomDialogFragment newFragment = new CustomDialogFragment();

if (mIsLargeLayout) {
    // The device is using a large layout, so show the fragment as a dialog
    newFragment.show(fragmentManager, "dialog");
} else {
    // The device is smaller, so show the fragment fullscreen
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    // For a little polish, specify a transition animation
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    // To make it fullscreen, use the 'content' root view as the container
    // for the fragment, which is always the root view for the activity
    transaction.add(android.R.id.content, newFragment)
               .addToBackStack(null).commit();
}
}
```
附赠大小屏幕判断方法：
res/values/bools.xml
```java
<!-- Default boolean values -->
<resources>
<bool name="large_layout">false</bool>
</resources>
```
res/values-large/bools.xml
```java
<!-- Large screen boolean values -->
<resources>
<bool name="large_layout">true</bool>
</resources>
```

6. ProgressDialog
```java
final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
progressDialog.setIndeterminate(false);
progressDialog.setTitle("你好");
progressDialog.setIcon(R.mipmap.ic_launcher);
progressDialog.setMessage("加载中。。。");
progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
}
});
progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
}
});
progressDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "再说", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
Toast.makeText(MainActivity.this, "再说", Toast.LENGTH_SHORT).show();
}
});
progressDialog.show();
```

7. 一个最简单的progressbar
```java
Dialog dialog = new Dialog(MainActivity.this, R.style.loading_dialog);
dialog.setContentView(getLayoutInflater().inflate(R.layout.dialog_progress, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
dialog.show();

<style name="loading_dialog" parent="android:style/Theme.Dialog">
<item name="android:windowFrame">@null</item>
<item name="android:windowNoTitle">true</item>
<!--<item name="android:windowBackground">@drawable/loading_bg</item>-->
<item name="android:windowBackground">@android:color/transparent</item>
<item name="android:windowIsFloating">true</item>
<item name="android:windowContentOverlay">@null</item>
</style>
```

8. 附上dialog无背景style
```java
<style name="AppTheme_Dialog" parent="android:Theme.Holo.Light.Dialog">
<item name="android:windowNoTitle">true</item>
<item name="android:windowCloseOnTouchOutside">true</item>
<item name="android:windowBackground">@color/full_transparent</item>
</style>
```