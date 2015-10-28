package com.vgaw.netty.publish;

import com.vgaw.netty.protopojo.FlyCatProto;

/**
 * Created by Administrator on 2015/9/23.
 */
public abstract class AsyncTask001 implements ResponseHandleListener{
    private FlyCatProto.FlyCat flyCat;

    public AsyncTask001(FlyCatProto.FlyCat flyCat){
        this.flyCat = flyCat;

        ResponseQueue.getInstance().setResponseHandleListener(this);

    }

    public void execute(){
        HttpClient.getInstance().sendHttpRequest(this.flyCat);
    }

    //注意：和ResponseQueue在同一个线程，如果需要更新UI，需用handler发送Message.
    public void onGetData(FlyCatProto.FlyCat flyCat){
        //System.out.println(flyCat.getStringV(0) + "::" + flyCat.getStringV(1));
        forAndroid(flyCat);
    }

    protected abstract void forAndroid(FlyCatProto.FlyCat flyCat);

}
