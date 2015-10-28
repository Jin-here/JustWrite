package com.vgaw.netty.channlehandler;

import com.vgaw.netty.protopojo.FlyCatProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.util.List;

/**
 * Created by Administrator on 2015/9/8.
 */
public class FlyCatToFullHttpRequestEncoder extends MessageToMessageEncoder<FlyCatProto.FlyCat> {
    private final URI httpURI;

    public FlyCatToFullHttpRequestEncoder(URI httpURI){
        this.httpURI = httpURI;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, FlyCatProto.FlyCat msg, List<Object> out) throws Exception {
        byte[] byteArray = msg.toByteArray();
        ByteBuf directBuf = ctx.alloc().directBuffer(byteArray.length, byteArray.length).writeBytes(byteArray);

        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.POST, httpURI.toString(), directBuf);
        request.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        HttpHeaderUtil.setKeepAlive(request, false);
        HttpHeaderUtil.setContentLength(request, request.content().readableBytes());

        out.add(request);
    }
}
