package com.zlx.module_base.module;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.tencent.mmkv.MMKV;
import com.zlx.module_base.BaseApplication;
import com.zlx.module_base.R;
import com.zlx.module_base.loadsir.EmptyCallback;
import com.zlx.module_base.loadsir.ErrorCallback;
import com.zlx.module_base.loadsir.LoadingCallback;

/**
 * Created by zlx on 2020/9/22 14:28
 * Email: 1170762202@qq.com
 * Description: 模块初始化
 */
public class CommonModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        //SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(application));
        //SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(application));
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, android.R.color.darker_gray);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });

        MMKV.initialize(application);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(application);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();


        //DbUtil.getInstance().init(application, "wanandroid");


/*        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return false;
        }
        LeakCanary.install(application);*/


//        NeverCrashHelper.init((t, e) -> {
//            LogUtils.i(e.getMessage());
//            new Handler(Looper.getMainLooper()).post(() -> {
//                Toast.makeText(application, e.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });
        return false;
    }

    @Override
    public boolean onInitAfter(BaseApplication application) {
        return false;
    }
}
