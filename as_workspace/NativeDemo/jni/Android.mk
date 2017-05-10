LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := SrsFlv
LOCAL_SRC_FILES := SrsFlv.cpp

LOCAL_CPPFLAGS += -fexceptions
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog

include $(BUILD_SHARED_LIBRARY)