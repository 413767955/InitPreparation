package com.dkcr.blibala.ui.makefriends.ac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityEditInformationBinding;
import com.dkcr.blibala.databinding.ActivityFriendDetailsBinding;
import com.dkcr.blibala.ui.makefriends.adapter.FriendDynamicAdapter;
import com.dkcr.blibala.ui.mine.ac.BasicInformationActivity;
import com.dkcr.blibala.ui.mine.ac.SelfIntroductActivity;
import com.dkcr.blibala.ui.mine.adapter.LifeRecordAdapter;
import com.dkcr.blibala.ui.mine.entity.LifeRecordEntity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.event.EventHandlers;
import com.zlx.module_base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 交友-详情
 */
public class FriendDetailsActivity extends BaseMvvmAc<ActivityFriendDetailsBinding, BaseViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context, FriendDetailsActivity.class));
    }
    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_friend_details;
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
        binding.setEvent(new Event());
        binding.titleBar.setLineVisible(false);
        binding.titleBar.setBackgroundColor(getResources().getColor(R.color.transparent));
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                onBackPressed();
            }
        });
    }

    private void initRv(){
        List<LifeRecordEntity> list = new ArrayList<>();
        FriendDynamicAdapter adapter = new FriendDynamicAdapter(null);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 5; i++) {
            list.add(new LifeRecordEntity());
        }
        adapter.addData(list);
        binding.recyclerView.setAdapter(adapter);
    }

    public class Event extends EventHandlers{
        //基本信息
        public void onInfo(){
            BasicInformationActivity.toActivity(FriendDetailsActivity.this);
        }
        //自我介绍
        public void onIntroduct(){
            SelfIntroductActivity.toActivity(FriendDetailsActivity.this);
        }
    }
}