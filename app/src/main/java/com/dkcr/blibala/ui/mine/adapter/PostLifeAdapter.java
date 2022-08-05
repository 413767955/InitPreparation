package com.dkcr.blibala.ui.mine.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ItemLifeImageAddBinding;
import com.dkcr.blibala.ui.mine.entity.MultiPictureEntity;

import java.util.List;

/**
 * @Description:发布生活记录的图片适配器
 * @Author: lyl
 * @Date: 2022/8/3 14:47
 */
public class PostLifeAdapter extends BaseQuickAdapter<MultiPictureEntity, BaseDataBindingHolder<ItemLifeImageAddBinding>> {
    public PostLifeAdapter(@Nullable List<MultiPictureEntity> data) {
        super(R.layout.item_life_image_add, data);
        addChildClickViewIds(R.id.btn_delete);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemLifeImageAddBinding> holder, MultiPictureEntity entity) {
        if (entity.isAddImage()){
            holder.setGone(R.id.btn_delete, true)
                    .setImageResource(R.id.iv_img, R.mipmap.ic_add);
        }else {
            holder.setGone(R.id.btn_delete, false);
            Glide.with(getContext())
                    .load(entity.getImageUri())
                    .into((ImageView) holder.getView(R.id.iv_img));
        }
    }

    public void clear(){
        getData().clear();
    }

    public void remove(){

        for (int i = 0; i < getData().size(); i++) {
            if (!getData().get(i).isNetImage()){
                LogUtils.e(i);
                removeAt(i);
                i--;
            }
        }

    }
}
