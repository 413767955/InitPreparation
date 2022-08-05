package com.dkcr.blibala.ui.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dkcr.blibala.R;
import com.dkcr.blibala.ui.mine.entity.LifeRecordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lyl
 * @Date: 2022/8/2 15:26
 */
public class LifeRecordAdapter extends BaseQuickAdapter<LifeRecordEntity, BaseViewHolder> {
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMG = 2;

    private RecyclerView recyclerView;
    private RecyclerView rvComment;

    public LifeRecordAdapter(@Nullable List<LifeRecordEntity> data) {
        super(R.layout.item_life_record_img, data);
    }

/*    public LifeRecordAdapter() {
        super();
        // 第一步，设置代理
        setMultiTypeDelegate(new BaseMultiTypeDelegate<LifeRecordEntity>() {
            // 根据数据，自己判断应该返回的类型
            @Override
            public int getItemType(@NonNull List<? extends LifeRecordEntity> list, int i) {
                return list.get(i).getType();
            }
        });
        getMultiTypeDelegate()
                .addItemType(TYPE_TEXT, R.layout.item_life_record_text)
                .addItemType(TYPE_IMG, R.layout.item_life_record_img);

    }*/

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, LifeRecordEntity entity) {
        recyclerView = baseViewHolder.getView(R.id.recyclerView);
        rvComment = baseViewHolder.getView(R.id.rv_comment);
        //图片的adapter
        LifeImgAdapter adapter = new LifeImgAdapter(null);
        List<LifeRecordEntity.ImagerEntity> imagerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            imagerList.add(new LifeRecordEntity.ImagerEntity());
        }
        adapter.addData(imagerList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(adapter);
        //评论的adapter
        LifeCommentAdapter commentAdapter = new LifeCommentAdapter(null);
        List<LifeRecordEntity.CommentEntity> commentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            commentList.add(new LifeRecordEntity.CommentEntity(i));
        }
        commentAdapter.addData(commentList);
        rvComment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvComment.setAdapter(commentAdapter);

    }

}
