package com.vgaw.chat.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2015/9/21.
 */
public abstract class MyHandler extends Handler {
    WeakReference<Activity> mActivity;

    MyHandler(Activity activity) {
        mActivity = new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        Activity theActivity = mActivity.get();
        handleMsgWhat(msg.what, theActivity);
    }

    /**
     * get the reference of member vars by "weak"
     * the "weak" is now the replacement of the activity you put into the constructor
     * @param what
     * @param weak
     */
    public abstract void handleMsgWhat(int what, Activity weak);
}
