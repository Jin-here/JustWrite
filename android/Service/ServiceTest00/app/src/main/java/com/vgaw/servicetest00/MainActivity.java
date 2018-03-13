package com.vgaw.servicetest00;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private Button btn_stop;
    private Button btn_bind;
    private Button btn_unbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_bind = (Button) findViewById(R.id.btn_bind);
        btn_unbind = (Button) findViewById(R.id.btn_unbind);
        btn_start.setOnClickListener(clickListener);
        btn_stop.setOnClickListener(clickListener);
        btn_bind.setOnClickListener(clickListener);
        btn_unbind.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    Intent startIntent = new Intent(MainActivity.this, MyService.class);
                    startService(startIntent);
                    break;
                case R.id.btn_stop:
                    Intent stopIntent = new Intent(MainActivity.this, MyService.class);
                    stopService(stopIntent);
                    break;
                case R.id.btn_bind:
                    Intent bindIntent = new Intent(MainActivity.this, MyService.class);
                    bindService(bindIntent, connection, BIND_AUTO_CREATE);
                    break;
                case R.id.btn_unbind:
                    unbindService(connection);
                    break;
            }
        }
    };

    /*
    // 普通方式
    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };*/

    private IMyAidlInterface iMyAidlInterface;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                int result = iMyAidlInterface.plus(1, 2);
                String upperStr = iMyAidlInterface.toUpperCase("hello");
                Log.e("fuck", result + ":" + upperStr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
