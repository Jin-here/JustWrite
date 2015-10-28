package com.vgaw.chat.communication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by Administrator on 2015/9/22.
 * msg.what = 0x00被占用.
 */
/*public abstract class HttpAsyncTask extends AsyncTask001 {
    private InternalHandler handler;

    public HttpAsyncTask(FlyCatProto.FlyCat flyCat){
        super(flyCat);
        handler = new InternalHandler();
    }

    @Override
    protected void forAndroid(FlyCatProto.FlyCat flyCat) {
        Message msg = handler.obtainMessage();
        msg.what = 0x00;
        Bundle bundle = new Bundle();
        bundle.putByteArray("flycat", flyCat.toByteArray());
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    private class InternalHandler extends Handler{
        public InternalHandler(){}

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x00:
                    try {
                        FlyCatProto.FlyCat flyCat = FlyCatProto.FlyCat.parseFrom(msg.getData().getByteArray("flycat"));
                        updateUI(flyCat);
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public abstract void updateUI(FlyCatProto.FlyCat flyCat);

}*/
