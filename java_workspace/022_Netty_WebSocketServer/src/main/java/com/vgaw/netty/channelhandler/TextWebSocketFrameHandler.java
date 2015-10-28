package com.vgaw.netty.channelhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * Created by Administrator on 2015/8/31.
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group){
        this.group = group;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
            ctx.pipeline().remove(HttpRequestHandler.class);
            group.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined"));
            group.add(ctx.channel());
        }else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //保留收到的消息，并通过writeAndFlush()传递给所有连接的客户端
        //如果接收到TextWebSocketFrame，调用retain()，并将其写、刷新到ChannelGroup， 使用所有
        //连接的WebSocketChannel都能接收到他。和以前一样，retain()是必须的，因为当messageReceived
        //返回时，TextWebSocketFrame的引用计数器将递减。由于所有操作都是异步的，writeAndFlush()可能会
        //在以后完成，我们不希望它访问无效的引用
        group.writeAndFlush(msg.retain());
    }
}
