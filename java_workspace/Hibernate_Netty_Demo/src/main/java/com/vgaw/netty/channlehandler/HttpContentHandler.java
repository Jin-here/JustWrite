package com.vgaw.netty.channlehandler;

import com.alibaba.fastjson.JSON;
import com.vgaw.hibernate.dao.UserDao;
import com.vgaw.hibernate.pojo.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;

import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by Administrator on 2015/9/9.
 */
public class HttpContentHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (HttpHeaders.is100ContinueExpected(msg)) {
            ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
        }
        boolean keepAlive = HttpHeaders.isKeepAlive(msg);

        ByteBuf raw = msg.content();
        byte[] rawArray = new byte[raw.readableBytes()];
        raw.getBytes(raw.readerIndex(), rawArray);

        String jsonResponse = null;
        // json数据
        jsonResponse = new String(rawArray, "UTF-8");
        System.out.println(jsonResponse == null ? "null" : jsonResponse);
        jsonResponse = separateJson(jsonResponse);

        FullHttpResponse response = null;
        if (jsonResponse != null) {
            byte[] byteArray = jsonResponse.getBytes();
            ByteBuf directBuf = ctx.alloc().directBuffer(jsonResponse.length(), jsonResponse.length()).writeBytes(byteArray);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, directBuf);
            response.headers().set("content-type", "text/plain");
            response.headers().set("content-length", response.content().readableBytes());
        } else {
            // no data to send back.
            response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            response.headers().set("content-length", response.content().readableBytes());
        }

        if (!keepAlive) {
            //response.headers().set(CONNECTION, HttpHeaderValues.CLOSE);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set("connection", "keep-alive");
            ctx.writeAndFlush(response);
        }


    }

    /**
     * Invoked when the last message read by the current read operation has been consumed by
     * {@link #channelRead(ChannelHandlerContext, Object)}.  If {@link ChannelOption#AUTO_READ} is off, no further
     * attempt to read an inbound data from the current {@link Channel} will be made until
     * {@link ChannelHandlerContext#read()} is called.
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private String separateJson(String json) {
        User user = JSON.parseObject(json, User.class);
        UserDao dao = new UserDao();
        dao.saveUser(user);
        return "ok";
    }

}
