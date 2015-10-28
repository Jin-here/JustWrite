package com.vgaw.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;

/**
 * Created by Administrator on 2015/8/12.
 */

/**
 * �ᷢ��ճ��
 */
public class TimeServer {
    public void bind(int port) throws Exception{
        //���÷���˵�NIO�߳���
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            //�󶨶˿ڣ�ͬ���ȴ��ɹ�
            ChannelFuture f = b.bind(port).sync();

            //�ȴ�����˼����˿ڹر�
            f.channel().closeFuture().sync();
        }finally {
            //�����˳����ͷ��̳߳���Դ
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<io.netty.channel.socket.SocketChannel> {

        @Override
        protected void initChannel(io.netty.channel.socket.SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws Exception{
        new TimeServer().bind(7777);
    }

    private class TimeServerHandler extends ChannelHandlerAdapter {
        private int counter;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
            System.out.println("The time server receive order:" + body
                + "; the counter is:" + ++counter);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
            ctx.writeAndFlush(resp);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}
