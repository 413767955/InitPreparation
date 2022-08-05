package com.dkcr.blibala.ui.mine.entity;

import com.luck.picture.lib.entity.LocalMedia;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: lyl
 * @Date: 2022/8/3 15:06
 */
@Data
@NoArgsConstructor
public class MultiPictureEntity {
    private LocalMedia localMedia;
    private String imageUri;
    private boolean isAddImage;
    private boolean isNetImage;

    public MultiPictureEntity(LocalMedia localMedia, boolean isAddImage,boolean isNetImage) {
        this.localMedia = localMedia;
        this.isAddImage = isAddImage;
        this.imageUri = getUri();
        this.isNetImage = isNetImage;
    }

    public MultiPictureEntity(boolean isAddImage,boolean isNetImage) {
        this.isAddImage = isAddImage;
        this.isNetImage = isNetImage;
    }

    private String getUri(){
        String uri;
        // 优先使用压缩图片uri
        if (localMedia.isCompressed()){
            uri = localMedia.getCompressPath();
        }else {
            // 其次裁剪的uri
            if (localMedia.isCut()){
                uri = localMedia.getCutPath();
            }else {
                // 最后原图片uri
                uri = localMedia.getPath();
            }
        }
        return  uri;
    }

}
