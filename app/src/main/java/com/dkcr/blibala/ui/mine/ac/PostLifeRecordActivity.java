package com.dkcr.blibala.ui.mine.ac;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityPostLifeRecordBinding;
import com.dkcr.blibala.ui.account.PerfectInfoActivity;
import com.dkcr.blibala.ui.mine.adapter.PostLifeAdapter;
import com.dkcr.blibala.ui.mine.entity.MultiPictureEntity;
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
import com.zlx.module_base.viewmodel.BaseViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 发布生活记录
 * 记录生活
 */
public class PostLifeRecordActivity extends BaseMvvmAc<ActivityPostLifeRecordBinding, BaseViewModel> {

    public static void toActivity(Context context){
        context.startActivity(new Intent(context, PostLifeRecordActivity.class));
    }

    private PostLifeAdapter mAdapter;
    private static final int MAX_SELECT_IMG = 9;//最多选择张数
    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_post_life_record;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initEvents() {
        super.initEvents();
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                onBackPressed();
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                ToastUtils.showShort("发布");
            }
        });
    }

    @Override
    public void initViews() {
        super.initViews();
        initRv();
    }

    private void initRv(){
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new PostLifeAdapter(null);
        binding.recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.addData(new MultiPictureEntity(true,false));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.btn_delete:
                    ToastUtils.showShort("delete");
                    mAdapter.removeAt(position);
                    initAddItem();
                    break;
                default:
                    break;
            }
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            //添加图片按钮才能点击进入相册
            if (mAdapter.getData().get(position).isAddImage()) {
                pickImage();
            }
        });
    }

    private void pickImage() {
        PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine.createGlideEngine())
                .setSelectionMode(SelectModeConfig.MULTIPLE)
                .setMaxSelectNum(MAX_SELECT_IMG)
                .setLanguage(LanguageConfig.ENGLISH)
                .setSelectedData(getSelectList())// 传入已选择的图片
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        Uri uri = ImageUtils.getMediaUriFromPath(PostLifeRecordActivity.this,result.get(0).getRealPath());
                        File file = ImageUtils.getFile(PostLifeRecordActivity.this,uri);
                        //GlideLoadUtils.getInstance().loadCircleImg(PostLifeRecordActivity.this,binding.imgAvatar,file.getPath(),0);
                        //showLoading();
                        //publicViewModel.pictureUpload(file);
                        mAdapter.remove();
                        for (LocalMedia localMedia : result){
                            mAdapter.addData(new MultiPictureEntity(localMedia, false, false));
                        }
                        initAddItem();
                    }

                    @Override
                    public void onCancel() {
                        LogUtils.e("onCancel");
                    }
                });
    }
    /**
     * 初始化添加的Item，数据改动后需要调用
     */
    private void initAddItem() {
        // 小于9个时，添加添加按钮
        if (mAdapter.getData().size() < MAX_SELECT_IMG) {
            if (!mAdapter.getData().get(mAdapter.getItemCount() - 1).isAddImage()) {
                mAdapter.addData(new MultiPictureEntity(true, false));
            }
        }
    }

    /**
     * 获取当前已选择的图片列表，会排除添加图片的Item
     *
     * @return 图片列表
     */
    private List<LocalMedia> getSelectList() {
        List<LocalMedia> localMediaList = new ArrayList<>();
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            // 不是添加图片的那个item才会传入选择列表
            if (!mAdapter.getData().get(i).isAddImage() && !mAdapter.getData().get(i).isNetImage()) {
                localMediaList.add(mAdapter.getData().get(i).getLocalMedia());
            }
        }
        return localMediaList;
    }
}