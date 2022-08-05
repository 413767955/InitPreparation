package com.dkcr.blibala;

import com.zlx.module_base.BaseApplication;
import com.zlx.module_base.config.ModuleLifecycleConfig;

public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //优先初始化
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);

        //UMConfigure.setLogEnabled(true);
        //初始化组件化基础库, 所有友盟业务SDK都必须调用此初始化接口。
        //UMConfigure.init(this, AppConstant.UMENG_KEY, "Umeng", , "");
        //UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"Umeng");

        //initAppsFlyer();
        //initLanguage();
    }

    /**
     * 初始化AppsFlyer
     */
    private void initAppsFlyer(){
        /*AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    //LogUtils.e("attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }
            @Override
            public void onConversionDataFail(String errorMessage) {
                //LogUtils.e( "error getting conversion data: " + errorMessage);
            }
            @Override
            public void onAppOpenAttribution(Map<String, String> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    //LogUtils.e("attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }
            @Override
            public void onAttributionFailure(String errorMessage) {
                //LogUtils.e("error onAttributionFailure : " + errorMessage);
            }
        };
        AppsFlyerLib.getInstance().init(AppConstant.AppsFlyerId, conversionListener, getApplicationContext());
        AppsFlyerLib.getInstance().start(this);*/
    }

    /**
     * 初始化语言包
     */
    private void initLanguage(){
//        if (MMkvHelper.getInstance().getLanguage() == null){
//            Locale locale = new Locale("hi_RIN");
//            MMkvHelper.getInstance().saveLanguage(locale);
//            LanguageUtil.switchLanguage(MMkvHelper.getInstance().getLanguage());
//        }else {
//            LanguageUtil.switchLanguage(MMkvHelper.getInstance().getLanguage());
//        }
    }

}
