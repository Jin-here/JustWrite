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
        //�����յ�����Ϣ����ͨ��writeAndFlush()���ݸ��������ӵĿͻ���
        //������յ�TextWebSocketFrame������retain()��������д��ˢ�µ�ChannelGroup�� ʹ������
        //���ӵ�WebSocketChannel���ܽ��յ���������ǰһ����retain()�Ǳ���ģ���Ϊ��messageReceived
        //����ʱ��TextWebSocketFrame�����ü��������ݼ����������в��������첽�ģ�writeAndFlush()���ܻ�
        //���Ժ���ɣ����ǲ�ϣ����������Ч������
        group.writeAndFlush(msg.retain());
    }
}
