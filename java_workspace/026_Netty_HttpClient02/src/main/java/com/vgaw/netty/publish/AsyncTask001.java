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

    //ע�⣺��ResponseQueue��ͬһ���̣߳������Ҫ����UI������handler����Message.
    public void onGetData(FlyCatProto.FlyCat flyCat){
        //System.out.println(flyCat.getStringV(0) + "::" + flyCat.getStringV(1));
        forAndroid(flyCat);
    }

    protected abstract void forAndroid(FlyCatProto.FlyCat flyCat);

}
