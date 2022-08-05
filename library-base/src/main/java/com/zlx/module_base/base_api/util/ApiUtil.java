package com.zlx.module_base.base_api.util;

import com.zlx.module_base.base_api.module.BlindBoxApi;
import com.zlx.module_base.base_api.module.ImagerApi;
import com.zlx.module_base.base_api.module.LoginApi;
import com.zlx.module_base.base_api.module.PublicApi;
import com.zlx.module_base.base_api.module.UserApi;
import com.zlx.module_base.base_api.module.WithdrawApi;
import com.zlx.module_network.api2.RetrofitCreateHelper;
import com.zlx.module_network.constrant.Imager;
import com.zlx.module_network.constrant.U;

/**
 * Created by zlx on 2020/9/28 15:09
 * Email: 1170762202@qq.com
 * Description: 不同模块BASE_URL可能不同
 */
public class ApiUtil {

    public static BlindBoxApi getBaseApi() {
        return RetrofitCreateHelper.getInstance().create(U.BASE_URL, BlindBoxApi.class);
    }

    public static LoginApi getLoginApi() {
        return RetrofitCreateHelper.getInstance().create(U.BASE_URL, LoginApi.class);
    }

    public static UserApi getUserApi() {
        return RetrofitCreateHelper.getInstance().create(U.BASE_URL, UserApi.class);
    }

    public static WithdrawApi getWithdrawApi() {
        return RetrofitCreateHelper.getInstance().create(U.BASE_URL, WithdrawApi.class);
    }

    public static PublicApi getPublicApi() {
        return RetrofitCreateHelper.getInstance().create(U.BASE_URL, PublicApi.class);
    }
    public static ImagerApi getImagerApi() {
        return RetrofitCreateHelper.getInstance().create(Imager.BASE_URL, ImagerApi.class);
    }

}
