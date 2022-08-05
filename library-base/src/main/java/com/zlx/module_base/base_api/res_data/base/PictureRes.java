package com.zlx.module_base.base_api.res_data.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Author: lyl
 * Date: 2022/6/23 21:44
 */
@NoArgsConstructor
@Data
public class PictureRes {

    private String hash;
    private Long id;
    private String md5;
    private String url;
}
