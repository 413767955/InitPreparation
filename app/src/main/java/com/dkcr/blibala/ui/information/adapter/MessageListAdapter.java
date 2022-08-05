package com.dkcr.blibala.ui.information.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ItemMessageListBinding;

import java.util.List;

/**
 * Description:
 * Author: lyl
 * Date: 2022/7/31 18:11
 */
public class MessageListAdapter extends BaseQuickAdapter<String, BaseDataBindingHolder<ItemMessageListBinding>> {
    public MessageListAdapter(@Nullable List<String> data) {
        super(R.layout.item_message_list, data);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemMessageListBinding> holder, String s) {

    }
}
