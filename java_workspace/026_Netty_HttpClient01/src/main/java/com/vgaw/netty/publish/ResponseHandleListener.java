package com.vgaw.netty.publish;

import com.vgaw.netty.protopojo.FlyCatProto;

/**
 * Created by Administrator on 2015/9/23.
 */
public interface ResponseHandleListener {
    public void onGetData(FlyCatProto.FlyCat flyCat);
}
