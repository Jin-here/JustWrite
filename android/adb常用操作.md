 
# adb常用操作

1. 安装:
```java
adb install 安装包路径
```


2. 卸载:
```java
adb uninstall 软件包名
```


3. 查看安装的软件列表:
```java
adb shell pm list packages
```


4. 查看安装的三方软件列表:
```java
adb shell pm list packages -3
```


5. 输入:
```java
adb shell input text “你想要输入的内容”
```


6. 进入设置页面:
```java
adb shell am start -n com.android.settings/com.android.settings.Settings
```


7. 获取系统版本:
```java
adb shell getprop ro.build.version.sdk
```


8. 获取屏幕分辨率:
```java
adb shell wm size
```


9. 查看可用内存:
```java
adb shell cat /proc/meminfo
```


10. 保存日志:
```java
adb logcat > 目录/logcat.txt
```


11. 清除日志:
```java
adb logcat -c
```


12. 杀死应用:
```java
adb shell am force-stop 软件包名
```


13. 查看页面路径:
```java
adb shell dumpsys activity activities
```


14. 重置电池数据收集:
```java
adb shell dumpsys batterystats --reset
```


15. 转存电池数据:
```java
adb shell dumpsys batterystats > 目录/batterystats.txt
```


16. 生成电池使用报告:
```java
adb bugreport > 目录/bugreport.zip
adb bugreport > 目录/bugreport.txt
```

17. 生成systrace报告:
```java
python  SDK路径/platform-tools/systrace/systrace.py -o 目录/mynewtrace.html
```