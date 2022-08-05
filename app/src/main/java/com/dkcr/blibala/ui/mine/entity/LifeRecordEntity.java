package com.dkcr.blibala.ui.mine.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: lyl
 * @Date: 2022/8/2 16:01
 */
@Data
@NoArgsConstructor
public class LifeRecordEntity {
    private int type;
    private List<ImagerEntity> imgList;


    public static class ImagerEntity implements MultiItemEntity {
        private int imgUrl;
        private int itemType = 1;

        @Override
        public int getItemType() {
            return itemType;
        }
    }

    @Data
    @AllArgsConstructor
    public static class CommentEntity {
        private int comment;
    }
}
