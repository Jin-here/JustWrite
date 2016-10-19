package com.vgaw.servicetest00;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by caojin on 2016/10/19.
 */

public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("fuck", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("fuck", "onStartCommed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fuck", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("fuck", "onBind");
        return mBinder;
    }

    /*
    // 普通方式
    private MyBinder mBinder = new MyBinder();

    class MyBinder extends Binder{
        public void startDownload(){
            Log.e("fuck", "startDownload");
        }
    }*/

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            return str.toUpperCase();
        }
    };
}
