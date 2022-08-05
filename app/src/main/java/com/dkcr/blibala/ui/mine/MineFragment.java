package com.dkcr.blibala.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.FragmentMineBinding;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.dkcr.blibala.ui.mine.ac.EditInformationActivity;
import com.dkcr.blibala.ui.mine.ac.LifeRecordActivity;
import com.dkcr.blibala.ui.mine.adapter.MineLabelAdapter;
import com.dkcr.blibala.ui.setting.SettingActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.zlx.module_base.base_fg.BaseMvvmFg;
import com.zlx.module_base.event.EventHandlers;

/**
 */
public class MineFragment extends BaseMvvmFg<FragmentMineBinding, HomeViewModel> {
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }
    @Override
    protected int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true,R.color.color_F2F2F2)
                .statusBarDarkFont(false)
                .keyboardEnable(true)
                .init();

    }

    @Override
    protected void initViews() {
        super.initViews();
        binding.setEvent(new Event());
        initLabel();
    }

    private void initLabel(){
        MineLabelAdapter adapter = new MineLabelAdapter(null);
        adapter.addData(new String());
        adapter.addData(new String());
        adapter.addData(new String());
        adapter.addData(new String());
        binding.rvLabel.setLayoutManager(new GridLayoutManager(getContext(),4));
        binding.rvLabel.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view1, position) -> {
            switch (position) {
                case 0://签到
                    break;
                case 1://生活记录
                    LifeRecordActivity.toActivity(getContext());
                    break;
                case 2://充值
                    break;
                case 3://客服
                    break;
            }
            if (isLogin()) {//登录状态

            }else {
                //startLogin();
            }
        });
    }

    public class Event extends EventHandlers {
        public void onEditInfo(){
            EditInformationActivity.toActivity(getContext());
        }

        public void onSetting(){
            SettingActivity.toActivity(getContext());
        }
    }
}