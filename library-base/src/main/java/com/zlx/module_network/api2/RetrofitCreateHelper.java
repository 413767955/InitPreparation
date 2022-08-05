package com.zlx.module_network.api2;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.EncryptUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zlx.library_common.provier.AppProvider;
import com.zlx.module_base.base_util.LanguageUtil;
import com.zlx.module_base.base_util.LogUtils;
import com.zlx.module_base.database.MMkvHelper;
import com.zlx.module_network.factory.ApiCallAdapterFactory;
import com.zlx.module_network.interceptor.LogInterceptor;
import com.zlx.module_network.service.ApiService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Invocation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Zlx on 2017/12/12.
 */
public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 60;
    private static final int TIMEOUT_CONNECTION = 60;

    private static ApiService mApiUrl;
    private static RetrofitCreateHelper instance;

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_DEL = "DELETE";

    private String SIGN = "sign";
    private String TIME = "time";
    private String Rule = "PpsA1E61ZEt39vRcZGAyLoF5u3p5kSi6";
    private String LANGUAGE = "language";//语言环境

    public static RetrofitCreateHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitCreateHelper.class) {
                instance = new RetrofitCreateHelper();
            }
        }
        return instance;
    }

    private RetrofitCreateHelper() {
    }


    public <T> T create(String baseURL, Class<T> service) {
        return initRetrofit(baseURL, initOkHttp()).create(service);
    }


    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(ApiCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     * 初始化okhttp
     */
    @NonNull
    private OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//设置写入超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
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
            LogUtils.e(request.toString());
            Response response = chain.proceed(request);
            // 对返回code统一拦截
            if (response.code() == 400) {
                //StartIntentActivity.StartLoginActivity(context, "1");
            } else if (response.code() == 402) {
                //StartIntentActivity.StartForgetPasswordActivity(context);
            } else if (response.code() == 508) {//账号冻结
                //ParentEntity parentEntity = Base.getParentEntity(response.body());
                //StartIntentActivity.startAccountLockActivity(context, parentEntity.getTitle(), parentEntity.getMsg());

              /*  ResponseBody body = ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");
                response = response.newBuilder().body(body).build();*/
            }
            return response;

        }

        private Request rebuildRequest(Request request) {
            HttpUrl.Builder urlBuilder = request.url().newBuilder();
            HashMap<String, String> map = new HashMap<>();

                if (METHOD_GET.equals(request.method())) { // GET方法
                    HttpUrl httpUrl = urlBuilder.build();
                    // 打印所有get参数
                    Set<String> paramKeys = httpUrl.queryParameterNames();
                    for (String key : paramKeys) {
                        String value = httpUrl.queryParameter(key);
                        map.put(key, value);
                    }
                } else if (METHOD_POST.equals(request.method())) {//POST方法   RequestBody请求
                    RequestBody body = request.body();
                    if (body == null) {
                        LogUtils.e("body==null");
                    }else {
                        Buffer buffer = new Buffer();
                        try {
                            body.writeTo(buffer);
                            Charset charset = Charset.forName("UTF-8");
                            MediaType contentType = body.contentType();
                            if (contentType != null) {
                                charset = contentType.charset(charset);
                            }
                            String params = buffer.readString(charset);
                            params = params.substring(1,params.length()-1);
                            if (!TextUtils.isEmpty(params)){
                                map = getStringToMap(params);
                            }
                            System.out.println(map);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                } else if (METHOD_PUT.equals(request.method())) {//PUT方法，RequestBody请求
                    RequestBody body = request.body();
                    if (body == null) {
                        LogUtils.e("body==null");
                    } else {
                        Buffer buffer = new Buffer();
                        try {
                            body.writeTo(buffer);
                            Charset charset = Charset.forName("UTF-8");
                            MediaType contentType = body.contentType();
                            if (contentType != null) {
                                charset = contentType.charset(charset);
                            }
                            String params = buffer.readString(charset);
                            params = params.substring(1, params.length() - 1);
                            map = getStringToMap(params);
                            System.out.println(map);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else if (METHOD_DEL.equals(request.method())) { // GET方法
                    LogUtils.e("DELETE类型请求");
                    HttpUrl httpUrl = urlBuilder.build();
                    // 打印所有get参数
                    Set<String> paramKeys = httpUrl.queryParameterNames();
                    for (String key : paramKeys) {
                        String value = httpUrl.queryParameter(key);
                        map.put(key, value);
                    }
                }


            String time = (System.currentTimeMillis()/1000)+"";
            String sign = MD5(map, time);
            String url = request.url().toString();
            return request.newBuilder()
                    .addHeader("Authorization", MMkvHelper.getInstance().getAuthorization())
                    .addHeader(SIGN, sign)
                    .addHeader(TIME, time)
                    .addHeader(LANGUAGE,LanguageUtil.getCurrentLanguage().toString())
                    .url(url)
                    .build();
        }
    };
    public String MD5(HashMap<String, String> hashMap, String time) {
        if (hashMap == null)
            hashMap = new HashMap<>();
        //hashMap.put(TIME, time);
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
        if (!TextUtils.isEmpty(sign)) {
            sign = sign.substring(0, sign.length() - 1) + Rule;
        }else {
            sign = Rule;
        }

        String md5 = EncryptUtils.encryptMD5ToString(sign);
        LogUtils.e(sign+"---"+md5.toLowerCase());
        return md5.toLowerCase();
    }
    /**
     *
     * String转map
     * @param str
     * @return
     */
    public static HashMap<String,String> getStringToMap(String str){
        //创建Map对象
        HashMap<String,String> map = new HashMap<>();
        //判断str是否包含image/jpg字段，如果包含该字段的话，表示当前是图片上传，则跳过拼接验签
        if (!str.contains("image/jpg")) {
            //根据逗号截取字符串数组
            String[] str1 = str.split(",");
            //循环加入map集合
            for (int i = 0; i < str1.length; i++) {
                //根据":"截取字符串数组
                String[] str2 = str1[i].split(":");
                //str2[0]为KEY,str2[1]为值
                map.put(str2[0].replace("\"", ""),str2[1].replace("\"", ""));
            }
        }
        return map;
    }
}
