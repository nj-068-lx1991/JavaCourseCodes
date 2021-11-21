package com.geekuniversity.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author Created by lx_068
 * HttpClient 访问服务器地址
 */
public class HttpClient {

    public final static String URL = "http://127.0.0.1:8801/hello.html";
    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * GET 调用
     *
     * @param url 路径
     * @return java.lang.String
     * @throws IOException 流异常
     */
    public static String getAsString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            return EntityUtils.toString(entity1, "UTF-8");
        } finally {
            response1.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String text = HttpClient.getAsString(URL);
        System.out.println("url: " + URL + " ; response: \n" + text);
    }
}
