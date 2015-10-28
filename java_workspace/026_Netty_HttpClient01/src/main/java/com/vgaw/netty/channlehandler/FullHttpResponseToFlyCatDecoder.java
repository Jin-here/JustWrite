package com.vgaw.netty.channlehandler;

import com.vgaw.netty.protopojo.FlyCatProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.List;

/**
 * Created by Administrator on 2015/9/8.
 */
public class FullHttpResponseToFlyCatDecoder extends MessageToMessageDecoder<FullHttpResponse> {
    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpResponse msg, List<Object> out) throws Exception {
        ByteBuf raw = msg.content();
        int length = raw.readableBytes();
        byte[] rawArray = new byte[length];
        raw.getBytes(raw.readerIndex(), rawArray);

        out.add(FlyCatProto.FlyCat.parseFrom(rawArray));
    }
}
