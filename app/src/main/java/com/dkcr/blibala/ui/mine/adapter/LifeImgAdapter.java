package com.dkcr.blibala.ui.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.ui.mine.entity.LifeRecordEntity;

import java.util.List;

/**
 * @Description:
 * @Author: lyl
 * @Date: 2022/8/2 17:21
 */
public class LifeImgAdapter extends BaseMultiItemQuickAdapter<LifeRecordEntity.ImagerEntity, BaseViewHolder> {
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOUR = 2;//4张图片

    public LifeImgAdapter(@Nullable List<LifeRecordEntity.ImagerEntity> data) {
        super(data);
        addItemType(TYPE_NORMAL, R.layout.item_life_tyep_img);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, LifeRecordEntity.ImagerEntity imagerEntity) {
        LogUtils.e(baseViewHolder.getItemViewType());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
