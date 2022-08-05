package com.dkcr.blibala.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityLoginBinding;
import com.dkcr.blibala.ui.account.PerfectInfoActivity;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.viewmodel.BaseViewModel;

/**
 * 登录
 */
public class LoginActivity extends BaseMvvmAc<ActivityLoginBinding, BaseViewModel> {
    public static void toActivity(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }
    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}