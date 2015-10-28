package com.vgaw.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2015/8/29.
 */
public class SimpleChatServer {
    private int port;

    public SimpleChatServer(int port){
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SimpleChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("SimpleChatServer is running...");

            //�붡�˿ڣ���ʼ���ս���������
            ChannelFuture f = b.bind(port).sync();

            //�ȴ�������socket�ر�
            //����������У��ⲻ�ᷢ��������������ŵĹر���ķ�����
            f.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

            System.out.println("SimpleChatServer is closed.");
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 7777;
        new SimpleChatServer(port).run();
    }
}
