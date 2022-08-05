package com.zlx.module_base.base_api.module;

import com.zlx.module_base.base_api.res_data.MsgRes;
import com.zlx.module_network.factory.ApiCall;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;

/**
 *
 */
public interface UserApi {

//    @GET("user")
//    ApiCall<UserInfo> user(@QueryMap HashMap<String,String> map);

    @POST("user/address")
    ApiCall<MsgRes> address(@Body RequestBody body);

    /**
     * 地址列表
     */
    @GET("user/address")
    ApiCall<Object> addressList(@QueryMap HashMap<String,String> map);

    /**
     * 获取默认地址
     * @param map
     * @return
     */
//    @GET("user/address/default")
//    ApiCall<DefaultAddressBean> defaultAddress(@QueryMap HashMap<String,String> map);

    /**
     * 修改密码（忘记密码）
     * @param body
     * @return
     */
    @POST("forgetPwd")
    ApiCall<Object> forgetPwd(@Body RequestBody body);

    /**
     * 修改设置密码
     * @param body
     * @return
     */
    @PUT("user/changePwd")
    ApiCall<Object> changePwd(@Body RequestBody body);

    /**
     * 获取用户签到数据
     * @param hashMap   null
     * @return
     */
//    @GET("state")
//    ApiCall<SignInStateRes> state(@QueryMap HashMap<String,String > hashMap);

    /**
     * 签到
     * @param body
     * @return
     */
//    @POST("sign")
//    ApiCall<SignInRes> sign(@Body RequestBody body);

    /**
     * 修改用户信息
     * @param body
     * @return
     */
    @PUT("user")
    ApiCall<Object> changeUser(@Body RequestBody body);

    /**
     * 根据用户字段修改信息
     * @param body
     * @return
     */
    @PUT("userfield")
    ApiCall<MsgRes> userfield(@Body RequestBody body);

    /**
     * 添加银行卡
     * @param body
     * @return
     */
    @POST("user/bank")
    ApiCall<MsgRes> addBank(@Body RequestBody body);

    /**
     * 每日任务列表
     * @param hashMap
     * @return
     */
//    @GET("task")
//    ApiCall<TaskListRes> task(@QueryMap HashMap<String,String > hashMap);

    /**
     * 领取任务奖励
     * @param hashMap
     * @return
     */
//    @GET("receive/award/{id}")
//    ApiCall<TaskAwardRes> taskAward(@Path("id") Integer id, @QueryMap HashMap<String,String > hashMap);

    /**
     * 我的推荐信息
     * @param hashMap
     * @return
     */
//    @GET("my/recommend")
//    ApiCall<RecommendRes> recommend(@QueryMap HashMap<String,String > hashMap);
//
//    @GET("recommend/list")
//    ApiCall<RecommendListRes> recommendList(@QueryMap HashMap<String,String > hashMap);

}
