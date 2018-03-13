package com.vgaw.okhttpdemo;

import android.os.Handler;
import android.support.annotation.MainThread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */

// 网址参考：https://github.com/square/okhttp/wiki/Recipes
public class HttpRequest {
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private static Handler mHandler;
    private static OkHttpClient client;

    @MainThread
    public static void init() {
        client = new OkHttpClient();
        mHandler = new Handler();
    }

    public static Call get(HttpRequestListener listener) {
        Request request = new Request.Builder()
                .url(HttpStatics.URL)
                .build();
        return enqueue(request, listener);
    }

    public static Call sendJson(Object params, HttpRequestListener listener) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, JSON.toJSONString(params));
        Request request = new Request.Builder()
                .url(HttpStatics.URL)
                .post(body)
                .build();
        return enqueue(request, listener);
    }

    public static Call sendForm(HashMap<String, String> params, HttpRequestListener listener) {
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            builder.add(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(HttpStatics.URL)
                .post(builder.build())
                .build();

        return enqueue(request, listener);
    }

    public static Call sendFrom(Object params, HttpRequestListener listener) {
        FormBody.Builder builder = new FormBody.Builder();

        JSONObject json = JSON.parseObject(JSON.toJSONString(params));
        Set<String> keySet = json.keySet();
        for (String key : keySet) {
            builder.add(key, json.getString(key));
        }
        Request request = new Request.Builder()
                .url(HttpStatics.URL)
                .post(builder.build())
                .build();

        return enqueue(request, listener);
    }

    public static Call sendFile(File file, HttpRequestListener listener) {
        Request request = new Request.Builder()
                .url(HttpStatics.URL)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        return enqueue(request, listener);
    }

    private static Call enqueue(Request request, final HttpRequestListener listener) {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final int code = response.code();
                final String s = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            listener.onResponse(s);
                        } else {
                            listener.onFailure();
                        }
                    }
                });
            }
        });
        return call;
    }

    public interface HttpRequestListener {
        void onFailure();

        void onResponse(String response);
    }
}
