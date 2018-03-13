注：设备需和Android Studio在同一无线网络下，假设设备的网址为：xxxx，操作步骤如下：

1. 进入Android Studio terminal
2. adb shell
3. setprop service.adb.tcp.port 5555
4. exit
5. adb tcpip 5555
6. adb connect xxx