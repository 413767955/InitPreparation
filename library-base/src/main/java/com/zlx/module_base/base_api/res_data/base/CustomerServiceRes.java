package com.zlx.module_base.base_api.res_data.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Author: lyl
 * Date: 2022/6/25 23:13
 */
@NoArgsConstructor
@Data
public class CustomerServiceRes {

    private String content;//客服地址
    private Integer id;
    private String name;
    private String telegram;//群地址
}
