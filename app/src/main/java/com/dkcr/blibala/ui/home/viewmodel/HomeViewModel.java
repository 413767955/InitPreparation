package com.dkcr.blibala.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zlx.module_base.base_ac.BaseTopBarViewModel;

/**
 * Description:
 * Author: lyl
 * Date: 2022/7/18 20:48
 */
public class HomeViewModel extends BaseTopBarViewModel<HomeRepository> {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
}
