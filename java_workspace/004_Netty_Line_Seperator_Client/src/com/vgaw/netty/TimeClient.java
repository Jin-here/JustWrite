package com.vgaw.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2015/8/12.
 */

/**
 * �ᷢ��ճ��
 */
public class TimeClient {
    public void connect(int port, String host) {
        //���ÿͻ���NIO�߳���
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            //�����첽���Ӳ���
            ChannelFuture f = b.connect(host, port).sync();

            //�ȴ��ͻ�����·�ر�
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //�����˳����ͷ�NIO�߳���
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new TimeClient().connect(7777, "127.0.0.1");
    }
}
