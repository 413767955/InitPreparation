package com.zlx.module_base.base_ac;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.zlx.module_base.database.MMkvHelper;
import com.zlx.module_base.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseMvvmAc<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseAc {

    protected VM viewModel;
    protected V binding;

    @Override
    public void afterOnCreate(Bundle savedInstanceState) {
        super.afterOnCreate(savedInstanceState);
        initViewDataBinding(savedInstanceState);
        getLifecycle().addObserver(viewModel);
    }

    private void initViewDataBinding(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));

        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) new ViewModelProvider(this,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                    .get(modelClass);
        }
        if (initVariableId() > 0) {
            binding.setVariable(initVariableId(), viewModel);
        }

        viewModel.uiChangeLiveData().onBackPressedEvent().observe(this, o -> {
            onBackPressed();
        });
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 初始化根布局
     *
     * @param savedInstanceState
     * @return 布局layout的id
     */
    protected abstract int initContentView(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    protected abstract int initVariableId();

    /**
     * 是否登录
     * @return
     */
    public boolean isLogin(){
        if (TextUtils.isEmpty(MMkvHelper.getInstance().getAuthorization())){
            //ToastUtils.showShort("not_logged_in");
            return false;
        }
        return true;
    }
}
