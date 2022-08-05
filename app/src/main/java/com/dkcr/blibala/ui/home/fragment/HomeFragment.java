package com.dkcr.blibala.ui.home.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.FragmentHomeBinding;
import com.dkcr.blibala.ui.home.adapter.NearbyAdapter;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener;
import com.zlx.module_base.base_fg.BaseMvvmFg;
import com.zlx.module_network.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class HomeFragment extends BaseMvvmFg<FragmentHomeBinding, HomeViewModel> {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    protected int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(binding.toolbar,false)
                .keyboardEnable(true)
                .init();
    }

    @Override
    protected void initViews() {
        super.initViews();
        initRv();
    }

    private void initRv(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NearbyAdapter adapter = new NearbyAdapter(new ArrayList<>());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new String());
        }
        adapter.addData(list);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(adapter);
        binding.refreshLayout.setOnMultiListener(new SimpleMultiListener() {

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //page++;
                //LogUtils.e(page);
                //viewModel.myOrder(page,type+"");
                LogUtil.show("触发加载事件");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                LogUtil.show("触发刷新事件");
                //page = 1;
                //viewModel.myOrder(page,type+"");
            }
        });
    }
}