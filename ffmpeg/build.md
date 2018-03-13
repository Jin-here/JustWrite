## FFmpeg编译
#### MinGW
1. 下载MinGW：https://sourceforge.net/projects/mingw/
2. 运行，安装下列组件：
   - C Compiler
   - C++ Compiler
   - MSYS Basic system
   - MinGW Developer ToolKit
3. 下载完成进入MinGW/msys/1.0/文件夹，运行msys.bat，其用来执行下述的脚本


#### 下载ffmpeg
进入[下载页面](http://ffmpeg.org)，点击Download进行下载


#### 支持x264(脚本参考：https://github.com/dxjia/ffmpeg-compile-shared-library-for-android.git)
1. 下载：http://www.videolan.org/developers/x264.html
2. 解压复制至与ffmpeg文件夹同等级目录下，重命名x264解压文件夹名称为"x264"
3. 复制仓库中x264文件夹中脚本至x264目录下
4. 更改脚本中相关个性化配置：如NDK目录等
5. 运行build_android_all.sh：./build_android_all.sh
6. 执行完毕将在x264目录中生成名称为"android"的文件夹


#### 编译FFmpeg(脚本参考：https://github.com/dxjia/ffmpeg-compile-shared-library-for-android.git)
1. 修改ffmpeg文件夹中configure文件：
替换：
```java
SLIBNAME_WITH_MAJOR='$(SLIBNAME).$(LIBMAJOR)'
LIB_INSTALL_EXTRA_CMD='＄＄(RANLIB) "$(LIBDIR)/$(LIBNAME)"'
SLIB_INSTALL_NAME='$(SLIBNAME_WITH_VERSION)'
SLIB_INSTALL_LINKS='$(SLIBNAME_WITH_MAJOR) $(SLIBNAME)'
```
为：
```java
SLIBNAME_WITH_MAJOR='$(SLIBPREF)$(FULLNAME)-$(LIBMAJOR)$(SLIBSUF)'
LIB_INSTALL_EXTRA_CMD='＄＄(RANLIB)"$(LIBDIR)/$(LIBNAME)"'
SLIB_INSTALL_NAME='$(SLIBNAME_WITH_MAJOR)'
SLIB_INSTALL_LINKS='$(SLIBNAME)'
```
2. 复制仓库中ffmpeg文件夹中脚本至ffmpeg目录下
3. 更改脚本中相关个性化配置：如NDK目录等
4. 运行build_android_all.sh: ./build_android_all.sh
5. 执行完毕将在ffmpeg目录中生成名称为"android"的文件夹


#### 集成进Android(NDK)
1. 复制ffmpeg中生成的android文件夹中例如armabi中lib文件夹中的带版本号的so文件至module name/src/main/jni/lib文件夹中
2. 复制ffmpeg中生成的android文件夹中例如armabi中include文件夹至module name/src/main/include文件夹中
3. 配置Android.mk:
```java
LOCAL_PATH := $(call my-dir)

# FFmpeg library
include $(CLEAR_VARS)
LOCAL_MODULE := avcodec
LOCAL_SRC_FILES := libavcodec-57.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avdevice
LOCAL_SRC_FILES := libavdevice-57.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avfilter
LOCAL_SRC_FILES := libavfilter-6.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avformat
LOCAL_SRC_FILES := libavformat-57.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avutil
LOCAL_SRC_FILES := libavutil-55.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := postproc
LOCAL_SRC_FILES := libpostproc-54.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swresample
LOCAL_SRC_FILES := libswresample-2.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swscale
LOCAL_SRC_FILES := libswscale-4.so
include $(PREBUILT_SHARED_LIBRARY)

# Program
include $(CLEAR_VARS)
PATH_TO_LIBFFMPEG_SO=$(LOCAL_PATH)
LOCAL_MODULE := your_name
LOCAL_SRC_FILES :=your_name.c
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include
LOCAL_LDLIBS := -llog -lz
LOCAL_SHARED_LIBRARIES := avcodec avdevice avfilter avformat avutil postproc swresample swscale
include $(BUILD_SHARED_LIBRARY)
```

#### 集成进Android(CMake)
```java
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html
# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.4.1)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
add_library( # Sets the name of the library.
             native-lib
             # Sets the library as a shared library.
             SHARED
             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp )
add_library( avcodec-57
             SHARED
             IMPORTED )
set_target_properties( avcodec-57
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavcodec-57.so )
add_library( avfilter-6
             SHARED
             IMPORTED )
set_target_properties( avfilter-6
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavfilter-6.so )
add_library( avformat-57
             SHARED
             IMPORTED )
set_target_properties( avformat-57
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavformat-57.so )
add_library( avutil-55
             SHARED
             IMPORTED )
set_target_properties( avutil-55
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavutil-55.so )
add_library( swresample-2
             SHARED
             IMPORTED )
set_target_properties( swresample-2
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libswresample-2.so )
add_library( swscale-4
             SHARED
             IMPORTED )
set_target_properties( swscale-4
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libswscale-4.so )
include_directories( libs/include )
find_library( # Sets the name of the path variable.
              log-lib
              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries( # Specifies the target library.
                       native-lib
                       avcodec-57
                       avfilter-6
                       avformat-57
                       avutil-55
                       swresample-2
                       swscale-4
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
```