package com.dkcr.blibala.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.zlx.module_base.base_fg.BaseFg;
import com.zlx.module_base.database.MMkvHelper;
import com.zlx.module_base.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description:
 * Author: lyl
 * Date: 2022/5/4 22:52
 */
public abstract class LazyLoadFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFg {
    protected VM viewModel;
    protected V binding;
    private Context mContext;
    private boolean isFirstLoad = true; // 是否第一次加载

    protected void initParams() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        initImmersionBar();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewDataBinding();
        getLifecycle().addObserver(viewModel);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            // 将数据加载逻辑放到onResume()方法中
            initViews();
            initData();
            isFirstLoad = false;
        }
        lazyLoad();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    protected void initViews() {
    }

    protected void initData() {
    }

    /**
     * 懒加载
     */
    protected void lazyLoad(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binding != null) {
            binding.unbind();
        }
    }

    private void initViewDataBinding() {

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
                    ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                    .get(modelClass);
        }
        if (initVariableId() > 0) {
            binding.setVariable(initVariableId(), viewModel);
        }
        viewModel.uiChangeLiveData().onBackPressedEvent().observe(this, o -> {
            requireActivity().onBackPressed();
        });
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getContext(), clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clz);
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
    protected abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    protected abstract int initVariableId();

    public boolean isLogin() {
        if (MMkvHelper.getInstance().getUserInfo() == null) {
            return false;
        }
        return true;
    }
}
