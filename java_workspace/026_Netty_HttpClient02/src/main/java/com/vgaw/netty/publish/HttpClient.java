package com.vgaw.netty.publish;

import com.vgaw.netty.initializer.HttpClientInitializer;
import com.vgaw.netty.protopojo.FlyCatProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.URI;

/**
 * Created by Administrator on 2015/9/22.
 */
public class HttpClient {
    private static HttpClient instance;

    private URI httpURI;
    private Bootstrap bootstrap;
    private EventLoopGroup group;

    public static HttpClient getInstance(){
        if (instance == null) {
            synchronized (HttpClient.class) {
                instance = new HttpClient();
            }
        }
        return instance;
    }

    private HttpClient(){}

    /**
     * ���û�����ʩ��ֻ�ܵ���һ�Σ��������֮ǰ�����release()�ͷ�.
     * @param httpURI
     */
    public void boot(URI httpURI){
        this.httpURI = httpURI;
        ResponseQueue.getInstance().start();

        run();
    }

    private void run() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HttpClientInitializer(this.httpURI));
    }

    /**
     * release the resource, must be called before you application is end.
     * �ͷ���Դ���ڳ������֮ǰ�������ͷ�
     */
    public void release() {
        if (group != null) {
            group.shutdownGracefully();
        }
        if (ResponseQueue.getInstance().isAlive()){
            ResponseQueue.getInstance().close();
        }
    }

    /**
     * send request to the server.
     * ���������������
     * @param flyCat
     * @throws Exception
     */
    public void sendHttpRequest(FlyCatProto.FlyCat flyCat) {
        Channel channel = null;
        try {
            channel = bootstrap.connect(this.httpURI.getHost(), this.httpURI.getPort()).sync().channel();
            channel.writeAndFlush(flyCat);
        } catch (InterruptedException e) {
            group.shutdownGracefully();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        URI uri = new URI("http://100.66.78.221:7778/");
        HttpClient.getInstance().boot(uri);

        new AsyncTask001(FlyCatProto.FlyCat.newBuilder().setFlag(0).addStringV("caojin").addStringV("123").build()) {
            @Override
            protected void forAndroid(FlyCatProto.FlyCat flyCat) {
                System.out.println(flyCat.getStringV(0) + "::" + flyCat.getStringV(1));
            }
        }.execute();

        while (true);
        //HttpClient.getInstance().release();
    }

}
