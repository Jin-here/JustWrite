package com.vgaw.netty.client;

import com.vgaw.netty.initializer.HttpClientInitializer;
import com.vgaw.netty.protopojo.FlyCatProto;
import com.vgaw.netty.publish.ResponseHandleListener;
import com.vgaw.netty.publish.ResponseQueue;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Administrator on 2015/9/10.
 */
public class HttpClient {
    private final URI httpURI;

    public HttpClient(URI httpURI) {
        this.httpURI = httpURI;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HttpClientInitializer(httpURI));

            while (true){
                sendHttpRequest(bootstrap);
            }

            //channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void sendHttpRequest(Bootstrap bootstrap) throws Exception {
        Channel channels[] = new Channel[1000];
        //Channel channel = bootstrap.connect(this.httpURI.getHost(), this.httpURI.getPort()).sync().channel();
        for (int i = 0;i < 1000;i++){
            channels[i] = bootstrap.connect(this.httpURI.getHost(), this.httpURI.getPort()).sync().channel();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String msg = in.readLine();
        if ("bye".equals(msg)) {
            System.exit(0);
        }

        char[] msgC = msg.toCharArray();
        int i = 0;
        while (msgC[i] != 32) {
            i++;
        }
        String nickName = msg.substring(0, i);
        String password = msg.substring(i + 1);
        FlyCatProto.FlyCat flyCat = FlyCatProto.FlyCat.newBuilder()
                .setFlag(0)
                .addStringV(nickName)
                .addStringV(password)
                .build();

        for (Channel channel : channels)
            channel.writeAndFlush(flyCat);
    }

    public static void main(String[] args) throws Exception {
        ResponseQueue.getInstance().setResponseHandleListener(new ResponseHandleListener() {
            public void onGetData(FlyCatProto.FlyCat flyCat) {
                System.out.println(flyCat.getStringV(0) + "::" + flyCat.getStringV(1));
            }
        });
        ResponseQueue.getInstance().start();

        URI uri = new URI("http://127.0.0.1:7778/");
        HttpClient httpClient = new HttpClient(uri);
        httpClient.run();
    }
}
