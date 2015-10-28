package com.vgaw.netty.channlehandler;

import com.vgaw.netty.protopojo.FlyCatProto;
import com.vgaw.netty.publish.ResponseQueue;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Administrator on 2015/9/10.
 */
public class HttpContentHandler extends SimpleChannelInboundHandler<FlyCatProto.FlyCat> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FlyCatProto.FlyCat msg) throws Exception {
        //System.out.println(msg.getStringV(0) + ":" + msg.getStringV(1));
        ResponseQueue.getInstance().addResponse(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
