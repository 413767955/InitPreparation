package com.zlx.module_network.api1.livedata;

import com.zlx.module_network.bean.ApiResponse;
import com.zlx.module_network.widget.popwindow.PopUtil;

public class BaseObserver<T> implements IBaseObserver<T> {

    private BaseObserverCallBack<T> baseObserverCallBack;

    public BaseObserver(BaseObserverCallBack<T> baseObserverCallBack) {
        this.baseObserverCallBack = baseObserverCallBack;
    }

    @Override
    public void onChanged(T t) {
        if (t instanceof ApiResponse) {
            ApiResponse apiResponse = (ApiResponse) t;
            if (apiResponse.isSuccess()) {
                baseObserverCallBack.onSuccess(t);
            } else {
                baseObserverCallBack.onFail(apiResponse.getMessage());
                if (baseObserverCallBack.showErrorMsg()) {
                    PopUtil.show(apiResponse.getMessage());
                }
            }
        } else {
            baseObserverCallBack.onFail("系统繁忙!");
        }
        baseObserverCallBack.onFinish();
    }
}
