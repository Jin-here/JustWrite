package com.environmentalmonitoring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetUtil 
{
	
	public static boolean isNetworkAvailable(Context context)
    {
        boolean isNetworkOK = false;
		try 
		{
	        ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        if (null == conn || null == conn.getActiveNetworkInfo()) 
	        {
	            isNetworkOK = false;
	            //Log.i(TAG, "No Active Network");
	        }
	        else 
	        {
	            isNetworkOK = true;
	            String netType = conn.getActiveNetworkInfo().getTypeName();
	            if ("WIFI".equalsIgnoreCase(netType)) 
	            {
	            	//Log.i(TAG, "Active Network is WIFI");
	            } 
	            else 
	            {
	            	//Log.i(TAG, "Active Network is mobile");
	            }
	        }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        return isNetworkOK;
    }
	
	//采用http post的方式发送jason格式报文
    public static String sendByPost(String url, String params)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Android/1.0");
            conn.setRequestProperty("Content-Type", "application/octet-stream;charset=utf-8");
            conn.setConnectTimeout(20*1000);//设置连接超时
			conn.setReadTimeout(20*1000);//设置读取超时
            conn.setDoOutput(true);
            conn.setDoInput(true);

            //发�?�http body内容
            OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(params);
			osw.flush();
			osw.close();

			//读取服务器端的回应�??
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
            	if(result.equals("")){
            		result += line;
            	} else {
            		result += "\n" + line;
            	}
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //采用http post的方式发送soap格式报文
    public static String sendSoap(String url, String soapAction, String soapXml)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Android/1.0");
            conn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");//mime类型为text/xml
            conn.setRequestProperty("SOAPAction",soapAction);//设置action名称
            conn.setConnectTimeout(20*1000);//设置连接超时
			conn.setReadTimeout(20*1000);//设置读取超时
            conn.setDoOutput(true);
            conn.setDoInput(true);

            //发�?�http body内容
            OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(soapXml);
			osw.flush();
			osw.close();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            //读取服务器端的回应�??
            while ((line = in.readLine()) != null)
            {
            	if(result.equals("")){
            		result += line;
            	} else {
            		result += "\n" + line;
            	}
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
