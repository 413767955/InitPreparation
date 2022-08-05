package com.zlx.module_base.base_api.module;

import com.zlx.module_network.bean.ApiResponse;
import com.zlx.module_network.factory.ApiCall;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 */
public interface LoginApi {


    @POST("sms/send")
    ApiCall<ApiResponse> sendSms(@Body RequestBody body);





}
