package com.dkcr.blibala.ui.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ItemLifeCommentBinding;
import com.dkcr.blibala.ui.mine.entity.LifeRecordEntity;

import java.util.List;

/**
 * Description:评论
 * Author: lyl
 * Date: 2022/8/2 21:17
 */
public class LifeCommentAdapter extends BaseQuickAdapter<LifeRecordEntity.CommentEntity, BaseDataBindingHolder<ItemLifeCommentBinding>> {

    public LifeCommentAdapter(@Nullable List<LifeRecordEntity.CommentEntity> data) {
        super(R.layout.item_life_comment, data);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemLifeCommentBinding> holder, LifeRecordEntity.CommentEntity entity) {
        holder.setText(R.id.tv_user_name,entity.getComment()+"");
    }
}
