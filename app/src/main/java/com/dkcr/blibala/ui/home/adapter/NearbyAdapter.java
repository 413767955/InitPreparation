package com.dkcr.blibala.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ItemNearbyListBinding;

import java.util.List;

/**
 * Description:
 * Author: lyl
 * Date: 2022/7/24 0:35
 */
public class NearbyAdapter extends BaseQuickAdapter<String, BaseDataBindingHolder<ItemNearbyListBinding>> {
    public NearbyAdapter(@Nullable List<String> data) {
        super(R.layout.item_nearby_list, data);

    }


    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemNearbyListBinding> holder, String s) {

    }
}
