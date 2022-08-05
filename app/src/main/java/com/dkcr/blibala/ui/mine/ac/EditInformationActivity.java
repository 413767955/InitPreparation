package com.dkcr.blibala.ui.mine.ac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityEditInformationBinding;
import com.dkcr.blibala.ui.login.LoginActivity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.event.EventHandlers;
import com.zlx.module_base.viewmodel.BaseViewModel;

/**
 * 编辑资料
 */
public class EditInformationActivity extends BaseMvvmAc<ActivityEditInformationBinding, BaseViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context, EditInformationActivity.class));
    }
    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_edit_information;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initEvents() {
        super.initEvents();
        binding.setEvent(new Event());
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                onBackPressed();
            }
        });
    }

    public class Event extends EventHandlers{
        //基本信息
        public void onInfo(){
            BasicInformationActivity.toActivity(EditInformationActivity.this);
        }
        //自我介绍
        public void onIntroduct(){
            SelfIntroductActivity.toActivity(EditInformationActivity.this);
        }
    }
}