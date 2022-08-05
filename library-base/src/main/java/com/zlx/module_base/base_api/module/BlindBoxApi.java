package com.zlx.module_base.base_api.module;
import com.zlx.module_network.factory.ApiCall;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @Description:
 */
public interface BlindBoxApi {

    /**
     * 盲盒分类
     * @param hashMap
     * @return
     */
//    @GET("blind/category")
//    ApiCall<BlindCategoryRes> blindCategory(@QueryMap HashMap<String,String > hashMap);

    /**
     * 盲盒列表
     * @param hashMap
     * @return
     */
//    @GET("blindlist")
//    ApiCall<BlindListRes> blindlist(@QueryMap HashMap<String,String > hashMap);

    /**
     * 盲盒详情
     * @param id
     * @param hashMap
     * @return
     */

//    @GET("blind/{id}/detail")
//    ApiCall<DlindDetailRes> blindDetail(@Path("id") Integer id, @QueryMap HashMap<String,String > hashMap);

    /**
     * 商品详情
     * @param id
     * @param hashMap
     * @return
     */
//    @GET("goods/{id}/detail")
//    ApiCall<GoodsDetailRes> goodsDetail(@Path("id") int id, @QueryMap HashMap<String,String > hashMap);

    /**
     * 订单列表
     * @param hashMap
     * @return
     */
//    @GET("my/order")
//    ApiCall<OrderListBean> myOrder(@QueryMap HashMap<String,String > hashMap);

    /**
     * 提交订单
     * @param body
     * @return
     */
//    @POST("order")
//    ApiCall<OrderRes> order(@Body RequestBody body);

    /**
     * 待支付订单付款
     * @param order_id
     * @param body
     * @return
     */
//    @PUT("order/{order_id}/pend")
//    ApiCall<PayOrderRes> pend(@Path("order_id") String order_id, @Body RequestBody body);

    /**
     * 更改订单状态
     * @param order_id
     * @param body
     * @return
     */
    @PUT("order/{order_id}/status")
    ApiCall<Object> orderStatus(@Path("order_id") Integer order_id,@Body RequestBody body);

    /**
     * 获取订单状态
     * @return
     */
//    @GET("order/{order_id}/status")
//    ApiCall<OrderRes> getOrderStatus(@Path("order_id") String order_id,@QueryMap HashMap<String,String > hashMap);

//    @GET("goodslist")
//    ApiCall<GoodsListRes> goodslist(@QueryMap HashMap<String,String > hashMap);

    /**
     * 订单详情
     * @param order_id
     * @param hashMap
     * @return
     */
//    @GET("order/{order_id}/detail")
//    ApiCall<OrderDetailRes> orderDetail(@Path("order_id") String order_id, @QueryMap HashMap<String,String > hashMap);

}
