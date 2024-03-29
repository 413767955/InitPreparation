package com.dkcr.blibala.ui.makefriends.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dkcr.blibala.R;
import com.dkcr.blibala.ui.makefriends.bean.CardBean;
import com.zlx.module_base.base_util.GlideLoadUtils;

import java.util.List;

/**
 * Created by linchen on 2018/1/26.
 * mail: linchen@sogou-inc.com
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<CardBean> mCardBeanList;
    private RequestOptions mRequestOptions;

    public CardAdapter(List<CardBean> cardBeanList) {
        mCardBeanList = cardBeanList;
        mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.mipmap.card_default_film_bg).error(R.mipmap.card_default_film_bg).diskCacheStrategy(DiskCacheStrategy.NONE);

    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_img_txt, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        final CardBean bean = mCardBeanList.get(position);
        GlideLoadUtils.getInstance().loadImage(holder.img,bean.getUrl(),R.mipmap.card_default_film_bg);
        holder.text.setText(bean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "click " + bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"点击了 img",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardBeanList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;

        public CardHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.card_img);
            text = itemView.findViewById(R.id.card_txt);
        }
    }
}
