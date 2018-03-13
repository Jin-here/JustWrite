## 基本使用
SharedPreferences是Android应用程序中存储本地数据最简单、快速和高效的方法。他的本质是一个框架，允许用户存储并关联每一个key-value对到用户的应用程序（可以认为是映射到应用程序，以便用户可以随时访问）。此外，由于每个应用程序都与自己的SharedPreferences类相关联，因此被存储以及提交的数据将会在所有用户会话当中得以体现。然而，由于其简单和高效的性质，SharedPreferences只允许用户存储原始数据类型，即boolean、float、float、long、int和String以及Set<String>类型

#### 获取
- context.getSharedPreferences(name, mode);
  - name:指明了要映射到shared preference（每一个应用程序可以拥有几个单独的shared preference，因此，就像在数据库中指定表名一样，必须指明要检索的映射）。
  - mode:指定要检索的shared preference实例的可见性，可选参数如下：
	- MODE_PRIVATE:只有当前的应用程序才能访问映射到内容
	- MODE_MULTI_PROCESS:该模式从API Level 11（Android 3.0版本）开始提供，允许用户使用多个进程修改，可能会写入相同shared preference实例的映射。
- PreferenceManager.getDefaultSharedPreferences();


#### 写
```java
Editor e = sp.edit();
e.putString(key, value);
e.commit();(或者e.apply())
```
commit()和apply()两者区别：
- commit()将修改直接写入硬盘，如果出现多线程存储的情况，以最后修改的为准
- apply()会立刻将修改写入缓存，然后会异步写入硬盘（此时如果写入失败，不会有任何反馈）。如果在异步写入硬盘的过程中出现该SharedPreferences的其他commit()，会等待当前操作完再进行其他操作

#### 读
```java
sp.getString(key, defaultValue);
```

#### 补充
面临在两个应用程序间共享信息的需求，其解决方法是在所涉及的应用程序清单文件中设置
android:sharedUserId。
其工作原理是，每个应用程序在签名和导出时，会自动生成应用程序ID。如果在应用程序清单文件中明确设置了这个ID，假设两个应用程序使用相同的密钥进行签名，那么他们就不需要将其数据暴露给用户手机上其他应用程序就能够自由地访问彼此的数据。换句话说，当两个应用程序设置了相同的ID，这两个（也只有这两个）应用程序可以访问彼此的数据。

## 搭配设置页面使用