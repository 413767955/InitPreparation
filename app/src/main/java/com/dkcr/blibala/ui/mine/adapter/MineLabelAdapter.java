package com.dkcr.blibala.ui.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ItemMineLabelBinding;

import java.util.List;

/**
 * Description:
 * Author: lyl
 * Date: 2022/8/1 23:39
 */
public class MineLabelAdapter extends BaseQuickAdapter<String, BaseDataBindingHolder<ItemMineLabelBinding>> {
    public MineLabelAdapter(@Nullable List<String> data) {
        super(R.layout.item_mine_label, data);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemMineLabelBinding> holder, String s) {

    }
}
