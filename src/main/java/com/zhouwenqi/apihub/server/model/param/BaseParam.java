package com.zhouwenqi.apihub.server.model.param;

import com.zhouwenqi.apihub.server.model.ParamType;

import java.io.Serializable;

/**
 * 基本参数
 * Created by zhouwenqi on 2018/10/16.
 */
public class BaseParam implements Serializable {
    // 参数名
    private String key;
    // 参数值
    private String value;
}
