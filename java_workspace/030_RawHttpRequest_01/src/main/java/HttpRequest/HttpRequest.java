package HttpRequest;

import protopojo.FlyCatProto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Administrator on 2015/10/23.
 */
public class HttpRequest {
    public void start() throws Exception {
        String uri = "http://127.0.0.1:7778/";

        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        //conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(1000);
        conn.setReadTimeout(1000);
        conn.setRequestProperty("Content-Type", "text/plain");
        conn.setRequestProperty("Connection", "close");

        // info need to send to the server.
        String nickName = "caojin";
        String password = "123";
        FlyCatProto.FlyCat flyCat = FlyCatProto.FlyCat.newBuilder()
                .setFlag(0)
                .addStringV(nickName)
                .addStringV(password)
                .build();

        OutputStream out = conn.getOutputStream();
        out.write(flyCat.toByteArray());
        out.flush();
        out.close();

        InputStream in = null;
        if (conn.getResponseCode() == 200) {
            in = conn.getInputStream();
        } else {
            in = conn.getErrorStream();
        }
        FlyCatProto.FlyCat flyCat1 = FlyCatProto.FlyCat.parseFrom(readInputStream(in));
        System.out.println(flyCat1.getFlag() + ":" + flyCat1.getStringV(0) + ":" + flyCat1.getStringV(1));
    }

    public byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
    public static void main(String[] args) throws Exception {
        new HttpRequest().start();
    }

}
