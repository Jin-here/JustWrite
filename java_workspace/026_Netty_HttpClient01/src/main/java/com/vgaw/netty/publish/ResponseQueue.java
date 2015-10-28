package com.vgaw.netty.publish;

import com.vgaw.netty.protopojo.FlyCatProto;

/**
 * Created by Administrator on 2015/9/23.
 */
public class ResponseQueue extends Thread {
    private boolean isClose = false;
    private static ResponseQueue instance;

    public static ResponseQueue getInstance(){
        if (instance == null) {
            synchronized (ResponseQueue.class) {
                instance = new ResponseQueue();
            }
        }
        return instance;
    }

    public ResponseQueue(){}

    @Override
    public void run() {
        while (!isClose){
            //ÎÞÏÞÑ­»· loop forever until being closed
        }
    }

    public void addResponse(FlyCatProto.FlyCat flyCat){

        responseHandleListener.onGetData(flyCat);
    }

    private ResponseHandleListener responseHandleListener;


    public void setResponseHandleListener(ResponseHandleListener responseHandleListener){
        this.responseHandleListener = responseHandleListener;
    }

    public void close(){
        isClose = true;
    }
}
