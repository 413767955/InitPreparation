package com.dkcr.blibala.base.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Description:
 * @Author: lyl
 * @Date: 2022/2/17 16:14
 */
public class HomePagerAdapter extends FragmentStateAdapter {
    private List<Fragment> fragments;
    public HomePagerAdapter(@NonNull FragmentManager fragmentManager, @NotNull Lifecycle lifecycle, List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
