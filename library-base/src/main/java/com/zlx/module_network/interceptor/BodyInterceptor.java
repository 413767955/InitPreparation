package com.zlx.module_network.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description: 公共参数拦截器
 */
public class BodyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String selfID = "";
        String token = "";
        HttpUrl url = originalRequest.url()
                .newBuilder()
                //.addQueryParameter("userId", selfID)
                .build();
        Log.e("TAG", "统一参数： " + selfID + "   " + token+"   "+url);
        Request authorised = originalRequest.newBuilder()
                //.header("Authorization", selfID + token)
                .url(url)
                .build();

        return chain.proceed(authorised);
    }
}
/*public class RetrofitAPIManager {
    public static final String SERVER_URL = "url";
    public static ClientAPI provideClientApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .client(genericClient())
                .build();
        return retrofit.create(ClientAPI.class);
    }
    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Accept", "")
                                .addHeader("Cookie", "add cookies here")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient; }}*/
