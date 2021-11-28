package com.geekuniversity.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author create by lx_068
 * HttpClient
 */
public class HttpClientUtils {

    private static final String URL = "https://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html";
    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String getAsString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);

        String var4;
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            var4 = EntityUtils.toString(entity1, "UTF-8");
        } finally {
            response1.close();
        }

        return var4;
    }

    public static void main(String[] args) throws Exception {

        String text = HttpClientUtils.getAsString(URL);
        System.out.println("url: " + URL + " ; response: \n" + text);
    }
}

