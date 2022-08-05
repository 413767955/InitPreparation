package com.zlx.module_network.api1.livedata;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zlx.library_common.provier.AppProvider;
import com.zlx.module_network.interceptor.LogInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zlx on 2017/12/12.
 */
public class RetrofitCreateLiveDataHelper {
    private HashMap<String, String> hashMap;
    private String SIGN = "sign";
    private String TIME = "itme";
    private String Rule = "";

    private static final int TIMEOUT_READ = 60;
    private static final int TIMEOUT_CONNECTION = 60;

    private static RetrofitCreateLiveDataHelper instance;

    public static RetrofitCreateLiveDataHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitCreateLiveDataHelper.class) {
                instance = new RetrofitCreateLiveDataHelper();
            }
        }
        return instance;
    }

    private RetrofitCreateLiveDataHelper() {
    }


    public <T> T create(String baseURL, Class<T> service) {
        return initRetrofitWithLiveData(baseURL, initOkHttp()).create(service);
    }
    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofitWithLiveData(String baseURL,OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 初始化okhttp
     */
    @NonNull
    private OkHttpClient initOkHttp() {
        LogUtils.e("11111111111");
        return new OkHttpClient().newBuilder()
                .readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//设置写入超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                //失败重连
                .retryOnConnectionFailure(true)
                .cookieJar(getCookieJar())
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                //.addInterceptor(new BodyInterceptor())
                .addNetworkInterceptor(networkInterceptor)
                .build();
    }
    private ClearableCookieJar cookieJar;

    public ClearableCookieJar getCookieJar() {
        if (cookieJar == null) {
            cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AppProvider.getInstance().getApp()));
        }
        return cookieJar;
    }

    Interceptor networkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = rebuildRequest(chain.request());

            Response response = chain.proceed(request);
            // 对返回code统一拦截
            if (response.code() == 401) {
                //StartIntentActivity.StartLoginActivity(context, "1");
            } else if (response.code() == 402) {
                //StartIntentActivity.StartForgetPasswordActivity(context);
            } else if (response.code() == 508) {//账号冻结
                //ParentEntity parentEntity = Base.getParentEntity(response.body());
                //StartIntentActivity.startAccountLockActivity(context, parentEntity.getTitle(), parentEntity.getMsg());

                ResponseBody body = ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");
                response = response.newBuilder().body(body).build();
            }
            return response;

        }
    };
    private Request rebuildRequest(Request request) {

        String time = System.currentTimeMillis()/100+"";
        String sign = MD5(hashMap, time);
        String url = request.url().toString();
        //LogUtils.e(url);
        return request.newBuilder()
                .header("client", "Android")
                //.header("versionName", Base.getVersion(context))
                //.header("versionCode", Base.getVersionCode(context) + "")
                .header(SIGN, sign)
                .header(TIME, time)
                .url(url)
                .build();
    }

    public String MD5(HashMap<String, String> hashMap, String time) {
        if (hashMap == null)
            hashMap = new HashMap<>();
        hashMap.put(TIME, time);
        List arrayList = new ArrayList(hashMap.entrySet());

        Collections.sort(arrayList, new Comparator() {
            public int compare(Object arg1, Object arg2) {
                Map.Entry obj1 = (Map.Entry) arg1;
                Map.Entry obj2 = (Map.Entry) arg2;
                return (obj1.getKey()).toString().compareTo(obj2.getKey().toString());
            }
        });
        String sign = "";
        for (Iterator iter = arrayList.iterator(); iter.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iter.next();
            sign = sign + entry.getKey() + "=" + entry.getValue() + ":";

        }
        sign = sign.substring(0, sign.length() - 1) + Rule;
//        LogUtils.e("539",sign);
        String md5 = EncryptUtils.encryptMD2ToString(sign);

        return md5;
    }
}
