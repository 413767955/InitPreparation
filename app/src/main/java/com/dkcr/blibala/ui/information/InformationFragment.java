package com.dkcr.blibala.ui.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.FragmentInformationBinding;
import com.dkcr.blibala.databinding.FragmentRecommendBinding;
import com.dkcr.blibala.ui.home.adapter.NearbyAdapter;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.dkcr.blibala.ui.information.ac.ChatActivity;
import com.dkcr.blibala.ui.information.adapter.MessageListAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener;
import com.zlx.module_base.base_fg.BaseMvvmFg;
import com.zlx.module_network.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 */
public class InformationFragment extends BaseMvvmFg<FragmentInformationBinding, HomeViewModel> {

    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();
        return fragment;
    }
    @Override
    protected int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_information;
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
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected void initViews() {
        super.initViews();
        initEvent();
        initRv();
    }
    private void initEvent(){
    }

    private void initRv(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MessageListAdapter adapter = new MessageListAdapter(null);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new String());
        }
        adapter.addData(list);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view1, position) -> {
            ChatActivity.toActivity(getContext());
        });
        binding.refreshLayout.setEnableRefresh(false);
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