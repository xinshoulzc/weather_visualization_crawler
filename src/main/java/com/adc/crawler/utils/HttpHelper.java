package com.adc.crawler.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * @author zhichengliu
 * @create -02-27-11:43
 **/

public class HttpHelper {
    public static String getDataByURL(String url) {
        BufferedReader in = null;
        try {
            System.out.println(new Date().toString() + " Download data from: " + url);
            URL realUrl = new URL(url);
            URLConnection urlConn = realUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) urlConn;
            conn.setConnectTimeout(300000);
            conn.setReadTimeout(30000);
            conn.connect();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            StringBuilder ret = new StringBuilder();
            while((line = in.readLine()) != null) ret.append(line);
            return ret.toString();
        }catch (Exception e) {
            System.out.println("Get Error: " + e);
            e.printStackTrace();
        }
        return null;
    }
    public static String getCsvData(String url) {
        String urlData = getDataByURL(url);
        if(urlData == null){
            System.out.println("URL Error:" + url);
            return null;
        }
        JSONObject jsonData = (JSONObject) JSONValue.parse(urlData);
        System.out.println(jsonData);
        return getDataByURL((String) jsonData.get("Data"));
    }
}
