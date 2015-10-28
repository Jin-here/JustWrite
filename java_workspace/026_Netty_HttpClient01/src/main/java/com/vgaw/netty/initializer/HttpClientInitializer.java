package com.vgaw.netty.initializer;

import com.vgaw.netty.channlehandler.FlyCatToFullHttpRequestEncoder;
import com.vgaw.netty.channlehandler.FullHttpResponseToFlyCatDecoder;
import com.vgaw.netty.channlehandler.HttpContentHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

import java.net.URI;

/**
 * Created by Administrator on 2015/9/10.
 */
public class HttpClientInitializer extends ChannelInitializer<Channel> {
    private final URI httpURI;

    public HttpClientInitializer(URI httpURI){
        this.httpURI = httpURI;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //----------------FullHttpResponse(FullHttpRequest)-----------------------//
        //-HttpResponse-+-HttpContent-+-HttpContent-+-LastHttpContent-------------//
        pipeline.addLast(new HttpClientCodec());

        //Decompresses an HttpMessage and an HttpContent compressed in gzip or deflate encoding.
        //pipeline.addLast(new HttpContentDecompressor());

        pipeline.addLast(new HttpObjectAggregator(64 * 1024));

        pipeline.addLast(new FullHttpResponseToFlyCatDecoder());

        pipeline.addLast(new HttpContentHandler());

        pipeline.addLast(new FlyCatToFullHttpRequestEncoder(httpURI));
    }
}
