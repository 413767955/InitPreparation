package com.dkcr.blibala.ui.mine.ac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityLifeRecordBinding;
import com.dkcr.blibala.ui.mine.adapter.LifeRecordAdapter;
import com.dkcr.blibala.ui.mine.entity.LifeRecordEntity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 生活记录
 */
public class LifeRecordActivity extends BaseMvvmAc<ActivityLifeRecordBinding, BaseViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context, LifeRecordActivity.class));
    }

    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_life_record;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViews() {
        super.initViews();
        initRv();
    }

    @Override
    public void initEvents() {
        super.initEvents();
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                //OnTitleBarListener.super.onLeftClick(titleBar);
                onBackPressed();
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                //OnTitleBarListener.super.onRightClick(titleBar);
                ToastUtils.showShort("发布");
                PostLifeRecordActivity.toActivity(LifeRecordActivity.this);
            }
        });
    }

    private void initRv(){
        List<LifeRecordEntity> list = new ArrayList<>();
        LifeRecordAdapter adapter = new LifeRecordAdapter(null);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 5; i++) {
            list.add(new LifeRecordEntity());
        }
        adapter.addData(list);
        binding.recyclerView.setAdapter(adapter);
    }
}