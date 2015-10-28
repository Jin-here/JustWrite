package com.vgaw.netty.channlehandler;

import com.vgaw.hibernate.dao.UserDao;
import com.vgaw.hibernate.pojo.User;
import com.vgaw.hibernate.util.HibernateUtil;
import com.vgaw.netty.protopojo.FlyCatProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hibernate.Session;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by Administrator on 2015/9/9.
 */
public class HttpContentHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (HttpHeaderUtil.is100ContinueExpected(msg)) {
            ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
        }
        boolean keepAlive = HttpHeaderUtil.isKeepAlive(msg);

        ByteBuf raw = msg.content();
        byte[] rawArray = new byte[raw.readableBytes()];
        raw.getBytes(raw.readerIndex(), rawArray);

        FlyCatProto.FlyCat flyCat = FlyCatProto.FlyCat.parseFrom(rawArray);
        flyCat = separate(flyCat);

        FullHttpResponse response;
        if (flyCat != null) {
            byte[] byteArray = flyCat.toByteArray();
            ByteBuf directBuf = ctx.alloc().directBuffer(byteArray.length, byteArray.length).writeBytes(byteArray);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, directBuf);
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        } else {
            // no data to send back.
            response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        }

        if (!keepAlive) {
            //response.headers().set(CONNECTION, HttpHeaderValues.CLOSE);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
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

    /**
     * separate the parcel flycats bring
     */
    private FlyCatProto.FlyCat separate(FlyCatProto.FlyCat flyCat) {
        UserDao userDao = new UserDao();

        switch (flyCat.getFlag()) {
            case 1:
                // save user info.
                User user = new User();
                user.setToken(flyCat.getStringV(0));
                user.setName(flyCat.getStringV(1));
                user.setPassword(flyCat.getStringV(2));
                userDao.saveUser(user);
                break;
            case 4:
                // query whether the user is exist.
                User user1 = userDao.isUserExist(flyCat.getStringV(0));
                if (user1 != null) {
                    // exist.
                    return FlyCatProto.FlyCat.newBuilder().setFlag(5)
                            .addBoolV(true)
                            .addStringV(user1.getToken())
                            .addStringV(user1.getName())
                            .addStringV(user1.getPassword()).build();
                }
                return FlyCatProto.FlyCat.newBuilder().setFlag(5).addBoolV(false).build();
            case 40:
                // if password is valid.
                User user2 = userDao.isUserExist(flyCat.getStringV(0));
                if (flyCat.getStringV(1).equals(user2.getPassword())){
                    return FlyCatProto.FlyCat.newBuilder().setFlag(5).addBoolV(true).addStringV(user2.getToken()).addStringV(user2.getName()).addStringV(user2.getPassword()).build();
                }
                return FlyCatProto.FlyCat.newBuilder().setFlag(5).addBoolV(false).build();
        }
        return null;

    }

}
