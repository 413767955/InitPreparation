package com.dkcr.blibala;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.ToastUtils;
import com.dkcr.blibala.base.adapter.HomePagerAdapter;
import com.dkcr.blibala.databinding.ActivityMainBinding;
import com.dkcr.blibala.ui.home.fragment.HomeFragment;
import com.dkcr.blibala.ui.mine.MineFragment;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.dkcr.blibala.ui.information.InformationFragment;
import com.dkcr.blibala.ui.makefriends.MakeFriendFragment;
import com.dkcr.blibala.ui.recommend.RecommendFragment;
import com.google.android.material.tabs.TabLayoutMediator;
import com.gyf.immersionbar.ImmersionBar;
import com.zlx.module_base.base_ac.BaseMvvmAc;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMvvmAc<ActivityMainBinding, HomeViewModel> {
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] tabStrs = new String[]{"首页","推荐","交友","消息","我的"};
    private int[] tabImgs = new int[]{R.drawable.tab_main_home,R.drawable.tab_main_home,
            R.drawable.tab_main_home,R.drawable.tab_main_home, R.drawable.tab_main_mine};
    private HomePagerAdapter pagerAdapter;

    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .keyboardEnable(true)
                .init();
    }

    @Override
    public void initViews() {
        super.initViews();
        initTab();
    }

    private void initTab() {
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(RecommendFragment.newInstance());
        fragmentList.add(MakeFriendFragment.newInstance());
        fragmentList.add(InformationFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());

        pagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        binding.viewPager2.setAdapter(pagerAdapter);
        binding.viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        binding.viewPager2.setUserInputEnabled(false); //true:滑动，false：禁止滑动
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, false, false,
                (tab, position) -> {
                })
                .attach();
        addTab();
    }

    private void addTab() {
        for (int i = 0; i < fragmentList.size(); i++) {
            binding.tabLayout.getTabAt(i).setCustomView(getTabView(tabStrs[i], tabImgs[i]));
        }
    }

    private View getTabView(String title, int image) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.main_tab_item_view, null);
        TextView textView = view.findViewById(R.id.textview);
        ImageView imageView = view.findViewById(R.id.imageview);
        textView.setText(title);
        imageView.setImageResource(image);
        return view;
    }

    private long mExitTime = 0;

    //点击两次退出程序 有时间间隔 间隔内点击则退出程序 否则 则提示
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShort("再按一次回到桌面");
                mExitTime = System.currentTimeMillis();
            } else {
                //后台运行 不结束程序 只是退出到后台
                Intent intent = new Intent();
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                startActivity(intent);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}