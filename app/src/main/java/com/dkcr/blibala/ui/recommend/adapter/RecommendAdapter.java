package com.dkcr.blibala.ui.recommend.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.ui.recommend.bean.FriendCircleBean;
import com.dkcr.blibala.ui.recommend.bean.ListDataBean;

import java.util.List;

/**
 * Description:
 * Author: lyl
 * Date: 2022/7/29 0:07
 */
public class RecommendAdapter extends BaseMultiItemQuickAdapter<ListDataBean, BaseViewHolder> {


    public RecommendAdapter(@Nullable List<ListDataBean> data) {
        super(data);
        addItemType(1, R.layout.item_recom_image_one);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ListDataBean bean) {

    }
}
