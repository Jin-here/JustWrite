package com.vgaw.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;

/**
 * Created by Administrator on 2015/8/29.
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * Gets called after the {@link ChannelHandler} was added to the actual context and it's ready to handle events.
     * 服务器端收到客户端连接时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("lifecycle-----handlerAdded-----");

    }

    /**
     * Gets called after the {@link ChannelHandler} was removed from the actual context and it doesn't handle events
     * anymore.
     * 服务器端收到客户端断开时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("lifecycle-----handlerRemoved-----");

        Channel incoming = ctx.channel();
        for (Channel channel : channels){
            channel.writeAndFlush("[SEVER] - " + incoming.remoteAddress() + " go away\n");
        }
        channels.remove(ctx.channel());
    }

    /**
     * 服务器端读到客户端写入信息时触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels){
            if (channel != incoming){
                String msg0 = "[" + incoming.remoteAddress() + "]" + msg + "\n";
                channel.writeAndFlush(Unpooled.copiedBuffer(msg0, CharsetUtil.UTF_8));
            }else {
                String msg0 = "[you]" + msg + "\n";
                channel.writeAndFlush(Unpooled.copiedBuffer(msg0, CharsetUtil.UTF_8));
            }
        }
    }

    /**
     * The {@link Channel} of the {@link ChannelHandlerContext} is now active
     * 服务器端监听到客户端活动时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("inbound-----channelActive-----");

        Channel incoming = ctx.channel();

        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "online");

        for (Channel channel : channels){
            channel.writeAndFlush(Unpooled.copiedBuffer("[SERVER] - " + incoming.remoteAddress() + " join in\n", CharsetUtil.UTF_8));
        }
        channels.add(ctx.channel());

    }

    /**
     * The {@link Channel} of the {@link ChannelHandlerContext} was registered is now inactive and reached its
     * end of lifetime.
     * 服务端监听到客户端不活动时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("inbound-----channelInactive-----");

        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "offline");
    }

    /**
     * Gets called if a {@link Throwable} was thrown.
     * 当Netty由于IO错误或者处理器在处理时间是抛出的异常（可能可以在关闭连接之前发送一个错误码的响应消息）
     * 捕获的异常应该被记录下来并且把关联的channel给关闭掉
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //System.out.println("inbound-----exceptionCaught-----");

        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "exception");
        //当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * The {@link Channel} of the {@link ChannelHandlerContext} was registered with its {@link EventLoop}
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("inbound-----channelRegistered-----");
    }

    /**
     * The {@link Channel} of the {@link ChannelHandlerContext} was unregistered from its {@link EventLoop}
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("inbound-----channelUnregistered-----");
    }

    /**
     * Invoked when the current {@link Channel} has read a message from the peer.
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //System.out.println("inbound-----chanelRead-----");
    }

    /**
     * Invoked when the last message read by the current read operation has been consumed by
     * {@link #channelRead(ChannelHandlerContext, Object)}.  If {@link ChannelOption#AUTO_READ} is off, no further
     * attempt to read an inbound data from the current {@link Channel} will be made until
     * {@link ChannelHandlerContext#read()} is called.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("inbound-----channelReadComplete-----");
    }

    /**
     * Gets called if an user event was triggered.
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //System.out.println("inbound-----userEventTriggered-----");
    }

    /**
     * Gets called once the writable state of a {@link Channel} changed. You can check the state with
     * {@link Channel#isWritable()}.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("inbound-----channelWritabilityChanged-----");
    }

    /**
     * Called once a bind operation is made.
     * @param ctx
     * @param localAddress
     * @param promise
     * @throws Exception
     */
    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        //System.out.println("outbound-----bind-----");
    }

    /**
     * Called once a connect operation is made.
     * @param ctx
     * @param remoteAddress
     * @param localAddress
     * @param promise
     * @throws Exception
     */
    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        //System.out.println("outbound-----connect-----");
    }

    /**
     * Called once a disconnect operation is made.
     * @param ctx
     * @param promise
     * @throws Exception
     */
    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        //System.out.println("outbound-----disconnect-----");
    }

    /**
     * Called once a close operation is made.
     * @param ctx
     * @param promise
     * @throws Exception
     */
    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        //System.out.println("outbound-----close-----");
    }

    /**
     * Called once a deregister operation is made from the current registered {@link EventLoop}.
     * @param ctx
     * @param promise
     * @throws Exception
     */
    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        //System.out.println("outbound-----deregister-----");
    }

    /**
     * Intercepts {@link ChannelHandlerContext#read()}.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("outbound-----read-----");
    }

    /**
     * Called once a write operation is made. The write operation will write the messages through the
     * {@link ChannelPipeline}. Those are then ready to be flushed to the actual {@link Channel} once
     * {@link Channel#flush()} is called
     * @param ctx
     * @param msg
     * @param promise
     * @throws Exception
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //System.out.println("outbound-----write-----");
    }

    /**
     * Called once a flush operation is made. The flush operation will try to flush out all previous written messages
     * that are pending.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("outbound-----flush-----");
    }


}
