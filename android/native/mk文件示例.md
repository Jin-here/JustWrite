## 依赖其他so文件
Android.mk
```java
LOCAL_PATH := $(call my-dir)

# FFmpeg library
PATH_TO_LIBFFMPEG_SO:=$(APP_ABI)

include $(CLEAR_VARS)
LOCAL_MODULE := avcodec
LOCAL_SRC_FILES := $(PATH_TO_LIBFFMPEG_SO)/libavcodec-57.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avutil
LOCAL_SRC_FILES := $(PATH_TO_LIBFFMPEG_SO)/libavutil-55.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swresample
LOCAL_SRC_FILES := $(PATH_TO_LIBFFMPEG_SO)/libswresample-2.so
include $(PREBUILT_SHARED_LIBRARY)

# Program
include $(CLEAR_VARS)
LOCAL_MODULE := djivideojni
LOCAL_SRC_FILES :=dji_video_jni.c
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include
LOCAL_LDLIBS := -llog -lz
LOCAL_SHARED_LIBRARIES := avcodec avutil swresample
include $(BUILD_SHARED_LIBRARY)
```

Application.mk
```java
#APP_ABI := all
#APP_ABI := x86
APP_ABI := armeabi-v7a
APP_PLATFORM := android-21
#APP_ABI := arm64-v8a
```

## C文件引用其他C文件
Android.mk
```java
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := EncryptionTool
LOCAL_SRC_FILES := EncryptionTool.cpp md5.cpp
LOCAL_CPPFLAGS += -fexceptions
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog
include $(BUILD_SHARED_LIBRARY)
```

Application.mk
```java
APP_ABI := armeabi-v7a, arm64-v8a, x86
APP_PLATFORM := android-21
APP_STL:=stlport_static
```