package com.zlx.module_network.util;

import com.zlx.module_network.bean.ApiResponse;
import com.zlx.module_network.interceptor.ExceptionHandle;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 有关Rx的工具类
 */
public class RxUtils {

    /**
     * 线程调度器
     */

    public static <T> ObservableTransformer<T, T> observableToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> observableBothToIo() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> exceptionTransformer() {
        return observable -> observable
//                        .map(new HandleFuc<T>())  //这里可以取出BaseResponse中的Result
                .onErrorResumeNext(new HttpResponseFunc());
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            return Observable.error(ExceptionHandle.handleException(t));
        }
    }

    private static class HandleFuc<T> implements Function<ApiResponse<T>, T> {
        @Override
        public T apply(ApiResponse<T> response) {
            if (!response.isSuccess())
                throw new RuntimeException(!"".equals(response.getCode() + "" + response.getMessage()) ? response.getMessage() : "");
            return response.getData();
        }
    }

}
