package com.zlx.module_base.base_api.module;

import com.zlx.module_base.base_api.res_data.MsgRes;
import com.zlx.module_network.factory.ApiCall;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Description:
 * Author: lyl
 * Date: 2022/4/26 22:42
 */
public interface WithdrawApi {
    /**
     * 充值金额页面
     * @param map
     * @return
     */
//    @GET("recharge/amount")
//    ApiCall<RechargePageBean> rechargeAmount(@QueryMap HashMap<String,String> map);

    /**
     * 充值
     * @param body
     * @return
     */
//    @POST("recharge")
//    ApiCall<RechargeRes> recharge(@Body RequestBody body);

    /**
     * 用户充值记录
     * @return
     */
//    @GET("recharge")
//    ApiCall<RechargeRecordRes> rechargeRecord(@QueryMap HashMap<String,String> map);

    /**
     * 获取充值状态
     * @param order_id
     * @param map
     * @return
     */
//    @GET("recharge/{order_id}/status")
//    ApiCall<RechargeRes> rechargesStatus(Integer order_id,@QueryMap HashMap<String,String> map);

    /**
     * 积分明细
     * @param map
     * @return
     */
//    @GET("integral/record")
//    ApiCall<IntegralRecordRes> integralRecord(@QueryMap HashMap<String,String> map);

    /**
     * 申请提现页面
     * @param map
     * @return
     */
//    @GET("withdrawal")
//    ApiCall<WithdrawalPageRes> withdrawalPage(@QueryMap HashMap<String,String> map);

    /**
     * 提现
     * @param body
     * @return
     */
    @POST("withdrawal")
    ApiCall<MsgRes> withdrawal(@Body RequestBody body);

}
