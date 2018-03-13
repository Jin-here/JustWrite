# BroadcastReceiver

## LocalBroadcastManager
如果你不需要在不同application之间交流，用LocalBroadcastManager就可以了。这个类会提供更有效的实现（因为没有了进程间通信），且你不需要考虑因与其他application通信而产生的安全问题。

## 注册
你可以通过context.registerReceiver()动态注册或者通过在manifest配置。

## 注意
如果broadcastreceiver在Activity.onResume()中注册，必须在Activity.onPause()中解除注册。
不要在Activity.onSaveInstanceState()中解除注册，because this won’t be called if the user moves back in the history stack.

## 两种broadcast
- Normal broadcasts（通过Context.sendBroadcast()发送），是完全异步的，无序的发送给所有receiver，经常是同时。This is more efficient, but means that receivers cannot use the result or abort APIs included here.

- Ordered broadcasts（通过Context.sendOrderedBroadcast发送），一次发送给一个receiver，因为所有receiver按顺序执行，所以一个receiver可以把broadcast传播给下一个receiver，或者直接abort the broadcast so that it won’t be passed to other receivers. Receivers的执行顺序可以通过android:priority attribute of the matching intent-filter进行设置，同等优先级的receiver同样会无序接收。

## Receiver生命周期
BroadcastReceiver只有在调用onReceive(Context, Intent)时才有效。一旦你从该方法出来，生命周期结束。所以不能所有代码都能放在该方法中运行：所有的异步方法不能放在此方法中，因为在结果返回前，该方法生命周期已经结束；尤其，你不能在其中弹出对话框或者绑定一个service，对于前者来说，你应该用NotificationManager API代替，对于后者，你可以使用Context.startService()来代替。

## Process Lifecycle
正在执行BroadcastReceiver（即正在运行onReceive(Context, Intent)方法）的进程属于foreground process，且会一直运行除非在内存严重不足的情况下。
一旦你从onReceive中退出，其所在进程优先级就会和其他普通进程优先级一样。如果该进程中只有broadcastreceiver，一旦执行完onReceive()方法，该进程就会杀死。这就意味着如果你需要长时间的操作，you will often use a Service in conjunction with a BroadcastReceiver to keep the containing process active for the entire time of your operation.

## 安全
Receivers used with the Context APIs are by their nature a cross-application facility, 所以你必须考虑其他应用滥用你的应用receiver的问题：
- The Intent namespace is global. Make sure that Intent aciton names and other strings are 	written in a namespace you own, or else you may inadvertently conflict with other 	applications.
- 当你调用registerReceiver(BroadcastReceiver, IntentFilter)，任何应用都可以发送	broadcast到你的receiver。你应该通过设置权限进行控制。
- When you publish a receiver in your application’s manifest and specify intent-filters for it, 	any other application can send broadcasts to it regargless of the filters you specify. 为了防	止该情况，可以通过android:exported=”false”进行设置。
- 从ICE_CREM_SANDWITCH开始，你可以通过Intent.setPackage来安全的进行限制
- 权限设置可以在发送或者接收时：发送时可以通过sendBroadcast(Intent, String)或者	sendOrderedBroadcast(Intent, String, BroadcastReceiver, android.os.Handler, int, String , 	Bundle)进行设置。只有授权的receiver（通过设置<uses-permission>）才能接收到；接	收时可以通过registerReceiver(BroadcastReceiver, IntentFilter, String, android.os.Handler)	或者<receiver>中设置。只有授权的broadcast才能发送.
