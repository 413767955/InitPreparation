package com.dkcr.blibala.ui.setting.ac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.AppUtils;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.base.constant.AppConstant;
import com.dkcr.blibala.databinding.ActivityAboutUsBinding;
import com.dkcr.blibala.ui.webview.WebViewActivity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.event.EventHandlers;
import com.zlx.module_base.viewmodel.BaseViewModel;

/**
 * 关于我们
 */
public class AboutUsActivity extends BaseMvvmAc<ActivityAboutUsBinding, BaseViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context,AboutUsActivity.class));
    }

    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_about_us;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
    @Override
    public void initViews() {
        super.initViews();
        binding.setEvent(new Event());
        binding.setVersion(AppUtils.getAppVersionName());//获取版本号
    }

    @Override
    public void initEvents() {
        super.initEvents();
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                onBackPressed();
            }
        });
    }

    public class Event extends EventHandlers {
        public void onPrivacy(){
            WebViewActivity.toActivity(AboutUsActivity.this, AppConstant.PRIVACY_POLICY,getString(R.string.privacy_policy));
        }

        public void onAgreement(){
            WebViewActivity.toActivity(AboutUsActivity.this, AppConstant.USER_AGREEMENT,getString(R.string.user_agreement));
        }
    }
}