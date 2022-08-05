package com.dkcr.blibala.ui.account;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityPerfectInfoBinding;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.dkcr.blibala.util.GlideEngine;
import com.dkcr.blibala.util.ImageUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.luck.picture.lib.language.LanguageConfig;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.base_util.GlideLoadUtils;
import com.zlx.module_base.event.EventHandlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 首次登录完善信息
 */
public class PerfectInfoActivity extends BaseMvvmAc<ActivityPerfectInfoBinding, HomeViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context,PerfectInfoActivity.class));
    }

    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_perfect_info;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViews() {
        super.initViews();
        binding.setEvent(new Event());
    }

    @Override
    public void initEvents() {
        super.initEvents();
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                onBackPressed();
            }
        });
    }

    public class Event extends EventHandlers {
        public void onAvatar(){
            PermissionUtils.permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .callback(new PermissionUtils.SingleCallback() {
                        @Override
                        public void callback(boolean isAllGranted, @NonNull List<String> granted, @NonNull List<String> deniedForever, @NonNull List<String> denied) {
                            if (isAllGranted){
                                PictureSelector.create(PerfectInfoActivity.this)
                                        .openGallery(SelectMimeType.ofImage())
                                        .setImageEngine(GlideEngine.createGlideEngine())
                                        .setSelectionMode(SelectModeConfig.SINGLE)
                                        .setLanguage(LanguageConfig.ENGLISH)
                                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                                            @Override
                                            public void onResult(ArrayList<LocalMedia> result) {
                                                Uri uri = ImageUtils.getMediaUriFromPath(PerfectInfoActivity.this,result.get(0).getRealPath());
                                                File file = ImageUtils.getFile(PerfectInfoActivity.this,uri);
                                                GlideLoadUtils.getInstance().loadCircleImg(PerfectInfoActivity.this,binding.imgAvatar,file.getPath(),0);
                                                //showLoading();
                                                //publicViewModel.pictureUpload(file);
                                            }

                                            @Override
                                            public void onCancel() {
                                                LogUtils.e("onCancel");
                                            }
                                        });
                            }
                        }
                    })
                    .request();

        }
    }
}