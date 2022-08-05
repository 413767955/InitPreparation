package com.zlx.module_base.base_api.module;

import com.zlx.module_base.base_api.res_data.base.PictureRes;
import com.zlx.module_network.factory.ApiCall;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Description:
 * Author: lyl
 * Date: 2022/6/25 1:50
 */
public interface ImagerApi {
    /**
     * 单张图片上传
     * @param file
     * @return
     */
    @Multipart
    @POST("buckets/upload")
    ApiCall<PictureRes> pictureUpload(@Part MultipartBody.Part file);
}
