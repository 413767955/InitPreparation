package com.zlx.module_base.base_api.res_data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: lyl
 * @Date: 2022/6/20 16:26
 */
@NoArgsConstructor
@Data
public class UpdataVersionRes {

    private String force;
    private String version;
    private String upgradeUrl;
    private String upgradeContent;
    private String versionCode;
}
