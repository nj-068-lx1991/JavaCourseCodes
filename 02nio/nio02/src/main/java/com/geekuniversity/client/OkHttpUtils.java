package com.geekuniversity.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by lx_068
 * okhttp
 */
public class OkHttpUtils {

    private static final String URL = "https://square.github.io/okhttp/";

    /**
     * 缓存客户端实例
     */
    public static OkHttpClient client = new OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .build();

    /**
     * GET 调用
     */
    public static String getAsString(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public static void main(String[] args) throws Exception {

        String text = OkHttpUtils.getAsString(URL);
        System.out.println("url: " + URL + " ; response: \n" + text);

        // 清理资源
        OkHttpUtils.client = null;
    }
}