package com.dkcr.blibala.ui.information.emoji;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dkcr.blibala.R;

import java.util.List;

public class EmojiAdapter extends BaseQuickAdapter< EmojiBean, BaseViewHolder> {


    public EmojiAdapter(@Nullable List<EmojiBean> data, int index, int pageSize) {
         super(R.layout.item_emoji,  data);
     }

    @Override
    protected void convert(BaseViewHolder helper, EmojiBean item) {
        //判断是否为最后一个item
        if (item.getId()==0) {
             helper.setBackgroundResource(R.id.et_emoji,R.mipmap.rc_icon_emoji_delete );
        } else {
             helper.setText(R.id.et_emoji,item.getUnicodeInt() );
        }



    }


}
