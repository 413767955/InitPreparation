package com.zlx.module_base.base_api.module;

import com.zlx.module_base.base_api.res_data.UpdataVersionRes;
import com.zlx.module_base.base_api.res_data.base.CustomerServiceRes;
import com.zlx.module_network.bean.ApiResponse;
import com.zlx.module_network.factory.ApiCall;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @Description:
 * @Author: lyl
 * @Date: 2022/6/20 16:22
 */
public interface PublicApi {

    /**
     * 版本信息
     *
     * @param hashMap
     * @return
     */
    @GET("app/upgrade")
    ApiCall<UpdataVersionRes> upgrade(@QueryMap HashMap<String, String> hashMap);

    @GET("customer/services")
    ApiCall<CustomerServiceRes> customerServices(@QueryMap HashMap<String, String> hashMap);
}
