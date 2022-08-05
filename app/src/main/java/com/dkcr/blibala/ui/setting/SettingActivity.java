package com.dkcr.blibala.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivitySettingBinding;
import com.dkcr.blibala.ui.setting.ac.AboutUsActivity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.event.EventHandlers;
import com.zlx.module_base.viewmodel.BaseViewModel;

/**
 * 设置
 */
public class SettingActivity extends BaseMvvmAc<ActivitySettingBinding, BaseViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context, SettingActivity.class));
    }
    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViews() {
        super.initViews();
        binding.setEvent(new Event());
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
        /**
         * 关于我们
         */
        public void onAboutUsClick(){
            AboutUsActivity.toActivity(SettingActivity.this);
        }
    }
}