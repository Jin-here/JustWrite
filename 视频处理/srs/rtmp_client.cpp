//
// Created by dell on 2018/1/17.
//

#include <jni.h>
#include <srs_librtmp.h>
#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <android/log.h>
#include "RTMPClient.h"

#define TAG "rtmp_client"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

srs_rtmp_t rtmp;
bool connected = false;

void disconnect() {
    if (connected) {
        connected = false;

        srs_rtmp_destroy(rtmp);
        LOGD("destroy stream");
    }
}

JNIEXPORT jint JNICALL Java_Your_package_name_RTMPClient_connect
        (JNIEnv *env, jobject obj, jstring url) {
    // connect rtmp context
    const char *url_c = env->GetStringUTFChars(url, 0);
    rtmp = srs_rtmp_create(url_c);
    env->ReleaseStringUTFChars(url, url_c);

    if (srs_rtmp_handshake(rtmp) != 0) {
        LOGD("simple handshake failed.");
        goto rtmp_destroy;
    }
    LOGD("simple handshake success");

    if (srs_rtmp_connect_app(rtmp) != 0) {
        LOGD("connect vhost/app failed.");
        goto rtmp_destroy;
    }
    LOGD("connect vhost/app success");

    if (srs_rtmp_publish_stream(rtmp) != 0) {
        LOGD("publish stream failed.");
        goto rtmp_destroy;
    }
    LOGD("publish stream success");
    connected = true;
    return 0;

    rtmp_destroy:
    disconnect();

    return -1;
}

JNIEXPORT void JNICALL Java_Your_package_name_RTMPClient_disconnect
        (JNIEnv *env, jobject obj) {
    disconnect();
}

JNIEXPORT jint JNICALL Java_Your_package_name_RTMPClient_writeH264RawFrame
        (JNIEnv *env, jobject obj, jbyteArray frame, jint size, jlong dts, jlong pts) {
    jbyte* frame_c = env->GetByteArrayElements(frame, NULL);
    int ret = srs_h264_write_raw_frames(rtmp, (char *) frame_c, size, dts, pts);
    if (ret != 0) {
        if (srs_h264_is_dvbsp_error(ret)) {
            LOGD("ignore drop video error, code=%d", ret);
        } else if (srs_h264_is_duplicated_sps_error(ret)) {
            LOGD("ignore duplicated sps, code=%d", ret);
        } else if (srs_h264_is_duplicated_pps_error(ret)) {
            LOGD("ignore duplicated pps, code=%d", ret);
        } else {
            LOGD("send h264 raw data failed. ret=%d", ret);
            disconnect();
        }
    }

    env->ReleaseByteArrayElements(frame, frame_c, 0);
    return ret;
}