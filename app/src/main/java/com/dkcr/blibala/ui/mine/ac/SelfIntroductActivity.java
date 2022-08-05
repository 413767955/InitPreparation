package com.dkcr.blibala.ui.mine.ac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityBasicInfoBinding;
import com.dkcr.blibala.databinding.ActivitySelfIntroductBinding;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.viewmodel.BaseViewModel;

/**
 * 自我介绍
 */
public class SelfIntroductActivity extends BaseMvvmAc<ActivitySelfIntroductBinding, BaseViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context, SelfIntroductActivity.class));
    }
    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_self_introduct;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
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
}